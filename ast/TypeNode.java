package ast;

// import IR.type.*;
import utils.*;

public class TypeNode extends AstNode {
  public String type;
//   public IRType irType;
public int dim = 0;
public boolean isClass = false;
  public TypeNode(String name) {//内置
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
      
    // if (dim == 0) {
    //   if()
    //   switch (name) {

    //     case "int":
    //       this.type = IntType; break;
    //     case "bool":
    //       this.type = BoolType; break;
    //     case "string":
    //       this.type = StringType; break;
    //     case "void":
    //       this.type = VoidType; break;
    //     default:
    //       this.type = new Type(name);
          
    //   }
    // } else {
    //   this.type = new Type(name, dim);
    // }
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
    // switch (name) {
    //   case "int":
    //     this.type = IntType; break;
    //   case "bool":
    //     this.type = BoolType; break;
    //   case "string":
    //     this.type = StringType; break;
    //   case "void":
    //     this.type = VoidType; break;
    //   default:
    //     this.type = new Type(name);
    // }
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

  // public boolean isArrayType() {
  //   return dim > 0;
  // }
  @Override
  public void accept(ASTvisitor visitor) {
    visitor.visit(this);
  }
}