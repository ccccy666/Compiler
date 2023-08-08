package ast;

import ast.stmt.*;
import java.util.ArrayList;
import utils.*;

public class VariabledefNode extends StmtNode {
  public ArrayList<Variable> units = new ArrayList<Variable>();

  public VariabledefNode(Position pos) {
    super(pos);
  }
  
  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}