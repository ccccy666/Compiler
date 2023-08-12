package ast.stmt;
import ast.*;
import utils.*;
public class BreakstmtNode extends StmtNode{
    public BreakstmtNode(Position pos){
        super(pos);
    }
    @Override
    public void accept(ASTvisitor vis)
    {
        vis.visit(this);
    }
}