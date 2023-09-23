@str.0 = private unnamed_addr constant [4 x i8] c"uyy\00"
@str.2 = private unnamed_addr constant [4 x i8] c"yuv\00"
@str.1 = private unnamed_addr constant [4 x i8] c"yuy\00"
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

define i32 @main() {
entry0:
  %.0 = call i32 @getInt()
  %.1 = call i32 @getInt()
  %.2 = call i32 @getInt()
  %.3 = add i32 %.0, 1
  %tmp.4 = icmp eq i32 %.3, %.1
  br i1 %tmp.4, label %mhs2, label %rhs3
mhs2:
  br label %end4
rhs3:
  br label %end4
end4:
  %.5 = phi ptr [ @str.1, %rhs3 ], [ @str.0, %mhs2 ]
  %.6 = sub i32 %.2, 1
  %tmp.7 = icmp eq i32 %.1, %.6
  br i1 %tmp.7, label %mhs5, label %rhs6
mhs5:
  br label %end7
rhs6:
  br label %end7
end7:
  %.8 = phi ptr [ @str.2, %rhs6 ], [ @str.0, %mhs5 ]
  %.9 = call i1 @streq(ptr %.10, ptr %.8)
  br i1 %.9, label %then9, label %ifend8
then9:
  call void @printlnInt(i32 %.3)
  br label %ifend8
ifend8:
  br label %return1
return1:
  ret i32 0
}

