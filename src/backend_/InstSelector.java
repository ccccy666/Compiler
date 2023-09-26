package backend_;

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

  public InstSelector(ASMModule module) {
    this.module = module;
  }

  public void visit(Program node) {
    for (var globalVar : node.globalVarList) {
      globalVar.asmreg = new GlobalValue(globalVar);
      module.globalValues.add((GlobalValue) globalVar.asmreg);
    }
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
    VirtualReg.cnt = 0;
    int maxArgCnt = 0;
    for (Basicblock blk : node.blocks) {//Blockmap:blk对应asmblock
      blockMap.put(blk, new ASMBlock(".L" + blockCnt++, blk.loopDepth));
      for (Ins inst : blk.insts)
        if (inst instanceof Call)
          maxArgCnt = Math.max(maxArgCnt, ((Call) inst).args.size());
    }
    //最大参数个数计算当前函数参数所需的空间curFunc.paramUsed看是否需要在栈上分配空间 以及 分配多少空间
    curFunc.paramUsed = (maxArgCnt > 8 ? maxArgCnt - 8 : 0) << 2;
    
    //前8个参数，为每个参数创建一个相应大小的VirtualReg对象
    for (int i = 0; i < node.params.size() && i < 8; ++i)
        node.params.get(i).asmreg = new VirtualReg(node.params.get(i).type.size);
      //第一个基本块，将寄存器ra的值存储到sp内存位置在函数开始时（第0个基本块），将返回地址寄存器"ra"的内容保存到栈中的某个位置。
//在函数调用其他函数之前，需要保存返回地址以确保在新函数返回后能够正确地返回到调用它的函数中。
        //具体位置是由栈指针"sp"和curFunc.paramUsed决定的。
        //用物理寄存器是因为这里可以得知明确的寄存器分配
    for (int i = 0; i < node.blocks.size(); ++i) {
      curBlock = blockMap.get(node.blocks.get(i));
      if (i == 0){
        storeReg(4, PhysicsReg.get("ra"), PhysicsReg.get("sp"), curFunc.paramUsed);
      }//store ra 到 sp偏移offset
        
      node.blocks.get(i).accept(this);
      curFunc.addBlock(curBlock);
    }
    curFunc.entryBlock = curFunc.blocks.get(0);
    curFunc.exitBlock = curFunc.blocks.get(curFunc.blocks.size() - 1);
    
    for (int i = 0; i < node.params.size() && i < 8; ++i)
      curFunc.entryBlock.insts.addFirst(new MvInst(node.params.get(i).asmreg, PhysicsReg.get("a" + i)));
//将参数的值从物理寄存器"a0"到"a7"中移动到虚拟寄存器中。
//函数开始执行时，前8个参数已经在这些寄存器中了
    // add callee save
    // 如果函数不是"main"函数，则为被调用者保存的寄存器（callee save）添加相应的指令。

    if (!node.name.equals("main")){
      //System.out.print("9999999999999");
      for (var reg : PhysicsReg.callee) {
        VirtualReg storeReg = new VirtualReg(4);
        curFunc.entryBlock.insts.addFirst(new MvInst(storeReg, reg));
        curFunc.exitBlock.insts.addLast(new MvInst(reg, storeReg));
      }//调用函数会修改当前寄存器，所以在调用前store，调用后变为原来的值
    }
    curFunc.virtualRegCnt = VirtualReg.cnt;
    for (var block : curFunc.blocks) {
      block.insts.addAll(block.phis);
      block.insts.addAll(block.jumpOrBr);
    }
    //curFunc.exitBlock.insts.add(new RetInst());
  }
  public void visit(Basicblock node) {
    for (var inst : node.insts){
      if (inst != node.insts.getLast())
        inst.accept(this);
    }
    boolean flag=true;
    if (node.terminalInst instanceof Br && node.insts.getLast() instanceof Icmp ) {//Br在terminalinst而不是node.insts里
          Br brInst=(Br)node.terminalInst;
          Icmp cmpInst=(Icmp)node.insts.getLast();
          if(brInst.getUse().contains(cmpInst.cmp)){
            flag=false;
            combineCmpAndBranch(cmpInst, brInst);
          }
    } 
    if(flag){
      if (!node.insts.isEmpty())
        node.insts.getLast().accept(this);
      node.terminalInst.accept(this);
    }
  }
  public void visit(Alloca node) {
    if (node.func_para < 8) {
      //计算一个偏移量，通过将当前函数的参数使用量(curFunc.paramUsed)和分配使用量(curFunc.allocaUsed)相加得到
      int offset = curFunc.paramUsed + curFunc.allocaUsed;
//       偏移量在某个限制范围内（小于1 << 11，即小于2048），则添加一个"addi"指令，用于将栈指针(sp)与偏移量相加，并将结果存储在node.allocaReg中。
// 如果偏移量超过了限制，添加一个"add"指令，使用物理寄存器和虚拟立即数(immToReg(new VirtualImm(offset)))执行加法操作，并将结果存储在node.allocaReg中
      if (offset < 1 << 11){//2048
      //sp寄存器加上offset偏移量 表示为当前函数开的栈空间
        //sp在多次alloca的时候是不会变的，这只是每次都将sp+offset的值存到allocaReg中
        curBlock.addInst(new UnaryInst("addi", getReg(node.allocaReg), PhysicsReg.get("sp"), new Imm(offset)));
    }
      else{
        curBlock.addInst(new BinaryInst("add", getReg(node.allocaReg), PhysicsReg.get("sp"),immToReg(new VirtualImm(offset))));
      }
        
      curFunc.allocaUsed += 4;//curFunc.allocaUsed值增加4，表示此次Alloca操作使用了4个字节。
    } else {
      VirtualReg reg = new VirtualReg(4);
      curBlock.addInst(new LiInst(reg, new StackImm(curFunc, (node.func_para - 8 )<< 2)));
      curBlock.addInst(new BinaryInst("add", getReg(node.allocaReg), PhysicsReg.get("sp"), reg));
    }
  }

  public void visit(Br node) {//加前驱后继
    curBlock.addInst(new BeqzInst(getReg(node.cond), blockMap.get(node.elseBlock)));
    curBlock.succ.add(blockMap.get(node.elseBlock));
    blockMap.get(node.elseBlock).pred.add(curBlock);
    curBlock.addInst(new JumpInst(blockMap.get(node.thenBlock)));
    curBlock.succ.add(blockMap.get(node.thenBlock));
    blockMap.get(node.thenBlock).pred.add(curBlock);
  }
  public void visit(Binary node) {
      curBlock.addInst(new BinaryInst(node.op, getReg(node.res), getReg(node.lhs), getReg(node.rhs)));
  }
