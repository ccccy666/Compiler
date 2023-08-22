package ast.stmt;


import ast.expr.*;
import utils.*;

import java.util.ArrayList;

import IR.*;

public abstract class circle extends StmtNode {
  public ExprNode cond;
  public ArrayList<StmtNode> stmts = new ArrayList<StmtNode>();
  public Basicblock condBlock, loopBlock, nextBlock;
  public circle(Position pos) {
    super(pos);
  }
}
