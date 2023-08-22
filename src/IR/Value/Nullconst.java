package IR.Value;

import IR.Type.*;
import IR.Type.Void;
public class Nullconst extends Const {
  public Nullconst() {
    super(new Ptr(new Void()));
  }

  public Nullconst(Basetype type) {
    super(type);
  }

  @Override
  public String toString() {
    return "null";
  }

  @Override
  public String toStringWithType() {
    return type == new Ptr(new Void()) ? toString()  : type + " " + toString();
  }

  @Override
  public boolean isZero() {
    return true;
  }
}