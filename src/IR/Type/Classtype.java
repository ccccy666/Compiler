package IR.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import IR.Value.*;

public class Classtype extends Basetype {
  
  public ArrayList<Basetype> memberType = new ArrayList<Basetype>();
  public HashMap<String, Integer> memberOffset = new HashMap<>();
  public boolean hasBuild = false;

  public Classtype(String name, int size) {
    super(name, size);
  }

  public void addMember(String name, Basetype type) {
    memberType.add(type);
    memberOffset.put(name, memberType.size() - 1);
  }

  public boolean hasMember(String name) {
    return memberOffset.containsKey(name);
  }

  public Basetype getMemberType(String name) {
    return !memberOffset.containsKey(name) ? null : memberType.get(memberOffset.get(name));
  }

  public void calcSize() {
    size = memberType.size() << 2; 
  }

  @Override
  public String toString() {
    return "%struct." + name;
  }
  
  @Override
  public Valu defaultValue() {
    return new Nullconst(this);
  }
}