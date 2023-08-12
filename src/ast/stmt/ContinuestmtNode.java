package ast.stmt;

import ast.*;
import utils.*;

public class ContinuestmtNode extends StmtNode {
  public ContinuestmtNode(Position pos) {
    super(pos);
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}