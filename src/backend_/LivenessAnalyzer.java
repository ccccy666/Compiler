package backend_;

import assembly.*;
import assembly.inst.*;
import assembly.operand.*;
import java.util.HashSet;
import java.util.LinkedList;

public class LivenessAnalyzer {
  ASMFunction func;

  LinkedList<ASMBlock> workList = new LinkedList<>();
  HashSet<ASMBlock> inWorkList = new HashSet<>();

  public LivenessAnalyzer(ASMFunction func) {
    this.func = func;
    // init
    for (ASMBlock block : func.blocks) {
      block.liveIn.clear();
      block.liveOut.clear();
    }
    // compute the use and def of each block
    // chapter 17.4.2
//     a=i;
// i=k;
// b=i;
    for (ASMBlock block : func.blocks) {
      block.use.clear();
      block.def.clear();
      for (Inst inst : block.insts) {
        for (var reg : inst.getUse())
          if (!block.def.contains(reg))
            block.use.add(reg);//use之前没有def
        block.def.addAll(inst.getDef());//
      }
    }
  }

  // chapter 17.4.5
  public void work() {//一次迭代
    workList.clear();
    inWorkList.clear();
    workList.add(func.exitBlock);
    inWorkList.add(func.exitBlock);
    while (!workList.isEmpty()) {

      ASMBlock block = workList.removeFirst();
      inWorkList.remove(block);

      HashSet<Reg> newLiveOut = new HashSet<>();
      for (var succ : block.succ)
        newLiveOut.addAll(succ.liveIn);//liveout=后继livein的并

      HashSet<Reg> newLiveIn = new HashSet<>(block.use);
      newLiveIn.addAll(newLiveOut);//livein=use 并 (liveout-def)
      newLiveIn.removeAll(block.def);
      if (!newLiveIn.equals(block.liveIn) || !newLiveOut.equals(block.liveOut)) {
        block.liveIn = newLiveIn;
        block.liveOut = newLiveOut;
        for (var pred : block.pred)
          if (!inWorkList.contains(pred)) {
            workList.add(pred);
            inWorkList.add(pred);
          }
      }
    }
  }
}
