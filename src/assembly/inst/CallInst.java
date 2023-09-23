package assembly.inst;

import assembly.operand.*;
import java.util.HashSet;

public class CallInst extends Inst {//
  String funcName;
  
  HashSet<Reg> use = new HashSet<>();
  static HashSet<Reg> def = new HashSet<>(PhysicsReg.callerSave);

  public CallInst(String funcName) {
    this.funcName = funcName;
  }

  public void addUse(Reg reg) {
    use.add(reg);
  }



  @Override
  public String toString() {
    return "call " + funcName;
  }
}

