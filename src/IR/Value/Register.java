package IR.Value;

import IR.Type.*;

public class Register extends Valu {
  public String name;
  public int index = -1;
  public static int regCnt = 0;

  public Register(String name, Basetype type) {
    super(type);
    this.name = name;
  }

  @Override
  public String toString() {
    if (index == -1 && (name == null || !name.equals("retval"))){
      index = regCnt++;
    }
    String ret ="%";
    boolean fl=name != null && name.equals("retval") ;
    if(fl){
      ret+=name;
    }else{
      ret+= (name==null?("." + String.valueOf(index)):(name+"." + String.valueOf(index)));
    }
    return ret;
   
  }

  @Override
  public String toStringWithType() {
    return type + " " + toString();
  }
}