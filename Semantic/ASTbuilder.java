package Semantic;

import utils.*;
import utils.Error;

import java.util.List;

// import .antlr.MxParser.StmtContext;
import ast.*;
import ast.stmt.*;
import ast.expr.*;
// import .antlr.MxParser.ClassdefContext;
// import .antlr.MxParser.ConstructordefContext;
import gram.*;
import gram.MxParser.*;


public class ASTbuilder extends MxBaseVisitor<AstNode> {
  /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
  
  @Override
  public AstNode visitProgram(MxParser.ProgramContext ctx) {
    ProgramNode program = new ProgramNode(new Position(ctx));
    for (var ele : ctx.children)
      if (ele instanceof ClassDefContext) {
        program.defList.add((ClassdefNode) visit(ele));
        // program.regi
      } else if (ele instanceof FuncDefContext) {
        program.defList.add((FuncdefNode) visit(ele));
      } else if (ele instanceof VarDefContext) {
        program.defList.add((VariabledefNode) visit(ele));
      }
    return program;
  }
  /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
  @Override
  public AstNode visitFuncDef(MxParser.FuncDefContext ctx) {
    FuncdefNode funcDef = new FuncdefNode(new Position(ctx), ctx.ID().getText());
    funcDef.returnType = (TypeNode) visit(ctx.returnType());
    if (ctx.parameterList() != null)
      funcDef.params = (ParametersNode) visit(ctx.parameterList());
    funcDef.stmts = ((ContentstmtNode) visit(ctx.suite())).stmts;
    return funcDef;
  }
  /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
  @Override
  public AstNode visitReturnType(MxParser.ReturnTypeContext ctx) {
    if (ctx.Void() != null)
      return new TypeNode(new Position(ctx), ctx.getText());
    else
      return  new TypeNode(new Position(ctx), ctx.type().typeName().getText(), ctx.type().LBracket().size());
  }
  /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
  @Override
  public AstNode visitParameterList(MxParser.ParameterListContext ctx) {
    ParametersNode parameterList = new ParametersNode(new Position(ctx));
    for (int i = 0; i < ctx.type().size(); i++)
      parameterList.units.add(new Variable(new Position(ctx.type(i)),(TypeNode) visit(ctx.type(i)),ctx.ID(i).getText(),null));
    return parameterList;
  }
  /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
  @Override
  public AstNode visitSuite(MxParser.SuiteContext ctx) {
    ContentstmtNode suite = new ContentstmtNode(new Position(ctx));
    List<StatementContext> lis=ctx.statement();
    for(var ele:lis){
      suite.stmts.add((StmtNode) visit(ele));
    }
    //  ctx.statement().forEach(stmt -> suite.stmts.add((StmtNode) visit(stmt)));
    return suite;
  }
  /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
  @Override
  public AstNode visitClassDef(MxParser.ClassDefContext ctx) {
    ClassdefNode classDef = new ClassdefNode(new Position(ctx), ctx.ID().getText());
    boolean hasConstructor = false;
    for (var ele : ctx.children)
      if (ele instanceof FuncDefContext) {
        classDef.funcDefList.add((FuncdefNode) visit(ele));
      } else if (ele instanceof ClassBuildContext) {
        if (hasConstructor){
          // String s=ele..ID().getText;
          throw new Error(new Position(ctx), "Class "+ctx.ID().getText()+" has mutiple constructors");
        }
        hasConstructor = true;
        classDef.classBuild = (ConstructordefNode) visit(ele);
      }else if (ele instanceof VarDefContext) {
        classDef.varDefList.add((VariabledefNode) visit(ele));
      }  
    return classDef;
  }
  
