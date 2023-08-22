package IR.Type;

import IR.Value.*;

public class Int extends Basetype {
  public int length;

  public Int(int length) {
    super("i" + String.valueOf(length), length / 8);
    this.length = length;
  }

  @Override
  public String toString() {
    return "i" + String.valueOf(length);
  }
 
  @Override
  public Valu defaultValue() {
    return new Intconst(0);
  }
}