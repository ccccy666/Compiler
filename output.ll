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

define void @foo(i32 %.0, i32 %.1, i32 %.2, i32 %.3, i32 %.4, i32 %.5, i32 %.6, i32 %.7, i32 %.8, i32 %.9) {
entry0:
  %q.addr.10 = alloca i32
  %w.addr.11 = alloca i32
  %e.addr.12 = alloca i32
  %ee.addr.13 = alloca i32
  %r.addr.14 = alloca i32
  %y.addr.15 = alloca i32
  %yy.addr.16 = alloca i32
  %yu.addr.17 = alloca i32
  %g.addr.18 = alloca i32
  %fff.addr.19 = alloca i32
  %mmmm.addr.20 = alloca i32
  store i32 %.0, ptr %q.addr.10
  store i32 %.1, ptr %w.addr.11
  store i32 %.2, ptr %e.addr.12
  store i32 %.3, ptr %ee.addr.13
  store i32 %.4, ptr %r.addr.14
  store i32 %.5, ptr %y.addr.15
  store i32 %.6, ptr %yy.addr.16
  store i32 %.7, ptr %yu.addr.17
  store i32 %.8, ptr %g.addr.18
  store i32 %.9, ptr %fff.addr.19
  store i32 1, ptr %mmmm.addr.20
  br label %return1
return1:
  ret void
}

define i32 @main() {
entry0:
  %retval = alloca i32
  %f.addr.0 = alloca i32
  call void @foo(i32 1, i32 2, i32 3, i32 4, i32 5, i32 6, i32 7, i32 8, i32 9, i32 10)
  store i32 1, ptr %f.addr.0
  br label %return1
return1:
  %ret.1 = load i32, ptr %retval
  ret i32 %ret.1
}

