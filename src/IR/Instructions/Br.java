package IR.Instructions;

import java.util.LinkedHashSet;

import IR.*;
import IR.Value.*;


public class Br extends TerminalInst {
  public Valu cond;
  public Basicblock thenBlock;
  public Basicblock elseBlock;

  public Br(Basicblock block, Valu cond, Basicblock thenBlock, Basicblock elseBlock) {
    super(block);
    this.cond = cond;
    this.thenBlock = thenBlock;
    this.elseBlock = elseBlock;
  }
  @Override
  public String toString() {
    return "br " + cond.toStringWithType() + ", label %" + thenBlock.name + ", label %" + elseBlock.name;
  }

  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
  
  @Override
  public LinkedHashSet<Valu> getUse() {
    LinkedHashSet<Valu> ret = new LinkedHashSet<>();
    ret.add(cond);
    return ret;
  }

  @Override
  public Register getDef() {
    return null;
  }

  @Override
  public void replaceUse(Valu old, Valu newOne) {
    cond = cond == old ? newOne : cond;
  }
}