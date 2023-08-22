package IR.Value;

import IR.Type.*;
public class Boolconst extends Const {
  public boolean val;

  public Boolconst(boolean val) {
    super(irBoolType);
    this.val = val;
  }

  @Override
  public String toString() {
    return val ? "true" : "false";
  }

  @Override
  public String toStringWithType() {
    return "i1 " + toString();
  }

  @Override
  public boolean isZero() {
    return !val;
  }
}