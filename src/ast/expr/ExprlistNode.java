package ast.expr;

import ast.*;
import java.util.ArrayList;
import utils.*;

public class ExprlistNode extends AstNode {
  public ArrayList<ExprNode> exprs = new ArrayList<ExprNode>();

  public ExprlistNode(Position pos) {
    super(pos);
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}