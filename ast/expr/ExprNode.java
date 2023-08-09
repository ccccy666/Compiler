package ast.expr;

import ast.*;
import utils.*;


public abstract class ExprNode extends AstNode {
  public String str;
  public TypeNode type;
  public FuncdefNode funcDef = null;
  

  public ExprNode(Position pos) {
    super(pos);
  }

  public abstract boolean isLeftValue();

};