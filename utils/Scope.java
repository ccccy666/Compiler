package utils;

import java.util.HashMap;

// import IR.*;
// import IR.entity.*;
import ast.*;
import ast.stmt.*;
import utils.*;


public class Scope {
  // public HashMap<String, TypeNode> prevarMember = new HashMap<>();//变量
  public HashMap<String, midvar> varMember = new HashMap<>();//变量
  public Scope parentScope = null;
  public ClassdefNode inclass = null;//在类中
  public circle incircle = null;
  public TypeNode returnType = null;//函数返回类型 
  public boolean circle = false, isReturned = false;
  
//   public HashMap<String, IRRegister> IRVarMember = new HashMap<>();
//   public HashMap<String, IRFunction> IRFuncMember = new HashMap<>();
  // public HashMap<String, IRRegister> IRStringConst = new HashMap<>();

  public Scope() {}
  public Scope(Scope parentScope) {
    this.parentScope = parentScope;
    this.circle = parentScope.circle;
    this.incircle = parentScope.incircle;
    this.inclass = parentScope.inclass;
    for(String name:parentScope.varMember.keySet()){
      midvar mid=new midvar();
      mid.typ=parentScope.varMember.get(name).typ;
      varMember.put(name, mid);
    }
    // prevarMember=parentScope.varMember;
  }
  public Scope(Scope parentScope, ClassdefNode inclass) {
    this(parentScope);
    this.inclass = inclass;
  }
  
  public Scope(Scope parentScope, circle incircle) {
    this(parentScope);
    this.circle = true;
    this.incircle = incircle;
  }
  public Scope(Scope parentScope, TypeNode returnType) {
    this(parentScope);
    this.returnType = returnType;
    // this.inclass = parentScope.inclass;
  }
  public Scope(Scope parentScope, boolean isLoopScope) {
    this(parentScope);
    this.circle = isLoopScope;
  }

  // public void addVar(String name, TypeNode type) {
  //   varMember.put(name, type);
  // }
  // public boolean hasVarInThisScope(String name) {
  //   return varMember.containsKey(name);
  // }
  public TypeNode getVarType(String name) {
    if (varMember.containsKey(name))
      return varMember.get(name).typ;
    else
      return parentScope != null ? parentScope.getVarType(name) : null;
  }


}