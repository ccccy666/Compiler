package IR.Value;
import IR.Type.*;
import IR.Type.Void;
public class Voidconst extends Const {
  public Voidconst() {
    super(new Void());
  }

  @Override
  public String toString() {
    return "void";
  }

  @Override
  public String toStringWithType() {
    return toString();
  }
  @Override
  public boolean isZero() {
    return false;
  }
}