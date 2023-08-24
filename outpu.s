  .section .data
N:
  .word 15000
b:
  .word 0
resultCount:
  .word 0
  .section .rodata
.str.0:
  .string " "
.str.1:
  .string "Total: "
  .text
  .globl global_init
global_init:
.L0:
  li t0, -8
  add sp, sp, t0
  sw ra, 0(sp)
  li t1, 15005
  mv a0, t1
  li t1, 15001
  mv a1, t1
  call __newPtrArray
  mv t0, a0
  sw t0, 4(sp)
  lui t1, %hi(b)
  addi t1, t1, %lo(b)
  lw t0, 4(sp)
  sw t0, 0(t1)
  j .L1
.L1:
  lw ra, 0(sp)
  li t0, 8
  add sp, sp, t0
  ret
  .text
  .globl main
main:
.L2:
  li t0, -252
  add sp, sp, t0
  sw ra, 0(sp)
  call global_init
  li t0, 4
  add t0, sp, t0
  sw t0, 20(sp)
  li t0, 8
  add t0, sp, t0
  sw t0, 24(sp)
  li t0, 12
  add t0, sp, t0
  sw t0, 28(sp)
  li t0, 16
  add t0, sp, t0
  sw t0, 32(sp)
  lw t1, 24(sp)
  li t0, 1
  sw t0, 0(t1)
  j .L3
.L3:
  lw t1, 24(sp)
  lw t0, 0(t1)
  sw t0, 36(sp)
  lui t1, %hi(N)
  addi t1, t1, %lo(N)
  lw t0, 0(t1)
  sw t0, 40(sp)
  lw t1, 40(sp)
  lw t0, 36(sp)
  slt t0, t1, t0
  sw t0, 44(sp)
  lw t1, 44(sp)
  xori t0, t1, 1
  sb t0, 48(sp)
  lb t1, 48(sp)
  beqz t1, .L6
  j .L4
.L4:
  lui t1, %hi(b)
  addi t1, t1, %lo(b)
  lw t0, 0(t1)
  sw t0, 52(sp)
  lw t1, 24(sp)
  lw t0, 0(t1)
  sw t0, 56(sp)
  lw t1, 52(sp)
  lw t0, 56(sp)
  add t0, t1, t0
  sw t0, 60(sp)
  lw t1, 60(sp)
  li t0, 1
  sb t0, 0(t1)
  j .L5
