package IR.Instructions;

import IR.*;
import IR.Value.*;


public class Ret extends TerminalInst {
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
}