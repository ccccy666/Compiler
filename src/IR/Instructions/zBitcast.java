// package IR.Instructions;

// import IR.*;
// import IR.Type.*;
// import IR.Value.*;

// public class Bitcast extends CastInst {
//   public Bitcast(Basicblock block, Valu val, Basetype type, Register dest) {
//     super(block, val, type, dest);
//   }

//   @Override
//   public String toString() {
//     return dest + " = bitcast " + val.toStringWithType() + " to " + to_type;
//   }
// }