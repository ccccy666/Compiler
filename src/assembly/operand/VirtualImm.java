package assembly.operand;

import IR.Value.*;

public class VirtualImm extends Reg {
  int value;
  public VirtualImm(int value) {
    this.value = value;
  }

  public VirtualImm(Const constVal) {
    if (constVal instanceof Intconst) {
      value = ((Intconst) constVal).val;
    } else if (constVal instanceof Boolconst) {
      value = ((Boolconst) constVal).val ? 1 : 0;
    
    }else {
      value = 0;
    
  }
}

  public String toString() {
    return Integer.toString(value);
  }
}