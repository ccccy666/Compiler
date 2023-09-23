package IR.Instructions;

import IR.*;
import IR.Type.*;
import IR.Value.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Getelementptr extends Ins {
  public Register res;
  public Valu ptr;
  public Basetype to_type;
  public ArrayList<Valu> indexList = new ArrayList<Valu>();

  public Getelementptr(Basicblock block, Valu ptr, Register res, Valu... indexList) {
    super(block);
    this.ptr = ptr;
    this.to_type = ((Ptr) ptr.type).pointToType();
    this.res = res;
    for (Valu index : indexList)
      this.indexList.add(index);
  }

  @Override
  public String toString() {
    String ret = res + " = getelementptr " + to_type + ", " + ptr.toStringWithType();
    for (Valu index : indexList){
      ret += ", " + index.toStringWithType();
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
    ret.add(ptr);
    for (Valu index : indexList)
      ret.add(index);
    return ret;
  }

  @Override
  public Register getDef() {
    return res;
  }

  @Override
  public void replaceUse(Valu old, Valu newOne) {
    ptr = ptr == old ? newOne : ptr;
    for (int i = 0; i < indexList.size(); ++i)
      if (indexList.get(i) == old)
        indexList.set(i, newOne);
  }
}