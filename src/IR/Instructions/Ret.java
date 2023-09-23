package IR.Instructions;

import java.util.LinkedHashSet;

import IR.*;
import IR.Value.*;
import utils.*;

public class Ret extends TerminalInst implements Elements{
  public Valu val;

  public Ret(Basicblock block, Valu val) {
    super(block);
    this.val = val;
  }
  
  @Override
  public String toString() {
    return "ret " + val.toStringWithType();
  }

  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
  @Override
  public LinkedHashSet<Valu> getUse() {
    LinkedHashSet<Valu> ret = new LinkedHashSet<>();
    ret.add(val);
    return ret;
  }

  @Override
  public Register getDef() {
    return null;
  }

  @Override
  public void replaceUse(Valu old, Valu newOne) {
    val = val == old ? (newOne == null ? Intconst0 : newOne) : val;
  }
}