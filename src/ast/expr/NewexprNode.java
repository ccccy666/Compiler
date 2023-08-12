package ast.expr;

import ast.*;
import utils.*;
import java.util.ArrayList;

public class NewexprNode extends ExprNode {
  public String typeName;
  public int dim = 0;
  public ArrayList<ExprNode> sizeList = new ArrayList<ExprNode>();


  public NewexprNode(Position pos, String typeName) {
    super(pos);
    this.typeName = typeName;
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