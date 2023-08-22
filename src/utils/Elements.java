package utils;

// import IR.entity.Boolconst;
// import IR.entity.Cond;
// import IR.entity.Intconst;
// import IR.entity.IRNullConst;
import IR.Value.*;
import IR.Type.*;
import IR.Type.Void;
import ast.*;


public interface Elements {
  

  FuncdefNode PrintFunc = new FuncdefNode(null, new TypeNode("void"), "print", new TypeNode("string"), 1);
  FuncdefNode PrintlnFunc = new FuncdefNode(null, new TypeNode("void"), "println", new TypeNode("string"), 1);
  FuncdefNode PrintIntFunc = new FuncdefNode(null, new TypeNode("void"), "printInt", new TypeNode("int"), 1);
  FuncdefNode PrintlnIntFunc = new FuncdefNode(null, new TypeNode("void"), "printlnInt", new TypeNode("int"), 1);
  FuncdefNode GetStringFunc = new FuncdefNode(null, new TypeNode("string"), "getString", null, 0);
  FuncdefNode GetIntFunc = new FuncdefNode(null, new TypeNode("int"), "getInt", null, 0);
  FuncdefNode ToStringFunc = new FuncdefNode(null, new TypeNode("string"), "toString", new TypeNode("int"), 1);

  FuncdefNode StringLengthFunc = new FuncdefNode(null, new TypeNode("int"), "length", "string", null, 0);
  FuncdefNode StringSubStringFunc = new FuncdefNode(null, new TypeNode("string"), "substring", "string", new TypeNode("int"), 2);
  FuncdefNode StringParseIntFunc = new FuncdefNode(null, new TypeNode("int"), "parseInt", "string", null, 0);
  FuncdefNode StringOrdFunc = new FuncdefNode(null, new TypeNode("int"), "ord", "string", new TypeNode("int"), 1);

  FuncdefNode ArraySizeFunc = new FuncdefNode(null, new TypeNode("int"), "size", null, 0);
  

  Basetype irVoidType = new Void(), irIntType = new Int(32), irCharType = new Int(8), 
  irBoolType = new Int(1), irStringType = new Ptr(irCharType), irIntPtrType = new Ptr(irIntType), irNullType = new Ptr(irVoidType);
  
  Voidconst irVoidConst = new Voidconst();
  
    //= irBoolType;
  
  

  
  

  
  
}