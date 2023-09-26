package IRopt;

import java.util.LinkedList;

import IR.*;
import IR.Instructions.*;


public class CFGBuilder {//找前驱后继，变成CFG
  Program program;

  public CFGBuilder(Program program) {
    this.program = program;
  }

  public void work() {
    for(var func:program.funcList){
      workOnFunc(func);
    }
    //program.funcList.forEach(func -> ); 
  }

  public void workOnFunc(Functionblock func) {
    for(var block:func.blocks){
      if (block.terminalInst instanceof Jump) {
        Jump jumpInst = (Jump) block.terminalInst;//jump到后继块
        block.succs.add(jumpInst.toBlock);
        jumpInst.toBlock.preds.add(block);
      } else if (block.terminalInst instanceof Br) {//Br分支
        Br branchInst = (Br) block.terminalInst;
        block.succs.add(branchInst.thenBlock);
        branchInst.thenBlock.preds.add(block);

        block.succs.add(branchInst.elseBlock);
        branchInst.elseBlock.preds.add(block);
      }
    }
    
    
    LinkedList<Basicblock> newBlocks = new LinkedList<>();//没有前驱且不是入口块的块从函数的基本块列表中移除，同时也将其后继块的前驱移除
    for (var block : func.blocks)
      if (!block.preds.isEmpty() || block == func.entryBlock){
        newBlocks.add(block);
      }
        
      else
        for (var succ : block.succs)
          succ.preds.remove(block);
    func.blocks = newBlocks;
  }
}
