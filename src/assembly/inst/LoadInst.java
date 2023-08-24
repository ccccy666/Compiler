package assembly.inst;

import assembly.operand.*;

public class LoadInst extends Inst {
  int size;

  public LoadInst(int size, Reg rd, Reg rs1, Imm imm) {
    this.size = size;
    this.rd = rd;
    this.rs1 = rs1;
    this.imm = imm;
  }
  public LoadInst(int size, Reg rd, Reg rs1) {
    this(size, rd, rs1, new Imm(0));
  }

  @Override
  public String toString() {
    return "l" + (size == 4 ? "w" : "b") + " " + rd + ", " + imm + "(" + rs1 + ")";
  }
}