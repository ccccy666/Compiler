package middleend;

import IR.*;

public class IROptimizer {
  public IROptimizer(Program program) {
    //new Global2Local(program).work();

    new CFGBuilder(program).work();
    new DomTreeBuilder(program).work();
    new Mem2Reg(program).work();
    
    //new DeadCodeEliminator(program).work();
    //new ConstPropagation(program).work();
  }
}