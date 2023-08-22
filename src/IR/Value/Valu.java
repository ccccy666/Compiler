package IR.Value;

import utils.*;
import IR.Type.*;
import assembly.operand.Reg;

public abstract class Valu implements Elements {
  public Basetype type;

  public Reg asmreg;
  
  Valu(Basetype type) {
    this.type = type;
  }

  public abstract String toString();

  public abstract String toStringWithType();
}