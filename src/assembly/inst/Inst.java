package assembly.inst;

import assembly.operand.*;
import java.util.HashSet;

public abstract class Inst {////////////
  public Imm imm;
  public Reg rd, rs1, rs2;

  public HashSet<Reg> liveIn = new HashSet<Reg>(), liveOut = new HashSet<Reg>();
 
  
  public HashSet<Reg> Def() {
    HashSet<Reg> ret = new HashSet<Reg>();
    if (rd != null) ret.add(rd);
    return ret;
  }
  public HashSet<Reg> Use() {
    HashSet<Reg> ret = new HashSet<Reg>();
    if (rs1 != null) ret.add(rs1);
    if (rs2 != null) ret.add(rs2);
    return ret;
  }
   public abstract String toString();
}

