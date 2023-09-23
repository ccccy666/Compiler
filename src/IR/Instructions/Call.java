package IR.Instructions;

import IR.*;
import IR.Type.*;
import IR.Value.*;


import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Call extends Ins {
  
  public Register call;
  public Basetype returnType;
  public ArrayList<Valu> args = new ArrayList<Valu>();
  public String funcName;
  
  public Call(Basicblock block, Register call, Basetype returnType, String funcName, Valu... args) {
    super(block);
    this.returnType = returnType;
    this.call = call;
    this.funcName = funcName;
    for (var arg : args){
      this.args.add(arg);
    }
      
  }
  public Call(Basicblock block, Basetype returnType, String funcName) {
    super(block);
    this.returnType = returnType;
    this.funcName = funcName;
  }
  

  @Override
  public String toString() {
    
    String ret = (call != null ? call + " = call " : "call ") + returnType + " @" + funcName + "(";
    for (int i = 0; i < args.size(); ++i) {
      ret += args.get(i).toStringWithType();
      if (i != args.size() - 1){
        ret += ", ";
      } 
    }
    ret += ")";
    return ret;
  }

  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
  @Override
  public LinkedHashSet<Valu> getUse() {
    LinkedHashSet<Valu> ret = new LinkedHashSet<>();
    for (Valu arg : args)
      ret.add(arg);
    return ret;
  }

  @Override
  public Register getDef() {
    return call;
  }

  @Override
  public void replaceUse(Valu old, Valu newOne) {
    for (int i = 0; i < args.size(); ++i)
      args.set(i, args.get(i) == old ? newOne : args.get(i));
  }
}