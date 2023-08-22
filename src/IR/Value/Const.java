package IR.Value;

import IR.Type.*;

public abstract class Const extends Valu {
  public Const(Basetype type) {
    super(type);
  }
  public abstract boolean isZero();
  
}