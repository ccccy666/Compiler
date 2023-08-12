package ast.stmt;

import ast.*;
// import ast.expr.*;
import utils.*;
import ast.expr.ExprNode;

public class ReturnstmtNode extends StmtNode {
  public ExprNode expr;

  public ReturnstmtNode(Position pos, ExprNode expr) {
    super(pos);
    this.expr = expr;
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}