//函数传参实际上是通过物理寄存器来传递。这里调用函数了，只需要把这些寄存器准备好，再加一个call指令，到被调用的函数内部，
//再把寄存器一个个取出来，就相当于传参了
//对于多余的参数，SP相当于一个“中继器”，它能告诉被调用的函数，这些多余参数的起始内存地址在哪
  public void visit(Call node) {
    CallInst callInst = new CallInst(node.funcName);
    for (int i = 0; i < node.args.size(); ++i) {
      Valu arg = node.args.get(i);
      if (i < 8) {
        curBlock.addInst(new MvInst(PhysicsReg.get("a" + i), getReg(arg)));
        callInst.addUse(PhysicsReg.get("a" + i));//添加使用的寄存器
      } else
        storeReg(arg.type.size, getReg(arg), PhysicsReg.get("sp"), i - 8 << 2);//store进内存
    }
    curBlock.addInst(callInst);
    if (node.call != null)
      curBlock.addInst(new MvInst(getReg(node.call), PhysicsReg.get("a0")));
  }//将返回值从物理寄存器"a0"中移动到虚拟寄存器中

  public void visit(Getelementptr node) {//address
    
        //irBoolType是i1 
    if (node.to_type == irBoolType || node.to_type == irCharType) {
      curBlock.addInst(new BinaryInst("add", getReg(node.res), getReg(node.ptr), getReg(node.indexList.get(0))));
    } else {
      //如果是类类型，那么第一个肯定是0，要解引用，就取第二个索引，否则取第一个索引
      Reg idx = node.to_type instanceof Classtype ? getReg(node.indexList.get(1)) : getReg(node.indexList.get(0));
      VirtualReg tmp = new VirtualReg(4);
      if (idx == PhysicsReg.get("zero"))
        curBlock.addInst(new MvInst(getReg(node.res), getReg(node.ptr)));//如果索引是零，不需要任何偏移，只需要复制基指针到结果寄存器。
      else {
        curBlock.addInst(new UnaryInst("slli", tmp, idx, new Imm(2)));
        curBlock.addInst(new BinaryInst("add", getReg(node.res), getReg(node.ptr), tmp));
      }//一个地址单元是4字节
        //然后将得到的偏移与基指针相加，得到最终的地址
    }
  }

  public void visit(Icmp node) {
    VirtualReg tmp = new VirtualReg(4);
    switch (node.op) {
      case "eq":
        curBlock.addInst(new BinaryInst("sub", tmp, getReg(node.lhs), getReg(node.rhs)));
        curBlock.addInst(new UnaryInst("seqz", getReg(node.cmp), tmp));
        break;
      case "ne":
        curBlock.addInst(new BinaryInst("sub", tmp, getReg(node.lhs), getReg(node.rhs)));
        curBlock.addInst(new UnaryInst("snez", getReg(node.cmp), tmp));
        break;
      case "sgt":
        curBlock.addInst(new BinaryInst("slt", getReg(node.cmp), getReg(node.rhs), getReg(node.lhs)));
        break;
      case "sge":
        curBlock.addInst(new BinaryInst("slt", tmp, getReg(node.lhs), getReg(node.rhs)));
        curBlock.addInst(new UnaryInst("xori", getReg(node.cmp), tmp, new Imm(1)));
        break;
      case "slt":
        curBlock.addInst(new BinaryInst("slt", getReg(node.cmp), getReg(node.lhs), getReg(node.rhs)));
        break;
      case "sle":
        curBlock.addInst(new BinaryInst("slt", tmp, getReg(node.rhs), getReg(node.lhs)));
        curBlock.addInst(new UnaryInst("xori", getReg(node.cmp), tmp, new Imm(1)));
        break;
    }
  }

  public void visit(Jump node) {
    curBlock.addInst(new JumpInst(blockMap.get(node.toBlock)));
    curBlock.succ.add(blockMap.get(node.toBlock));
    blockMap.get(node.toBlock).pred.add(curBlock);
  }

  public void visit(Load node) {
      loadReg(node.type.size, getReg(node.destReg), getReg(node.storeptr), 0);
  }

  public void visit(Ret node) {
    if (node.val != irVoidConst)
      curBlock.addInst(new MvInst(PhysicsReg.get("a0"), getReg(node.val)));
    loadReg(4, PhysicsReg.get("ra"), PhysicsReg.get("sp"), curFunc.paramUsed);
    //sp寄存器加上curFunc.paramUsed的值，存到ra中
        
  }

  public void visit(Store node) {
    if (node.func_para >= 8)//目的就是把参数从物理寄存器转移到栈上，而idx>=8的参数已经在栈上了
      return;
    
    storeReg(node.val.type.size, getReg(node.val), getReg(node.destAddr), 0);
  }
  public void visit(Phi node) {
    VirtualReg tmp = new VirtualReg(node.dest.type.size);
    curBlock.addInst(new MvInst(getReg(node.dest), tmp));
    ASMBlock tem=curBlock;
    for (int i = 0; i < node.values.size(); ++i) {
      curBlock=blockMap.get(node.blocks.get(i));
      Valu val = node.values.get(i);
      if (val instanceof Const &&!(val instanceof Stringconst)){
        Const constVal=(Const) val;
        blockMap.get(node.blocks.get(i)).phis.add(new LiInst(tmp, new VirtualImm(constVal)));
      }else
        blockMap.get(node.blocks.get(i)).phis.add(new MvInst(tmp, getReg(node.values.get(i))));
    }
    curBlock=tem;
  }

  Reg immToReg(VirtualImm imm) {
    VirtualReg reg = new VirtualReg(4);
    curBlock.addInst(new LiInst(reg, imm));
    return reg;
  }

  Reg getReg(Valu entity) {
    if (entity.asmreg == null) {//检查给定的 entity 是否已经有一个关联的汇编寄存器 (entity.asmReg)
      if (entity instanceof Register)
        entity.asmreg = new VirtualReg(entity.type.size);//如果这个 entity 是一个 中间表示的寄存器，它会为其创建一个新的虚拟汇编寄存器 (VirtualReg)
      else if (entity instanceof Const)
        return ((Const) entity).isZero() ? PhysicsReg.get("zero") : immToReg(new VirtualImm((Const) entity));
        //如果这个 entity 是一个 IRConst（中间表示的常量），它会检查这个常量是否为零。如果是，返回特定的物理寄存器 (PhysicsReg) 代表 "zero"；否则，它会转换这个常量到一个汇编寄存器。
    } else if (entity.asmreg instanceof Global) {//创建一个新的 4-byte 虚拟寄存器 (VirtualReg)。
      VirtualReg reg = new VirtualReg(4);
      String name = ((Global) entity.asmreg).name;
      curBlock.addInst(new LuiInst(reg, new Globalop(Globalop.Type.hi, name)));
      curBlock.addInst(new UnaryInst("addi", reg, reg, new Globalop(Globalop.Type.lo, name)));
      return reg;
    }
    return entity.asmreg;
  }

  void storeReg(int size, Reg value, Reg dest, int offset) {
    
    //地址是由dest寄存器指向的基地址加上offset偏移得到的。
    //load只能load -2^11到2^11 的数，否则要开一个寄存器存立即数
    if (offset < 1 << 11)
      curBlock.addInst(new StoreInst(size, dest, value, new Imm(offset)));
      //store value to -> dest偏移offset
    else {
      VirtualReg tmp = new VirtualReg(4);
      curBlock.addInst(new BinaryInst("add", tmp, dest, immToReg(new VirtualImm(offset))));
      curBlock.addInst(new StoreInst(size, tmp, value));
    }
  }

  void loadReg(int size, Reg dest, Reg src, int offset) {
    if (offset < 1 << 11)
      curBlock.addInst(new LoadInst(size, dest, src, new Imm(offset)));
    else {
      VirtualReg tmp = new VirtualReg(4);
      curBlock.addInst(new BinaryInst("add", tmp, src, immToReg(new VirtualImm(offset))));
      curBlock.addInst(new LoadInst(size, dest, tmp));
    }
  }
  void combineCmpAndBranch(Icmp cmpInst, Br brInst) {

    String op = "";
    switch (cmpInst.op) {
      case "eq": 
        op = "bne"; 
        break;
      case "ne": 
        op = "beq"; 
        break;
      case "sgt": 
        op = "ble"; 
        break;
      case "sge": 
        op = "blt"; 
        break;
      case "slt": 
        op = "bge"; 
        break;
      case "sle": 
        op = "bgt"; 
        break;
    }
    curBlock.addInst(new BrCmpInst(op, getReg(cmpInst.lhs), getReg(cmpInst.rhs), blockMap.get(brInst.elseBlock)));
    curBlock.succ.add(blockMap.get(brInst.elseBlock));
    blockMap.get(brInst.elseBlock).pred.add(curBlock);
    curBlock.addInst(new JumpInst(blockMap.get(brInst.thenBlock)));
    curBlock.succ.add(blockMap.get(brInst.thenBlock));
    blockMap.get(brInst.thenBlock).pred.add(curBlock);
  }
  
}