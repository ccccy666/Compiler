package IR.Value;
import IR.Type.*;

public class Intconst extends Const {
  public int val;

  public Intconst(int val) {
    super(irIntType);
    this.val = val;
  }

  @Override
  public String toString() {
    return String.valueOf(val);
  }

  @Override
  public String toStringWithType() {
    return "i32 " + toString();
  }
  @Override
  public boolean isZero() {
    return val == 0;
  }
 
}