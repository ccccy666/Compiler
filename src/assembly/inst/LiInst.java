package assembly.inst;

import assembly.operand.*;

public class LiInst extends Inst {
  public VirtualImm Imm;

  public LiInst(Reg rd, VirtualImm imm) {
    this.rd = rd;
    this.Imm = imm;
  }

  @Override
  public String toString() {
    return "li " + rd + ", " + Imm;
  }
}