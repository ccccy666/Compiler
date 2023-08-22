package backend;

import java.util.HashMap;

import IR.*;
import IR.Instructions.*;
import IR.Type.*;
import IR.Value.*;
import assembly.*;
import assembly.operand.*;
import utils.*;
import assembly.inst.*;

public class InstSelector implements IRVisitor, Elements {
  ASMModule module;
  ASMFunction curFunc;
  ASMBlock curBlock;
  int blockCnt = 0;

  HashMap<Basicblock, ASMBlock> blockMap = new HashMap<>();

  static HashMap<Integer, Integer> log2 = new HashMap<>() {
    {
      for (int i = 0; i < 31; ++i) put(1 << i, i);
    }
  };

  public InstSelector(ASMModule module) {
    this.module = module;
  }

  public void visit(Program node) {
    // add global vars
    for (var globalVar : node.globalVarList) {
      globalVar.asmreg = new GlobalValue(globalVar);
      module.globalValues.add((GlobalValue) globalVar.asmreg);
    }
    // add global strings
    for (var str : node.stringConst.values()) {
      str.asmreg = new GlobalString(".str." + String.valueOf(str.id), str.val);
      module.globalStrings.add((GlobalString)str.asmreg);
    }
    for (var func : node.funcList) {
      curFunc = new ASMFunction(func.name);
      module.functions.add(curFunc);
      func.accept(this);
    }
  }

  public void visit(Functionblock node) {
    // add params
    VirtualReg.cnt = 0;
    // find max argument cnt
    int maxArgCnt = 0;
    for (Basicblock blk : node.blocks) {//Blockmap:blk对应asmblock
      blockMap.put(blk, new ASMBlock(".L" + blockCnt++, blk.loopDepth));
      for (Ins inst : blk.insts)
        if (inst instanceof Call)
          maxArgCnt = Math.max(maxArgCnt, ((Call) inst).args.size());
    }
    //最大参数个数计算当前函数参数所需的空间curFunc.paramUsed
    curFunc.paramUsed = (maxArgCnt > 8 ? maxArgCnt - 8 : 0) << 2;
    // set params
    //前8个参数，为每个参数创建一个相应大小的VirtualReg对象
    for (int i = 0; i < node.params.size() && i < 8; ++i)
      node.params.get(i).asmreg = new VirtualReg(node.params.get(i).type.size);
      //第一个基本块，将寄存器ra的值存储到sp内存位置

    for (int i = 0; i < node.blocks.size(); ++i) {
      curBlock = blockMap.get(node.blocks.get(i));
      if (i == 0)
        storeReg(4, PhysicsReg.get("ra"), PhysicsReg.get("sp"), curFunc.paramUsed);
      node.blocks.get(i).accept(this);
      curFunc.addBlock(curBlock);
    }
    

//设置当前函数的入口块和出口块
    curFunc.entryBlock = curFunc.blocks.get(0);
    curFunc.exitBlock = curFunc.blocks.get(curFunc.blocks.size() - 1);
    

// 更新当前函数的虚拟寄存器计数器curFunc.virtualRegCnt。
// 将每个基本块的phi转换指令和跳转/分支指令添加到对应的基本块中
//     函数参数与寄存器相关联，将参数的值从对应的寄存器移到entryBlock中。
    for (int i = 0; i < node.params.size() && i < 8; ++i)
      curFunc.entryBlock.insts.addFirst(new ASMMvInst(node.params.get(i).asmreg, PhysicsReg.get("a" + i)));

    // add callee save
    // 如果函数不是"main"函数，则为被调用者保存的寄存器（callee save）添加相应的指令。
    if (!node.name.equals("main"))
      for (var reg : PhysicsReg.calleeSave) {
        VirtualReg storeReg = new VirtualReg(4);
        curFunc.entryBlock.insts.addFirst(new ASMMvInst(storeReg, reg));
        curFunc.exitBlock.insts.addLast(new ASMMvInst(reg, storeReg));
      }//调用函数会修改当前寄存器，所以在调用前store，调用后变为原来的值
      
    curFunc.virtualRegCnt = VirtualReg.cnt;
    for (var block : curFunc.blocks) {
      block.insts.addAll(block.phiConvert);
      block.insts.addAll(block.jumpOrBr);
    }
  }

