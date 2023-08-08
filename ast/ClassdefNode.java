package ast;

import utils.*;
import java.util.ArrayList;
import java.util.HashMap;

// import ast.stmt.ConstructordefNode;

public class ClassdefNode extends AstNode {
  public String name;
  public ConstructordefNode classBuild;
  public ArrayList<VariabledefNode> varDefList = new ArrayList<VariabledefNode>();
  public ArrayList<FuncdefNode> funcDefList = new ArrayList<FuncdefNode>();
  public HashMap<String, FuncdefNode> funcMember = new HashMap<String, FuncdefNode>();
  public HashMap<String, Variable> varMember = new HashMap<String, Variable>();
  

  public ClassdefNode(Position pos, String name) {
    super(pos);
    this.name = name;
  }

  public FuncdefNode getFuncDef(String name) {
    return funcMember.get(name);
  }
  public TypeNode getVarType(String name) {
    Variable unit = varMember.get(name);
    if (unit == null) return null;
    return unit.type;
  }
  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}