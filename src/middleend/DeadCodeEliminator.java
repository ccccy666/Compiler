package middleend;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import IR.*;
import IR.Instructions.*;
import IR.Value.*;

public class DeadCodeEliminator {
  Program program;

  HashMap<Register, HashSet<Ins>> useList;
  HashMap<Register, Ins> defList = new HashMap<>();
  LinkedList<Register> workList = new LinkedList<>();
  HashSet<Register> inWorkList = new HashSet<>();

  public DeadCodeEliminator(Program program) {
    this.program = program;
  }

  public void work() {
    for(var func:program.funcList){
      workOnFunc(func);
    }
  }

  void workOnFunc(Functionblock func) {
    useList = new HashMap<>();
    for (var block : func.blocks) {

      for (var inst : block.insts) {
        if (inst.getDef() != null) {
          defList.put(inst.getDef(), inst);
          workList.add(inst.getDef());
          inWorkList.add(inst.getDef());
        }
        for (var use : inst.getUse())
          if (use instanceof Register ){
            Register reg=(Register) use;
            useList.computeIfAbsent(reg, k -> new HashSet<>()).add(inst);
          }
            
      }
      var inst = block.terminalInst;
      for (var use : inst.getUse())
        if (use instanceof Register ){
          Register reg=(Register) use;
          useList.computeIfAbsent(reg, k -> new HashSet<>()).add(inst);
        }
          
    }
    while (!workList.isEmpty()) {
      Register reg = workList.removeFirst();
      inWorkList.remove(reg);
      if (useList.get(reg) == null || useList.get(reg).isEmpty()) {
        Ins inst = defList.get(reg);
        if (inst instanceof Call || inst == null)
          continue; // call inst has side effect
        inst.isDeleted = true;
        for (var use : inst.getUse())//def语句
          if (use instanceof Register ) {
            Register useReg=(Register) use;
            useList.get(useReg).remove(inst);//删去语句
            if (!inWorkList.contains(useReg)) {//一个语句中的def没有用过，use可能也只是定义没有用过
              workList.add(useReg);
              inWorkList.add(useReg);
            }
          }
      }
      func.useList = useList;
    }

    for (var block : func.blocks)
      block.insts.removeIf(inst -> inst.isDeleted);
  }
}
