package assembly.inst;

import assembly.*;

public class JumpInst extends Inst {
  public ASMBlock toBlock;

  public JumpInst(ASMBlock toBlock) {
    this.toBlock = toBlock;
  }

  @Override
  public String toString() {
    return "j " + toBlock.name;
  }
}