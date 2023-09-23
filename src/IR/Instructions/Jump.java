package IR.Instructions;

import java.util.LinkedHashSet;

import IR.*;
import IR.Value.Register;
import IR.Value.Valu;


public class Jump extends TerminalInst {
  public Basicblock toBlock;

  public Jump(Basicblock block, Basicblock toBlock) {
    super(block);
    this.toBlock = toBlock;
  }

  @Override
  public String toString() {
    return "br label %" + toBlock.name;
  }

  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
  @Override
  public LinkedHashSet<Valu> getUse() {
    return new LinkedHashSet<>();
  }

  @Override
  public Register getDef() {
    return null;
  }

  @Override
  public void replaceUse(Valu old, Valu newOne) {
  }
}