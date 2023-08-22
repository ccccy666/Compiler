package Semantic;

import ast.*;
import ast.expr.*;
import ast.stmt.*;

import utils.*;
import utils.Error;


public class SemanticChecker implements ASTvisitor,Elements {

  public Globalscope globalScope;
  public Scope currentScope;
  boolean judge(TypeNode tp1,TypeNode tp2){
    return !tp1.equals(tp2) && (!tp1.Nbasic() || !(new TypeNode("null")).equals(tp2));
  }
  public SemanticChecker(Globalscope globalScope) {
    this.globalScope = globalScope;
    currentScope = globalScope;
  }
  public void visit(ProgramNode node){
    FuncdefNode func=globalScope.funcMember.get("main");
    if(func==null||func.params!=null||func.returnType.equals(new TypeNode("void"))){
      throw new Error(node.pos,"Main function is wrong");
    }
    for(var ele:node.defList){
      ele.accept(this);
    }
  }

  // public void visit(NullNode node);
  public void visit(FuncdefNode node){
    node.returnType.accept(this);
    // System.out.print(currentScope.inclass.name);
    currentScope = new Scope(currentScope,node.returnType);//
    // System.out.print(currentScope.inclass.name);
    if(node.params!=null){
      node.params.accept(this);
    }
    for(var ele:node.stmts){
      ele.accept(this);
    }
    if(!node.name.equals("main")&&!node.returnType.equals(new TypeNode("void")) && !currentScope.isReturned){
      throw new Error(node.pos,"Return of function "+node.name+" is wrong");
      
    }//
    currentScope=currentScope.parentScope;
  };

  public void visit(TypeNode node){
    // System.out.print(node.type);
    if(!(node.type.equals("int")||node.type.equals("bool")||node.type.equals("string")||node.type.equals("null")||node.type.equals("this")||node.type.equals("void"))){
      if(!globalScope.classMember.containsKey(node.type)){
        throw new Error(node.pos,node.type+" does not exist");
      }
    }
  };

  public void visit(ParametersNode node){
    for(var ele:node.units){
      if(ele.initVal!=null){
        
        throw new Error(node.pos,"Parameters can not have default value");
      }
      ele.accept(this);
    }
  };

  public void visit(ContentstmtNode node){
    currentScope=new Scope(currentScope);
    for(var ele:node.stmts){
      ele.accept(this);
    }
    currentScope=currentScope.parentScope;
  };

  public void visit(ClassdefNode node){

    currentScope = new Scope(currentScope,node);//
    // System.out.print(currentScope.inclass.name);
    for(var ele:node.varDefList){
      ele.accept(this);
    }
    // System.out.print(currentScope.varMember.get("a").typ.type);
    if(node.classBuild!=null){
      if(!node.name.equals(node.classBuild.name)){//
        throw new Error(node.pos,"Name of Constuctor "+node.name+" is wrong");
      }else{
        node.classBuild.accept(this);
      }
    }
    for(var ele:node.funcDefList){
      ele.accept(this);
    }
    currentScope=currentScope.parentScope;
  };

  public void visit(ConstructordefNode node){
    currentScope=new Scope(currentScope,new TypeNode("void"));
    node.suite.accept(this);
    currentScope=currentScope.parentScope;
  };
  public void visit(Variable node){
    node.type.accept(this);
    //System.out.println("888888888888");
    if(node.type.equals(new TypeNode("void"))||node.type.equals(new TypeNode("null"))||node.type.equals(new TypeNode("this"))){
      throw new Error(node.pos,"Variable can not be illegal");
    }
    if(node.initVal!=null){
      node.initVal.accept(this);
      // if(node.initVal instanceof BasicexprNode)System.out.print(node.initVal.str+'\n');
      if(!node.initVal.type.equals(new TypeNode("null"))&&node.type.dim!=node.initVal.type.dim){
        throw new Error(node.pos,"Dim is wrong");
      }
    }
    
    // if(currentScope.inclass!=null){//
    //   if(currentScope.inclass.getFuncDef(node.varName)!=null){
    //     throw new Error(node.pos,"Variable can not use the name of function");
    //   }
    // }else{
    //   if(globalScope.funcMember.get(node.varName)!=null){
    //     throw new Error(node.pos,"Variable can not use the name of function");
    //   }
    // }
    //todo
    if(currentScope.varMember.containsKey(node.varName)){
      if(currentScope.varMember.get(node.varName).cover==false){
        currentScope.varMember.get(node.varName).cover=true;
        currentScope.varMember.get(node.varName).typ=node.type;
        return;
      }else{
        throw new Error(node.pos,"Redefinition of variable "+node.varName);
      }
      
    }
    midvar mi=new midvar();
    mi.typ=node.type;
    mi.cover=true;
    currentScope.varMember.put(node.varName,mi);
  };
  public void visit(VariabledefNode node){
    for(var ele:node.units){
      ele.accept(this);
    }
  };
  
  
  public void visit(ExprstmtNode node){
    if(node.expr!=null){
      node.expr.accept(this);
    }
  };

  
  public void visit(ForstmtNode node){///////////////
    
    currentScope=new Scope(currentScope,true);
    if(node.init!=null){
      node.init.accept(this);
    }
    if(node.varDef!=null){
      node.varDef.accept(this);
    }

    if(node.cond!=null){
      node.cond.accept(this);
      if(!node.cond.type.equals(new TypeNode("bool"))){
        throw new Error(node.pos,"Type of condition is wrong");
      }
    
    }
    if(node.step!=null){
      node.step.accept(this);
    }
    
    for(var ele:node.stmts){
      ele.accept(this);
    }
    currentScope=currentScope.parentScope;
  };

