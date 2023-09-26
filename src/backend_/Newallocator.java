package backend_;

import assembly.*;
import assembly.inst.*;
import assembly.operand.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Stack;

//虎书 p176
public class Newallocator {
  public ASMModule module;
  LinkedList<Inst> newInsts;
  public int K = 27; 
  PhysicsReg RegSp = PhysicsReg.regMap.get("sp");
  public HashSet<Reg> spillList = new HashSet<>();//栈上开的内存
  public LinkedHashSet<Reg> preColored = new LinkedHashSet<Reg>(PhysicsReg.regMap.values());//预着色，机器寄存器
  public LinkedHashSet<Reg> initial = new LinkedHashSet<Reg>();//临时寄存器集合
  public LinkedList<Reg> simplifyWorkList = new LinkedList<Reg>();//简化，低度数传送无关
  public LinkedList<Reg> freezeWorkList = new LinkedList<Reg>();//低度数传送有关
  public LinkedList<Reg> spillWorkList = new LinkedList<Reg>();//可能spill，高度数
  public LinkedHashSet<Reg> spilledNodes = new LinkedHashSet<Reg>();//spill
  public LinkedHashSet<Reg> coalescedNodes = new LinkedHashSet<Reg>();//合并
  public LinkedHashSet<Reg> coloredNodes = new LinkedHashSet<Reg>();//染色了
  public Stack<Reg> selectStack = new Stack<Reg>();//stack
  public HashSet<Edge> adjSet = new HashSet<>();//边集合
  public HashMap<Reg, HashSet<Reg>> adjList = new HashMap<>();//一个点对应到它连接的点
  public HashMap<Reg, Integer> degree = new HashMap<>();//每个点度数
  public HashMap<Reg, HashSet<MvInst>> moveList = new HashMap<>();
  public HashMap<Reg, Reg> alias = new HashMap<>();//并查集
  public HashMap<Reg, Integer> color = new HashMap<>();//为结点选择的颜色
  public LinkedHashSet<MvInst> coalescedMoves = new LinkedHashSet<>();//可以被合并的mv指令集合
  public LinkedHashSet<MvInst> constrainedMoves = new LinkedHashSet<>();//受抑制，冲突
  public LinkedHashSet<MvInst> frozenMoves = new LinkedHashSet<>();//不考虑合并
  public LinkedHashSet<MvInst> workListMoves = new LinkedHashSet<>();//可能合并
  public LinkedHashSet<MvInst> activeMoves = new LinkedHashSet<>();///没有做好合并准备

  void allocateUse(VirtualReg newReg, VirtualReg reg) {
    if (reg.stackOffset < 1 << 11)
      newInsts.add(new LoadInst(reg.size, newReg, RegSp, new Imm(reg.stackOffset)));
    else {
      newInsts.add(new LiInst(newReg, new VirtualImm(reg.stackOffset)));
      newInsts.add(new BinaryInst("add", newReg, newReg, RegSp));
      newInsts.add(new LoadInst(reg.size, newReg, newReg));
    }
  }

  void allocateDef(VirtualReg newReg, VirtualReg reg) {
    if (reg.stackOffset < 1 << 11)
      newInsts.add(new StoreInst(reg.size, RegSp, newReg, new Imm(reg.stackOffset)));
    else {
      VirtualReg addr = new VirtualReg(4);
      spillList.add(addr);
      newInsts.add(new LiInst(addr, new VirtualImm(reg.stackOffset)));
      newInsts.add(new BinaryInst("add", addr, addr, RegSp));
      newInsts.add(new StoreInst(reg.size, addr, newReg));
    }
  }
  public static class Edge {//冲突图的边
    public Reg u, v;

    public Edge(Reg u, Reg v) {
      this.u = u;
      this.v = v;
    }
    @Override
    public int hashCode() {
      return u.hashCode() ^ v.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
      if (obj == null || !(obj instanceof Edge))
        return false;
      Edge e = (Edge) obj;
      return (u == e.u && v == e.v) || (u == e.v && v == e.u);
    }
  }

