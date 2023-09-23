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
  
  //use in ir_opt

  //cfg
  public LinkedList<Basicblock> preds = new LinkedList<>(), succs = new LinkedList<>();
//dom tree
  public Basicblock idom = null;
  //支配的节点
  public LinkedList<Basicblock> domChildren = new LinkedList<>();
  //支配边界
  public LinkedList<Basicblock> domFrontier = new LinkedList<>();

  public LinkedList<Phi> phiInsts = new LinkedList<>();
  
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
    if (inst instanceof Phi ) {
      Phi phiInst=(Phi) inst;
      
      for (Phi enumInst : phiInsts)
        if (phiInst.src == enumInst.src)
          return;
      phiInsts.add((Phi) inst);
      return;
    }
    
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