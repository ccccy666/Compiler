package IR.Instructions;

import java.util.LinkedHashSet;

import IR.*;
import IR.Value.*;


public class Store extends Ins {
  public Register destAddr;
  public Valu val;
  public int func_para = -1;
  

  public Store(Basicblock block, Valu val, Register destAddr) {
    super(block);
    this.val = val;
    this.destAddr = destAddr;
  }

  public Store(Basicblock block, Valu val, Register destAddr, int para) {
    super(block);
    this.val = val;
    this.destAddr = destAddr;
    this.func_para = para;
  }

  @Override
  public String toString() {
    return "store " + val.toStringWithType() + ", " + destAddr.toStringWithType();
  }

  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
  @Override
  public LinkedHashSet<Valu> getUse() {
    LinkedHashSet<Valu> ret = new LinkedHashSet<>();
    ret.add(val);
    ret.add(destAddr);
    return ret;
  }

  @Override
  public Register getDef() {
    return null;
  }

  @Override
  public void replaceUse(Valu old, Valu newOne) {
    if (val == old) {
      // System.out.println("replace " + old.toStringWithType() + " with " + newOne.toStringWithType() + " in " + this.toString() + " in " + parentBlock.name);
      val = newOne;
    }
    if (destAddr == old)
      destAddr = (Register) newOne;
  }
}