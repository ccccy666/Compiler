package IRopt;

import IR.*;
import IR.Instructions.*;
import IR.Value.*;
import IR.Type.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
//https://blog.csdn.net/dashuniuniu/article/details/103275708?spm=1001.2014.3001.5502
public class Mem2Reg {//////shit
  
  Program program;
  //Functionblock curFunc;
  LinkedHashSet<Register> Allocas = new LinkedHashSet<>();//可以消除的alloca
  HashMap<Register, HashSet<Basicblock>> Defs = new HashMap<>();//alloca的寄存器到有def语句的块对应
  HashMap<Register, Valu> replace = new HashMap<>();
  HashMap<Register, Valu> replaceload = new HashMap<>();

  public Mem2Reg(Program program) {
    this.program = program;
  }

  public void work() {
    //new DomTreeBuilder(program).work();
    for(var func:program.funcList){
      workOnFunc(func);
    }
  }

  void workOnFunc(Functionblock func) {
    //curFunc = func;

    Allocas.clear();
    Collect(func);//收集能删除的alloca

    addphi(Allocas);
    //addphi
      

    replace.clear();
    renameVar(func.entryBlock);

    simplifyPhi(func.entryBlock);
  }

  void Collect(Functionblock func) {
    
    for (var inst : func.entryBlock.insts) {
      if (!(inst instanceof Alloca))
        break;
      Register reg = ((Alloca) inst).allocaReg;
      Defs.put(reg, new HashSet<>());
      if (canbedel((Alloca) inst,func))
        Allocas.add(reg); //可以被优化的alloca，当前函数没有use过
    }
  }

  boolean canbedel(Alloca inst,Functionblock func) {
    if (inst.func_para >= 8)
      return false;
    Register reg = inst.allocaReg;//
    for (var block : func.blocks)
      for (var user : block.insts) {
        if (!(user instanceof Load) && !(user instanceof Store) && user.getUse().contains(reg))//作为了LOAD,STORE以外的use
          return false;
        if (user instanceof Store   ){
          Store storeInst =(Store) user ;
          
          if(storeInst.destAddr == reg){
            Defs.get(reg).add(block);//defs(v)
          }
        }
      }
    return true;
  }

  // reference : SSA Book
  void addphi(LinkedHashSet<Register> reglist) {//reg 是alloca出来的
    for(var reg:reglist){
      HashSet<Basicblock> hasPhi = new HashSet<>();//F
      HashSet<Basicblock> inWorkList = new HashSet<>(Defs.get(reg));//有def的block  
      LinkedList<Basicblock> workList = new LinkedList<>(inWorkList);
      while (!workList.isEmpty()) {
      Basicblock block = workList.removeFirst();//x  w

      inWorkList.remove(block);
      for (Basicblock df : block.domFrontier)//Y
        if (!hasPhi.contains(df)) {
          df.addInst(new Phi(df, reg, new Register("", ((Ptr) reg.type).pointToType())));
          hasPhi.add(df);
          if (!inWorkList.contains(df)) {
            workList.add(df);
            inWorkList.add(df);
          }
        }
      }
    }
    
  }

  void renameVar(Basicblock block) {//去除load ,store ,alloca
    
    var oldreplace = new HashMap<>(replace);//方便之后回溯
    LinkedList<Ins> newInsts = new LinkedList<>();//新指令集
    for (var inst : block.phiInsts) {
      replace.put(inst.src, inst.dest);//src是原先alloca出来的，dest是重命名的
      
    }
    for (int i = 0; i < block.insts.size(); ++i) {
      boolean flag=true;
      var inst = block.insts.get(i);
      if (inst instanceof Alloca  ){
        Alloca alloca=(Alloca)inst;
        if(Allocas.contains(alloca.allocaReg)){
          continue;// 跳过可删除的 alloca 指令
        }
        
      }
      if (inst instanceof Store  ) {
        Store st=(Store)inst;
        
        if(Allocas.contains(st.destAddr)){//要被删除，把值存入，之后遇到load指令就把使用load出来的寄存器的地方全部替换成val
          flag=false;
          replace.put(st.destAddr, st.val);
        }
        
        
      }
      if (inst instanceof Load  ) {
        Load ld=(Load )inst;
        
        if(Allocas.contains(ld.storeptr)){//被load的寄存器是要被删除的
          flag=false;
          for (int j = i + 1; j < block.insts.size(); ++j){
            var ins=block.insts.get(j);
            ins.replaceUse(ld.destReg, replace.get(ld.storeptr));// 用当前的 SSA 名称替换 load出来的值
            
          }
          replaceload.put(ld.destReg, replace.get(ld.storeptr));
          
        if (block.terminalInst != null)
          block.terminalInst.replaceUse(ld.destReg, replace.get(ld.storeptr));
        }
        // System.out.println(ld + ", " + replace.get(ld.srcAddr));
        
      }
      if(flag){//不是alloca,store,load，正常保留
        //inst.replaceUse(, null);
        var uselist=inst.getUse();
        for(var use:uselist){
          var repla=replaceload.get(use);
          if(repla!=null){
            inst.replaceUse(use, repla);
          }
        }
        newInsts.add(inst);
      }
    }
    block.insts = newInsts;

    for(var succ:block.succs){//填充后继 phi 函数参数
      for(var phi:succ.phiInsts){
        phi.add(replace.get(phi.src), block);
      }
    }
    
    for(var child:block.domChildren){
      renameVar(child);//domtree上遍历
    }
    
    replace = oldreplace;//重置状态
  }


  void simplifyPhi(Basicblock block) {//遍历支配树删去Phi
    for(var phi :block.phiInsts){
      Valu val = phi.values.get(0);
      boolean flag = true;
      for (int j = 1; j < phi.values.size(); ++j)
        if (phi.values.get(j) != val) {
          flag = false;
          break;
        }
      if (flag) {//phi后面值相同
        for(var inst:block.insts){
          inst.replaceUse(phi.dest, val);
        }
       
        phi.isDeleted = true;
      }
    }
    
    for (int i = block.phiInsts.size() - 1; i >= 0; --i) {
      Phi phi = block.phiInsts.get(i);
      if (!phi.isDeleted)
        block.insts.addFirst(phi);
    }

    for(var child:block.domChildren){
      simplifyPhi(child);
    }
  }
}
