declare i1 @strlt(ptr, ptr)
declare i1 @strle(ptr, ptr)
declare i1 @strgt(ptr, ptr)
declare i1 @strge(ptr, ptr)
declare i1 @streq(ptr, ptr)
declare i1 @strneq(ptr, ptr)

declare dso_local ptr @malloc(i32)
declare dso_local i32 @strlen(ptr)
declare ptr @getString()
declare i32 @getInt()
declare ptr @toString(i32)
declare ptr @substring(ptr, i32, i32)
declare i32 @parseInt(ptr)
declare i32 @ord(ptr, i32)
declare ptr @stradd(ptr, ptr)
declare void @print(ptr)
declare void @println(ptr)
declare void @printInt(i32)
declare void @printlnInt(i32)
declare i32 @array_size(ptr)
declare ptr @newPtrArray(i32)
declare ptr @newClass(i32)

define void @foo(i32 %.0, i32 %.1, i32 %.2) {
entry0:
  %.3 = mul i32 %.0, 1000
  %.4 = mul i32 %.1, 10
  %.5 = add i32 %.3, %.4
  %.6 = add i32 %.5, %.2
  %.7 = call ptr @toString(i32 %.6)
  call void @println(ptr %.7)
  %tmp.8 = icmp eq i32 %.0, 1
  br i1 %tmp.8, label %then3, label %ifend2
then3:
  br label %return1
ifend2:
  call void @foo(i32 1, i32 %.2, i32 %.1)
  %.9 = mul i32 %.0, 1000
  %.10 = mul i32 %.2, 10
  %.11 = add i32 %.9, %.10
  %.12 = add i32 %.11, %.1
  %.13 = call ptr @toString(i32 %.12)
  call void @println(ptr %.13)
  br label %return1
return1:
  %.14 = phi i32 [ %.2, %ifend2 ], [ %.1, %then3 ]
  %.15 = phi i32 [ %.1, %ifend2 ], [ %.2, %then3 ]
  %.16 = phi i32 [ %.1, %ifend2 ], [ 0, %then3 ]
  ret void
}

define i32 @main() {
entry0:
  call void @foo(i32 7, i32 5, i32 3)
  br label %return1
return1:
  ret i32 0
}

