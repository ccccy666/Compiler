package IR.Type;

import IR.Value.*;

public class Void extends Basetype {
  public Void() {
    super("void", 0);
  }

  @Override
  public String toString() {
    return "void";
  }
  
  @Override
  public Valu defaultValue() {
    return null;
  }
}