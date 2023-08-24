@str.0 = private unnamed_addr constant [2 x i8] c" \00"
@str.1 = private unnamed_addr constant [8 x i8] c"Total: \00"
@N = dso_local global i32 15000
@b = dso_local global ptr null
@resultCount = dso_local global i32 0

declare dso_local ptr @malloc(i32)
declare dso_local i32 @strlen(ptr)
declare dso_local void @print(ptr)
declare dso_local void @println(ptr)
declare dso_local void @printInt(i32)
declare dso_local void @printlnInt(i32)
declare i32 @string_length(ptr)
declare dso_local ptr @getString()
declare dso_local i32 @getInt()
declare dso_local ptr @toString(i32)
declare ptr @string_substring(ptr, i32, i32)
declare i32 @string_parseInt(ptr)
declare i32 @string_ord(ptr, i32)
declare ptr @string_add(ptr, ptr)
declare i1 @string_equal(ptr, ptr)
declare i1 @string_notEqual(ptr, ptr)
declare i1 @string_less(ptr, ptr)
declare i1 @string_lessOrEqual(ptr, ptr)
declare i1 @string_greater(ptr, ptr)
declare i1 @string_greaterOrEqual(ptr, ptr)

declare i32 @__array_size(ptr)
declare ptr @__newPtrArray(i32, i32)
define void @global_init() {
entry_0:
  %.2 = call ptr @__newPtrArray(i32 15005, i32 15001)
  store ptr %.2, ptr @b
  br label %return_1
return_1:
  ret void
}

define i32 @main() {
entry_0:
  call void @global_init()
  %retval = alloca i32
  %.6 = alloca i32
  %.22 = alloca i32
  %.25 = alloca i1
  store i32 1, ptr %.6
  br label %for.cond_2
for.cond_2:
  %.7 = load i32, ptr %.6
  %.8 = load i32, ptr @N
  %.9 = icmp sle i32 %.7, %.8
  br i1 %.9, label %for.loop_3, label %for.end_5
for.loop_3:
  %.10 = load ptr, ptr @b
  %.12 = load i32, ptr %.6
  %.11 = getelementptr i1, ptr %.10, i32 %.12
  store i1 1, ptr %.11
  br label %for.step_4
for.step_4:
  %.13 = load i32, ptr %.6
  %.14 = add i32 %.13, 1
  store i32 %.14, ptr %.6
  br label %for.cond_2
for.end_5:
  store i32 2, ptr %.6
  br label %for.cond_6
for.cond_6:
  %.15 = load i32, ptr %.6
  %.16 = load i32, ptr @N
  %.17 = icmp sle i32 %.15, %.16
  br i1 %.17, label %for.loop_7, label %for.end_9
for.loop_7:
  %.18 = load ptr, ptr @b
  %.20 = load i32, ptr %.6
  %.19 = getelementptr i1, ptr %.18, i32 %.20
  %.21 = load i1, ptr %.19
  br i1 %.21, label %if.then_11, label %if.end_10
if.then_11:
  store i32 2, ptr %.22
  %.23 = load i32, ptr %.6
  %.24 = icmp sgt i32 %.23, 3
  br i1 %.24, label %rhsBlock_12, label %falseBlock_14
rhsBlock_12:
  %.26 = load i32, ptr %.6
  %.27 = sub i32 %.26, 2
  %.28 = load ptr, ptr @b
  %.29 = getelementptr i1, ptr %.28, i32 %.27
  %.30 = load i1, ptr %.29
  br i1 %.30, label %trueBlock_13, label %falseBlock_14
trueBlock_13:
  store i1 1, ptr %.25
  br label %shortCir.end_15
falseBlock_14:
  store i1 0, ptr %.25
  br label %shortCir.end_15
shortCir.end_15:
  %.31 = load i1, ptr %.25
  br i1 %.31, label %if.then_17, label %if.end_16
if.then_17:
  %.32 = load i32, ptr @resultCount
  %.33 = add i32 %.32, 1
  store i32 %.33, ptr @resultCount
  %.34 = load i32, ptr %.6
  %.35 = sub i32 %.34, 2
  %.36 = call ptr @toString(i32 %.35)
  %.37 = call ptr @string_add(ptr %.36, [2 x i8]* @str.0)
  %.38 = load i32, ptr %.6
  %.39 = call ptr @toString(i32 %.38)
  %.40 = call ptr @string_add(ptr %.37, ptr %.39)
  call void @println(ptr %.40)
  br label %if.end_16
if.end_16:
  br label %while.cond_18
while.cond_18:
  %.41 = load i32, ptr %.6
  %.42 = load i32, ptr %.22
  %.43 = mul i32 %.41, %.42
  %.44 = load i32, ptr @N
  %.45 = icmp sle i32 %.43, %.44
  br i1 %.45, label %while.loop_19, label %while.end_20
while.loop_19:
  %.46 = load i32, ptr %.6
  %.47 = load i32, ptr %.22
  %.48 = mul i32 %.46, %.47
  %.49 = load ptr, ptr @b
  %.50 = getelementptr i1, ptr %.49, i32 %.48
  store i1 0, ptr %.50
  %.51 = load i32, ptr %.22
  %.52 = add i32 %.51, 1
  store i32 %.52, ptr %.22
  br label %while.cond_18
while.end_20:
  br label %if.end_10
if.end_10:
  br label %for.step_8
for.step_8:
  %.53 = load i32, ptr %.6
  %.54 = add i32 %.53, 1
  store i32 %.54, ptr %.6
  br label %for.cond_6
for.end_9:
  %.55 = load i32, ptr @resultCount
  %.56 = call ptr @toString(i32 %.55)
  %.57 = call ptr @string_add([8 x i8]* @str.1, ptr %.56)
  call void @println(ptr %.57)
  store i32 0, ptr %retval
  br label %return_1
return_1:
  %.5 = load i32, ptr %retval
  ret i32 %.5
}

