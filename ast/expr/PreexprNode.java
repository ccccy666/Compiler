package ast.expr;

import ast.*;
import utils.*;

public class PreexprNode extends SufexprNode {
  public PreexprNode(Position pos, String op, ExprNode expr) {
    super(pos, op, expr);
  }

  @Override
  public boolean isLeftValue() {
    return true;
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}