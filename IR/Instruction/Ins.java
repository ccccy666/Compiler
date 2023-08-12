package IR.Instruction;

import IR.*;
import IR.Value.*;
import java.util.LinkedHashSet;

public abstract class Ins {
  public boolean flag = false;
  public Basicblock parentBlock = null;
  public abstract String toString();

  public Ins(Basicblock block) {
    this.parentBlock = block;
  }


  public abstract LinkedHashSet<Val> getUse();
//   public abstract IRRegister getDef();

  public abstract void replaceUse(Val old, Val newOne);
  public abstract void accept(IRvisitor visitor);

}
