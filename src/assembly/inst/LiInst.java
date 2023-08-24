package assembly.inst;

import assembly.operand.*;

public class LiInst extends Inst {
  public VirtualImm pseudoImm;

  public LiInst(Reg rd, VirtualImm imm) {
    this.rd = rd;
    this.pseudoImm = imm;
  }

  @Override
  public String toString() {
    return "li " + rd + ", " + pseudoImm;
  }
}