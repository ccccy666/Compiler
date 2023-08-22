package utils;

import java.util.HashMap;

import ast.*;
import utils.Elements;

public class Globalscope extends Scope  implements Elements{
  public HashMap<String, ClassdefNode> classMember = new HashMap<>();
  public HashMap<String, FuncdefNode> funcMember = new HashMap<>();
  

  public Globalscope() {
    
    ClassdefNode stringDef = new ClassdefNode(null, "string");
    
    stringDef.funcMember.put("length", StringLengthFunc);
    stringDef.funcMember.put("substring", StringSubStringFunc);
    stringDef.funcMember.put("parseInt", StringParseIntFunc);
    stringDef.funcMember.put("ord", StringOrdFunc);
    // stringDef.funcMember.put("size", new FuncdefNode(null, new TypeNode("int"), "size", null, 0));
    classMember.put("string", stringDef);
    classMember.put("int", new ClassdefNode(null, "int"));
    classMember.put("bool", new ClassdefNode(null, "bool"));
    
    funcMember.put("print", PrintFunc);
    funcMember.put("println", PrintlnFunc);
    funcMember.put("printInt", PrintIntFunc);
    funcMember.put("printlnInt", PrintlnIntFunc);
    funcMember.put("getString", GetStringFunc);
    funcMember.put("getInt", GetIntFunc);
    funcMember.put("toString", ToStringFunc);
  }

  
}