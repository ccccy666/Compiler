package assembly.inst;

import assembly.operand.*;

public class StoreInst extends Inst {
  int size;

  public StoreInst(int size, Reg rs1, Reg rs2, Imm imm) {
    this.size = size;
    this.rs1 = rs1;
    this.rs2 = rs2;
    this.imm = imm;
  }
  public StoreInst(int size, Reg rs1, Reg rs2) {
    this(size, rs1, rs2, new Imm(0));
  }

  @Override
  public String toString() {
    return "s" + (size == 4 ? "w" : "b") + " " + rs2 + ", " + imm + "(" + rs1 + ")";
  }
}