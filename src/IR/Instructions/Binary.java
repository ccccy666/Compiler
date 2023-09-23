package IR.Instructions;

import java.util.LinkedHashSet;

import IR.*;
import IR.Type.*;
import IR.Value.*;



public class Binary extends Ins {
  public Basetype resultType;
  public String op;
  public Register res;
  public Valu lhs, rhs;

  public Binary(Basicblock block, Basetype resultType, Register res, Valu lhs, Valu rhs, String op) {
    super(block);
    this.resultType = resultType;
    this.res = res;
    this.lhs = lhs;
    this.rhs = rhs;
    this.op = op;
  }
  
  @Override
  public String toString() {
    return res + " = " + op + " " + resultType + ' '+lhs + ", " + rhs;
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
    return res;
  }

  @Override
  public void replaceUse(Valu old, Valu newOne) {
    lhs = lhs == old ? newOne : lhs;
    rhs = rhs == old ? newOne : rhs;
  }
  public Intconst calcConst() {
    if (lhs instanceof Intconst && rhs instanceof Intconst) {
      int lhsVal = ((Intconst) lhs).val;
      int rhsVal = ((Intconst) rhs).val;
      int resVal = 0;
      switch (op) {
        case "add":
          resVal = lhsVal + rhsVal;
          break;
        case "sub":
          resVal = lhsVal - rhsVal;
          break;
        case "mul":
          resVal = lhsVal * rhsVal;
          break;
        case "sdiv":
          if (rhsVal == 0) return null;
          resVal = lhsVal / rhsVal;
          break;
        case "srem":
          resVal = lhsVal % rhsVal;
          break;
        case "shl":
          resVal = lhsVal << rhsVal;
          break;
        case "ashr":
          resVal = lhsVal >> rhsVal;
          break;
        case "and":
          resVal = lhsVal & rhsVal;
          break;
        case "or":
          resVal = lhsVal | rhsVal;
          break;
        case "xor":
          resVal = lhsVal ^ rhsVal;
          break;
      }
      return new Intconst(resVal);
    }
    return null;
  }
}