package IR.Instructions;

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
}