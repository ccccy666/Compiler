package IR;

import java.util.ArrayList;
import java.util.HashMap;

import IR.Type.*;
import IR.Instruction.*;
import IR.Value.*;
import ast.*;
import ast.expr.*;
import ast.stmt.*;
import utils.*;

public class IRbuilder implements ASTvisitor{
    HashMap<String, Classtype> structTypeMap = new HashMap<>();
    // HashMap<IRRegister, Integer> arraySizeMap = new HashMap<>();
    Globalscope globalScope;
    Scope currentScope;
    Functionblock currentFunction = null;
    Basicblock currentBlock = null;
    Classtype currentClass = null;
    
    IRbuilder(Globalscope gs){
        globalScope=gs;
        currentScope=gs;
    }
    @Override
    public void visit(ProgramNode node){

    };

    // public void visit(NullNode node);
    @Override
    public void visit(FuncdefNode node){

    };
    @Override
    public void visit(TypeNode node){

    };
    @Override
    public void visit(ParametersNode node){

    };
    @Override
    public void visit(ClassdefNode node){

    };
    @Override
    public void visit(ConstructordefNode node){

    };
    @Override
    public void visit(VariabledefNode node){

    };
    @Override
    public void visit(Variable node){

    };
    @Override

    public void visit(BasicexprNode node){

    };
    @Override
    public void visit(RecurexprNode node){

    };
    @Override
    public void visit(BinaryexprNode node){
        node.lhs.accept(this);
        switch (node.op){
            case "&&":
            case "||":
                

                break;
            default:
                node.rhs.accept(this);
                break;
        }
        
    };
    @Override
    public void visit(TernaryexprNode node){

    };
    @Override
    public void visit(SufexprNode node){

    };
    @Override
    public void visit(PreexprNode node){

    };
    @Override
    public void visit(AssignexprNode node){

    };
    @Override
    public void visit(FuncexprNode node){

    };
    @Override
    public void visit(IndexexprNode node){

    };
    @Override
    public void visit(MemberexprNode node){

    };  
    @Override
    public void visit(NewexprNode node){

    };
    @Override
    public void visit(ExprlistNode node){

    };
    
    @Override
    public void visit(ContentstmtNode node){

    };
    @Override
    public void visit(IfstmtNode node){

    };
    @Override
    public void visit(WhilestmtNode node){

    };
    @Override
    public void visit(ForstmtNode node){

    };
    @Override
    public void visit(ContinuestmtNode node){

    };
    @Override
    public void visit(BreakstmtNode node){

    };
    @Override
    public void visit(ReturnstmtNode node){

    };
    @Override
    public void visit(ExprstmtNode node){

    };
}
