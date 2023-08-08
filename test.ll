@formatString = private unnamed_addr constant [4 x i8] c"%d\0A\00"

define i32 @add(i32 %a, i32 %b) {
    %sum = add i32 %a, %b
    ret i32 %sum
}

declare i32 @printf(i8*, ...)

define void @main() {
    %a = alloca i32
    %b = alloca i32
    
    store i32 10, i32* %a
    store i32 20, i32* %b
    
    %valA = load i32, i32* %a
    %valB = load i32, i32* %b
    
    %result = call i32 @add(i32 %valA, i32 %valB)
    
    %castedFormatString = getelementptr [4 x i8], [4 x i8]* @formatString, i32 0, i32 0
    
    call i32 (i8*, ...) @printf(i8* %castedFormatString, i32 %result)
    
    ret void
}
