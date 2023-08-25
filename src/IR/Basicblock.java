package IR;

import java.util.LinkedList;

import IR.Instructions.*;

public class Basicblock {
  public static int blockCnt = 0;
  public String name;
  public TerminalInst terminalInst = null;
  public Functionblock parentFunction = null;
  public int loopDepth = 0;
  public boolean isFinished = false;
  public LinkedList<Ins> insts = new LinkedList<Ins>();
  
  
  public Basicblock(Functionblock function, String name, Basicblock toBlock, int loopDepth) {
    this.parentFunction = function;
    this.name = name + String.valueOf(blockCnt++);
    this.loopDepth = loopDepth;
    this.terminalInst = new Jump(this, toBlock);
  }
  public Basicblock(Functionblock function, String name, int loopDepth) {
    this.parentFunction = function;
    this.name = name + String.valueOf(blockCnt++);
    this.loopDepth = loopDepth;
  }
  

  public String toString() {
    String ret = name + ":\n";
    for (Ins inst : insts)
      ret += "  " + inst + "\n";
    if (terminalInst != null)
      ret += "  " + terminalInst + "\n";
    return ret;
  }
  public void addInst(Ins inst) {
    
      if (isFinished) return;
      if (inst instanceof Alloca)
        parentFunction.allocaInsts.add((Alloca) inst);
      else if (inst instanceof TerminalInst)
        terminalInst = (TerminalInst) inst;
      else
        insts.add(inst);
    
  }
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }

}