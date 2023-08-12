package ast.expr;

import ast.*;
import utils.*;

public class RecurexprNode extends BasicexprNode {
  public RecurexprNode(Position pos, String str) {
    super(pos, str);
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