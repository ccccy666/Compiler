package assembly;
import java.util.HashSet;
import java.util.LinkedList;

import assembly.inst.*;
import assembly.operand.*;



public class ASMBlock {///////////
  public String name; 
  public int loopDepth = 0;
  public LinkedList<ASMBlock> succ = new LinkedList<ASMBlock>(), pred = new LinkedList<ASMBlock>();
  public LinkedList<Inst> insts = new LinkedList<Inst>();
  public LinkedList<Inst> phis = new LinkedList<Inst>();
  public LinkedList<Inst> jumpOrBr = new LinkedList<Inst>();

  //use in color algorithm
  public HashSet<Reg> use = new HashSet<Reg>(), def = new HashSet<Reg>();
  public HashSet<Reg> liveIn = new HashSet<Reg>(), liveOut = new HashSet<Reg>();
  

  public ASMBlock(String name, int loopDepth) {
    this.name = name;
    this.loopDepth = loopDepth;
  }
  public String toString() {
    String ret = "";
    if (name != null) ret += name + ":\n";
    for (Inst inst : insts)
      ret += "  " + inst + "\n";
    return ret;
  }
  public void addInst(Inst inst) {
    if (inst instanceof JumpInst || inst instanceof BeqzInst || inst instanceof BrCmpInst)
      jumpOrBr.add(inst);
    else
      insts.add(inst);
  }

  
}



