  .text
  .globl foo
foo:
.L0:
  li t0, -92
  add sp, sp, t0
  sw ra, 0(sp)
  li t0, 4
  add t0, sp, t0
  sw t0, 48(sp)
  li t0, 8
  add t0, sp, t0
  sw t0, 52(sp)
  li t0, 12
  add t0, sp, t0
  sw t0, 56(sp)
  li t0, 16
  add t0, sp, t0
  sw t0, 60(sp)
  li t0, 20
  add t0, sp, t0
  sw t0, 64(sp)
  li t0, 24
  add t0, sp, t0
  sw t0, 68(sp)
  li t0, 28
  add t0, sp, t0
  sw t0, 72(sp)
  li t0, 32
  add t0, sp, t0
  sw t0, 76(sp)
  li t0, 36
  add t0, sp, t0
  sw t0, 80(sp)
  li t0, 40
  add t0, sp, t0
  sw t0, 84(sp)
  li t0, 44
  add t0, sp, t0
  sw t0, 88(sp)
  lw t1, 48(sp)
  sw a0, 0(t1)
  lw t1, 52(sp)
  sw a1, 0(t1)
  lw t1, 56(sp)
  sw a2, 0(t1)
  lw t1, 60(sp)
  sw a3, 0(t1)
  lw t1, 64(sp)
  sw a4, 0(t1)
  lw t1, 68(sp)
  sw a5, 0(t1)
  lw t1, 72(sp)
  sw a6, 0(t1)
  lw t1, 76(sp)
  sw a7, 0(t1)
  lw t1, 80(sp)
  lw t0, 92(sp)
  sw t0, 0(t1)
  lw t1, 84(sp)
  lw t0, 96(sp)
  sw t0, 0(t1)
  lw t1, 88(sp)
  li t0, 1
  sw t0, 0(t1)
  j .L1
.L1:
  lw ra, 0(sp)
  li t0, 92
  add sp, sp, t0
  ret
  .text
  .globl main
main:
.L2:
  li t0, -32
  add sp, sp, t0
  sw ra, 8(sp)
  li t0, 12
  add t0, sp, t0
  sw t0, 20(sp)
  li t0, 16
  add t0, sp, t0
  sw t0, 24(sp)
  li t1, 1
  mv a0, t1
  li t1, 2
  mv a1, t1
  li t1, 3
  mv a2, t1
  li t1, 4
  mv a3, t1
  li t1, 5
  mv a4, t1
  li t1, 6
  mv a5, t1
  li t1, 7
  mv a6, t1
  li t1, 8
  mv a7, t1
  li t0, 9
  sw t0, 0(sp)
  li t0, 10
  sw t0, 4(sp)
  call foo
  lw t1, 24(sp)
  li t0, 1
  sw t0, 0(t1)
  j .L3
.L3:
  lw t1, 20(sp)
  lw t0, 0(t1)
  sw t0, 28(sp)
  lw t1, 28(sp)
  mv a0, t1
  lw ra, 8(sp)
  li t0, 32
  add sp, sp, t0
  ret
