package IR.Instructions;

import java.util.LinkedHashSet;

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
  @Override
  public LinkedHashSet<Valu> getUse() {
    LinkedHashSet<Valu> ret = new LinkedHashSet<>();
    ret.add(lhs);
    ret.add(rhs);
    return ret;
  }

  @Override
  public Register getDef() {
    return cmp;
  }

  @Override
  public void replaceUse(Valu old, Valu newOne) {
    lhs = lhs == old ? newOne : lhs;
    rhs = rhs == old ? newOne : rhs;
  }
  public Boolconst calcConst() {
    if (lhs instanceof Intconst && rhs instanceof Intconst) {
      int lhsVal = ((Intconst) lhs).val;
      int rhsVal = ((Intconst) rhs).val;
      boolean resVal = false;
      switch (op) {
        case "eq":
          resVal = lhsVal == rhsVal;
          break;
        case "ne":
          resVal = lhsVal != rhsVal;
          break;
        case "sgt":
          resVal = lhsVal > rhsVal;
          break;
        case "sge":
          resVal = lhsVal >= rhsVal;
          break;
        case "slt":
          resVal = lhsVal < rhsVal;
          break;
        case "sle":
          resVal = lhsVal <= rhsVal;
          break;
      }
      return new Boolconst(resVal);
    }
    return null;
  }
}