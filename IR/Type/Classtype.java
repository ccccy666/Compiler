package IR.Type;

import java.util.ArrayList;
import java.util.HashMap;
import IR.Value.*;

public class Classtype extends Base {
  public ArrayList<Base> memberType = new ArrayList<Base>();
  public HashMap<String, Integer> memberOffset = new HashMap<>();
  public boolean hasBuild = false;

  public Classtype(String name, int size) {
    super(name, size);
  }

  public void addMember(String name, Base type) {
    memberType.add(type);
    memberOffset.put(name, memberType.size() - 1);
  }

  public boolean hasMember(String name) {
    return memberOffset.containsKey(name);
  }

  public Base getMemberType(String name) {
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
  public int getsize() {
    return  0;
  }
}