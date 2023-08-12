package IR;

// import java.lang.ProcessBuilder.Redirect.Type;
// import java.util.ArrayList;
import java.util.LinkedList;

import IR.*;
import IR.Type.*;
import IR.Instruction.*;
import IR.Value.*;
import utils.*;

public class Basicblock extends Base{
  public String name;
  public LinkedList<Ins> insts = new LinkedList<Ins>();
//   public IRTerminalInst terminalInst = null;
  public Functionblock parentFunction = null;
  public int loopDepth = 0;
  public boolean isFinished = false;

  public LinkedList<Basicblock> preds = new LinkedList<>(), succs = new LinkedList<>();
  public Basicblock idom = null;
  public LinkedList<Basicblock> domChildren = new LinkedList<>();
  public LinkedList<Basicblock> domFrontier = new LinkedList<>();

//   public LinkedList<IRPhiInst> phiInsts = new LinkedList<>();

  public static int blockCnt = 0;

  public Basicblock(Functionblock function, String name, int loopDepth) {
    super(name + String.valueOf(blockCnt++));
    this.parentFunction = function;
    // this.name = 
    this.loopDepth = loopDepth;
  }
  public Basicblock(Functionblock function, String name, Basicblock toBlock, int loopDepth) {
    this(function, name, loopDepth);
    // this.terminalInst = new IRJumpInst(this, toBlock);
  }
  
  public void addInst(Ins inst) {
    // if (inst instanceof IRPhiInst phiInst) {
    //   for (IRPhiInst enumInst : phiInsts)
    //     if (phiInst.src == enumInst.src)
    //       return;
    //   phiInsts.add((IRPhiInst) inst);
    // } else {
    //   if (isFinished) return;
    //   if (inst instanceof IRAllocaInst)
    //     parentFunction.allocaInsts.add((IRAllocaInst) inst);
    //   else if (inst instanceof IRTerminalInst)
    //     terminalInst = (IRTerminalInst) inst;
    //   else
    //     insts.add(inst);
    // }
  }
  @Override
  public int getsize(){
    return -1;
  }
  @Override
  public String toString() {
    String ret = name + ":\n";
    for (Ins inst : insts)
      ret += "  " + inst + "\n";
    // if (terminalInst != null)
    //   ret += "  " + terminalInst + "\n";
    return ret;
  }
//   @Override
  public void accept(IRvisitor visitor) {
    visitor.visit(this);
  }
}
