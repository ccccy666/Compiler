package assembly;

import assembly.inst.*;
import assembly.operand.*;

import java.util.HashSet;
import java.util.LinkedList;

public class ASMBlock {///////////
  public String name; // do not print when null
  public int loopDepth = 0;
  public LinkedList<Inst> insts = new LinkedList<Inst>();
  public LinkedList<Inst> phiConvert = new LinkedList<Inst>();
  public LinkedList<Inst> jumpOrBr = new LinkedList<Inst>();
  public LinkedList<ASMBlock> succ = new LinkedList<ASMBlock>(), pred = new LinkedList<ASMBlock>();
  public HashSet<Reg> liveIn = new HashSet<Reg>(), liveOut = new HashSet<Reg>();
  public HashSet<Reg> use = new HashSet<Reg>(), def = new HashSet<Reg>();

  public ASMBlock(String name, int loopDepth) {
    this.name = name;
    this.loopDepth = loopDepth;
  }

  public void addInst(Inst inst) {
    
      insts.add(inst);
  }

  public String toString() {
    String ret = "";
    if (name != null) ret += name + ":\n";
    for (Inst inst : insts)
      ret += "  " + inst + "\n";
    return ret;
  }
}