  public void visit(Basicblock node) {
    for (var inst : node.insts)
      if (inst != node.insts.getLast())
        inst.accept(this);
    if (node.terminalInst instanceof Br && node.insts.getLast() instanceof Icmp 
        ) {
          Br brInst=(Br)node.terminalInst;
          Icmp cmpInst=(Icmp)node.insts.getLast();
          if(brInst.getUse().contains(cmpInst.cmp)){
            combineCmpAndBranch(cmpInst, brInst);
          }
      
    } else {
      if (!node.insts.isEmpty())
        node.insts.getLast().accept(this);
      node.terminalInst.accept(this);
    }
  }

  

  /*
   * 将带有 param_idx 且 >= 8 的 allocaInst 替换为
   * li reg, ParaImm(param_idx - 8 << 2)
   * add allocaReg, sp, reg
   * 且不占用栈的 alloca 区
   */
  public void visit(Alloca node) {
    if (node.func_para < 8) {
      int offset = curFunc.paramUsed + curFunc.allocaUsed;
      if (offset < 1 << 11)
        curBlock.addInst(new ASMUnaryInst("addi", getReg(node.allocaReg), PhysicsReg.get("sp"), new Imm(offset)));
      else
        curBlock.addInst(new ASMBinaryInst("add", getReg(node.allocaReg), PhysicsReg.get("sp"),
            immToReg(new VirtualImm(offset))));
      curFunc.allocaUsed += 4;
    } else {
      VirtualReg reg = new VirtualReg(4);
      curBlock.addInst(new ASMLiInst(reg, new StackImm(curFunc, node.func_para - 8 << 2)));
      curBlock.addInst(new ASMBinaryInst("add", getReg(node.allocaReg), PhysicsReg.get("sp"), reg));
    }
  }

  public void visit(Br node) {
    curBlock.addInst(new ASMBeqzInst(getReg(node.cond), blockMap.get(node.elseBlock)));
    curBlock.succ.add(blockMap.get(node.elseBlock));
    blockMap.get(node.elseBlock).pred.add(curBlock);
    curBlock.addInst(new ASMJumpInst(blockMap.get(node.thenBlock)));
    curBlock.succ.add(blockMap.get(node.thenBlock));
    blockMap.get(node.thenBlock).pred.add(curBlock);
  }

  public void visit(Binary node) {
    Intconst intConst=new Intconst(0);
    if(node.rhs instanceof Intconst){
          intConst=(Intconst )node.rhs ;

        }
    switch (node.op) {
      case "add":
      case "and":
      case "or":
      case "xor":
        if (node.lhs instanceof Intconst) {
          Valu tmp = node.lhs;
          node.lhs = node.rhs;
          node.rhs = tmp;
        }
      case "shl":
      case "ashr":
        
        if (node.rhs instanceof Intconst  && intConst.val < 1 << 11 && intConst.val >= -(1 << 11)){
          
          
          curBlock.addInst(new ASMUnaryInst(node.op + "i", getReg(node.res), getReg(node.lhs), new Imm(intConst.val)));
        }
          
        else
          curBlock.addInst(new ASMBinaryInst(node.op, getReg(node.res), getReg(node.lhs), getReg(node.rhs)));
        break;
      case "sub":
        if (node.rhs instanceof Intconst  && intConst.val <= 1 << 11 && intConst.val > -(1 << 11))
          curBlock.addInst(new ASMUnaryInst("addi", getReg(node.res), getReg(node.lhs), new Imm(-intConst.val)));
        else
          curBlock.addInst(new ASMBinaryInst(node.op, getReg(node.res), getReg(node.lhs), getReg(node.rhs)));
        break;
      case "mul":
        if (node.lhs instanceof Intconst  && log2.containsKey(intConst.val)) {
          Valu tmp = node.lhs;
          node.lhs = node.rhs;
          node.rhs = tmp;
        }
        if (node.rhs instanceof Intconst  && log2.containsKey(intConst.val))
          curBlock.addInst(new ASMUnaryInst("slli", getReg(node.res), getReg(node.lhs), new Imm(log2.get(intConst.val))));
        else
          curBlock.addInst(new ASMBinaryInst(node.op, getReg(node.res), getReg(node.lhs), getReg(node.rhs)));
        break;
      case "sdiv":
        if (node.rhs instanceof Intconst  && log2.containsKey(intConst.val))
          curBlock.addInst(new ASMUnaryInst("srai", getReg(node.res), getReg(node.lhs), new Imm(log2.get(intConst.val))));
        else
          curBlock.addInst(new ASMBinaryInst(node.op, getReg(node.res), getReg(node.lhs), getReg(node.rhs)));
        break;
      default:
        curBlock.addInst(new ASMBinaryInst(node.op, getReg(node.res), getReg(node.lhs), getReg(node.rhs)));
    }
  }

