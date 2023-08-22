; ModuleID = 'builtin.c'
source_filename = "builtin.c"
target datalayout = "e-m:e-p:32:32-p270:32:32-p271:32:32-p272:64:64-f64:32:64-f80:32-n8:16:32-S128"
target triple = "i386-pc-linux-gnu"

@.str = private unnamed_addr constant [3 x i8] c"%s\00", align 1
@.str.1 = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str.2 = private unnamed_addr constant [4 x i8] c"%s\0A\00", align 1
@.str.3 = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local zeroext i8 @strlt(ptr noundef %s, ptr noundef %t) #0 {
entry:
  %s.addr = alloca ptr, align 4
  %t.addr = alloca ptr, align 4
  store ptr %s, ptr %s.addr, align 4
  store ptr %t, ptr %t.addr, align 4
  %0 = load ptr, ptr %s.addr, align 4
  %1 = load ptr, ptr %t.addr, align 4
  %call = call i32 @strcmp(ptr noundef %0, ptr noundef %1)
  %cmp = icmp slt i32 %call, 0
  %conv = zext i1 %cmp to i32
  %conv1 = trunc i32 %conv to i8
  ret i8 %conv1
}

declare i32 @strcmp(ptr noundef, ptr noundef) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local zeroext i8 @strle(ptr noundef %s, ptr noundef %t) #0 {
entry:
  %s.addr = alloca ptr, align 4
  %t.addr = alloca ptr, align 4
  store ptr %s, ptr %s.addr, align 4
  store ptr %t, ptr %t.addr, align 4
  %0 = load ptr, ptr %s.addr, align 4
  %1 = load ptr, ptr %t.addr, align 4
  %call = call i32 @strcmp(ptr noundef %0, ptr noundef %1)
  %cmp = icmp sle i32 %call, 0
  %conv = zext i1 %cmp to i32
  %conv1 = trunc i32 %conv to i8
  ret i8 %conv1
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local zeroext i8 @strgt(ptr noundef %s, ptr noundef %t) #0 {
entry:
  %s.addr = alloca ptr, align 4
  %t.addr = alloca ptr, align 4
  store ptr %s, ptr %s.addr, align 4
  store ptr %t, ptr %t.addr, align 4
  %0 = load ptr, ptr %s.addr, align 4
  %1 = load ptr, ptr %t.addr, align 4
  %call = call i32 @strcmp(ptr noundef %0, ptr noundef %1)
  %cmp = icmp sgt i32 %call, 0
  %conv = zext i1 %cmp to i32
  %conv1 = trunc i32 %conv to i8
  ret i8 %conv1
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local zeroext i8 @strge(ptr noundef %s, ptr noundef %t) #0 {
entry:
  %s.addr = alloca ptr, align 4
  %t.addr = alloca ptr, align 4
  store ptr %s, ptr %s.addr, align 4
  store ptr %t, ptr %t.addr, align 4
  %0 = load ptr, ptr %s.addr, align 4
  %1 = load ptr, ptr %t.addr, align 4
  %call = call i32 @strcmp(ptr noundef %0, ptr noundef %1)
  %cmp = icmp sge i32 %call, 0
  %conv = zext i1 %cmp to i32
  %conv1 = trunc i32 %conv to i8
  ret i8 %conv1
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local zeroext i8 @streq(ptr noundef %s, ptr noundef %t) #0 {
entry:
  %s.addr = alloca ptr, align 4
  %t.addr = alloca ptr, align 4
  store ptr %s, ptr %s.addr, align 4
  store ptr %t, ptr %t.addr, align 4
  %0 = load ptr, ptr %s.addr, align 4
  %1 = load ptr, ptr %t.addr, align 4
  %call = call i32 @strcmp(ptr noundef %0, ptr noundef %1)
  %cmp = icmp eq i32 %call, 0
  %conv = zext i1 %cmp to i32
  %conv1 = trunc i32 %conv to i8
  ret i8 %conv1
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local zeroext i8 @strneq(ptr noundef %s, ptr noundef %t) #0 {
entry:
  %s.addr = alloca ptr, align 4
  %t.addr = alloca ptr, align 4
  store ptr %s, ptr %s.addr, align 4
  store ptr %t, ptr %t.addr, align 4
  %0 = load ptr, ptr %s.addr, align 4
  %1 = load ptr, ptr %t.addr, align 4
  %call = call i32 @strcmp(ptr noundef %0, ptr noundef %1)
  %cmp = icmp ne i32 %call, 0
  %conv = zext i1 %cmp to i32
  %conv1 = trunc i32 %conv to i8
  ret i8 %conv1
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local ptr @getString() #0 {
entry:
  %s = alloca ptr, align 4
  %call = call ptr @malloc(i32 noundef 256) #3
  store ptr %call, ptr %s, align 4
  %0 = load ptr, ptr %s, align 4
  %call1 = call i32 (ptr, ...) @scanf(ptr noundef @.str, ptr noundef %0)
  %1 = load ptr, ptr %s, align 4
  ret ptr %1
}

