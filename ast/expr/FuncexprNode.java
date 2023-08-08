package ast.expr;

import ast.*;
import utils.*;

public class FuncexprNode extends ExprNode {
  public ExprNode funcName;//其实是recurexprNode，存函数名,同时在semantic check的时候获取函数定义
  public ExprlistNode args;

  public FuncexprNode(Position pos, ExprNode func) {
    super(pos);
    this.funcName = func;
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