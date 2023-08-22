package ast.expr;

import java.util.ArrayList;

import ast.*;
import utils.*;

public class IndexexprNode extends ExprNode {
  public ExprNode array;
  // public ArrayList<ExprNode> indexlist=new ArrayList<>();
  public ExprNode index;
  public IndexexprNode(Position pos, ExprNode array,ExprNode index) {
    super(pos);
    this.array = array;
    this.index = index;
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