  /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
  @Override
  public AstNode visitClassBuild(MxParser.ClassBuildContext ctx) {
    ConstructordefNode classBuild = new ConstructordefNode(new Position(ctx),ctx.ID().getText(),(ContentstmtNode) visit(ctx.suite()));
    return classBuild;
  }
  /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
  @Override
  public AstNode visitVarDef(MxParser.VarDefContext ctx) {
    VariabledefNode varDef = new VariabledefNode(new Position(ctx));
    TypeNode type = (TypeNode) visit(ctx.type());
    for (var ele : ctx.varDefUnit())
      varDef.units.add((new Variable( new Position(ele),type,ele.ID().getText(),ele.expr() == null ? null : (ExprNode) visit(ele.expr()))));
    return varDef;
  }
  /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
  @Override
  public AstNode visitType(MxParser.TypeContext ctx) {
    return new TypeNode(new Position(ctx), ctx.typeName().getText(), ctx.LBracket().size());
  }
  /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
  @Override
  public AstNode visitStatement(MxParser.StatementContext ctx) {     
    if (ctx.suite() != null){
      // MxParser.SuiteContext Ctx=ctx.suite();
      return visit(ctx.suite());
    }  
    if (ctx.varDef() != null){//vardef
      MxParser.VarDefContext Ctx=ctx.varDef();

      VariabledefNode varDef = new VariabledefNode(new Position(Ctx));
      TypeNode type = (TypeNode) visit(Ctx.type());
      for (var ele : Ctx.varDefUnit())
        varDef.units.add((new Variable( new Position(ele),type,ele.ID().getText(),ele.expr() == null ? null : (ExprNode) visit(ele.expr()))));
      return varDef;    
    }
    if (ctx.exprStmt() != null){//expr
      return new ExprstmtNode(new Position(ctx), ctx.exprStmt().expr() == null ? null : (ExprNode) visit(ctx.exprStmt().expr()));
    }
    if (ctx.ifStmt() != null){
      MxParser.IfStmtContext Ctx=ctx.ifStmt();
      IfstmtNode ifStmt = new IfstmtNode(new Position(Ctx), (ExprNode) visit(Ctx.expr()));
      if (Ctx.statement(0).suite() != null)
        ifStmt.thenStmts = ((ContentstmtNode) visit(Ctx.statement(0).suite())).stmts;
      else
        ifStmt.thenStmts.add((StmtNode) visit(Ctx.statement(0)));
      if (Ctx.Else() != null) {
        if (Ctx.statement(1).suite() != null)
          ifStmt.elseStmts = ((ContentstmtNode) visit(Ctx.statement(1).suite())).stmts;
        else
          ifStmt.elseStmts.add((StmtNode) visit(Ctx.statement(1)));
      }
      return ifStmt;
    }      
    if (ctx.forStmt() != null){
      MxParser.ForStmtContext Ctx=ctx.forStmt();
      ForstmtNode forStmt = new ForstmtNode(new Position(Ctx));
      
      if (Ctx.forInit().varDef() != null)//初始化
        forStmt.varDef = (VariabledefNode) visit(Ctx.forInit().varDef());
      else
        forStmt.init = ((ExprstmtNode) visit(Ctx.forInit().exprStmt())).expr;
      forStmt.cond = ((ExprstmtNode) visit(Ctx.exprStmt())).expr;//退出条件
      if (Ctx.expr() != null)//参数修改
        forStmt.step = (ExprNode) visit(Ctx.expr());
      if (Ctx.statement().suite() != null)//循环操作
        forStmt.stmts = ((ContentstmtNode) visit(Ctx.statement().suite())).stmts;
      else
        forStmt.stmts.add((StmtNode) visit(Ctx.statement()));
      return forStmt;    
  }      
    if (ctx.whileStmt() != null){
      MxParser.WhileStmtContext Ctx=ctx.whileStmt();
      WhilestmtNode whileStmt = new WhilestmtNode(new Position(ctx), (ExprNode) visit(Ctx.expr()));
      if (Ctx.statement().suite() != null)
        whileStmt.stmts = ((ContentstmtNode) visit(Ctx.statement().suite())).stmts;
      else
        whileStmt.stmts.add((StmtNode) visit(Ctx.statement()));
      return whileStmt;
    }
     
    if (ctx.returnStmt() != null){
      return new ReturnstmtNode(new Position(ctx.returnStmt()),
        ctx.returnStmt().expr() == null ? null : (ExprNode) visit(ctx.returnStmt().expr()));//单return或有返回值
    }
      
    if (ctx.breakStmt() != null){
      return new BreakstmtNode(new Position(ctx.breakStmt()));
    }
      
    if (ctx.continueStmt() != null){
      return new ContinuestmtNode(new Position(ctx.continueStmt()));
    }
      
    
    return visitChildren(ctx); 
  }
  /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
  @Override
  public AstNode visitExprStmt(MxParser.ExprStmtContext ctx) {
    return new ExprstmtNode(new Position(ctx), ctx.expr() == null ? null : (ExprNode) visit(ctx.expr()));
  }
  /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
  @Override
  public AstNode visitNewExpr(MxParser.NewExprContext ctx) {
    NewexprNode newExpr = new NewexprNode(new Position(ctx), ctx.typeName().getText());
    newExpr.dim = ctx.unit().size();
    boolean flag = false;
    int total=ctx.unit().size();
    // System.out.print(total);
    
    for(int i=0;i<total;i++){
      if(ctx.unit(i).expr()==null){
        flag=true;
        
      }else if(flag==true){
        throw new Error(new Position(ctx), "Define of array is wrong");
      }else {
        // System.out.print(ctx.expr(i).getText());
        newExpr.sizeList.add((ExprNode) visit(ctx.unit(i).expr()));
      }

    }
    // if(newExpr.dim !=ctx.expr().size())
    // for(int i=0;i<newExpr.dim;i++){
      
    // }
    
    return newExpr;
  }
  /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
  @Override
  public AstNode visitIndexExpr(MxParser.IndexExprContext ctx) {
    IndexexprNode expr = new IndexexprNode(new Position(ctx), (ExprNode) visit(ctx.expr(0)));
    List<ExprContext> list=ctx.expr();
    for(int i=1;i<list.size();i++){
      expr.indexlist.add((ExprNode) visit(ctx.expr(i)));
    }
    
    expr.str = ctx.getText();
    return expr;
  }
  /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
  @Override
  public AstNode visitFuncExpr(MxParser.FuncExprContext ctx) {
    
    FuncexprNode funcExpr = new FuncexprNode(new Position(ctx), (ExprNode) visit(ctx.expr()));
    if (ctx.exprList() != null)
      funcExpr.args = (ExprlistNode) visit(ctx.exprList());
    return funcExpr;
  }
  /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
  @Override

