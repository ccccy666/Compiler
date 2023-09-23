package backend_;

import assembly.*;
import assembly.inst.*;
import assembly.operand.*;

public class StackManager {
  ASMModule module;

  public StackManager(ASMModule module) {
    this.module = module;
  }

  public void work() {
    for (var curFunc : module.functions) {
      int totalStack = curFunc.paramUsed + curFunc.allocaUsed + curFunc.spillUsed;
     
      if (totalStack < 1 << 11)
        curFunc.entryBlock.insts.addFirst(new UnaryInst("addi", PhysicsReg.get("sp"), PhysicsReg.get("sp"),
            new Imm(-totalStack)));
      else {
        curFunc.entryBlock.insts.addFirst(new BinaryInst("add", PhysicsReg.regMap.get("sp"), PhysicsReg.get("sp"),
            PhysicsReg.get("t0")));
        curFunc.entryBlock.insts.addFirst(new LiInst(PhysicsReg.get("t0"), new VirtualImm(-totalStack)));
      }

      if (totalStack < 1 << 11)
        curFunc.exitBlock.insts.add(new UnaryInst("addi", PhysicsReg.get("sp"), PhysicsReg.get("sp"),
            new Imm(totalStack)));
      else {
        curFunc.exitBlock.insts.add(new LiInst(PhysicsReg.get("t0"), new VirtualImm(totalStack)));
        curFunc.exitBlock.insts.add(new BinaryInst("add", PhysicsReg.get("sp"), PhysicsReg.get("sp"),
            PhysicsReg.get("t0")));
      }
      curFunc.exitBlock.insts.add(new RetInst());
    }
  }
}