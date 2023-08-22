@MAXN = dso_local global i32 10005
@MAXM = dso_local global i32 10005

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
define i32 @main() {
entry_0:
  %retval = alloca i32
  %.4 = alloca i32
  %.5 = alloca i32
  %.8 = alloca ptr
  %.13 = alloca ptr
  %.18 = alloca ptr
  %.23 = alloca i32
  %.24 = alloca i32
  %.6 = call i32 @getInt()
  store i32 %.6, ptr %.4
  %.7 = call i32 @getInt()
  store i32 %.7, ptr %.5
  %.9 = load i32, ptr @MAXN
  %.10 = mul i32 %.9, 4
  %.11 = add i32 %.10, 4
  %.12 = call ptr @__newPtrArray(i32 %.11, i32 %.9)
  store ptr %.12, ptr %.8
  %.14 = load i32, ptr @MAXN
  %.15 = mul i32 %.14, 4
  %.16 = add i32 %.15, 4
  %.17 = call ptr @__newPtrArray(i32 %.16, i32 %.14)
  store ptr %.17, ptr %.13
  %.19 = load i32, ptr @MAXN
  %.20 = mul i32 %.19, 4
  %.21 = add i32 %.20, 4
  %.22 = call ptr @__newPtrArray(i32 %.21, i32 %.19)
  store ptr %.22, ptr %.18
  store i32 0, ptr %.23
  br label %for.cond_2
for.cond_2:
  %.25 = load i32, ptr %.23
  %.26 = load i32, ptr %.5
  %.27 = icmp slt i32 %.25, %.26
  br i1 %.27, label %for.loop_3, label %for.end_5
for.loop_3:
  %.28 = call i32 @getInt()
  %.29 = load ptr, ptr %.8
  %.31 = load i32, ptr %.23
  %.30 = getelementptr i32, ptr %.29, i32 %.31
  store i32 %.28, ptr %.30
  %.32 = call i32 @getInt()
  %.33 = load ptr, ptr %.13
  %.35 = load i32, ptr %.23
  %.34 = getelementptr i32, ptr %.33, i32 %.35
  store i32 %.32, ptr %.34
  br label %for.step_4
for.step_4:
  %.37 = load i32, ptr %.23
  %.36 = add i32 %.37, 1
  store i32 %.36, ptr %.23
  br label %for.cond_2
for.end_5:
  store i32 0, ptr %.23
  br label %for.cond_6
for.cond_6:
  %.38 = load i32, ptr %.23
  %.39 = load i32, ptr %.5
  %.40 = icmp slt i32 %.38, %.39
  br i1 %.40, label %for.loop_7, label %for.end_9
for.loop_7:
  %.41 = load ptr, ptr %.13
  %.43 = load i32, ptr %.23
  %.42 = getelementptr i32, ptr %.41, i32 %.43
  %.44 = load i32, ptr %.42
  store i32 %.44, ptr %.24
  br label %for.cond_10
for.cond_10:
  %.45 = load i32, ptr %.24
  %.46 = load i32, ptr %.4
  %.47 = icmp sle i32 %.45, %.46
  br i1 %.47, label %for.loop_11, label %for.end_13
for.loop_11:
  %.48 = load ptr, ptr %.18
  %.50 = load i32, ptr %.24
  %.49 = getelementptr i32, ptr %.48, i32 %.50
  %.51 = load ptr, ptr %.13
  %.53 = load i32, ptr %.23
  %.52 = getelementptr i32, ptr %.51, i32 %.53
  %.54 = load i32, ptr %.24
  %.55 = load i32, ptr %.52
  %.56 = sub i32 %.54, %.55
  %.57 = load ptr, ptr %.18
  %.58 = getelementptr i32, ptr %.57, i32 %.56
  %.59 = load ptr, ptr %.8
  %.61 = load i32, ptr %.23
  %.60 = getelementptr i32, ptr %.59, i32 %.61
  %.62 = load i32, ptr %.58
  %.63 = load i32, ptr %.60
  %.64 = add i32 %.62, %.63
  %.65 = load i32, ptr %.49
  %.66 = icmp sle i32 %.65, %.64
  br i1 %.66, label %if.then_15, label %if.end_14
if.then_15:
  %.67 = load ptr, ptr %.13
  %.69 = load i32, ptr %.23
  %.68 = getelementptr i32, ptr %.67, i32 %.69
  %.70 = load i32, ptr %.24
  %.71 = load i32, ptr %.68
  %.72 = sub i32 %.70, %.71
  %.73 = load ptr, ptr %.18
  %.74 = getelementptr i32, ptr %.73, i32 %.72
  %.75 = load ptr, ptr %.8
  %.77 = load i32, ptr %.23
  %.76 = getelementptr i32, ptr %.75, i32 %.77
  %.78 = load i32, ptr %.74
  %.79 = load i32, ptr %.76
  %.80 = add i32 %.78, %.79
  %.81 = load ptr, ptr %.18
  %.83 = load i32, ptr %.24
  %.82 = getelementptr i32, ptr %.81, i32 %.83
  store i32 %.80, ptr %.82
  br label %if.end_14
if.end_14:
  br label %for.step_12
for.step_12:
  %.85 = load i32, ptr %.24
  %.84 = add i32 %.85, 1
  store i32 %.84, ptr %.24
  br label %for.cond_10
for.end_13:
  br label %for.step_8
for.step_8:
  %.87 = load i32, ptr %.23
  %.86 = add i32 %.87, 1
  store i32 %.86, ptr %.23
  br label %for.cond_6
for.end_9:
  %.88 = load ptr, ptr %.18
  %.90 = load i32, ptr %.4
  %.89 = getelementptr i32, ptr %.88, i32 %.90
  %.91 = load i32, ptr %.89
  %.92 = call ptr @toString(i32 %.91)
  call void @print(ptr %.92)
  store i32 0, ptr %retval
  br label %return_1
return_1:
  %.3 = load i32, ptr %retval
  ret i32 %.3
}

