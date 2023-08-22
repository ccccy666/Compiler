package IR.Instructions;

import IR.*;
import IR.Type.*;
import IR.Value.*;



public class Icmp extends Ins {
  public Basetype type;
  public Register cmp;
  public Valu lhs, rhs;
  public String op;

  public Icmp(Basicblock block, Basetype type, Register Reg, Valu lhs, Valu rhs, String op) {
    super(block);
    this.type = type;
    this.cmp = Reg;
    this.lhs = lhs;
    this.rhs = rhs;
    this.op = op;
  }

  @Override
  public String toString() {
    return cmp + " = icmp " + op +" " + type +  " " + lhs + ", " + rhs;
  }
  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
  
}