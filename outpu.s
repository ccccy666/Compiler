  .section .rodata
.str.0:
  .string "uyy"
.str.2:
  .string "yuv"
.str.1:
  .string "yuy"
  .text
  .globl main
main:
.L0:
  li t0, -80
  add sp, sp, t0
  sw ra, 0(sp)
  call getInt
  mv t0, a0
  sw t0, 4(sp)
  call getInt
  mv t0, a0
  sw t0, 8(sp)
  call getInt
  mv t0, a0
  sw t0, 12(sp)
  lw t1, 4(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 16(sp)
  lw t1, 16(sp)
  lw t0, 8(sp)
  sub t0, t1, t0
  sw t0, 20(sp)
  lw t1, 20(sp)
  seqz t0, t1
  sb t0, 24(sp)
  lb t1, 24(sp)
  beqz t1, .L2
  j .L1
.L1:
  lui t0, %hi(.str.0)
  sw t0, 40(sp)
  lw t1, 40(sp)
  addi t0, t1, %lo(.str.0)
  sw t0, 40(sp)
  lw t1, 40(sp)
  mv t0, t1
  sw t0, 28(sp)
  j .L3
.L2:
  lui t0, %hi(.str.1)
  sw t0, 36(sp)
  lw t1, 36(sp)
  addi t0, t1, %lo(.str.1)
  sw t0, 36(sp)
  lw t1, 36(sp)
  mv t0, t1
  sw t0, 28(sp)
  j .L3
.L3:
  lw t1, 28(sp)
  mv t0, t1
  sw t0, 32(sp)
  lw t1, 12(sp)
  li t0, 1
  sub t0, t1, t0
  sw t0, 44(sp)
  lw t1, 8(sp)
  lw t0, 44(sp)
  sub t0, t1, t0
  sw t0, 48(sp)
  lw t1, 48(sp)
  seqz t0, t1
  sb t0, 52(sp)
  lb t1, 52(sp)
  beqz t1, .L5
  j .L4
.L4:
  lui t0, %hi(.str.0)
  sw t0, 68(sp)
  lw t1, 68(sp)
  addi t0, t1, %lo(.str.0)
  sw t0, 68(sp)
  lw t1, 68(sp)
  mv t0, t1
  sw t0, 56(sp)
  j .L6
.L5:
  lui t0, %hi(.str.2)
  sw t0, 64(sp)
  lw t1, 64(sp)
  addi t0, t1, %lo(.str.2)
  sw t0, 64(sp)
  lw t1, 64(sp)
  mv t0, t1
  sw t0, 56(sp)
  j .L6
.L6:
  lw t1, 56(sp)
  mv t0, t1
  sw t0, 60(sp)
  lw t1, 72(sp)
  mv a0, t1
  lw t1, 60(sp)
  mv a1, t1
  call streq
  mv t0, a0
  sb t0, 76(sp)
  lb t1, 76(sp)
  beqz t1, .L8
  j .L7
.L7:
  lw t1, 16(sp)
  mv a0, t1
  call printlnInt
  j .L8
.L8:
  j .L9
.L9:
  li t1, 0
  mv a0, t1
  lw ra, 0(sp)
  li t0, 80
  add sp, sp, t0
  ret
