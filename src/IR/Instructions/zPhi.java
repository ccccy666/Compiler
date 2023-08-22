// // only insert in IR Optimizer
// package IR.Instructions;

// import java.util.ArrayList;
// import java.util.LinkedHashSet;

// import IR.*;
// import IR.Value.*;

// public class Phi extends Ins {
//   public Register dest;
//   public ArrayList<Valu> values = new ArrayList<>();
//   public ArrayList<Basicblock> blocks = new ArrayList<>();

//   public Phi(Basicblock block, Register dest) {
//     super(block);
//     // this.src = src;
//     this.dest = dest;
//   }

//   public void add(Valu value, Basicblock block) {
//     values.add(value == null ? dest.type.defaultValue() : value);
//     blocks.add(block);
//   }

//   @Override
//   public String toString() {
//     String ret = dest + " = phi " + dest.type + " ";
//     for (int i = 0; i < values.size(); ++i) {
//       ret += "[ " + values.get(i) + ", %" + blocks.get(i).name + " ]";
//       if (i != values.size() - 1)
//         ret += ", ";
//     }
//     return ret;
//   }

  
// }
