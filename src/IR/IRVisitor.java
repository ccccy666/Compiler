package IR;

import IR.Instructions.*;

public interface IRVisitor {
  public void visit(Program node);
  public void visit(Functionblock node);
  public void visit(Basicblock node);

  public void visit(Alloca node);
  public void visit(Br node);
  public void visit(Binary node);
  public void visit(Call node);
  //public void visit(CastInst node);
  public void visit(Getelementptr node);
  public void visit(Icmp node);
  public void visit(Jump node);
  public void visit(Load node);
  public void visit(Ret node);
  public void visit(Store node);
  public void visit(Phi node);
}