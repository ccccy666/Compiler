package IR.Value;

import IR.Type.*;

public class Globalvar extends Register {  
  public Valu initVal;
  public boolean init = false;
  
  public Globalvar(String name, Basetype type) {
    super(name, new Ptr(type));
    --regCnt;
  }

  @Override
  public String toString() {
    return "@" + name;
  }

  @Override
  public String toStringWithType() {
    return type + " " + toString();
  }
}