package ast;

import utils.*;
import java.util.ArrayList;

public class ParametersNode extends AstNode {
  public ArrayList<Variable> units = new ArrayList<Variable>();

  public ParametersNode(Position pos) {
    super(pos);
  }
  public ParametersNode(Position pos, TypeNode typ, int cnt) {
    super(pos);
    for (int i = 0; i < cnt; ++i)
      units.add(new Variable(pos, new TypeNode(pos, typ.type, typ.dim), "p" + i));
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}