  public Newallocator(ASMModule module) {
    this.module = module;
  }
  public void stack(){
    for (var curFunc : module.functions) {
      int totalStack = curFunc.paramUsed + curFunc.allocaUsed + curFunc.spillUsed;
      if (totalStack < 1 << 11)
        curFunc.entryBlock.insts.addFirst(new UnaryInst("addi", PhysicsReg.get("sp"), PhysicsReg.get("sp"),new Imm(-totalStack)));
      else {
        curFunc.entryBlock.insts.addFirst(new BinaryInst("add", PhysicsReg.regMap.get("sp"), PhysicsReg.get("sp"),PhysicsReg.get("t0")));
        curFunc.entryBlock.insts.addFirst(new LiInst(PhysicsReg.get("t0"), new VirtualImm(-totalStack)));
      }
      if (totalStack < 1 << 11)
        curFunc.exitBlock.insts.add(new UnaryInst("addi", PhysicsReg.get("sp"), PhysicsReg.get("sp"),new Imm(totalStack)));
      else {
        curFunc.exitBlock.insts.add(new LiInst(PhysicsReg.get("t0"), new VirtualImm(totalStack)));
        curFunc.exitBlock.insts.add(new BinaryInst("add", PhysicsReg.get("sp"), PhysicsReg.get("sp"),PhysicsReg.get("t0")));
      }
      curFunc.exitBlock.insts.add(new RetInst());
    }
  }
  public void work() {
    for (var func : module.functions){
      workOnFunc(func);
    }
    stack();
  }

  void initAll(ASMFunction func) {
    initial.clear();
    simplifyWorkList.clear();
    freezeWorkList.clear();
    spillWorkList.clear();
    spilledNodes.clear();
    coalescedNodes.clear();
    coloredNodes.clear();
    selectStack.clear();
    adjSet.clear();
    adjList.clear();
    degree.clear();
    moveList.clear();
    alias.clear();
    color.clear();
    coalescedMoves.clear();
    constrainedMoves.clear();
    frozenMoves.clear();
    workListMoves.clear();
    activeMoves.clear();
    for (var reg : PhysicsReg.regMap.values()) {//预着色
      adjList.put(reg, new HashSet<>());
      degree.put(reg, Integer.MAX_VALUE);
      moveList.put(reg, new HashSet<>());
      alias.put(reg, null);
      color.put(reg, reg.id);
    }
    for (var block : func.blocks)
      for (var inst : block.insts) {
        initial.addAll(inst.Def());
        initial.addAll(inst.Use());
      }
    initial.removeAll(preColored);
    for (var reg : initial) {//初始化
      adjList.put(reg, new HashSet<>());
      degree.put(reg, 0);
      moveList.put(reg, new HashSet<>());
      alias.put(reg, null);
      color.put(reg, null);
    }
  }

  void build(ASMFunction func) {
    for (var block : func.blocks) {
      LinkedHashSet<Reg> live = new LinkedHashSet<>(block.liveOut);//最后一条指令的liveout就是block的liveout
      for (int i = block.insts.size() - 1; i >= 0; i--) {
        Inst inst = block.insts.get(i);
        if (inst instanceof MvInst) {
          live.removeAll(inst.Use());
          for (var reg : inst.Def())
            moveList.get(reg).add((MvInst) inst);
          for (var reg : inst.Use())
            moveList.get(reg).add((MvInst) inst);
          workListMoves.add((MvInst) inst);
        }
        live.addAll(inst.Def());
        //去掉use加入def
        for (Reg def : inst.Def())
          for (var l : live)
            addEdge(def, l);//建边
        live.removeAll(inst.Def());//恢复原样
        live.addAll(inst.Use());
      }
    }
  }
  void addEdge(Reg u, Reg v) {
    Edge e = new Edge(u, v);
    if (u == v || adjSet.contains(e))//u==v 已经uv有边
      return;
    adjSet.add(e);
    if (!preColored.contains(u)) {//预着色
      adjList.get(u).add(v);
      degree.put(u, degree.get(u) + 1);
    }
    if (!preColored.contains(v)) {
      adjList.get(v).add(u);
      degree.put(v, degree.get(v) + 1);
    }
  }

  void makeWorkList() {
    for (var reg : initial) {
      if (degree.get(reg) >= K)
        spillWorkList.add(reg);
      else if (moveRelated(reg))//
        freezeWorkList.add(reg);
      else
        simplifyWorkList.add(reg);
    }
    initial.clear();
  }
  boolean moveRelated(Reg reg) {
    return nodeMoves(reg).size() > 0;
  }
  LinkedHashSet<MvInst> nodeMoves(Reg reg) {//reg相关的move指令 与activeMoves，workListMoves这两个集合的并 的交集
    LinkedHashSet<MvInst> ret = new LinkedHashSet<MvInst>(workListMoves);
    ret.addAll(activeMoves);
    ret.retainAll(moveList.get(reg));
    return ret;
  }

  void workOnFunc(ASMFunction func) {
    spillList.clear();
    mainPart(func);
    updateInst(func);
  }