; Function Attrs: allocsize(0)
declare ptr @malloc(i32 noundef) #2

declare i32 @scanf(ptr noundef, ...) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @getInt() #0 {
entry:
  %x = alloca i32, align 4
  %call = call i32 (ptr, ...) @scanf(ptr noundef @.str.1, ptr noundef %x)
  %0 = load i32, ptr %x, align 4
  ret i32 %0
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local ptr @toString(i32 noundef %x) #0 {
entry:
  %x.addr = alloca i32, align 4
  %s = alloca ptr, align 4
  store i32 %x, ptr %x.addr, align 4
  %call = call ptr @malloc(i32 noundef 16) #3
  store ptr %call, ptr %s, align 4
  %0 = load ptr, ptr %s, align 4
  %1 = load i32, ptr %x.addr, align 4
  %call1 = call i32 (ptr, ptr, ...) @sprintf(ptr noundef %0, ptr noundef @.str.1, i32 noundef %1)
  %2 = load ptr, ptr %s, align 4
  ret ptr %2
}

declare i32 @sprintf(ptr noundef, ptr noundef, ...) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local ptr @substring(ptr noundef %s, i32 noundef %l, i32 noundef %r) #0 {
entry:
  %s.addr = alloca ptr, align 4
  %l.addr = alloca i32, align 4
  %r.addr = alloca i32, align 4
  %t = alloca ptr, align 4
  %i = alloca i32, align 4
  store ptr %s, ptr %s.addr, align 4
  store i32 %l, ptr %l.addr, align 4
  store i32 %r, ptr %r.addr, align 4
  %0 = load i32, ptr %r.addr, align 4
  %1 = load i32, ptr %l.addr, align 4
  %sub = sub nsw i32 %0, %1
  %add = add nsw i32 %sub, 1
  %call = call ptr @malloc(i32 noundef %add) #3
  store ptr %call, ptr %t, align 4
  %2 = load i32, ptr %l.addr, align 4
  store i32 %2, ptr %i, align 4
  br label %for.cond

for.cond:                                         ; preds = %for.inc, %entry
  %3 = load i32, ptr %i, align 4
  %4 = load i32, ptr %r.addr, align 4
  %cmp = icmp slt i32 %3, %4
  br i1 %cmp, label %for.body, label %for.end

for.body:                                         ; preds = %for.cond
  %5 = load ptr, ptr %s.addr, align 4
  %6 = load i32, ptr %i, align 4
  %arrayidx = getelementptr inbounds i8, ptr %5, i32 %6
  %7 = load i8, ptr %arrayidx, align 1
  %8 = load ptr, ptr %t, align 4
  %9 = load i32, ptr %i, align 4
  %10 = load i32, ptr %l.addr, align 4
  %sub1 = sub nsw i32 %9, %10
  %arrayidx2 = getelementptr inbounds i8, ptr %8, i32 %sub1
  store i8 %7, ptr %arrayidx2, align 1
  br label %for.inc

for.inc:                                          ; preds = %for.body
  %11 = load i32, ptr %i, align 4
  %inc = add nsw i32 %11, 1
  store i32 %inc, ptr %i, align 4
  br label %for.cond, !llvm.loop !7

for.end:                                          ; preds = %for.cond
  %12 = load ptr, ptr %t, align 4
  %13 = load i32, ptr %r.addr, align 4
  %14 = load i32, ptr %l.addr, align 4
  %sub3 = sub nsw i32 %13, %14
  %arrayidx4 = getelementptr inbounds i8, ptr %12, i32 %sub3
  store i8 0, ptr %arrayidx4, align 1
  %15 = load ptr, ptr %t, align 4
  ret ptr %15
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @parseInt(ptr noundef %s) #0 {
entry:
  %s.addr = alloca ptr, align 4
  %x = alloca i32, align 4
  store ptr %s, ptr %s.addr, align 4
  %0 = load ptr, ptr %s.addr, align 4
  %call = call i32 (ptr, ptr, ...) @sscanf(ptr noundef %0, ptr noundef @.str.1, ptr noundef %x)
  %1 = load i32, ptr %x, align 4
  ret i32 %1
}

