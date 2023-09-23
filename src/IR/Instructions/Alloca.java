package IR.Instructions;

import java.util.LinkedHashSet;

import IR.*;
import IR.Type.*;
import IR.Value.*;


public class Alloca extends Ins {
  public Register allocaReg;
  public Basetype type;
  public int func_para = -1;
  public Alloca(Basicblock block, Basetype type, Register allocaReg) {
    super(block);
    this.type = type;
    this.allocaReg = allocaReg;
  }
  public Alloca(Basicblock block, Basetype type, Register allocaReg, int para) {
    super(block);
    this.type = type;
    this.allocaReg = allocaReg;
    this.func_para = para;
  }

  @Override
  public String toString() {
    return allocaReg + " = alloca " + type;
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
    return allocaReg;
  }

  @Override
  public void replaceUse(Valu old, Valu newOne) {
    // do nothing
  }
}

