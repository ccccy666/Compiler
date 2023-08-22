package ast.stmt;

import IR.*;
import ast.*;
import ast.expr.ExprNode;
import utils.*;


public class ForstmtNode extends circle {
  public VariabledefNode varDef;
  
  public ExprNode init, step;
  public Basicblock stepBlock;

  public ForstmtNode(Position pos) {
    super(pos);
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}