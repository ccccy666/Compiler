package IR.Value;

import IR.Type.*;

public class Stringconst extends Const {
  public String val;
  public int id;
  public static int cnt = 0;
  
  public Stringconst(String val) {
    super(new Ptr(irCharType));
    //super(new Ptr(new Array(irCharType, val.length() + 1)));

    this.val = val;
    this.id = cnt++;
  }

  @Override
  public String toString() {
    return "@str." + String.valueOf(id);
  }

  @Override
  public String toStringWithType() {
    return "ptr " + toString();
  }

  public String printStr() {
    String ret = "";
    for (int i = 0; i < val.length(); ++i) {
      char c = val.charAt(i);
      switch (c) {
        case '\n': ret += "\\0A"; break;
        case '\"': ret += "\\22"; break;
        case '\\': ret += "\\\\"; break;
        default: ret += c;
      }
    }
    return ret + "\\00";
  }

  @Override
  public boolean isZero() {
    return false;
  }
}