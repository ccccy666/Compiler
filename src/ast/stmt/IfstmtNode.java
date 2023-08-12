package ast.stmt;


import ast.*;
import ast.expr.ExprNode;
import utils.*;
import java.util.ArrayList;
public class IfstmtNode extends StmtNode{
    public ExprNode cond;
    // public ContentstmtNode Ifsuite;
    // public ContentstmtNode Elsesuite;
    public ArrayList<StmtNode> thenStmts = new ArrayList<StmtNode>();
  public ArrayList<StmtNode> elseStmts = new ArrayList<StmtNode>();

  public IfstmtNode(Position pos, ExprNode cond) {
    super(pos);
    this.cond = cond;
  }
    @Override
    public void accept(ASTvisitor vis)
    {
        vis.visit(this);
    }
}