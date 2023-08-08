package ast.expr;

import ast.*;
import utils.*;

public class BinaryexprNode extends ExprNode {
  public String op;
  public ExprNode lhs, rhs;

  public BinaryexprNode(Position pos, ExprNode lhs, String op, ExprNode rhs) {
    super(pos);
    this.lhs = lhs;
    this.op = op;
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