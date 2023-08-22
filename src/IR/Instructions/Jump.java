package IR.Instructions;

import IR.*;


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
}