package ast.expr;

import ast.*;
import utils.*;
import IR.*;
import IR.Value.*;
import IR.Type.*;

public abstract class ExprNode extends AstNode {
  public Register storePtr = null; // for left value in IR
  public Valu value = null;      // for IR
  public String str;
  public TypeNode type;
  public FuncdefNode funcDef = null;
  

  public ExprNode(Position pos) {
    super(pos);
  }

  public abstract boolean isLeftValue();
  public Basetype getBasetype() {
    return value.type;
  }
};