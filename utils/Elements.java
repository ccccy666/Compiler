package utils;

import ast.*;
// import IR.entity.*;
// import IR.type.*;

public interface Elements {
  // Type new TypeNode("void") = new Type("void");
  // Type new TypeNode("int") = new Type("int");
  // Type BoolType = new Type("bool");
  // Type new TypeNode("string") = new Type("string");
  // Type NullType = new Type("null");
  // Type ThisType = new Type("this");
  // Type AutoType = new Type("auto");

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

  // ------------------ IR Builtin Types ------------------

//   IRType irVoidType = new IRVoidType();
//   IRType irIntType = new IRIntType(32);
//   IRType irIntPtrType = new IRPtrType(irIntType);
//   IRType irNullType = new IRPtrType(irVoidType);
//   IRType irBoolType = new IRIntType(8), irCharType = irBoolType;
//   IRType irCondType = new IRIntType(1);
//   IRType irStringType = new IRPtrType(irCharType);

//   // ------------------ IR Builtin Constants ------------------

//   IRVoidConst irVoidConst = new IRVoidConst();
//   IRCondConst irTrueConst = new IRCondConst(true);
//   IRCondConst irFalseConst = new IRCondConst(false);
//   IRBoolConst irBoolTrueConst = new IRBoolConst(true);
//   IRBoolConst irBoolFalseConst = new IRBoolConst(false);
//   IRIntConst irIntConst0 = new IRIntConst(0);
//   IRIntConst irIntConst1 = new IRIntConst(1);
//   IRIntConst irIntConstn1 = new IRIntConst(-1);
//   IRIntConst irIntConst4 = new IRIntConst(4);
}