  public void visit(WhilestmtNode node){
    node.cond.accept(this);
    if(!node.cond.type.equals(new TypeNode("bool"))){
      throw new Error(node.pos,"Type of condition is wrong");
    }
    currentScope=new Scope(currentScope,true);
    for(var ele:node.stmts){
      ele.accept(this);
    
    }
    currentScope=currentScope.parentScope;
  };

  public void visit(BreakstmtNode node){
    if(!currentScope.circle){
      throw new Error(node.pos,"Can not use break while not in a circle");
    }
  };

  public void visit(ContinuestmtNode node){
    if(!currentScope.circle){
      throw new Error(node.pos,"Can not use continue while not in a circle");
    }
  };
  
  public void visit(ReturnstmtNode node){
    
    for (Scope theScope = currentScope; theScope != null; theScope = theScope.parentScope){
      if (theScope.returnType != null) {//
        if (node.expr != null) {
          node.expr.accept(this);
          if(node.expr.type==null){
            throw new Error(node.pos,"Illegal expression");
          }
          if(node.expr.type.equals(new TypeNode("null"))&&theScope.returnType.equals(new TypeNode("string"))){
            throw new Error(node.pos, "Type of return is wrong");
          }
          if(node.expr.type.dim!=theScope.returnType.dim){
            throw new Error(node.pos, "Type of return is wrong");
          }
          if (judge(theScope.returnType, node.expr.type)) {
            throw new Error(node.pos, "Type of return is wrong");
          }
          if(!node.expr.type.equals(new TypeNode("null"))&&!theScope.returnType.equals(node.expr.type)){
            throw new Error(node.pos, "Type of return is wrong");
          }
        } else {
          if (!theScope.returnType.equals((new TypeNode("void")))){
            throw new Error(node.pos, "Return type is wrong");
          }
            

          
          
        }
        theScope.isReturned = true;
        return;
      }
    }
      
    throw new Error(node.pos, "Not return in function");
  };

  public void visit(IfstmtNode node){
    node.cond.accept(this);
    if(!node.cond.type.equals(new TypeNode("bool"))){
      throw new Error(node.pos,"Type of condition is wrong");
    }
    currentScope=new Scope(currentScope);
    for(var ele:node.thenStmts){
      ele.accept(this);
    
    }
    currentScope=currentScope.parentScope;
    if(node.elseStmts!=null){
      currentScope=new Scope(currentScope);
    for(var ele:node.elseStmts){
      ele.accept(this);
    
    }
    currentScope=currentScope.parentScope;
    }
  };

  

