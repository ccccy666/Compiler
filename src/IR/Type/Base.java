package IR.Type;


import utils.*;

public abstract class Base  {
  public String name;
  public int size;  // cnt of bit

  public Base(String name) {
    this.name = name;
  }

  public Base(String name, int size) {
    this.name = name;
    this.size = size;
  }
  public abstract int getsize();
  public abstract String toString();
  
}

