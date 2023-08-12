package ast;
import utils.*;
abstract public class AstNode {
  public Position pos;

  public AstNode(Position pos) {
    this.pos = pos;
  }
    
    public abstract void accept(ASTvisitor vis);
}