package ast;

import java.util.ArrayList;

import utils.*;

public class ProgramNode extends AstNode {
  public ArrayList<AstNode> defList = new ArrayList<AstNode>();

  public ProgramNode(Position pos) {
    super(pos);
  }

  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}