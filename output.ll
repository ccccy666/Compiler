@str.0 = private unnamed_addr constant [4 x i8] c"<< \00"
@str.3 = private unnamed_addr constant [2 x i8] c" \00"
@str.4 = private unnamed_addr constant [4 x i8] c">> \00"
@str.2 = private unnamed_addr constant [3 x i8] c") \00"
@str.1 = private unnamed_addr constant [2 x i8] c"(\00"
@n = dso_local global i32 0
@p = dso_local global i32 0
@k = dso_local global i32 0
@i = dso_local global i32 0
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
declare ptr @newPtrArray(i32)
declare ptr @newClass(i32)

define i32 @main() {
entry0:
  %retval = alloca i32
  %binary_short.0 = alloca i1
  %.1 = call i32 @getInt()
  store i32 %.1, ptr @n
  %.2 = call i32 @getInt()
  store i32 %.2, ptr @p
  %.3 = call i32 @getInt()
  store i32 %.3, ptr @k
  %.4 = load i32, ptr @p
  %.5 = load i32, ptr @k
  %.6 = sub i32 %.4, %.5
  %.7 = icmp sgt i32 %.6, 1
  br i1 %.7, label %then3, label %ifend2
then3:
  call void @print(ptr @str.0)
  br label %ifend2
ifend2:
  %.8 = load i32, ptr @p
  %.9 = load i32, ptr @k
  %.10 = sub i32 %.8, %.9
  store i32 %.10, ptr @i
  br label %for.cond4
for.cond4:
  %.11 = load i32, ptr @p
  %.12 = load i32, ptr @k
  %.13 = add i32 %.11, %.12
  %.14 = load i32, ptr @i
  %.15 = icmp sle i32 %.14, %.13
  br i1 %.15, label %loop5, label %end7
loop5:
  %.16 = load i32, ptr @i
  %.17 = icmp sle i32 1, %.16
  br i1 %.17, label %rhsBlock8, label %falseBlock10
rhsBlock8:
  %.18 = load i32, ptr @i
  %.19 = load i32, ptr @n
  %.20 = icmp sle i32 %.18, %.19
  br i1 %.20, label %trueBlock9, label %falseBlock10
trueBlock9:
  store i1 true, ptr %binary_short.0
  br label %short.end11
falseBlock10:
  store i1 false, ptr %binary_short.0
  br label %short.end11
short.end11:
  %cond.21 = load i1, ptr %binary_short.0
  br i1 %cond.21, label %then13, label %ifend12
then13:
  %.22 = load i32, ptr @i
  %.23 = load i32, ptr @p
  %tmp.24 = icmp eq i32 %.22, %.23
  br i1 %tmp.24, label %then15, label %else16
then15:
  call void @print(ptr @str.1)
  %.25 = load i32, ptr @i
  %.26 = call ptr @toString(i32 %.25)
  call void @print(ptr %.26)
  call void @print(ptr @str.2)
  br label %ifend14
else16:
  %.27 = load i32, ptr @i
  call void @printInt(i32 %.27)
  call void @print(ptr @str.3)
  br label %ifend14
ifend14:
  br label %ifend12
ifend12:
  br label %step6
step6:
  %.28 = load i32, ptr @i
  %.29 = add i32 %.28, 1
  store i32 %.29, ptr @i
  br label %for.cond4
end7:
  %.30 = load i32, ptr @p
  %.31 = load i32, ptr @k
  %.32 = add i32 %.30, %.31
  %.33 = load i32, ptr @n
  %.34 = icmp slt i32 %.32, %.33
  br i1 %.34, label %then18, label %ifend17
then18:
  call void @print(ptr @str.4)
  br label %ifend17
ifend17:
  store i32 0, ptr %retval
  br label %return1
return1:
  %ret.35 = load i32, ptr %retval
  ret i32 %ret.35
}

