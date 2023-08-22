package IR;

import IR.Instructions.*;
import IR.Type.*;
import IR.Value.*;

import java.util.LinkedList;
import java.util.ArrayList;


public class Functionblock {
  public String name;
  public Basetype returnType;
  public Register retAddr;
  public Basicblock entryBlock, exitBlock;
  
  public ArrayList<Alloca> allocaInsts = new ArrayList<Alloca>();
  public ArrayList<Register> params = new ArrayList<Register>();
  public LinkedList<Basicblock> blocks = new LinkedList<Basicblock>();
  


  public Functionblock(String name, Basetype returnType) {
    this.name = name;
    this.returnType = returnType;
  }

  public Basicblock appendBlock(Basicblock block) {
    blocks.add(block);
    return block;
  }

  public String toString() {
    String ret = "define " + returnType.toString() + " @" + name + "(";
    Register.regCnt = 0;
    for (int i = 0; i < params.size(); ++i) {
      ret += params.get(i).toStringWithType();
      if (i != params.size() - 1) ret += ", ";
    }
    ret += ") {\n";
    for (Basicblock block : blocks)
      ret += block.toString();
    ret += "}\n";
    return ret;
  }
  public void finish() {
    entryBlock = blocks.getFirst();
    
    for (int i = allocaInsts.size() - 1; i >= 0; --i)
      entryBlock.insts.addFirst(allocaInsts.get(i));
    blocks.add(exitBlock);
  }
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }
}