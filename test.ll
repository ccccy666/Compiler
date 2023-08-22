; ModuleID = 'test.cpp'
source_filename = "test.cpp"
target datalayout = "e-m:e-p:32:32-p270:32:32-p271:32:32-p272:64:64-f64:32:64-f80:32-n8:16:32-S128"
target triple = "i386-pc-linux-gnu"

@.str = private unnamed_addr constant [7 x i8] c"%d%d%d\00", align 1
@.str.1 = private unnamed_addr constant [4 x i8] c"<< \00", align 1
@.str.2 = private unnamed_addr constant [6 x i8] c"(%d) \00", align 1
@.str.3 = private unnamed_addr constant [4 x i8] c"%d \00", align 1
@.str.4 = private unnamed_addr constant [4 x i8] c">> \00", align 1

; Function Attrs: mustprogress noinline norecurse optnone uwtable
define dso_local noundef i32 @main() #0 {
entry:
  %retval = alloca i32, align 4
  %n = alloca i32, align 4
  %p = alloca i32, align 4
  %k = alloca i32, align 4
  %i = alloca i32, align 4
  store i32 0, ptr %retval, align 4
  %call = call i32 (ptr, ...) @__isoc99_scanf(ptr noundef @.str, ptr noundef %n, ptr noundef %p, ptr noundef %k)
  %0 = load i32, ptr %p, align 4
  %1 = load i32, ptr %k, align 4
  %sub = sub nsw i32 %0, %1
  %cmp = icmp sgt i32 %sub, 1
  br i1 %cmp, label %if.then, label %if.end

if.then:                                          ; preds = %entry
  %call1 = call i32 (ptr, ...) @printf(ptr noundef @.str.1)
  br label %if.end

if.end:                                           ; preds = %if.then, %entry
  %2 = load i32, ptr %p, align 4
  %3 = load i32, ptr %k, align 4
  %sub2 = sub nsw i32 %2, %3
  store i32 %sub2, ptr %i, align 4
  br label %for.cond

for.cond:                                         ; preds = %for.inc, %if.end
  %4 = load i32, ptr %i, align 4
  %5 = load i32, ptr %p, align 4
  %6 = load i32, ptr %k, align 4
  %add = add nsw i32 %5, %6
  %cmp3 = icmp sle i32 %4, %add
  br i1 %cmp3, label %for.body, label %for.end

for.body:                                         ; preds = %for.cond
  %7 = load i32, ptr %i, align 4
  %cmp4 = icmp sle i32 1, %7
  br i1 %cmp4, label %land.lhs.true, label %if.end12

land.lhs.true:                                    ; preds = %for.body
  %8 = load i32, ptr %i, align 4
  %9 = load i32, ptr %n, align 4
  %cmp5 = icmp sle i32 %8, %9
  br i1 %cmp5, label %if.then6, label %if.end12

if.then6:                                         ; preds = %land.lhs.true
  %10 = load i32, ptr %i, align 4
  %11 = load i32, ptr %p, align 4
  %cmp7 = icmp eq i32 %10, %11
  br i1 %cmp7, label %if.then8, label %if.else

if.then8:                                         ; preds = %if.then6
  %12 = load i32, ptr %i, align 4
  %call9 = call i32 (ptr, ...) @printf(ptr noundef @.str.2, i32 noundef %12)
  br label %if.end11

if.else:                                          ; preds = %if.then6
  %13 = load i32, ptr %i, align 4
  %call10 = call i32 (ptr, ...) @printf(ptr noundef @.str.3, i32 noundef %13)
  br label %if.end11

if.end11:                                         ; preds = %if.else, %if.then8
  br label %if.end12

if.end12:                                         ; preds = %if.end11, %land.lhs.true, %for.body
  br label %for.inc

for.inc:                                          ; preds = %if.end12
  %14 = load i32, ptr %i, align 4
  %inc = add nsw i32 %14, 1
  store i32 %inc, ptr %i, align 4
  br label %for.cond, !llvm.loop !7

for.end:                                          ; preds = %for.cond
  %15 = load i32, ptr %p, align 4
  %16 = load i32, ptr %k, align 4
  %add13 = add nsw i32 %15, %16
  %17 = load i32, ptr %n, align 4
  %cmp14 = icmp slt i32 %add13, %17
  br i1 %cmp14, label %if.then15, label %if.end17

if.then15:                                        ; preds = %for.end
  %call16 = call i32 (ptr, ...) @printf(ptr noundef @.str.4)
  br label %if.end17

if.end17:                                         ; preds = %if.then15, %for.end
  ret i32 0
}

declare i32 @__isoc99_scanf(ptr noundef, ...) #1

declare i32 @printf(ptr noundef, ...) #1

attributes #0 = { mustprogress noinline norecurse optnone uwtable "frame-pointer"="all" "min-legal-vector-width"="0" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="i686" "target-features"="+cx8,+x87" "tune-cpu"="generic" }
attributes #1 = { "frame-pointer"="all" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="i686" "target-features"="+cx8,+x87" "tune-cpu"="generic" }

!llvm.module.flags = !{!0, !1, !2, !3, !4, !5}
!llvm.ident = !{!6}

!0 = !{i32 1, !"NumRegisterParameters", i32 0}
!1 = !{i32 1, !"wchar_size", i32 4}
!2 = !{i32 7, !"PIC Level", i32 2}
!3 = !{i32 7, !"PIE Level", i32 2}
!4 = !{i32 7, !"uwtable", i32 2}
!5 = !{i32 7, !"frame-pointer", i32 2}
!6 = !{!"Ubuntu clang version 15.0.7"}
!7 = distinct !{!7, !8}
!8 = !{!"llvm.loop.mustprogress"}
