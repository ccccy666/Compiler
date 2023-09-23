package IR.Instructions;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import IR.*;
import IR.Value.*;

public class Phi extends Ins {
  public Register dest, src;//src:SSA之前的寄存器名字
  public ArrayList<Valu> values = new ArrayList<>();
  public ArrayList<Basicblock> blocks = new ArrayList<>();

  public Phi(Basicblock block, Register src, Register dest) {
    super(block);
    this.src = src;
    this.dest = dest;
  }

  public void add(Valu value, Basicblock block) {
    values.add(value == null ? dest.type.defaultValue() : value);
    blocks.add(block);
  }

  @Override
  public String toString() {
    String ret = dest + " = phi " + dest.type + " ";
    for (int i = 0; i < values.size(); ++i) {
      ret += "[ " + values.get(i) + ", %" + blocks.get(i).name + " ]";
      if (i != values.size() - 1)
        ret += ", ";
    }
    return ret;
  }

  @Override
  public void accept(IRVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public LinkedHashSet<Valu> getUse() {
    LinkedHashSet<Valu> ret = new LinkedHashSet<>();
    for (Valu value : values)
      ret.add(value);
    return ret;
  }

  @Override
  public Register getDef() {
    return dest;
  }

  @Override
  public void replaceUse(Valu old, Valu newOne) {
    for (int i = 0; i < values.size(); ++i)
      values.set(i, values.get(i) == old ? newOne : values.get(i));
  }
}