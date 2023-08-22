package IR.Instructions;

import IR.*;


public abstract class Ins {
  public Basicblock parentBlock = null;
  

  public Ins(Basicblock block) {
    this.parentBlock = block;
  }
  public abstract String toString();
  public abstract void accept(IRVisitor visitor);

  
}

