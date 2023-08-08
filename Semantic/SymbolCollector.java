package Semantic;

import ast.*;
// import ast.expr.TernaryexprNode;
import ast.expr.*;
import ast.stmt.*;
import utils.*;
import utils.Error;

public class SymbolCollector implements ASTvisitor {//class和func的重名
  private Globalscope globalScope;

  public SymbolCollector(Globalscope globalScope) {
    this.globalScope = globalScope;
  }
  public void visit(ProgramNode node) {
    //deflist在建ast时加进了各种Node
    for(var ele:node.defList){
      ele.accept(this);
    }
    // node.defList.forEach(def -> def.accept(this));
  }

  public void visit(FuncdefNode node) {
    if (globalScope.funcMember.get(node.name) != null)
      throw new Error(node.pos, "Redefinition of function " + node.name);
    if (globalScope.classMember.get(node.name) != null)
      throw new Error(node.pos, "Function " + node.name + " is a class");
    globalScope.funcMember.put(node.name, node);
  }
  public void visit(ClassdefNode node) {
    if (globalScope.classMember.get(node.name) != null)
      throw new Error(node.pos, "Redefinition of class " + node.name );
    if (globalScope.funcMember.get(node.name) != null)
      throw new Error(node.pos, "Class " + node.name + " is a function");
    globalScope.classMember.put(node.name, node);
    for (var func : node.funcDefList) {
      if (node.funcMember.containsKey(func.name))
        throw new Error(func.pos, "Redefinition of function " + func.name );
      func.className = node.name;
      node.funcMember.put(func.name, func);
    }
    for (var var : node.varDefList)
      for (var unit : var.units) {
        if (node.varMember.containsKey(unit.varName))
          throw new Error(unit.pos, "Redefinition of variable " + unit.varName );
        node.varMember.put(unit.varName, unit);
      }
  }
  public void visit(RecurexprNode node) {

  }
  public void visit(Variable node) {

  }
  public void visit(ParametersNode node) {

  }
  public void visit(TypeNode node) {

  }
  public void visit(ConstructordefNode node) {

  }

  public void visit(StmtNode node) {

  }
  public void visit(ContentstmtNode node) {

  }
  public void visit(IfstmtNode node) {

  }
  public void visit(WhilestmtNode node) {

  }
  public void visit(ForstmtNode node) {

  }
  public void visit(ContinuestmtNode node) {

  }
  public void visit(BreakstmtNode node) {

  }
  public void visit(ReturnstmtNode node) {

  }
  public void visit(ExprstmtNode node) {

  }

  public void visit(ExprNode node) {

  }
  public void visit(BasicexprNode node) {

  }
  public void visit(VariabledefNode node) {
    
  }
  public void visit(BinaryexprNode node) {

  }
  public void visit(SufexprNode node) {

  }
  public void visit(PreexprNode node) {
    
  }
  public void visit(AssignexprNode node) {

  }
  public void visit(FuncexprNode node) {

  }
  public void visit(IndexexprNode node) {

  }
  public void visit(MemberexprNode node) {

  }
  public void visit(NewexprNode node) {


  }
  public void visit(TernaryexprNode node) {

  }
  public void visit(ExprlistNode node) {
    
  }

}
// public void visit(ProgramNode node) {
//     node.defList.forEach(def -> def.accept(this));
//   }

//   public void visit(FuncdefNode node) {
//     if (globalScope.getFuncDef(node.name) != null)
//       throw new Error(node.pos, "Function " + node.name + " is already defined");
//     if (globalScope.getClassDef(node.name) != null)
//       throw new Error(node.pos, "Function " + node.name + " is already defined as a class");
//     globalScope.addFunc(node.name, node);
//   }
//   public void visit(ClassdefNode node) {
//     if (globalScope.getClassDef(node.name) != null)
//       throw new Error(node.pos, "Class " + node.name + " is already defined");
//     if (globalScope.getFuncDef(node.name) != null)
//       throw new Error(node.pos, "Class " + node.name + " is already defined as a function");
//     globalScope.addClass(node.name, node);
//     for (var func : node.funcDefList) {
//       if (node.funcMember.containsKey(func.name))
//         throw new Error(func.pos, "Function " + func.name + " is already defined");
//       func.className = node.name;
//       node.funcMember.put(func.name, func);
//     }
//     for (var var : node.varDefList)
//       for (var unit : var.units) {
//         if (node.varMember.containsKey(unit.varName))
//           throw new Error(unit.pos, "Variable " + unit.varName + " is already defined");
//         node.varMember.put(unit.varName, unit);
//       }
//   }