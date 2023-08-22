package ast.stmt;

import ast.expr.ExprNode;
import ast.*;
import utils.*;

public class ExprstmtNode extends StmtNode {
  public ExprNode expr;
  
  public ExprstmtNode(Position pos, ExprNode expr) {
    super(pos);
    this.expr = expr;
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}