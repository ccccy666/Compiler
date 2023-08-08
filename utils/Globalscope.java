package utils;

import java.util.HashMap;

import ast.*;

public class Globalscope extends Scope  {
  public HashMap<String, ClassdefNode> classMember = new HashMap<>();//类
  public HashMap<String, FuncdefNode> funcMember = new HashMap<>();//函数
  

  public Globalscope() {
    // 内置函数
    funcMember.put("print", new FuncdefNode(null, new TypeNode("void"), "print", new TypeNode("string"), 1));
    funcMember.put("println", new FuncdefNode(null, new TypeNode("void"), "println", new TypeNode("string"), 1));
    funcMember.put("printInt", new FuncdefNode(null, new TypeNode("void"), "printInt", new TypeNode("int"), 1));
    funcMember.put("printlnInt", new FuncdefNode(null, new TypeNode("void"), "printlnInt", new TypeNode("int"), 1));
    funcMember.put("getString", new FuncdefNode(null, new TypeNode("string"), "getString", null, 0));
    funcMember.put("getInt", new FuncdefNode(null, new TypeNode("int"), "getInt", null, 0));
    funcMember.put("toString", new FuncdefNode(null, new TypeNode("string"), "toString", new TypeNode("int"), 1));
    //内置类型
    ClassdefNode stringDef = new ClassdefNode(null, "string");
    //string的内置函数
    stringDef.funcMember.put("length", new FuncdefNode(null, new TypeNode("int"), "length", "string", null, 0));
    stringDef.funcMember.put("substring", new FuncdefNode(null, new TypeNode("string"), "substring", "string", new TypeNode("int"), 2));
    stringDef.funcMember.put("parseInt", new FuncdefNode(null, new TypeNode("int"), "parseInt", "string", null, 0));
    stringDef.funcMember.put("ord", new FuncdefNode(null, new TypeNode("int"), "ord", "string", new TypeNode("int"), 1));
    // stringDef.funcMember.put("size", new FuncdefNode(null, new TypeNode("int"), "size", null, 0));
    classMember.put("string", stringDef);
    classMember.put("int", new ClassdefNode(null, "int"));
    classMember.put("bool", new ClassdefNode(null, "bool"));
    
  }

  // public void addFunc(String name, FuncdefNode funcDef) {
  //   funcMember.put(name, funcDef);
  // }
  // public FuncdefNode getFuncDef(String name) {
  //   return funcMember.get(name);
  // }

  // public void addClass(String name, ClassdefNode classDef) {
  //   classMember.put(name, classDef);
  // }
  // public ClassdefNode getClassDef(String name) {
  //   return classMember.get(name);
  // }
}