  public void visit(BasicexprNode node){//int,bool,string,null,this
    // System.out.print(node.str);
    if (node.str.equals("int")) {
      node.type = (new TypeNode("int"));
    }else if(node.str.matches("[1-9][0-9]*")||node.str.matches("0")){
      node.type = (new TypeNode("int"));
    }else if (node.str.matches("\".*\"")) {
      node.type = (new TypeNode("string"));

    } else if (node.str.equals("null")) {
      node.type = (new TypeNode("null"));
    } else if (node.str.equals("true") || node.str.equals("false")) {
      node.type = (new TypeNode("bool"));
    }else {
      
      if (currentScope.inclass != null){
        node.type = new TypeNode(currentScope.inclass.name);
      }
      else throw new Error(node.pos, "Only can use this in class");
      
    }
  };
  public void visit(BinaryexprNode node){
    node.lhs.accept(this);
    node.rhs.accept(this);
    if (node.lhs.type == null || node.rhs.type == null){//
      throw new Error(node.pos, "Illegal expression");
    }
    //
    if ((new TypeNode("void")).equals(node.lhs.type) || (new TypeNode("void")).equals(node.rhs.type)){
      throw new Error(node.pos, "Illegal expression");
    }
      
    if ((new TypeNode("null")).equals(node.lhs.type) || (new TypeNode("null")).equals(node.rhs.type)) {
      
      if ((node.op.equals("==") || node.op.equals("!="))&& (node.lhs.type.Nbasic() || node.rhs.type.Nbasic())) {
        node.type = (new TypeNode("bool"));return;
      } else if (!node.lhs.type.equals(node.rhs.type)) {
        throw new Error(node.pos, "Illegal expression");
      }
    }
    
    
    if (!node.lhs.type.equals(node.rhs.type))
      throw new Error(node.pos, "Type is different");
    switch (node.op) {
      case "+":
        if (!node.lhs.type.equals((new TypeNode("int"))) && !node.lhs.type.equals((new TypeNode("string"))))
          throw new Error(node.pos, "Type is wrong");
        node.type=node.lhs.type;
        break;
      case "-":
      case "*":
      case "/":
      case "%":
      case ">>":
      case "<<":
      case "&":
      case "^":
      case "|":
        if (!node.lhs.type.equals((new TypeNode("int"))))
          throw new Error(node.pos, "Type is wrong");
        node.type = (new TypeNode("int"));
        break;
      case "&&":
      case "||":
        if (!node.lhs.type.equals((new TypeNode("bool"))))
          throw new Error(node.pos, "Type is wrong");
        node.type = (new TypeNode("bool"));
        break;
      
      case "<=":
      case ">=":
      case "<":
      case ">":
        if (!node.lhs.type.equals((new TypeNode("int"))) && !node.lhs.type.equals((new TypeNode("string"))))
          throw new Error(node.pos, "Type is wrong");
        node.type = new TypeNode("bool");
        break;
      default:
        node.type = (new TypeNode("bool"));
    }
  };
  public void visit(TernaryexprNode node){
    node.lhs.accept(this);
    node.mhs.accept(this);
    node.rhs.accept(this);//System.out.print("666666666");
    if (node.lhs.type == null ||node.mhs.type==null ||node.rhs.type == null){
      throw new Error(node.pos, "Illegal expression");
    }
    if(!node.lhs.type.equals(new TypeNode("bool"))  ){
      throw new Error(node.pos,"Type is wrong");

    }
    
    if(!node.mhs.type.equals(node.rhs.type)) {
    
      if(((!node.rhs.type.Nbasic()||!node.mhs.type.equals(new TypeNode("null")))&&(!node.mhs.type.Nbasic()||!node.rhs.type.equals(new TypeNode("null"))))){
        throw new Error(node.pos,"Type is wrong");
      }
      
    }
    if(node.mhs.type.equals(new TypeNode("null"))){
      node.type=node.rhs.type;
    }else node.type=node.mhs.type;
    

  };
  public void visit(RecurexprNode node){
    // System.out.println("55555555555");
    
    midvar mi=currentScope.varMember.get(node.str);
    if(mi==null){
      node.type=null;

    }
    else node.type=mi.typ;
     
    if (currentScope.inclass != null && currentScope.inclass.funcMember.get(node.str) != null)
      node.funcDef = currentScope.inclass.funcMember.get(node.str);
    else
      node.funcDef = globalScope.funcMember.get(node.str);
    // System.out.print(node.str+'\n');
  };
  
  public void visit(SufexprNode node){
    node.expr.accept(this);
    if (node.expr.type == null)
      throw new Error(node.pos, "Illegal expression");
    if (!node.expr.type.equals((new TypeNode("int"))) ||!node.expr.isLeftValue() )
      throw new Error(node.pos, "Type of value is wrong");
    node.type = (new TypeNode("int"));
  };
  public void visit(PreexprNode node){
    node.expr.accept(this);
    if (node.expr.type == null)
      throw new Error(node.pos, "Illegal expression");
    if (node.op.equals("!")) {
      if (!node.expr.type.equals((new TypeNode("bool"))))
        throw new Error(node.pos, "Type should be bool");
      node.type = (new TypeNode("bool"));
    } else if (node.op.equals("++") || node.op.equals("--")) {
      if (!node.expr.type.equals((new TypeNode("int"))) ||!node.expr.isLeftValue() )
        throw new Error(node.pos, "Type of value is wrong");
      node.type = (new TypeNode("int"));
    } else {//
      if (!node.expr.type.equals((new TypeNode("int"))))
        throw new Error(node.pos, "Type should be int");
      node.type = (new TypeNode("int"));
    }
  };
  public void visit(AssignexprNode node){
    
    node.lhs.accept(this);
    node.rhs.accept(this);
    
    if(node.lhs instanceof FuncexprNode){
      throw new Error(node.pos, "Should be left value");
    }

    if (node.lhs.type == null || node.rhs.type == null){
      // System.out.print(node.lhs.type.type);
    // System.out.print("nmsl");
      throw new Error(node.pos, "Illegal expression");
    }
      
    if ((new TypeNode("void")).equals(node.lhs.type) || (new TypeNode("void")).equals(node.rhs.type)){

      throw new Error(node.pos, "Illegal expression");
    }
    
    if(node.lhs.str!=null&&node.lhs.str.equals("this")){

      throw new Error(node.pos, "Illegal expression");
    } 
    // System.out.println("sssssssssssss"+'\n');
    if (judge(node.lhs.type,node.rhs.type)){
      throw new Error(node.pos, "Type is wrong");
    }//
    // System.out.print(node.lhs.type.dim);
    // System.out.print(node.rhs.type.dim);
    if(!node.rhs.type.equals(new TypeNode("null"))&&node.lhs.type.dim!=node.rhs.type.dim){
      throw new Error(node.pos, "Dim is wrong");
    }
    if (!node.lhs.isLeftValue()){
      //System.out.print(node.lhs.str);
      throw new Error(node.pos, "Should be left value");
    }
      
    node.type = node.lhs.type;
    
  };

