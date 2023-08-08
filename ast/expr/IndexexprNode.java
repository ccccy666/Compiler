package ast.expr;

import java.util.ArrayList;

import ast.*;
import utils.*;

public class IndexexprNode extends ExprNode {
  public ExprNode array;
  public ArrayList<ExprNode> indexlist=new ArrayList<>();

  public IndexexprNode(Position pos, ExprNode array) {
    super(pos);
    this.array = array;
    // this.indexlist = index;
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