  public void visit(Call node) {
    ASMCallInst callInst = new ASMCallInst(node.funcName);
    for (int i = 0; i < node.args.size(); ++i) {
      Valu arg = node.args.get(i);
      if (i < 8) {
        curBlock.addInst(new ASMMvInst(PhysicsReg.get("a" + i), getReg(arg)));
        callInst.addUse(PhysicsReg.get("a" + i));
      } else
        storeReg(arg.type.size, getReg(arg), PhysicsReg.get("sp"), i - 8 << 2);
    }
    curBlock.addInst(callInst);
    if (node.call != null)
      curBlock.addInst(new ASMMvInst(getReg(node.call), PhysicsReg.get("a0")));
  }

  // public void visit(IRCastInst node) {
  //   curBlock.addInst(new ASMMvInst(getReg(node.dest), getReg(node.val)));
  // }

  public void visit(Getelementptr node) {
    if (node.to_type == irBoolType) {
      curBlock.addInst(new ASMBinaryInst("add", getReg(node.res), getReg(node.ptr), getReg(node.indexList.get(0))));
    } else {
      Reg idx = node.to_type instanceof Classtype ? getReg(node.indexList.get(1)) : getReg(node.indexList.get(0));
      VirtualReg tmp = new VirtualReg(4);
      if (idx == PhysicsReg.get("zero"))
        curBlock.addInst(new ASMMvInst(getReg(node.res), getReg(node.ptr)));
      else {
        curBlock.addInst(new ASMUnaryInst("slli", tmp, idx, new Imm(2)));
        curBlock.addInst(new ASMBinaryInst("add", getReg(node.res), getReg(node.ptr), tmp));
      }
    }
  }

  public void visit(Icmp node) {
    // LLVM_IR: eq, ne, sgt, sge, slt, sle
    // RISCV32_ASM: seqz, snez, slt
    VirtualReg tmp = new VirtualReg(4);
    switch (node.op) {
      case "eq":
        curBlock.addInst(new ASMBinaryInst("sub", tmp, getReg(node.lhs), getReg(node.rhs)));
        curBlock.addInst(new ASMUnaryInst("seqz", getReg(node.cmp), tmp));
        break;
      case "ne":
        curBlock.addInst(new ASMBinaryInst("sub", tmp, getReg(node.lhs), getReg(node.rhs)));
        curBlock.addInst(new ASMUnaryInst("snez", getReg(node.cmp), tmp));
        break;
      case "sgt":
        curBlock.addInst(new ASMBinaryInst("slt", getReg(node.cmp), getReg(node.rhs), getReg(node.lhs)));
        break;
      case "sge":
        curBlock.addInst(new ASMBinaryInst("slt", tmp, getReg(node.lhs), getReg(node.rhs)));
        curBlock.addInst(new ASMUnaryInst("xori", getReg(node.cmp), tmp, new Imm(1)));
        break;
      case "slt":
        curBlock.addInst(new ASMBinaryInst("slt", getReg(node.cmp), getReg(node.lhs), getReg(node.rhs)));
        break;
      case "sle":
        curBlock.addInst(new ASMBinaryInst("slt", tmp, getReg(node.rhs), getReg(node.lhs)));
        curBlock.addInst(new ASMUnaryInst("xori", getReg(node.cmp), tmp, new Imm(1)));
        break;
    }
  }

  public void visit(Jump node) {
    curBlock.addInst(new ASMJumpInst(blockMap.get(node.toBlock)));
    curBlock.succ.add(blockMap.get(node.toBlock));
    blockMap.get(node.toBlock).pred.add(curBlock);
  }

  public void visit(Load node) {
    if (node.storeptr.asmreg instanceof Global ) {
      Global global=(Global) node.storeptr.asmreg;
      String name = global.name;
      Reg reg = getReg(node.destReg);
      curBlock.addInst(new ASMLuiInst(reg, new RelocationFunc(RelocationFunc.Type.hi, name)));
      curBlock.addInst(new ASMLoadInst(node.type.size, reg, reg, new RelocationFunc(RelocationFunc.Type.lo, name)));
    } else
      loadReg(node.type.size, getReg(node.destReg), getReg(node.storeptr), 0);
  }

  public void visit(Ret node) {
    // ret val -> load val to a0 and return
    if (node.val != irVoidConst)
      curBlock.addInst(new ASMMvInst(PhysicsReg.get("a0"), getReg(node.val)));
    loadReg(4, PhysicsReg.get("ra"), PhysicsReg.get("sp"), curFunc.paramUsed);
    // 寄存器分配完再加 ret
  }

