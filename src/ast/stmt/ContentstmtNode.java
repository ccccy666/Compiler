package ast.stmt;
import ast.*;
import utils.*;

import java.util.ArrayList;


public class ContentstmtNode extends StmtNode {
  public ArrayList<StmtNode> stmts = new ArrayList<StmtNode>();

  public ContentstmtNode(Position pos) {
    super(pos);
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}
