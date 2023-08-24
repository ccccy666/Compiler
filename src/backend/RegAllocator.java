package backend;

import java.util.LinkedList;

import assembly.*;
import assembly.operand.*;
import assembly.inst.*;

public class RegAllocator {
  ASMModule module;
  int totalStack, virtualRegBegin;
  PhysicsReg RegT0 = PhysicsReg.regMap.get("t0");
  PhysicsReg RegT1 = PhysicsReg.regMap.get("t1");
  PhysicsReg RegT2 = PhysicsReg.regMap.get("t2");
  PhysicsReg RegSp = PhysicsReg.regMap.get("sp");
  LinkedList<Inst> workList;

  public RegAllocator(ASMModule module) {
    this.module = module;
  }

  public void work() {
    for (ASMFunction function : module.functions) {
      function.spillUsed = function.virtualRegCnt << 2;//将 function.virtualRegCnt 左移两位（相当于乘以4），得到需要用于存储临时寄存器（Spill Register）的空间大小。
      virtualRegBegin = function.paramUsed + function.allocaUsed;//irtualRegBegin 变量的值等于函数的参数使用量 (function.paramUsed) 加上分配使用量 (function.allocaUsed)。该值表示虚拟寄存器（Virtual Register）在堆栈中的起始位置
      totalStack = virtualRegBegin + function.spillUsed;//表示整个堆栈所需的总空间大小。
      for (ASMBlock block : function.blocks)
        visitBlock(block);
    }
  }

  public void visitBlock(ASMBlock block) {
    workList = new LinkedList<Inst>();
    for (Inst inst : block.insts) {
      if (inst instanceof LiInst && ((LiInst) inst).pseudoImm instanceof StackImm)//inst 是否是 ASMLiInst 类型，并且该指令的 pseudoImm 属性是否是 StackImm 类型。
        ((StackImm) ((LiInst) inst).pseudoImm).calc();
      if (inst.rs1 != null && !(inst.rs1 instanceof PhysicsReg)) {//当前指令 inst 的第一个源寄存器 (rs1) 是否为空且不是 PhysicsReg 类型
        allocateSrc(RegT1, inst.rs1);//allocateSrc 方法将源寄存器分配给 RegT1
        inst.rs1 = RegT1;
      }
      if (inst.rs2 != null && !(inst.rs2 instanceof PhysicsReg)) {
        allocateSrc(RegT0, inst.rs2);
        inst.rs2 = RegT0;
      }
      workList.add(inst);
      if (inst.rd != null && !(inst.rd instanceof PhysicsReg)) {
        allocaDest(RegT0, inst.rd);
        inst.rd = RegT0;
      }
    }
    block.insts = workList;
  }

  void allocateSrc(PhysicsReg reg, Reg src) {
    if (src instanceof VirtualReg) {
      int offset = ((VirtualReg) src).id != -1? virtualRegBegin + ((VirtualReg) src).id * 4 : totalStack + ((VirtualReg) src).param_idx * 4;
      //如果虚拟寄存器具有有效的 id，则使用 virtualRegBegin 加上 id 乘以 4；否则，使用 totalStack 加上 param_idx 乘以 4。这些乘法和加法操作用于计算虚拟寄存器在堆栈中的地址偏移量。
      if (offset < 1 << 11)
        workList.add(new LoadInst(((VirtualReg) src).size, reg, RegSp, new Imm(offset)));
      else {
        workList.add(new LiInst(RegT2, new VirtualImm(offset)));// 添加一个 ASMLiInst 指令，该指令用于将偏移量加载到寄存器 RegT2 中
        workList.add(new BinaryInst("add", RegT2, RegT2, RegSp));//加一个 ASMBinaryInst 指令，该指令将 RegT2 和 RegSp 相加，并将结果存储回 RegT2
        workList.add(new LoadInst(((VirtualReg) src).size, reg, RegT2));//添加一个 ASMLoadInst 指令，该指令从内存中加载值，并将其存储到物理寄存器 reg 中
      }
    } else if (src instanceof VirtualImm) {
      //workList.add(new ASMLiInst(reg, (VirtualImm) src));
    } else if (src instanceof Global) {
      // CAUTION: include instrution selection
      // workList.add(new ASMLuiInst(reg, new RelocationFunc(RelocationFunc.Type.hi, ((Global) src).name)));
      // workList.add(new ASMUnaryInst("addi", reg, reg, new RelocationFunc(RelocationFunc.Type.lo, ((Global) src).name)));
    }
  }

  void allocaDest(PhysicsReg reg, Reg dest) {
    if (dest instanceof VirtualReg) {
      int offset = ((VirtualReg) dest).id != -1? virtualRegBegin + ((VirtualReg) dest).id * 4: totalStack + ((VirtualReg) dest).param_idx * 4;
      if (offset < 1 << 11)
        workList.add(new StoreInst(((VirtualReg) dest).size, RegSp, reg, new Imm(offset)));
      else {
        workList.add(new LiInst(RegT2, new VirtualImm(offset)));
        workList.add(new BinaryInst("add", RegT2, RegT2, RegSp));
        workList.add(new StoreInst(((VirtualReg) dest).size, RegT2, reg));
      }
    }
  }
}