package ast.expr;

import ast.*;
import utils.*;

public class SufexprNode extends ExprNode {
  public String op;
  public ExprNode expr;

  public SufexprNode(Position pos, String op, ExprNode expr) {
    super(pos);
    this.op = op;
    this.expr = expr;
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