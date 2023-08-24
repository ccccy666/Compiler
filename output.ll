@str.0 = private unnamed_addr constant [2 x i8] c" \00"
@str.1 = private unnamed_addr constant [8 x i8] c"Total: \00"
@N = dso_local global i32 15000
@b = dso_local global ptr null
@resultCount = dso_local global i32 0
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

define void @init() {
entry0:
  %.0 = call ptr @newPtrArray(i32 15001)
  store ptr %.0, ptr @b
  br label %return1
return1:
  ret void
}

define i32 @main() {
entry0:
  call void @init()
  %retval = alloca i32
  %i.addr.0 = alloca i32
  %count.addr.1 = alloca i32
  %binary_short.2 = alloca i1
  store i32 1, ptr %i.addr.0
  br label %for.cond2
for.cond2:
  %.3 = load i32, ptr %i.addr.0
  %.4 = load i32, ptr @N
  %.5 = icmp sle i32 %.3, %.4
  br i1 %.5, label %loop3, label %end5
loop3:
  %.6 = load ptr, ptr @b
  %.7 = load i32, ptr %i.addr.0
  %.8 = getelementptr i1, ptr %.6, i32 %.7
  store i1 true, ptr %.8
  br label %step4
step4:
  %.9 = load i32, ptr %i.addr.0
  %.10 = add i32 %.9, 1
  store i32 %.10, ptr %i.addr.0
  br label %for.cond2
end5:
  store i32 2, ptr %i.addr.0
  br label %for.cond6
for.cond6:
  %.11 = load i32, ptr %i.addr.0
  %.12 = load i32, ptr @N
  %.13 = icmp sle i32 %.11, %.12
  br i1 %.13, label %loop7, label %end9
loop7:
  %.14 = load ptr, ptr @b
  %.15 = load i32, ptr %i.addr.0
  %.16 = getelementptr i1, ptr %.14, i32 %.15
  %.17 = load i1, ptr %.16
  br i1 %.17, label %then11, label %ifend10
then11:
  store i32 2, ptr %count.addr.1
  %.18 = load i32, ptr %i.addr.0
  %.19 = icmp sgt i32 %.18, 3
  br i1 %.19, label %rhsBlock12, label %falseBlock14
rhsBlock12:
  %.20 = load i32, ptr %i.addr.0
  %.21 = sub i32 %.20, 2
  %.22 = load ptr, ptr @b
  %.23 = getelementptr i1, ptr %.22, i32 %.21
  %.24 = load i1, ptr %.23
  br i1 %.24, label %trueBlock13, label %falseBlock14
trueBlock13:
  store i1 true, ptr %binary_short.2
  br label %short.end15
falseBlock14:
  store i1 false, ptr %binary_short.2
  br label %short.end15
short.end15:
  %cond.25 = load i1, ptr %binary_short.2
  br i1 %cond.25, label %then17, label %ifend16
then17:
  %.26 = load i32, ptr @resultCount
  %.27 = add i32 %.26, 1
  store i32 %.27, ptr @resultCount
  %.28 = load i32, ptr %i.addr.0
  %.29 = sub i32 %.28, 2
  %.30 = call ptr @toString(i32 %.29)
  %.31 = call ptr @stradd(ptr %.30, ptr @str.0)
  %.32 = load i32, ptr %i.addr.0
  %.33 = call ptr @toString(i32 %.32)
  %.34 = call ptr @stradd(ptr %.31, ptr %.33)
  call void @println(ptr %.34)
  br label %ifend16
ifend16:
  br label %whilecond18
whilecond18:
  %.35 = load i32, ptr %i.addr.0
  %.36 = load i32, ptr %count.addr.1
  %.37 = mul i32 %.35, %.36
  %.38 = load i32, ptr @N
  %.39 = icmp sle i32 %.37, %.38
  br i1 %.39, label %loop19, label %end20
loop19:
  %.40 = load i32, ptr %i.addr.0
  %.41 = load i32, ptr %count.addr.1
  %.42 = mul i32 %.40, %.41
  %.43 = load ptr, ptr @b
  %.44 = getelementptr i1, ptr %.43, i32 %.42
  store i1 false, ptr %.44
  %.45 = load i32, ptr %count.addr.1
  %.46 = add i32 %.45, 1
  store i32 %.46, ptr %count.addr.1
  br label %whilecond18
end20:
  br label %ifend10
ifend10:
  br label %step8
step8:
  %.47 = load i32, ptr %i.addr.0
  %.48 = add i32 %.47, 1
  store i32 %.48, ptr %i.addr.0
  br label %for.cond6
end9:
  %.49 = load i32, ptr @resultCount
  %.50 = call ptr @toString(i32 %.49)
  %.51 = call ptr @stradd(ptr @str.1, ptr %.50)
  call void @println(ptr %.51)
  store i32 0, ptr %retval
  br label %return1
return1:
  %ret.52 = load i32, ptr %retval
  ret i32 %ret.52
}

