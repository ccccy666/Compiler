package ast;

// import ast.*;
import ast.expr.*;
import ast.stmt.*;
// import ast.stmt.ReturnstmtNode;
import ast.stmt.BreakstmtNode;

public interface ASTvisitor {
  public void visit(ProgramNode node);

  // public void visit(NullNode node);
  public void visit(FuncdefNode node);
  public void visit(TypeNode node);
  public void visit(ParametersNode node);
  public void visit(ClassdefNode node);
  public void visit(ConstructordefNode node);
  public void visit(VariabledefNode node);
  public void visit(Variable node);
  
  public void visit(BasicexprNode node);
  public void visit(RecurexprNode node);
  public void visit(BinaryexprNode node);
  public void visit(TernaryexprNode node);
  public void visit(SufexprNode node);
  public void visit(PreexprNode node);
  public void visit(AssignexprNode node);
  public void visit(FuncexprNode node);
  public void visit(IndexexprNode node);
  public void visit(MemberexprNode node);  
  public void visit(NewexprNode node);
  public void visit(ExprlistNode node);

  public void visit(ContentstmtNode node);
  public void visit(IfstmtNode node);
  public void visit(WhilestmtNode node);
  public void visit(ForstmtNode node);
  public void visit(ContinuestmtNode node);
  public void visit(BreakstmtNode node);
  public void visit(ReturnstmtNode node);
  public void visit(ExprstmtNode node);
}