package backend;

import java.util.LinkedList;

import assembly.*;
import assembly.operand.*;
import assembly.inst.*;

public class Regallocator {
  ASMModule module;
  int totalStack, virtualRegBegin;
  PhysicsReg RegT0 = PhysicsReg.regMap.get("t0");
  PhysicsReg RegT1 = PhysicsReg.regMap.get("t1");
  PhysicsReg RegT2 = PhysicsReg.regMap.get("t2");
  PhysicsReg RegSp = PhysicsReg.regMap.get("sp");
  LinkedList<Inst> workList;

  public Regallocator(ASMModule module) {
    this.module = module;
  }

  public void work() {
    for (ASMFunction function : module.functions) {
      totalStack = function.totalStack;
      virtualRegBegin = function.paramUsed + function.allocaUsed;
      for (ASMBlock block : function.blocks)
        visitBlock(block);
    }
  }

  public void visitBlock(ASMBlock block) {
    workList = new LinkedList<Inst>();
    for (Inst inst : block.insts) {
      if (inst.rs1 != null && !(inst.rs1 instanceof PhysicsReg)) {
        allocateSrc(RegT1, inst.rs1);
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
      int offset = ((VirtualReg) src).id != -1? virtualRegBegin + ((VirtualReg) src).id * 4: totalStack + ((VirtualReg) src).param_idx * 4;
      
      if (offset < 1 << 11){
        //System.out.print(totalStack);
        workList.add(new LoadInst(((VirtualReg) src).size, reg, RegSp, new Imm(offset)));
      }
        
      else {
        workList.add(new LiInst(RegT2, new VirtualImm(offset)));
        workList.add(new BinaryInst("add", RegT2, RegT2, RegSp));
        workList.add(new LoadInst(((VirtualReg) src).size, reg, RegT2));
      }
    } else if (src instanceof VirtualImm) {
      workList.add(new LiInst(reg, (VirtualImm) src));
    } else if (src instanceof Global) {
      workList.add(new LuiInst(reg, new Globalop(Globalop.Type.hi, ((Global) src).name)));
      workList.add(new UnaryInst("addi", reg, reg, new Globalop(Globalop.Type.lo, ((Global) src).name)));
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