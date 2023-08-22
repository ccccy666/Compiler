package IR.Instructions;

import IR.*;
import IR.Type.*;
import IR.Value.*;


public class Load extends Ins {
  public Register destReg;
  public Valu storeptr;
  public Basetype type;

  public Load(Basicblock block, Register destReg, Valu srcAddr) {
    super(block);
    this.destReg = destReg;
    this.storeptr = srcAddr;
    this.type = destReg.type;
  }

  @Override
  public String toString() {
    return destReg + " = load " + type + ", " + storeptr.toStringWithType();
  }
  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
  
}