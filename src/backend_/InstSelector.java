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

  static HashMap<Integer, Integer> log2 = new HashMap<>() {
    {
      for (int i = 0; i < 31; ++i) {
        put(1 << i, i);
      }
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
    //最大参数个数计算当前函数参数所需的空间curFunc.paramUsed看是否需要在栈上分配空间 以及 分配多少空间
    curFunc.paramUsed = (maxArgCnt > 8 ? maxArgCnt - 8 : 0) << 2;
    // set params
    //前8个参数，为每个参数创建一个相应大小的VirtualReg对象
    for (int i = 0; i < node.params.size() && i < 8; ++i)
      node.params.get(i).asmreg = new VirtualReg(node.params.get(i).type.size);


      //第一个基本块，将寄存器ra的值存储到sp内存位置在函数开始时（第0个基本块），将返回地址寄存器"ra"的内容保存到栈中的某个位置。
//在函数调用其他函数之前，通常需要保存返回地址以确保在新函数返回后能够正确地返回到调用它的函数中。
        //这里的具体位置是由栈指针"sp"和curFunc.paramUsed决定的。
        //这里用物理寄存器是因为这里可以得知明确的寄存器分配，而无需虚拟寄存器来糊弄
    for (int i = 0; i < node.blocks.size(); ++i) {
      curBlock = blockMap.get(node.blocks.get(i));
      if (i == 0){
        storeReg(4, PhysicsReg.get("ra"), PhysicsReg.get("sp"), curFunc.paramUsed);
      }//store ra 到 sp偏移offset
        
      node.blocks.get(i).accept(this);
      curFunc.addBlock(curBlock);
    }
    

//设置当前函数的入口块和出口块
    curFunc.entryBlock = curFunc.blocks.get(0);
    curFunc.exitBlock = curFunc.blocks.get(curFunc.blocks.size() - 1);
    


//     函数参数与寄存器相关联，将参数的值从对应的寄存器移到entryBlock中。
    for (int i = 0; i < node.params.size() && i < 8; ++i)
      curFunc.entryBlock.insts.addFirst(new MvInst(node.params.get(i).asmreg, PhysicsReg.get("a" + i)));
//将参数的值从物理寄存器"a0"到"a7"中移动到虚拟寄存器中。
//当你的函数开始执行时，它的前8个参数已经在这些寄存器中了
    // add callee save
    // 如果函数不是"main"函数，则为被调用者保存的寄存器（callee save）添加相应的指令。
    if (!node.name.equals("main"))
      for (var reg : PhysicsReg.calleeSave) {
        VirtualReg storeReg = new VirtualReg(4);
        curFunc.entryBlock.insts.addFirst(new MvInst(storeReg, reg));
        curFunc.exitBlock.insts.addLast(new MvInst(reg, storeReg));
      }//调用函数会修改当前寄存器，所以在调用前store，调用后变为原来的值

      // 更新当前函数的虚拟寄存器计数器curFunc.virtualRegCnt。
// 将每个基本块的跳转/分支指令添加到对应的基本块中
    curFunc.virtualRegCnt = VirtualReg.cnt;
    for (var block : curFunc.blocks) {
      block.insts.addAll(block.phiConvert);
      block.insts.addAll(block.jumpOrBr);
    }
  }

  public void visit(Basicblock node) {

    for (var inst : node.insts){
      if (inst != node.insts.getLast())
        inst.accept(this);
    }
    // 如果Basicblock对象的终止指令是Br类型，且指令列表的最后一个指令是Icmp类型
    // 将终止指令强制转换为Br类型的变量brInst。
    // 将指令列表的最后一个指令强制转换为Icmp类型的变量cmpInst。
    // 如果终止指令的use包含cmpInst.cmp，则调用combineCmpAndBranch(cmpInst, brInst)方法来合并比较指令和分支指令
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
//       如果指令列表不为空，将调用最后一个指令的accept方法进行访问。
// 最后，将调用终止指令的accept方法进行访问
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
      //计算一个偏移量，通过将当前函数的参数使用量(curFunc.paramUsed)和分配使用量(curFunc.allocaUsed)相加得到
      int offset = curFunc.paramUsed + curFunc.allocaUsed;
//       偏移量在某个限制范围内（小于1 << 11，即小于2048），则添加一个"addi"指令，用于将栈指针(sp)与偏移量相加，并将结果存储在node.allocaReg中。
// 如果偏移量超过了限制，添加一个"add"指令，使用物理寄存器和虚拟立即数(immToReg(new VirtualImm(offset)))执行加法操作，并将结果存储在node.allocaReg中
      if (offset < 1 << 11){//2048
      //sp寄存器加上offset偏移量 表示为当前函数开的栈空间
        //实际上sp在多次alloca的时候是不会变的，这只是每次都将sp+offset的值存到allocaReg中
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

  public void visit(Br node) {
//     添加了一个条件分支指令(ASMBeqzInst)到当前块(curBlock)。
// 分支条件是通过获取node.cond的寄存器值进行判断。
// 如果条件为零（等于0），则控制流程跳转到node.elseBlock所代表的块
    curBlock.addInst(new BeqzInst(getReg(node.cond), blockMap.get(node.elseBlock)));
//     node.elseBlock所代表的块添加到当前块的后继块列表中。
// 这表示如果条件分支的条件为真（非零），则控制流程会跳过该行代码，并执行紧接着下一行代码
    curBlock.succ.add(blockMap.get(node.elseBlock));
    blockMap.get(node.elseBlock).pred.add(curBlock);
//     添加了一个无条件跳转指令(ASMJumpInst)到当前块(curBlock)。
// 跳转目标是node.thenBlock所代表的块
    curBlock.addInst(new JumpInst(blockMap.get(node.thenBlock)));

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
        if (node.lhs instanceof Intconst) {//如果左边是intConst就放到右边
          Valu tmp = node.lhs;
          node.lhs = node.rhs;
          node.rhs = tmp;
        }
      case "shl":
      case "ashr":
        
        if (node.rhs instanceof Intconst  && intConst.val < 1 << 11 && intConst.val >= -(1 << 11)){
          
          
          curBlock.addInst(new UnaryInst(node.op + "i", getReg(node.res), getReg(node.lhs), new Imm(intConst.val)));
        }//立即数
          
        else
          curBlock.addInst(new BinaryInst(node.op, getReg(node.res), getReg(node.lhs), getReg(node.rhs)));
        break;
      case "sub":
        if (node.rhs instanceof Intconst  && intConst.val <= 1 << 11 && intConst.val > -(1 << 11))
          curBlock.addInst(new UnaryInst("addi", getReg(node.res), getReg(node.lhs), new Imm(-intConst.val)));
        else
          curBlock.addInst(new BinaryInst(node.op, getReg(node.res), getReg(node.lhs), getReg(node.rhs)));
        break;
      case "mul":
        if (node.lhs instanceof Intconst  && log2.containsKey(intConst.val)) {//如果左边是intConst就放到右边
          Valu tmp = node.lhs;
          node.lhs = node.rhs;
          node.rhs = tmp;
        }
        if (node.rhs instanceof Intconst  && log2.containsKey(intConst.val))
          curBlock.addInst(new UnaryInst("slli", getReg(node.res), getReg(node.lhs), new Imm(log2.get(intConst.val))));
        else
          curBlock.addInst(new BinaryInst(node.op, getReg(node.res), getReg(node.lhs), getReg(node.rhs)));
        break;
      case "sdiv":
        if (node.rhs instanceof Intconst  && log2.containsKey(intConst.val))
          curBlock.addInst(new UnaryInst("srai", getReg(node.res), getReg(node.lhs), new Imm(log2.get(intConst.val))));
        else
          curBlock.addInst(new BinaryInst(node.op, getReg(node.res), getReg(node.lhs), getReg(node.rhs)));
        break;
      default:
        curBlock.addInst(new BinaryInst(node.op, getReg(node.res), getReg(node.lhs), getReg(node.rhs)));
    }
  }
//函数传参实际上是通过物理寄存器来传递。这里调用函数了，只需要把这些寄存器准备好，再加一个call指令，到被调用的函数内部，
//再把寄存器一个个取出来，就相当于传参了
//对于多余的参数，我们有SP，这个也相当于一个“中继器”，它能告诉被调用的函数，这些多余参数的起始内存地址在哪
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

  // public void visit(IRCastInst node) {
  //   curBlock.addInst(new ASMMvInst(getReg(node.dest), getReg(node.val)));
  // }

  public void visit(Getelementptr node) {//calc address
    
    //如果指向的是bool类型
        //irBoolType是i1 所以这里会遗漏irCharType的情况
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
      }//在这里，我们首先将索引乘4，因为一个地址单元是4字节
        //然后我们将得到的偏移与基指针相加，以得到最终的地址。
    }
  }

  public void visit(Icmp node) {
    // LLVM_IR: eq, ne, sgt, sge, slt, sle
    // RISCV32_ASM: seqz, snez, slt
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
//全局变量的地址固定
//main:
//lui     a0, %hi(globalVar) # 加载全局变量地址的高20位到a0寄存器
//lw      a0, %lo(globalVar)(a0) # 加载地址的低12位，并从该地址处加载globalVar的值到a0寄存器
//ret
  public void visit(Load node) {
    if (node.storeptr.asmreg instanceof Global ) {
      Global global=(Global) node.storeptr.asmreg;
      String name = global.name;
      Reg reg = getReg(node.destReg);
      curBlock.addInst(new LuiInst(reg, new RelocationFunc(RelocationFunc.Type.hi, name)));
      ////把全局变量地址的高20位放到reg中
      curBlock.addInst(new LoadInst(node.type.size, reg, reg, new RelocationFunc(RelocationFunc.Type.lo, name)));
      //把全局变量地址的低12位放到reg中
    } else
      loadReg(node.type.size, getReg(node.destReg), getReg(node.storeptr), 0);
  }

  public void visit(Ret node) {
    // ret val -> load val to a0 and return
    if (node.val != irVoidConst)
      curBlock.addInst(new MvInst(PhysicsReg.get("a0"), getReg(node.val)));
    loadReg(4, PhysicsReg.get("ra"), PhysicsReg.get("sp"), curFunc.paramUsed);
    //sp寄存器加上curFunc.paramUsed的值，存到ra中
    //注意：ra寄存器原先不知道是什么，是通过sp寄存器加上curFunc.paramUsed的值算出来存在里面的
    // 寄存器分配完再加 ret
    
  }

  // 忽略 param_idx 且 >= 8 的 storeInst
  public void visit(Store node) {
    // store : rs2 -> (rs1) address
    if (node.func_para >= 8)//目的就是把参数从物理寄存器转移到栈上，而idx>=8的参数已经在栈上了
      return;
    if (node.destAddr.asmreg instanceof Global ) {
      Global global=(Global) node.destAddr.asmreg;
      String name = global.name;
      VirtualReg reg = new VirtualReg(4);
      curBlock.addInst(new LuiInst(reg, new RelocationFunc(RelocationFunc.Type.hi, name)));//
      curBlock.addInst(new StoreInst(node.val.type.size, reg, getReg(node.val),new RelocationFunc(RelocationFunc.Type.lo, name)));//高低位地址都存到reg中
    } else
      storeReg(node.val.type.size, getReg(node.val), getReg(node.destAddr), 0);
  }
  public void visit(Phi node) {
    VirtualReg tmp = new VirtualReg(node.dest.type.size);
    curBlock.addInst(new MvInst(getReg(node.dest), tmp));
    for (int i = 0; i < node.values.size(); ++i) {
      Valu val = node.values.get(i);
      if (val instanceof Const ){
        Const constVal=(Const) val;
        blockMap.get(node.blocks.get(i)).phiConvert.add(new LiInst(tmp, new VirtualImm(constVal)));
      }
        
      else
        blockMap.get(node.blocks.get(i)).phiConvert.add(new MvInst(tmp, getReg(node.values.get(i))));
    }
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
      curBlock.addInst(new LuiInst(reg, new RelocationFunc(RelocationFunc.Type.hi, name)));
      curBlock.addInst(new UnaryInst("addi", reg, reg, new RelocationFunc(RelocationFunc.Type.lo, name)));
      return reg;
    }
    return entity.asmreg;
  }

  void storeReg(int size, Reg value, Reg dest, int offset) {
    //作用是将value寄存器的内容存储到一个地址中。
    //这个地址是由dest寄存器指向的基地址加上offset偏移得到的。
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