.L5:
  lw t1, 24(sp)
  lw t0, 0(t1)
  sw t0, 64(sp)
  lw t1, 64(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 68(sp)
  lw t1, 24(sp)
  lw t0, 68(sp)
  sw t0, 0(t1)
  j .L3
.L6:
  lw t1, 24(sp)
  li t0, 2
  sw t0, 0(t1)
  j .L7
.L7:
  lw t1, 24(sp)
  lw t0, 0(t1)
  sw t0, 72(sp)
  lui t1, %hi(N)
  addi t1, t1, %lo(N)
  lw t0, 0(t1)
  sw t0, 76(sp)
  lw t1, 76(sp)
  lw t0, 72(sp)
  slt t0, t1, t0
  sw t0, 80(sp)
  lw t1, 80(sp)
  xori t0, t1, 1
  sb t0, 84(sp)
  lb t1, 84(sp)
  beqz t1, .L21
  j .L8
.L8:
  lui t1, %hi(b)
  addi t1, t1, %lo(b)
  lw t0, 0(t1)
  sw t0, 88(sp)
  lw t1, 24(sp)
  lw t0, 0(t1)
  sw t0, 92(sp)
  lw t1, 88(sp)
  lw t0, 92(sp)
  add t0, t1, t0
  sw t0, 96(sp)
  lw t1, 96(sp)
  lb t0, 0(t1)
  sb t0, 100(sp)
  lb t1, 100(sp)
  beqz t1, .L19
  j .L9
.L9:
  lw t1, 28(sp)
  li t0, 2
  sw t0, 0(t1)
  lw t1, 24(sp)
  lw t0, 0(t1)
  sw t0, 104(sp)
  li t1, 3
  lw t0, 104(sp)
  slt t0, t1, t0
  sb t0, 112(sp)
  lb t1, 112(sp)
  beqz t1, .L12
  j .L10
.L10:
  lw t1, 24(sp)
  lw t0, 0(t1)
  sw t0, 116(sp)
  lw t1, 116(sp)
  li t0, 2
  sub t0, t1, t0
  sw t0, 120(sp)
  lui t1, %hi(b)
  addi t1, t1, %lo(b)
  lw t0, 0(t1)
  sw t0, 124(sp)
  lw t1, 124(sp)
  lw t0, 120(sp)
  add t0, t1, t0
  sw t0, 128(sp)
  lw t1, 128(sp)
  lb t0, 0(t1)
  sb t0, 132(sp)
  lb t1, 132(sp)
  beqz t1, .L12
  j .L11
.L11:
  lw t1, 32(sp)
  li t0, 1
  sb t0, 0(t1)
  j .L13
.L12:
  lw t1, 32(sp)
  li t0, 0
  sb t0, 0(t1)
  j .L13
.L13:
  lw t1, 32(sp)
  lb t0, 0(t1)
  sb t0, 136(sp)
  lb t1, 136(sp)
  beqz t1, .L15
  j .L14
.L14:
  lui t1, %hi(resultCount)
  addi t1, t1, %lo(resultCount)
  lw t0, 0(t1)
  sw t0, 140(sp)
  lw t1, 140(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 144(sp)
  lui t1, %hi(resultCount)
  addi t1, t1, %lo(resultCount)
  lw t0, 144(sp)
  sw t0, 0(t1)
  lw t1, 24(sp)
  lw t0, 0(t1)
  sw t0, 148(sp)
  lw t1, 148(sp)
  li t0, 2
  sub t0, t1, t0
  sw t0, 152(sp)
  lw t1, 152(sp)
  mv a0, t1
  call toString
  mv t0, a0
  sw t0, 156(sp)
  lw t1, 156(sp)
  mv a0, t1
  lui t1, %hi(.str.0)
  addi t1, t1, %lo(.str.0)
  mv a1, t1
  call string_add
  mv t0, a0
  sw t0, 160(sp)
  lw t1, 24(sp)
  lw t0, 0(t1)
  sw t0, 164(sp)
  lw t1, 164(sp)
  mv a0, t1
  call toString
  mv t0, a0
  sw t0, 168(sp)
  lw t1, 160(sp)
  mv a0, t1
  lw t1, 168(sp)
  mv a1, t1
  call string_add
  mv t0, a0
  sw t0, 172(sp)
  lw t1, 172(sp)
  mv a0, t1
  call println
  j .L15
.L15:
  j .L16
.L16:
  lw t1, 24(sp)
  lw t0, 0(t1)
  sw t0, 176(sp)
  lw t1, 28(sp)
  lw t0, 0(t1)
  sw t0, 180(sp)
  lw t1, 176(sp)
  lw t0, 180(sp)
  mul t0, t1, t0
  sw t0, 184(sp)
  lui t1, %hi(N)
  addi t1, t1, %lo(N)
  lw t0, 0(t1)
  sw t0, 188(sp)
  lw t1, 188(sp)
  lw t0, 184(sp)
  slt t0, t1, t0
  sw t0, 192(sp)
  lw t1, 192(sp)
  xori t0, t1, 1
  sb t0, 196(sp)
  lb t1, 196(sp)
  beqz t1, .L18
  j .L17
.L17:
  lw t1, 24(sp)
  lw t0, 0(t1)
  sw t0, 200(sp)
  lw t1, 28(sp)
  lw t0, 0(t1)
  sw t0, 204(sp)
  lw t1, 200(sp)
  lw t0, 204(sp)
  mul t0, t1, t0
  sw t0, 208(sp)
  lui t1, %hi(b)
  addi t1, t1, %lo(b)
  lw t0, 0(t1)
  sw t0, 212(sp)
  lw t1, 212(sp)
  lw t0, 208(sp)
  add t0, t1, t0
  sw t0, 216(sp)
  lw t1, 216(sp)
  li t0, 0
  sb t0, 0(t1)
  lw t1, 28(sp)
  lw t0, 0(t1)
  sw t0, 220(sp)
  lw t1, 220(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 224(sp)
  lw t1, 28(sp)
  lw t0, 224(sp)
  sw t0, 0(t1)
  j .L16
.L18:
  j .L19
.L19:
  j .L20
.L20:
  lw t1, 24(sp)
  lw t0, 0(t1)
  sw t0, 228(sp)
  lw t1, 228(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 232(sp)
  lw t1, 24(sp)
  lw t0, 232(sp)
  sw t0, 0(t1)
  j .L7
.L21:
  lui t1, %hi(resultCount)
  addi t1, t1, %lo(resultCount)
  lw t0, 0(t1)
  sw t0, 236(sp)
  lw t1, 236(sp)
  mv a0, t1
  call toString
  mv t0, a0
  sw t0, 240(sp)
  lui t1, %hi(.str.1)
  addi t1, t1, %lo(.str.1)
  mv a0, t1
  lw t1, 240(sp)
  mv a1, t1
  call string_add
  mv t0, a0
  sw t0, 244(sp)
  lw t1, 244(sp)
  mv a0, t1
  call println
  lw t1, 20(sp)
  li t0, 0
  sw t0, 0(t1)
  j .L22
.L22:
  lw t1, 20(sp)
  lw t0, 0(t1)
  sw t0, 248(sp)
  lw t1, 248(sp)
  mv a0, t1
  lw ra, 0(sp)
  li t0, 252
  add sp, sp, t0
  ret
