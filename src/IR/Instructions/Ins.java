package IR.Instructions;

import java.util.LinkedHashSet;

import IR.*;
import IR.Value.Register;
import IR.Value.Valu;


public abstract class Ins {
  public Basicblock parentBlock = null;
  public boolean isDeleted = false;

  public Ins(Basicblock block) {
    this.parentBlock = block;
  }
  public abstract String toString();
  public abstract void accept(IRVisitor visitor);

  public abstract LinkedHashSet<Valu> getUse();
  public abstract Register getDef();

  public abstract void replaceUse(Valu old, Valu newOne);
}

