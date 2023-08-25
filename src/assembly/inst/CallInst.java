package assembly.inst;

import assembly.operand.*;
import java.util.HashSet;

public class CallInst extends Inst {//
  String funcName;
  
  

  public CallInst(String funcName) {
    this.funcName = funcName;
  }

  



  @Override
  public String toString() {
    return "call " + funcName;
  }
}