  public void visit(IndexexprNode node){/////////////
    node.array.accept(this);
    node.index.accept(this);
    ExprNode ex1=node.array,ex2=node.index;
    if (ex1.type == null || ex2.type == null || !ex2.type.equals(new TypeNode("int"))){
      throw new Error(node.pos, "Illegal expression");
    }
    // System.out.print(ex1.str );
    // System.out.print(ex1.type.dim );
    // System.out.print(node.indexlist.size());
    // System.out.print('\n'); 
    // if(node.array.type == null ){
    //   // System.out.print("666");
    //   throw new Error(node.pos, "Illegal expression");
    // }
    // for(int i=0;i<node.indexlist.size();i++){
    //   ExprNode ex2=node.indexlist.get(i);
    //   ex2.accept(this);
    //   if ( ex2.type == null || !ex2.type.equals((new TypeNode("int")))){
     
        
    //   }
    // }
    
    // node.
     
  
    
    
     
    node.type = new TypeNode(null,ex1.type.type, ex1.type.dim-1);
    if (node.type.dim < 0){
      
      throw new Error(node.pos, "Illegal expression");
    }
     
  };

  public void visit(FuncexprNode node){
    node.funcName.accept(this);
    if (node.funcName.funcDef == null)
      throw new Error(node.pos, "Function " + node.funcName.str + " does not exist");
    FuncdefNode func = node.funcName.funcDef;
    if (node.args != null) {
      node.args.accept(this);
      int num=node.args.exprs.size();
      if (func.params == null || func.params.units.size() != node.args.exprs.size())
        throw new Error(node.pos, "Number of Parameters is wrong");
      for (int i = 0; i < num; i++) {
        ExprNode arg = node.args.exprs.get(i);
        Variable param = func.params.units.get(i);
        
        if (judge(param.type,arg.type))
          throw new Error(node.pos, "Parameter type is wrong");
      }
    } else if (func.params != null){
      
        throw new Error(node.pos, "Number of Parameters is wrong");
    }
    node.type = func.returnType;
  };
  
  public void visit(NewexprNode node){
    // node.
    TypeNode tmp=new TypeNode(node.pos, node.typeName);
    tmp.accept(this);
    for (var ele : node.sizeList) {
      ele.accept(this);
      if (ele.type == null || !ele.type.equals((new TypeNode("int")))){
        throw new Error(node.pos, "Illegal expression");
      }
        
    }
    
    node.type = new TypeNode(null,node.typeName, node.dim);
  };

  public void visit(ExprlistNode node){
    for(var ele:node.exprs){
      ele.accept(this);
    }
  };

  public void visit(MemberexprNode node){
    // node.
    node.obj.accept(this);
    if (node.obj.type == null)
      throw new Error(node.pos, "Illegal expression");
    if (!node.obj.type.Nbasic() && !"this".equals(node.obj.str) && !(new TypeNode("string")).equals(node.obj.type)){
      throw new Error(node.pos, "Type is error");
    }
    ClassdefNode classDef = "this".equals(node.obj.str)? currentScope.inclass: globalScope.classMember.get(node.obj.type.type);

    if (node.obj.type.dim==0 ) {

      if (classDef == null){
        throw new Error(node.pos, "Class " + node.obj.type.type + " does not exist");
      }
      node.funcDef = classDef.funcMember.get(node.member);
      node.type = classDef.getVarType(node.member);
      
    } else {
      if (classDef == null){
        throw new Error(node.pos, "Type is wrong");
      }
      else if (node.member.equals("size")){
        node.funcDef = ArraySizeFunc;
      }
      
    }
  };  
  
  
  
}





  