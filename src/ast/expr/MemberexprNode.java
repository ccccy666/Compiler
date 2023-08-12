package ast.expr;

import ast.*;
import utils.*;

public class MemberexprNode extends ExprNode {
  public ExprNode obj;
  public String member;


  public MemberexprNode(Position pos, ExprNode obj, String member) {
    super(pos);
    this.obj = obj;
    this.member = member;
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