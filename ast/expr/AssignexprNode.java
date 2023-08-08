package ast.expr;

import ast.*;
import utils.*;

public class AssignexprNode extends BinaryexprNode {

  public AssignexprNode(Position pos, ExprNode lhs, ExprNode rhs) {
    super(pos, lhs, "=", rhs);
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