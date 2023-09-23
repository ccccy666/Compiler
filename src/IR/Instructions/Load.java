package IR.Instructions;

import java.util.LinkedHashSet;

import IR.*;
import IR.Type.*;
import IR.Value.*;


public class Load extends Ins {
  public Register destReg;
  public Valu storeptr;
  public Basetype type;

  public Load(Basicblock block, Register destReg, Valu storeptr) {
    super(block);
    this.destReg = destReg;
    this.storeptr = storeptr;
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
  @Override
  public LinkedHashSet<Valu> getUse() {
    LinkedHashSet<Valu> ret = new LinkedHashSet<>();
    ret.add(storeptr);
    return ret;
  }

  @Override
  public Register getDef() {
    return destReg;
  }

  @Override
  public void replaceUse(Valu old, Valu newOne) {
    storeptr = storeptr == old ? newOne : storeptr;
  }
}