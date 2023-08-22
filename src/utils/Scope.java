package utils;

import java.util.HashMap;

// import IR.Functionblock;
import IR.Functionblock;
// import IR.Value.Register;
import IR.Value.Register;
import ast.*;
import ast.stmt.*;
import utils.*;


public class Scope {
  // public HashMap<String, TypeNode> prevarMember = new HashMap<>();
  //public HashMap<String, Functionblock> IRFunc = new HashMap<>();
  public HashMap<String, midvar> varMember = new HashMap<>();
  public Scope parentScope = null;
  public ClassdefNode inclass = null;
  public circle incircle = null;
  public TypeNode returnType = null;
  public boolean circle = false, isReturned = false;
  
  public HashMap<String, Register> IRVar = new HashMap<>();
  

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
  public void addIRVar(String name, Register reg) {
    IRVar.put(name, reg);
  }

  public Register getIRVarPtr(String name) {
    if (IRVar.containsKey(name))
      return IRVar.get(name);
    else
      return parentScope != null ? parentScope.getIRVarPtr(name) : null;
  }
  
  public TypeNode getVarType(String name) {
    if (varMember.containsKey(name))
      return varMember.get(name).typ;
    else
      return parentScope != null ? parentScope.getVarType(name) : null;
  }


}