  void mainPart(ASMFunction func){
    while (true) {
      LivenessAnalyzer anslyser=new LivenessAnalyzer(func);
      anslyser.work();
      initAll(func);
      build(func);
      makeWorkList();
      do {
        if (!simplifyWorkList.isEmpty())
          Simplify();
        else if (!workListMoves.isEmpty())
          Coalesce();
        else if (!freezeWorkList.isEmpty())
          Freeze();
        else if (!spillWorkList.isEmpty())
          SelectSpill();
      } while (!simplifyWorkList.isEmpty() || !workListMoves.isEmpty() ||!freezeWorkList.isEmpty() || !spillWorkList.isEmpty());
      assignColors();
      if (spilledNodes.isEmpty())//spillstack
        break;
      rewriteProgram(func);//为栈上变量改写指令
    }
  }
  void Simplify() {//全部进selectstack
    while (!simplifyWorkList.isEmpty()) {
      Reg reg = simplifyWorkList.removeFirst();
      selectStack.push(reg);
      for (var adj : adjacent(reg))
        decrementDegree(adj);
    }
  }
  LinkedHashSet<Reg> adjacent(Reg reg) {//找reg相邻点
    LinkedHashSet<Reg> ret = new LinkedHashSet<>(adjList.get(reg));
    ret.removeAll(selectStack);//已经在栈里面
    ret.removeAll(coalescedNodes);//已经合并了
    return ret;
  }
  void decrementDegree(Reg reg) {
    int d = degree.get(reg);
    degree.put(reg, d - 1);
    if (d == K) {
      LinkedHashSet<Reg> nodes = adjacent(reg);
      nodes.add(reg);
      enableMoves(nodes);//可能有点可以合并了
      spillWorkList.remove(reg);
      if (moveRelated(reg))
        freezeWorkList.add(reg);
      else
        simplifyWorkList.add(reg);
    }
  }
  void enableMoves(LinkedHashSet<Reg> nodes) {//
    for (var reg : nodes) {
      var nodeMoves = nodeMoves(reg);
      for (var mv : nodeMoves)
        if (activeMoves.contains(mv)) {
          activeMoves.remove(mv);
          workListMoves.add(mv);
        }
    }
  }

  boolean Briggs(LinkedHashSet<Reg> uv) {
    int k = 0;
    for (Reg reg : uv)
      if (degree.get(reg) >= K)
        k++;
    return k < K;
  }
  boolean George(Reg t, Reg r) {//低度数或有冲突
    return degree.get(t) < K || preColored.contains(t) || adjSet.contains(new Edge(t, r));
  }
  void Coalesce() {
    MvInst mv = workListMoves.iterator().next();
    Reg x = getAlias(mv.rd), y = getAlias(mv.rs1);//rd，rs1可能被合并了
    Edge e = preColored.contains(y) ? new Edge(y, x) : new Edge(x, y);//预着色的结点不能合并到其它点
    workListMoves.remove(mv);
    if (e.u == e.v) {//自己mv自己
      // delete the move instruction directly
      coalescedMoves.add(mv);
      addWorkList(e.u);
    } else if (preColored.contains(e.v) || adjSet.contains(e)|| e.u == PhysicsReg.get("zero") || e.v == PhysicsReg.get("zero")) {//预着色或有冲突
      
      constrainedMoves.add(mv);
      addWorkList(e.u);
      addWorkList(e.v);
    } else {
      boolean flag = true;
      for (Reg reg : adjacent(e.v))
        flag &= George(reg, e.u);
      LinkedHashSet<Reg> uv = new LinkedHashSet<>(adjacent(e.u));
      uv.addAll(adjacent(e.v));//u,v邻结点
      if (preColored.contains(e.u) && flag || !preColored.contains(e.u) && Briggs(uv)) {
        coalescedMoves.add(mv);
        combine(e.u, e.v);
        addWorkList(e.u);
      } else {
        activeMoves.add(mv);//
      }
    }
  }
  Reg getAlias(Reg reg) {
    if (coalescedNodes.contains(reg)) {//并查集
      Reg regAlias = getAlias(alias.get(reg));
      alias.put(reg, regAlias);
      return regAlias;
    } else return reg;
      
  }
  void addWorkList(Reg reg) {
    if (!preColored.contains(reg) && !moveRelated(reg) && degree.get(reg) < K) {// low degree and no move related
      freezeWorkList.remove(reg);
      simplifyWorkList.add(reg); 
    }
  }
  void combine(Reg u, Reg v) {//v并到u去
    if (freezeWorkList.contains(v))
      freezeWorkList.remove(v);
    else spillWorkList.remove(v);

    coalescedNodes.add(v);
    alias.put(v, u); // fa[v] = u
    moveList.get(u).addAll(moveList.get(v));
    LinkedHashSet<Reg> V = new LinkedHashSet<Reg>() ;
    V.add(v);
    enableMoves(V);

    for (var t : adjacent(v)) {
      addEdge(t, u);
      decrementDegree(t);
    }
    if (degree.get(u) >= K && freezeWorkList.contains(u)) {//不符合freezelist的定义了（u的边是原本u,v边的并）
      freezeWorkList.remove(u);
      spillWorkList.add(u);
    }
  }
  void Freeze() {
    Reg reg = freezeWorkList.getFirst();
    freezeWorkList.remove(reg);
    simplifyWorkList.add(reg);
    freezeMoves(reg);//不再视为move相关
  }

