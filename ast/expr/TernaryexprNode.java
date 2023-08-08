package ast.expr;

import ast.*;
import utils.*;

public class TernaryexprNode extends ExprNode {
  public String op1,op2;
  public ExprNode lhs, mhs,rhs;

  public TernaryexprNode(Position pos, ExprNode lhs, String op1, ExprNode mhs,String op2,ExprNode rhs) {
    super(pos);
    this.lhs = lhs;
    this.op1 = op1;
    this.mhs=mhs;
    this.op2=op2;
    this.rhs = rhs;
  }

  @Override
  public boolean isLeftValue() {
    return false;
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}