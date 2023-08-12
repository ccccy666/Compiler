package ast;

import utils.*;

public class TypeNode extends AstNode {
  public String type;
public int dim = 0;
public boolean isClass = false;
  public TypeNode(String name) {
    super(null);
    type=name;
  }

  public TypeNode(Position pos) {
    super(pos);
  }

  public TypeNode(Position pos, String name, int dim) {
    super(pos);
    this.type=name;
    this.dim=dim;
    if (!type.equals("void")
        && !type.equals("int")
        && !type.equals("bool")
        && !type.equals("string")
        && !type.equals("null")
        && !type.equals("this"))
      isClass = true;
      
    
  }
  public TypeNode(Position pos, String name) {
    super(pos);
    this.type=name;
    if (!type.equals("void")
        && !type.equals("int")
        && !type.equals("bool")
        && !type.equals("string")
        && !type.equals("null")
        && !type.equals("this"))
      isClass = true;
    
  }
  public boolean Nbasic() {
    return dim > 0 || isClass;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (obj == this)
      return true;
    if (!(obj instanceof TypeNode))
      return false;
    TypeNode typ = (TypeNode) obj;
    if (this.dim != typ.dim)
      return false;
    if (!this.type.equals(typ.type))
      return false;
    return true;
  }

  
  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}