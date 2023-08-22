package ast.stmt;

import ast.*;
// import ast.expr.*;
import ast.expr.*;
import utils.*;

public class WhilestmtNode extends circle {
  
  public WhilestmtNode(Position pos, ExprNode cond) {
    super(pos);
    this.cond = cond;
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}