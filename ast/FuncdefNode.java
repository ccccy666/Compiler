package ast;

import ast.stmt.*;
import utils.*;
import java.util.ArrayList;

public class FuncdefNode extends AstNode {
    
  public TypeNode returnType;
   public String name, className = null;
  public ParametersNode params = null;
  public ArrayList<StmtNode> stmts = new ArrayList<StmtNode>();

  public FuncdefNode(Position pos, String name) {
    super(pos);
    this.name = name;
  }
  public FuncdefNode(Position pos, TypeNode typ, String name, TypeNode paramType, int cnt) {
    super(pos);
    this.returnType = new TypeNode(pos, typ.type, typ.dim);
    this.name = name;
    if (paramType != null && cnt > 0)
      this.params = new ParametersNode(pos, paramType, cnt);
  }

  public FuncdefNode(Position pos, TypeNode typ, String name, String className, TypeNode paramType, int cnt) {
    super(pos);
    this.returnType = new TypeNode(pos, typ.type, typ.dim);
    this.name = name;
    this.className = className;
    if (paramType != null && cnt > 0)this.params = new ParametersNode(pos, paramType, cnt);
  }
  
  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}