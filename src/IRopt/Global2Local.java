package IRopt;

import IR.*;
import IR.Instructions.*;
import IR.Value.*;
import IR.Type.*;

import java.util.ArrayList;

public class Global2Local {
  Program program;

  public Global2Local(Program program) {
    this.program = program;
  }

  public void work() {
    var newList = new ArrayList<Globalvar>();
    for (var global : program.globalVarList) {
      if (global.init) {
        newList.add(global);
        continue;
      }
      Functionblock inFunc = null;
      boolean inOneFunc = true;
      for (var func : program.funcList)
        for (var block : func.blocks)
          for (var inst : block.insts)
            if (inst.getUse().contains(global)) {
              if (inFunc == null)
                inFunc = func;
              else if (inFunc != func) {
                inOneFunc = false;
                break;
              }
            }
      if (inOneFunc && inFunc != null && (inFunc == program.mainFunc || inFunc == program.initFunc)) { //只在单个函数使用
        Register reg = new Register("global", global.type);
        inFunc.allocaInsts.add(new Alloca(inFunc.entryBlock, ((Ptr) global.type).pointToType(), reg));
        inFunc.entryBlock.insts.addFirst(new Store(inFunc.entryBlock, global.initVal, reg));
        for (var block : inFunc.blocks)
          for (var inst : block.insts)
            inst.replaceUse(global, reg);
      } else if (inFunc != null) {
        newList.add(global);
      }
    }
    program.globalVarList = newList;
    for (var func : program.funcList)
      func.finish();
  }
}
