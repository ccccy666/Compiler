@b = dso_local global ptr null
@now = dso_local global ptr null
@t = dso_local global ptr null
@a = dso_local global ptr null
@n = dso_local global i32 0
@m = dso_local global i32 0
@p = dso_local global i32 0
@op = dso_local global i32 0
@L = dso_local global i32 1
@flag = dso_local global ptr null
@s = dso_local global ptr null
@sum = dso_local global ptr null
@ans = dso_local global i32 0
@aa = dso_local global i32 13131
@bb = dso_local global i32 5353
@MOD = dso_local global i32 0
@no = dso_local global i32 1
declare i1 @strlt(ptr, ptr)
declare i1 @strle(ptr, ptr)
declare i1 @strgt(ptr, ptr)
declare i1 @strge(ptr, ptr)
declare i1 @string_equal(ptr, ptr)
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

define void @init_() {
entry0:
  %.0 = call ptr @newPtrArray(i32 500005)
  store ptr %.0, ptr @b
  %.1 = call ptr @newPtrArray(i32 500005)
  store ptr %.1, ptr @now
  %.2 = call ptr @newPtrArray(i32 500005)
  store ptr %.2, ptr @t
  %.3 = call ptr @newPtrArray(i32 200005)
  store ptr %.3, ptr @a
  %.4 = call ptr @newPtrArray(i32 500005)
  store ptr %.4, ptr @flag
  %.5 = call ptr @newPtrArray(i32 500005)
  br label %forcond2
forcond2:
  %.6 = phi i32 [ 0, %entry0 ], [ %.7, %step4 ]
  %.8 = icmp slt i32 %.6, 500005
  br i1 %.8, label %loop3, label %end5
loop3:
  %.9 = call ptr @newPtrArray(i32 80)
  %.10 = getelementptr ptr, ptr %.5, i32 %.6
  store ptr %.9, ptr %.10
  br label %step4
step4:
  %.7 = add i32 %.6, 1
  br label %forcond2
end5:
  store ptr %.5, ptr @s
  %.11 = call ptr @newPtrArray(i32 500005)
  store ptr %.11, ptr @sum
  store i32 32761, ptr @MOD
  br label %return1
return1:
  ret void
}

define i32 @main() {
entry0:
  call void @init_()
  %retval = alloca i32
  store i32 0, ptr %retval
  br label %return1
return1:
  %ret.0 = load i32, ptr %retval
  ret i32 %ret.0
}

