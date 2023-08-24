package assembly.inst;

import assembly.operand.*;

public class LuiInst extends Inst {
  public LuiInst(Reg dest, Imm imm) {
    this.rd = dest;
    this.imm = imm;
  }

  @Override
  public String toString() {
    return "lui " + rd + ", " + imm;
  }
}