package ast.stmt;

import ast.*;
import utils.*;

public abstract class StmtNode extends AstNode {
  public StmtNode(Position pos) {
    super(pos);
  }
};