declare i32 @sscanf(ptr noundef, ptr noundef, ...) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @ord(ptr noundef %s, i32 noundef %x) #0 {
entry:
  %s.addr = alloca ptr, align 4
  %x.addr = alloca i32, align 4
  store ptr %s, ptr %s.addr, align 4
  store i32 %x, ptr %x.addr, align 4
  %0 = load ptr, ptr %s.addr, align 4
  %1 = load i32, ptr %x.addr, align 4
  %arrayidx = getelementptr inbounds i8, ptr %0, i32 %1
  %2 = load i8, ptr %arrayidx, align 1
  %conv = sext i8 %2 to i32
  ret i32 %conv
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local ptr @stradd(ptr noundef %s, ptr noundef %t) #0 {
entry:
  %s.addr = alloca ptr, align 4
  %t.addr = alloca ptr, align 4
  %p = alloca ptr, align 4
  store ptr %s, ptr %s.addr, align 4
  store ptr %t, ptr %t.addr, align 4
  %0 = load ptr, ptr %s.addr, align 4
  %call = call i32 @strlen(ptr noundef %0)
  %1 = load ptr, ptr %t.addr, align 4
  %call1 = call i32 @strlen(ptr noundef %1)
  %add = add i32 %call, %call1
  %add2 = add i32 %add, 1
  %call3 = call ptr @malloc(i32 noundef %add2) #3
  store ptr %call3, ptr %p, align 4
  %2 = load ptr, ptr %p, align 4
  %3 = load ptr, ptr %s.addr, align 4
  %call4 = call ptr @strcpy(ptr noundef %2, ptr noundef %3)
  %4 = load ptr, ptr %p, align 4
  %5 = load ptr, ptr %t.addr, align 4
  %call5 = call ptr @strcat(ptr noundef %4, ptr noundef %5)
  %6 = load ptr, ptr %p, align 4
  ret ptr %6
}

declare i32 @strlen(ptr noundef) #1

declare ptr @strcpy(ptr noundef, ptr noundef) #1

declare ptr @strcat(ptr noundef, ptr noundef) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @print(ptr noundef %s) #0 {
entry:
  %s.addr = alloca ptr, align 4
  store ptr %s, ptr %s.addr, align 4
  %0 = load ptr, ptr %s.addr, align 4
  %call = call i32 (ptr, ...) @printf(ptr noundef @.str, ptr noundef %0)
  ret void
}

declare i32 @printf(ptr noundef, ...) #1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @println(ptr noundef %s) #0 {
entry:
  %s.addr = alloca ptr, align 4
  store ptr %s, ptr %s.addr, align 4
  %0 = load ptr, ptr %s.addr, align 4
  %call = call i32 (ptr, ...) @printf(ptr noundef @.str.2, ptr noundef %0)
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @printInt(i32 noundef %x) #0 {
entry:
  %x.addr = alloca i32, align 4
  store i32 %x, ptr %x.addr, align 4
  %0 = load i32, ptr %x.addr, align 4
  %call = call i32 (ptr, ...) @printf(ptr noundef @.str.1, i32 noundef %0)
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @printlnInt(i32 noundef %x) #0 {
entry:
  %x.addr = alloca i32, align 4
  store i32 %x, ptr %x.addr, align 4
  %0 = load i32, ptr %x.addr, align 4
  %call = call i32 (ptr, ...) @printf(ptr noundef @.str.3, i32 noundef %0)
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @array_size(ptr noundef %__this) #0 {
entry:
  %__this.addr = alloca ptr, align 4
  store ptr %__this, ptr %__this.addr, align 4
  %0 = load ptr, ptr %__this.addr, align 4
  %arrayidx = getelementptr inbounds i32, ptr %0, i32 -1
  %1 = load i32, ptr %arrayidx, align 4
  ret i32 %1
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local ptr @newPtrArray(i32 noundef %size) #0 {
entry:
  %size.addr = alloca i32, align 4
  %array = alloca ptr, align 4
  store i32 %size, ptr %size.addr, align 4
  %0 = load i32, ptr %size.addr, align 4
  %shl = shl i32 %0, 2
  %add = add nsw i32 %shl, 4
  %call = call ptr @malloc(i32 noundef %add) #3
  store ptr %call, ptr %array, align 4
  %1 = load i32, ptr %size.addr, align 4
  %2 = load ptr, ptr %array, align 4
  %arrayidx = getelementptr inbounds i32, ptr %2, i32 0
  store i32 %1, ptr %arrayidx, align 4
  %3 = load ptr, ptr %array, align 4
  %add.ptr = getelementptr inbounds i32, ptr %3, i32 1
  ret ptr %add.ptr
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local ptr @newClass(i32 noundef %size) #0 {
entry:
  %size.addr = alloca i32, align 4
  %cl = alloca ptr, align 4
  store i32 %size, ptr %size.addr, align 4
  %0 = load i32, ptr %size.addr, align 4
  %call = call ptr @malloc(i32 noundef %0) #3
  store ptr %call, ptr %cl, align 4
  %1 = load ptr, ptr %cl, align 4
  ret ptr %1
}

attributes #0 = { noinline nounwind optnone uwtable "frame-pointer"="all" "min-legal-vector-width"="0" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="i686" "target-features"="+cx8,+x87" "tune-cpu"="generic" }
attributes #1 = { "frame-pointer"="all" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="i686" "target-features"="+cx8,+x87" "tune-cpu"="generic" }
attributes #2 = { allocsize(0) "frame-pointer"="all" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="i686" "target-features"="+cx8,+x87" "tune-cpu"="generic" }
attributes #3 = { allocsize(0) }

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
