package middleend;
import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedList;
import IR.*;

public class DomTreeBuilder {
  Program program;
// reverse post rpo
  HashSet<Basicblock> visited = new HashSet<>();
  HashMap<Basicblock, Integer> rpo = new HashMap<>();
  LinkedList<Basicblock> blocklist = new LinkedList<>();
  public DomTreeBuilder(Program program) {
    this.program = program;
  }
  public void work() {
    for(var func:program.funcList){
      workOnFunc(func);
    }
  }
  void workOnFunc(Functionblock func) {
    init();
    RPOdfs(func.entryBlock);//rpo
    
    func.entryBlock.idom = func.entryBlock;
    blocklist.removeFirst();//entryblock
    boolean flag=true;
    //找每个块的idom

    while (flag) {
      flag = false;
      for (Basicblock block : blocklist) {
        Basicblock idom = null;
        for (Basicblock pred : block.preds){
          if (idom == null)//idom=第一个前驱
            idom = pred;
          else if (pred.idom != null)//前驱的idom已经算好了
            idom = intersect(pred, idom);//最近公共祖先
        }
          
        if (idom != block.idom) {
          block.idom = idom;
          flag = true;
          //flag=true;
        }//else flag=false;
        
      }
    }

    //向最近支配节点的儿子添加自己
    for(var block:blocklist){
      block.idom.domChildren.add(block);
    }
    

    // 找支配边界(df(n)=m表示n支配m一个前驱但是并不支配m)
    blocklist.addFirst(func.entryBlock);
    for (Basicblock block : blocklist) {
      
      if (block.preds.size() <= 1)
        continue;
      //System.out.println("999999999");  
      for (Basicblock pred : block.preds) {
        Basicblock runner = pred;
        
        while (runner != block.idom) {//runner==null(没有前驱的块会出现这种情况)
          runner.domFrontier.add(block);
          runner = runner.idom;
        }
      }
    }
    
  }
  
  Basicblock intersect(Basicblock x, Basicblock y) {//找这俩最近公共祖先
    while (x != y) {
      while (rpo.get(x) < rpo.get(y)){
         x = x.idom;
      }
      while (rpo.get(y) < rpo.get(x)){
        y = y.idom;
      }
    }
    return x;
  }
  void RPOdfs(Basicblock block) {
    visited.add(block);
    for (var succ : block.succs){
      if (!visited.contains(succ))
        RPOdfs(succ);
    }

    rpo.put(block, blocklist.size()); 
    blocklist.addFirst(block);
  }
  void init(){
    visited.clear();
    rpo.clear();
    blocklist.clear();
  }
}