  // 忽略 param_idx 且 >= 8 的 storeInst
  public void visit(Store node) {
    // store : rs2 -> (rs1) address
    if (node.func_para >= 8)
      return;
    if (node.destAddr.asmreg instanceof Global ) {
      Global global=(Global) node.destAddr.asmreg;
      String name = global.name;
      VirtualReg reg = new VirtualReg(4);
      curBlock.addInst(new ASMLuiInst(reg, new RelocationFunc(RelocationFunc.Type.hi, name)));
      curBlock.addInst(new ASMStoreInst(node.val.type.size, reg, getReg(node.val),
          new RelocationFunc(RelocationFunc.Type.lo, name)));
    } else
      storeReg(node.val.type.size, getReg(node.val), getReg(node.destAddr), 0);
  }
  Reg immToReg(VirtualImm imm) {
    VirtualReg reg = new VirtualReg(4);
    curBlock.addInst(new ASMLiInst(reg, imm));
    return reg;
  }

  Reg getReg(Valu entity) {
    if (entity.asmreg == null) {
      if (entity instanceof Register)
        entity.asmreg = new VirtualReg(entity.type.size);
      else if (entity instanceof Const)
        return ((Const) entity).isZero() ? PhysicsReg.get("zero") : immToReg(new VirtualImm((Const) entity));
    } else if (entity.asmreg instanceof Global) {
      VirtualReg reg = new VirtualReg(4);
      String name = ((Global) entity.asmreg).name;
      curBlock.addInst(new ASMLuiInst(reg, new RelocationFunc(RelocationFunc.Type.hi, name)));
      curBlock.addInst(new ASMUnaryInst("addi", reg, reg, new RelocationFunc(RelocationFunc.Type.lo, name)));
      return reg;
    }
    return entity.asmreg;
  }

  void storeReg(int size, Reg value, Reg dest, int offset) {
    if (offset < 1 << 11)
      curBlock.addInst(new ASMStoreInst(size, dest, value, new Imm(offset)));
    else {
      VirtualReg tmp = new VirtualReg(4);
      curBlock.addInst(new ASMBinaryInst("add", tmp, dest, immToReg(new VirtualImm(offset))));
      curBlock.addInst(new ASMStoreInst(size, tmp, value));
    }
  }

  void loadReg(int size, Reg dest, Reg src, int offset) {
    if (offset < 1 << 11)
      curBlock.addInst(new ASMLoadInst(size, dest, src, new Imm(offset)));
    else {
      VirtualReg tmp = new VirtualReg(4);
      curBlock.addInst(new ASMBinaryInst("add", tmp, src, immToReg(new VirtualImm(offset))));
      curBlock.addInst(new ASMLoadInst(size, dest, tmp));
    }
  }
  void combineCmpAndBranch(Icmp cmpInst, Br brInst) {
    String op = "";
    switch (cmpInst.op) {
      case "eq": op = "bne"; break;
      case "ne": op = "beq"; break;
      case "sgt": op = "ble"; break;
      case "sge": op = "blt"; break;
      case "slt": op = "bge"; break;
      case "sle": op = "bgt"; break;
    }
    curBlock.addInst(new ASMBrCmpInst(op, getReg(cmpInst.lhs), getReg(cmpInst.rhs), blockMap.get(brInst.elseBlock)));
    curBlock.succ.add(blockMap.get(brInst.elseBlock));
    blockMap.get(brInst.elseBlock).pred.add(curBlock);
    curBlock.addInst(new ASMJumpInst(blockMap.get(brInst.thenBlock)));
    curBlock.succ.add(blockMap.get(brInst.thenBlock));
    blockMap.get(brInst.thenBlock).pred.add(curBlock);
  }
  // public void visit(IRPhiInst node) {
  //   VirtualReg tmp = new VirtualReg(node.dest.type.size);
  //   curBlock.addInst(new ASMMvInst(getReg(node.dest), tmp));
  //   for (int i = 0; i < node.values.size(); ++i) {
  //     Valu val = node.values.get(i);
  //     if (val instanceof IRConst constVal)
  //       blockMap.get(node.blocks.get(i)).phiConvert.add(new ASMLiInst(tmp, new VirtualImm(constVal)));
  //     else
  //       blockMap.get(node.blocks.get(i)).phiConvert.add(new ASMMvInst(tmp, getReg(node.values.get(i))));
  //   }
  // }
}