package middleend;

import IR.*;
import IR.Instructions.*;
import utils.*;
import IR.Value.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class ConstPropagation implements Elements {
  Program program;

  HashMap<Register, HashSet<Ins>> useList = new HashMap<>();
  LinkedList<Ins> workList = new LinkedList<>();
  HashSet<Ins> inWorkList = new HashSet<>();
  Functionblock curFunc;

  public ConstPropagation(Program program) {
    this.program = program;
  }

  public void work() {
    for(var func:program.funcList){
      workOnFunc(func);
    }
  }

  void workOnFunc(Functionblock func) {
    useList = func.useList;
    workList.clear();
    curFunc = func;
    for (var block : func.blocks) {
      for (var inst : block.insts)
        if (canBeReplaced(inst) != null) {
          workList.add(inst);
          inWorkList.add(inst);
        }
      workList.add(block.terminalInst);
      inWorkList.add(block.terminalInst);
    }

    while (!workList.isEmpty()) {
      Ins inst = workList.removeFirst();
      inWorkList.remove(inst);
      if (inst.isDeleted)
        continue;
      Valu c = canBeReplaced(inst), def = inst.getDef();
      if (c != null) {
        inst.isDeleted = true;
        for (var use : useList.get(def)) {
          use.replaceUse(def, c);
          if (c instanceof Register ){
            Register reg=(Register )c;
            useList.get(reg).add(use);
          }
            
          if (!inWorkList.contains(use)) {
            workList.add(use);
            inWorkList.add(use);
          }
        }
      } else if (inst instanceof Br   ) {//
        Br brInst =(Br)inst;
        if(brInst.cond instanceof Boolconst){
          Boolconst cond=(Boolconst) brInst.cond;
        Basicblock atBlock = brInst.parentBlock;
        Basicblock toBlock = cond.val ? brInst.thenBlock : brInst.elseBlock;
        Basicblock deleteBlock = cond.val ? brInst.elseBlock : brInst.thenBlock;
        atBlock.terminalInst = new Jump(atBlock, toBlock);
        atBlock.succs.remove(deleteBlock);
        deleteBlock.preds.remove(atBlock);
        for (var phiInst : deleteBlock.insts) {
          if (!(phiInst instanceof Phi ))
            break;
          boolean found = false;
          Phi phi=(Phi )phiInst;
          for (int i = 0; i < phi.values.size(); ++i)
            if (phi.blocks.get(i) == atBlock) {
              phi.blocks.remove(i);
              phi.values.remove(i);
              found = true;
              break;
            }
          if (found && !inWorkList.contains(phiInst)) {
            workList.add(phiInst);
            inWorkList.add(phiInst);
          }
        }
        if (deleteBlock.preds.size() == 0)
          deleteBlock(deleteBlock);
        }
        
      }
    }

    for (var block : func.blocks)
      block.insts.removeIf(inst -> inst.isDeleted);
  }

  void deleteBlock(Basicblock block) {
    curFunc.blocks.remove(block);
    for (var inst : block.insts) {
      inst.isDeleted = true;
      for (var use : inst.getUse())
        if (useList.get(use) != null)
          useList.get(use).remove(inst);
    }
    block.terminalInst.isDeleted = true;
    for (var use : block.terminalInst.getUse())
      useList.get(use).remove(block.terminalInst);
    for (var succ : block.succs) {
      succ.preds.remove(block);
      for (var phiInst : succ.insts) {
        if (!(phiInst instanceof Phi ))
          break;
        boolean found = false;
        Phi phi=(Phi )phiInst;
        for (int i = 0; i < phi.values.size(); ++i)
          if (phi.blocks.get(i) == block) {
            phi.blocks.remove(i);
            phi.values.remove(i);
            found = true;
            break;
          }
        if (found && !inWorkList.contains(phiInst)) {
          workList.add(phiInst);
          inWorkList.add(phiInst);
        }
      }
      if (succ.preds.size() == 0)
        deleteBlock(succ);
    }
  }

  Valu canBeReplaced(Ins inst) {
    if (inst instanceof Phi ) {
      Phi phiInst=(Phi )inst;
      if (phiInst.values.size() == 1)
        return phiInst.values.get(0);
      else if (phiInst.values.get(0) instanceof Const ) {
        Const constVal=(Const)phiInst.values.get(0);
        for (int i = 1; i < phiInst.values.size(); ++i){
          Const other=null;
          if(phiInst.values.get(i) instanceof Const){
            other=(Const)phiInst.values.get(i);
          }
          if (!(phiInst.values.get(i) instanceof Const ) || !constVal.equals(other))
            return null;
        }
          
        return constVal;
      }
      return null;
    }
    // if (inst instanceof IRZextInst castInst) {
    //   if (castInst.val instanceof Cond condConst)
    //     return condConst.val ? irBoolTrueConst : irBoolFalseConst;
    //   return null;
    // }
    // if (inst instanceof IRTruncInst castInst) {
    //   if (castInst.val instanceof IRBoolConst condConst)
    //     return condConst.val ? irTrueConst : irFalseConst;
    //   return null;
    // }
    if (inst instanceof Binary ){
      Binary calcInst=(Binary) inst;
      return calcInst.calcConst();
    }
      
    if (inst instanceof Icmp ){
      Icmp icmpInst=(Icmp) inst;
      return icmpInst.calcConst();
    }
      
    return null;
  }
}
