package IR;

import IR.Instructions.*;
import IR.Type.*;
import IR.Type.Void;
import IR.Value.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;

public class Program  {

  public HashMap<String, Stringconst> stringConst = new HashMap<>();
  public LinkedList<Functionblock> funcList = new LinkedList<Functionblock>();
  public ArrayList<Globalvar> globalVarList = new ArrayList<Globalvar>();
  public ArrayList<Classtype> structTypeList = new ArrayList<Classtype>();

  public Functionblock mainFunc;
  
  public Functionblock initFunc = new Functionblock("init_", new Void());
  public Basicblock initBlock = new Basicblock(initFunc, "entry", 0);
  


  public Program() {
    initFunc.appendBlock(initBlock);
    initFunc.exitBlock = new Basicblock(initFunc, "return", 0);
    initBlock.terminalInst = new Jump(initBlock, initFunc.exitBlock);
    initFunc.exitBlock.terminalInst = new Ret(initFunc.exitBlock, new Voidconst());
  }

  
  @Override
  public String toString() {
    String ret = "";
    
    for (var structType : structTypeList) {
      ret += structType + " = type {";
      for (int i = 0; i < structType.memberType.size(); ++i) {
        ret += structType.memberType.get(i);
        if (i != structType.memberType.size() - 1)
          ret += ", ";
      }
      ret += "}\n";
    }
    for (var str : stringConst.values()){
      ret += "@str." + String.valueOf(str.id) + " = private unnamed_addr constant ["+ String.valueOf(str.val.length() + 1) + " x i8] c\"" + str.printStr() + "\"\n";
    }
      
    for (var globalVar : globalVarList){
      ret += globalVar + " = dso_local global " + ((Ptr) globalVar.type).pointToType() + " " + globalVar.initVal + "\n";
    }
      

    ret += "declare i1 @strlt(ptr, ptr)\n";
    ret += "declare i1 @strle(ptr, ptr)\n";
    ret += "declare i1 @strgt(ptr, ptr)\n";
    ret += "declare i1 @strge(ptr, ptr)\n";
    ret += "declare i1 @string_equal(ptr, ptr)\n";
    ret += "declare i1 @strneq(ptr, ptr)\n";
    ret += "\ndeclare dso_local ptr @malloc(i32)\n";
    ret += "declare dso_local i32 @strlen(ptr)\n";
    ret += "declare ptr @getString()\n";
    ret += "declare i32 @getInt()\n";
    ret += "declare ptr @toString(i32)\n";
    ret += "declare ptr @substring(ptr, i32, i32)\n";
    ret += "declare i32 @parseInt(ptr)\n";
    ret += "declare i32 @ord(ptr, i32)\n";
    ret += "declare ptr @stradd(ptr, ptr)\n";
    ret += "declare void @print(ptr)\n";
    ret += "declare void @println(ptr)\n";
    ret += "declare void @printInt(i32)\n";
    ret += "declare void @printlnInt(i32)\n";
    ret += "declare i32 @array_size(ptr)\n";
    ret += "declare ptr @newPtrArray(i32)\n";
    ret += "declare ptr @newClass(i32)\n\n";


    for (var func : funcList){
      ret += func + "\n";
    }
      
    return ret;
  }
  public Stringconst addStringConst(String str) {
    // transfer escape characters
    String val = "";
    for (int i = 0; i < str.length(); ++i) {
      char c = str.charAt(i);
      if (c == '\\') {
        ++i;
        switch (str.charAt(i)) {
          case 'n': 
            val += '\n'; 
            break;
          case '\"': 
            val += '\"'; 
            break;
          default: val += '\\';
        }
      } else val += c;
    }
    if (!stringConst.containsKey(val))
      stringConst.put(val, new Stringconst(val));
    return stringConst.get(val);
  }
}