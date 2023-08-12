package ast;

import ast.stmt.*;
import utils.*;

public class ConstructordefNode extends AstNode {
  public String name;
  public ContentstmtNode suite;
  public FuncdefNode info;

  public ConstructordefNode(Position pos, String name, ContentstmtNode suite) {
    super(pos);
    this.name = name;
    this.suite = suite;
  }

  public FuncdefNode transToFuncDef() {
    FuncdefNode funcDef = new FuncdefNode(pos, name);
    funcDef.returnType = new TypeNode(pos, "void");
    funcDef.stmts = suite.stmts;
    return info = funcDef;
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}
