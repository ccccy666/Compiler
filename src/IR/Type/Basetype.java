package IR.Type;

import IR.Value.*;
import utils.*;

public abstract class Basetype  {
  public String name;
  public int size;  // cnt of bit

  public Basetype(String name) {
    this.name = name;
  }

  public Basetype(String name, int size) {
    this.name = name;
    this.size = size;
  }
  // public abstract boolean isEqual(Basetype other);
  public abstract String toString();
  public abstract Valu defaultValue();
}