  public AstNode visitTernaryExpr(MxParser.TernaryExprContext ctx) {

    return new TernaryexprNode(new Position(ctx),(ExprNode) visit(ctx.expr(0)),ctx.op1.getText(),(ExprNode) visit(ctx.expr(1)),ctx.op2.getText(),(ExprNode) visit(ctx.expr(2)));
  }
  /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
  @Override
  public AstNode visitMemberExpr(MxParser.MemberExprContext ctx) {
    MemberexprNode expr = new MemberexprNode(new Position(ctx), (ExprNode) visit(ctx.expr()), ctx.ID().getText());
    expr.str = ctx.getText();
    return expr;
  }
   
  /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
  @Override
  public AstNode visitAtomExpr(MxParser.AtomExprContext ctx) {
    ExprNode expr = (ExprNode) visitChildren(ctx); // no need to change
    expr.str = ctx.getText();
    return expr;
  }
  /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
  @Override
  public AstNode visitBinaryExpr(MxParser.BinaryExprContext ctx) {
    return new BinaryexprNode(new Position(ctx),(ExprNode) visit(ctx.expr(0)),ctx.op.getText(),(ExprNode) visit(ctx.expr(1)));
  }
  /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
  @Override
  public AstNode visitPreExpr(MxParser.PreExprContext ctx) {
    return new PreexprNode(new Position(ctx), ctx.op.getText(), (ExprNode) visit(ctx.expr()));
  }
  /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
  @Override
  public AstNode visitSufExpr(MxParser.SufExprContext ctx) {
    return new SufexprNode(new Position(ctx), ctx.op.getText(), (ExprNode) visit(ctx.expr()));
  }
  
  /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
  @Override
  public AstNode visitAssignExpr(MxParser.AssignExprContext ctx) {
    return new AssignexprNode(new Position(ctx),(ExprNode) visit(ctx.expr(0)),(ExprNode) visit(ctx.expr(1)));
  }
  /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
  @Override
  public AstNode visitParenExpr(MxParser.ParenExprContext ctx) {
    return (ExprNode) visit(ctx.expr());
  }
  /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
  @Override
  public AstNode visitSingle(MxParser.SingleContext ctx) {//基础类型？自定义
    return ctx.ID() == null ? new BasicexprNode(new Position(ctx), ctx.getText()) : new RecurexprNode(new Position(ctx), ctx.getText());
  }
  /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
  @Override
  public AstNode visitExprList(MxParser.ExprListContext ctx) {
    ExprlistNode exprList = new ExprlistNode(new Position(ctx));
    List<ExprContext> list=ctx.expr();
    for(var ele:list){
      exprList.exprs.add((ExprNode) visit(ele));
    }
    // ctx.expr().forEach(expr -> exprList.exprs.add((ExprNode) visit(expr)));
    return exprList;
  }
}