package assembly.operand;

public class Globalop extends Imm {
  public enum Type { hi, lo };
  public Type type;
  public String s;

  public Globalop(Type type, String s) {
    super(0);
    this.type = type;
    this.s = s;
  }

  @Override
  public String toString() {
    return type == Type.hi ? "%hi(" + s + ")" : "%lo(" + s + ")";
  }
}