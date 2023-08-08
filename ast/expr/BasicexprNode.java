package ast.expr;

import ast.*;
import utils.*;

public class BasicexprNode extends ExprNode {
  public BasicexprNode(Position pos, String str) {
    super(pos);
    this.str = str;
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