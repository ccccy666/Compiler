package assembly;
import java.util.ArrayList;
import assembly.operand.*;


public class ASMFunction {//
  public String name;
  public int totalStack = 0;
  public int allocaUsed = 4;
  public int paramUsed = 0;
  public ASMBlock entryBlock, exitBlock;
  public int virtualRegCnt = 0, spillUsed = 0;
  public ArrayList<ASMBlock> blocks = new ArrayList<ASMBlock>();
  public ArrayList<Reg> params = new ArrayList<Reg>();
  

 public String toString() {
    String ret = "  .text\n" + "  .globl " + name + "\n";
    ret += name + ":\n";
    for (ASMBlock block : blocks)
      ret += block;
    return ret;
  }  

  public ASMFunction(String name) {
    this.name = name;
  }

  public void addBlock(ASMBlock block) {
    blocks.add(block);
  }

  
}