  void SelectSpill() {
    Reg m = null;
    for (Reg reg : spillWorkList)  // 避免选择那种由读取前面已溢出的寄存器产生的、活跃范围很小的寄存器
      if (m == null ||  !spillList.contains(reg))//选择溢出优先级最低的
        m = reg;
    spillWorkList.remove(m);
    simplifyWorkList.add(m); // 看看是否是实际溢出
    freezeMoves(m);
  }
  void freezeMoves(Reg reg) {//move有关
    for (var mv : nodeMoves(reg)) {
      Reg x = mv.rd, y = mv.rs1, v;
      v = getAlias(y) == getAlias(reg) ? getAlias(x) : getAlias(y);
      activeMoves.remove(mv);
      frozenMoves.add(mv);//被冻结了
      if (nodeMoves(v).size() == 0 && degree.get(v) < K) {//u被移除导致v受影响
        freezeWorkList.remove(v);
        simplifyWorkList.add(v);
      }
    }
  }
  void assignColors() {
    while (!selectStack.isEmpty()) {
      Reg reg = selectStack.pop();
      LinkedHashSet<Integer> okColors = new LinkedHashSet<>();
      for (int i = 5; i < 32; i++)
        okColors.add(i);//添加可以染色的

      for (var adj : adjList.get(reg)) {
        Reg adjAlias = getAlias(adj);
        if (coloredNodes.contains(adjAlias) || preColored.contains(adjAlias))
          okColors.remove(color.get(adjAlias));//去除邻边的颜色
      }
      if (!okColors.isEmpty()){
        coloredNodes.add(reg);//添加颜色
        color.put(reg, okColors.iterator().next());
      }else {
        spilledNodes.add(reg);
      }
    }
    for (Reg reg : coalescedNodes)
      color.put(reg, color.get(getAlias(reg)));
  }

  void rewriteProgram(ASMFunction func) {
    for (Reg reg : spilledNodes) {//栈上分配空间
      ((VirtualReg) reg).stackOffset = func.paramUsed + func.allocaUsed + func.spillUsed;
      func.spillUsed += 4;
    }

    for (var block : func.blocks) {
      newInsts = new LinkedList<>();
      for (Inst inst : block.insts) {
        VirtualReg same = null;
        if (inst.rs1 != null && inst.rs1.stackOffset != null) {
          VirtualReg newReg = new VirtualReg(4);
          spillList.add(newReg);
          allocateUse(newReg, (VirtualReg) inst.rs1);
          if (inst.rs1 == inst.rs2)
            inst.rs2 = newReg;
          if (inst.rs1 == inst.rd)
            same = newReg;
          inst.rs1 = newReg;
        }
        if (inst.rs2 != null && inst.rs2.stackOffset != null) {
          VirtualReg newReg = new VirtualReg(4);
          spillList.add(newReg);
          allocateUse(newReg, (VirtualReg) inst.rs2);
          if (inst.rs2 == inst.rd)
            same = newReg;
          inst.rs2 = newReg;
        }
        newInsts.add(inst);
        if (inst.rd != null && inst.rd.stackOffset != null) {
          VirtualReg newReg = same == null ? new VirtualReg(4) : same;
          spillList.add(newReg);
          allocateDef(newReg, (VirtualReg) inst.rd);
          inst.rd = newReg;
        }
      }
      block.insts = newInsts;
    }
  }
  void updateInst(ASMFunction func){
    //改写指令
    for (var block : func.blocks) {
      newInsts = new LinkedList<>();
      for (Inst inst : block.insts) {
        if (inst instanceof LiInst && ((LiInst) inst).Imm instanceof StackImm)
          ((StackImm) ((LiInst) inst).Imm).calc();
        if (inst.rd instanceof VirtualReg)
          inst.rd = PhysicsReg.idReg.get(color.get(inst.rd));
        if (inst.rs1 instanceof VirtualReg)
          inst.rs1 = PhysicsReg.idReg.get(color.get(inst.rs1));
        if (inst.rs2 instanceof VirtualReg)
          inst.rs2 = PhysicsReg.idReg.get(color.get(inst.rs2));
        if (!(inst instanceof MvInst) || inst.rd != inst.rs1)//mv且rd==rs1
          newInsts.add(inst);
      }
      block.insts = newInsts;
    }
  }
}
