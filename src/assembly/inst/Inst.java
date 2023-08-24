package assembly.inst;

import assembly.operand.*;
import java.util.HashSet;

public abstract class Inst {////////////
  public Reg rd, rs1, rs2;
  public Imm imm;

  public abstract String toString();
  
}

