// package IR.Type;

// import java.util.Objects;

// import IR.Value.*;

// public class Array extends Basetype {
//   public Basetype baseType;
//   public int cnt;

//   public Array(Basetype baseType, int cnt) {
//     super("[" + String.valueOf(cnt) + " x " + baseType.name + "]", baseType.size * cnt);
//     this.baseType = baseType;
//     this.cnt = cnt;
//   }

//   @Override
//   public String toString() {
//     return "[" + String.valueOf(cnt) + " x " + baseType.toString() + "]";
//   }

//   @Override
//   public Valu defaultValue() {
//     return new Nullconst(this);
//   }
// }