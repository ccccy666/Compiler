package IR.Value;

import utils.*;
import IR.Type.*;

public abstract class Val  {
  public Base type;

//   public Reg asmReg;
  
  Val(Base type) {
    this.type = type;
  }

  public abstract String toString();

  public abstract String toStringWithType();
}