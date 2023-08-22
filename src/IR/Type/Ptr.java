package IR.Type;

import IR.Value.*;

public class Ptr extends Basetype {
  public Basetype baseType;
  public int dim = 1, cnt = 1;
  public boolean flag=false;
  public Ptr(Basetype baseType) {
    super("ptr", 4);
    
    if (baseType instanceof Ptr) {
      this.baseType = ((Ptr) baseType).baseType;
      this.dim = ((Ptr) baseType).dim + 1;
    } else {
      this.baseType = baseType;
      this.dim = 1;
    }
  }

  public Ptr(Basetype baseType, int dim) {
    super(baseType.name + "*".repeat(dim), 4);
    if (baseType instanceof Ptr) {
      this.baseType = ((Ptr) baseType).baseType;
      this.dim = ((Ptr) baseType).dim + dim;
    } else {
      this.baseType = baseType;
      this.dim = dim;
    }
  }

  public Basetype pointToType() {
    return dim == 1 ? baseType : new Ptr(baseType, dim - 1);
  }

 

  @Override
  public String toString() {
    return "ptr";
  }
 
  @Override
  public Valu defaultValue() {
    return new Nullconst(this);
  }
}