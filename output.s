  .text
  .globl foo
foo:
.L0:
  li t0, -88
  add sp, sp, t0
  mv t0, a2
  sw t0, 12(sp)
  mv t0, a1
  sw t0, 8(sp)
  mv t0, a0
  sw t0, 4(sp)
  sw ra, 0(sp)
  lw t1, 4(sp)
  li t0, 1000
  mul t0, t1, t0
  sw t0, 16(sp)
  lw t1, 8(sp)
  li t0, 10
  mul t0, t1, t0
  sw t0, 20(sp)
  lw t1, 16(sp)
  lw t0, 20(sp)
  add t0, t1, t0
  sw t0, 24(sp)
  lw t1, 24(sp)
  lw t0, 12(sp)
  add t0, t1, t0
  sw t0, 28(sp)
  lw t1, 28(sp)
  mv a0, t1
  call toString
  mv t0, a0
  sw t0, 32(sp)
  lw t1, 32(sp)
  mv a0, t1
  call println
  lw t1, 4(sp)
  li t0, 1
  sub t0, t1, t0
  sw t0, 36(sp)
  lw t1, 36(sp)
  seqz t0, t1
  sb t0, 40(sp)
  lb t1, 40(sp)
  beqz t1, .L2
  j .L1
.L1:
  lw t1, 8(sp)
  mv t0, t1
  sw t0, 64(sp)
  lw t1, 12(sp)
  mv t0, t1
  sw t0, 72(sp)
  li t0, 0
  sw t0, 80(sp)
  j .L3
.L2:
  li t1, 1
  mv a0, t1
  lw t1, 12(sp)
  mv a1, t1
  lw t1, 8(sp)
  mv a2, t1
  call foo
  lw t1, 4(sp)
  li t0, 1000
  mul t0, t1, t0
  sw t0, 44(sp)
  lw t1, 12(sp)
  li t0, 10
  mul t0, t1, t0
  sw t0, 48(sp)
  lw t1, 44(sp)
  lw t0, 48(sp)
  add t0, t1, t0
  sw t0, 52(sp)
  lw t1, 52(sp)
  lw t0, 8(sp)
  add t0, t1, t0
  sw t0, 56(sp)
  lw t1, 56(sp)
  mv a0, t1
  call toString
  mv t0, a0
  sw t0, 60(sp)
  lw t1, 60(sp)
  mv a0, t1
  call println
  lw t1, 12(sp)
  mv t0, t1
  sw t0, 64(sp)
  lw t1, 8(sp)
  mv t0, t1
  sw t0, 72(sp)
  lw t1, 8(sp)
  mv t0, t1
  sw t0, 80(sp)
  j .L3
.L3:
  lw t1, 64(sp)
  mv t0, t1
  sw t0, 68(sp)
  lw t1, 72(sp)
  mv t0, t1
  sw t0, 76(sp)
  lw t1, 80(sp)
  mv t0, t1
  sw t0, 84(sp)
  lw ra, 0(sp)
  li t0, 88
  add sp, sp, t0
  ret
  .text
  .globl main
main:
.L4:
  li t0, -4
  add sp, sp, t0
  sw ra, 0(sp)
  li t1, 7
  mv a0, t1
  li t1, 5
  mv a1, t1
  li t1, 3
  mv a2, t1
  call foo
  j .L5
.L5:
  li t1, 0
  mv a0, t1
  lw ra, 0(sp)
  li t0, 4
  add sp, sp, t0
  ret
