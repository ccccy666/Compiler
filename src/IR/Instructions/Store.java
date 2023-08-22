package IR.Instructions;

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
}