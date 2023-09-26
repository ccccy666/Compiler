package assembly.operand;

import IR.Value.*;

public class GlobalValue extends Global {
  public int word, size;
  public GlobalValue(Globalvar var) {
    super(var.name);
    if (var.initVal instanceof Intconst) {
      word = ((Intconst) var.initVal).val;
      size = 4;
    } else if (var.initVal instanceof Boolconst) {
      word = ((Boolconst) var.initVal).val ? 1 : 0;
      size = 1;
    } else if (var.initVal instanceof Nullconst) {
      word = 0;
      size = 4;
    } 
  }

  public String toString() {
    String ret = name + ":\n";
    ret += (size == 4 ? "  .word " : "  .byte ") + word + "\n";
    return ret;
  }
}