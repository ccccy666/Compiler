package ast;

import ast.expr.*;
import utils.*;

public class Variable extends AstNode {
  public TypeNode type;
  public String varName;
  public ExprNode initVal;

  public Variable(Position pos, TypeNode type, String name) {
    super(pos);
    this.type = type;
    this.varName = name;
  }
  public Variable(Position pos, TypeNode type, String name, ExprNode initVal) {
    super(pos);
    this.type = type;
    this.varName = name;
    this.initVal = initVal;
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}
