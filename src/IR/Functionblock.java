package IR;

import IR.Value.*;
import IR.Type.*;
import IR.Instruction.*;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Functionblock extends Base{
  public String name;
  public Base returnType;
//   public ArrayList<IRRegister> params = new ArrayList<IRRegister>();
  public LinkedList<Basicblock> blocks = new LinkedList<Basicblock>();
//   public ArrayList<IRAllocaInst> allocaInsts = new ArrayList<IRAllocaInst>();

  public Basicblock entryBlock, exitBlock;
//   public IRRegister retAddr;

//   public HashMap<IRRegister, HashSet<IRInst>> useList = new HashMap<>();

  public Functionblock(String name, Base returnType) {
    super(name);
    this.name = name;
    this.returnType = returnType;
  }

  public Basicblock appendBlock(Basicblock block) {
    blocks.add(block);
    return block;
  }

  public void finish() {
    entryBlock = blocks.getFirst();
    // for (int i = allocaInsts.size() - 1; i >= 0; --i)
    //   entryBlock.insts.addFirst(allocaInsts.get(i));
    blocks.add(exitBlock);
  }
  @Override
  public int getsize(){
    return 0;
  }
  @Override
  public String toString() {
    String ret = "define " + returnType.toString() + " @" + name + "(";
    // IRRegister.regCnt = 0;
    // for (int i = 0; i < params.size(); ++i) {
    //   ret += params.get(i).toStringWithType();
    //   if (i != params.size() - 1) ret += ", ";
    // }
    ret += ") {\n";
    for (Basicblock block : blocks)
      ret += block.toString();
    ret += "}\n";
    return ret;
  }

  public void accept(IRvisitor visitor) {
    visitor.visit(this);
  }
}