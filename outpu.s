  .section .data
b:
  .word 0
now:
  .word 0
t:
  .word 0
a:
  .word 0
n:
  .word 0
m:
  .word 0
p:
  .word 0
op:
  .word 0
L:
  .word 1
flag:
  .word 0
s:
  .word 0
sum:
  .word 0
ans:
  .word 0
aa:
  .word 13131
bb:
  .word 5353
MOD:
  .word 0
no:
  .word 1
pl:
  .word 0
pr:
  .word 0
  .text
  .globl init_
init_:
.L0:
  li t0, -84
  add sp, sp, t0
  sw ra, 0(sp)
  li t1, 500005
  mv a0, t1
  call newPtrArray
  mv t0, a0
  sw t0, 4(sp)
  lui t0, %hi(b)
  sw t0, 8(sp)
  lw t1, 8(sp)
  addi t0, t1, %lo(b)
  sw t0, 8(sp)
  lw t1, 8(sp)
  lw t0, 4(sp)
  sw t0, 0(t1)
  li t1, 500005
  mv a0, t1
  call newPtrArray
  mv t0, a0
  sw t0, 12(sp)
  lui t0, %hi(now)
  sw t0, 16(sp)
  lw t1, 16(sp)
  addi t0, t1, %lo(now)
  sw t0, 16(sp)
  lw t1, 16(sp)
  lw t0, 12(sp)
  sw t0, 0(t1)
  li t1, 500005
  mv a0, t1
  call newPtrArray
  mv t0, a0
  sw t0, 20(sp)
  lui t0, %hi(t)
  sw t0, 24(sp)
  lw t1, 24(sp)
  addi t0, t1, %lo(t)
  sw t0, 24(sp)
  lw t1, 24(sp)
  lw t0, 20(sp)
  sw t0, 0(t1)
  li t1, 200005
  mv a0, t1
  call newPtrArray
  mv t0, a0
  sw t0, 28(sp)
  lui t0, %hi(a)
  sw t0, 32(sp)
  lw t1, 32(sp)
  addi t0, t1, %lo(a)
  sw t0, 32(sp)
  lw t1, 32(sp)
  lw t0, 28(sp)
  sw t0, 0(t1)
  li t1, 500005
  mv a0, t1
  call newPtrArray
  mv t0, a0
  sw t0, 36(sp)
  lui t0, %hi(flag)
  sw t0, 40(sp)
  lw t1, 40(sp)
  addi t0, t1, %lo(flag)
  sw t0, 40(sp)
  lw t1, 40(sp)
  lw t0, 36(sp)
  sw t0, 0(t1)
  li t1, 500005
  mv a0, t1
  call newPtrArray
  mv t0, a0
  sw t0, 44(sp)
  li t0, 0
  sw t0, 48(sp)
  j .L1
.L1:
  lw t1, 48(sp)
  mv t0, t1
  sw t0, 52(sp)
  lw t1, 52(sp)
  li t0, 500005
  slt t0, t1, t0
  sb t0, 64(sp)
  lb t1, 64(sp)
  beqz t1, .L4
  j .L2
.L2:
  li t1, 80
  mv a0, t1
  call newPtrArray
  mv t0, a0
  sw t0, 68(sp)
  lw t1, 52(sp)
  slli t0, t1, 2
  sw t0, 72(sp)
  lw t1, 44(sp)
  lw t0, 72(sp)
  add t0, t1, t0
  sw t0, 76(sp)
  lw t1, 76(sp)
  lw t0, 68(sp)
  sw t0, 0(t1)
  j .L3
.L3:
  lw t1, 52(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 56(sp)
  lw t1, 56(sp)
  mv t0, t1
  sw t0, 48(sp)
  j .L1
.L4:
  lui t0, %hi(s)
  sw t0, 80(sp)
  lw t1, 80(sp)
  addi t0, t1, %lo(s)
  sw t0, 80(sp)
  lw t1, 80(sp)
  lw t0, 44(sp)
  sw t0, 0(t1)
  j .L5
.L5:
  lw ra, 0(sp)
  li t0, 84
  add sp, sp, t0
  ret
  .text
  .globl square
square:
.L6:
  li t0, -36
  add sp, sp, t0
  mv t0, a0
  sw t0, 4(sp)
  sw ra, 0(sp)
  lui t0, %hi(p)
  sw t0, 12(sp)
  lw t1, 12(sp)
  addi t0, t1, %lo(p)
  sw t0, 12(sp)
  lw t1, 12(sp)
  lw t0, 0(t1)
  sw t0, 8(sp)
  lw t1, 4(sp)
  lw t0, 8(sp)
  rem t0, t1, t0
  sw t0, 16(sp)
  lui t0, %hi(p)
  sw t0, 24(sp)
  lw t1, 24(sp)
  addi t0, t1, %lo(p)
  sw t0, 24(sp)
  lw t1, 24(sp)
  lw t0, 0(t1)
  sw t0, 20(sp)
  lw t1, 4(sp)
  lw t0, 20(sp)
  rem t0, t1, t0
  sw t0, 28(sp)
  lw t1, 16(sp)
  lw t0, 28(sp)
  mul t0, t1, t0
  sw t0, 32(sp)
  j .L7
.L7:
  lw t1, 32(sp)
  mv a0, t1
  lw ra, 0(sp)
  li t0, 36
  add sp, sp, t0
  ret
  .text
  .globl gcd
gcd:
.L8:
  li t0, -48
  add sp, sp, t0
  mv t0, a1
  sw t0, 8(sp)
  mv t0, a0
  sw t0, 4(sp)
  sw ra, 0(sp)
  lw t1, 8(sp)
  li t0, 0
  sub t0, t1, t0
  sw t0, 12(sp)
  lw t1, 12(sp)
  seqz t0, t1
  sb t0, 16(sp)
  lb t1, 16(sp)
  beqz t1, .L10
  j .L9
.L9:
  lw t1, 4(sp)
  mv t0, t1
  sw t0, 40(sp)
  j .L13
.L10:
  lw t1, 4(sp)
  lw t0, 8(sp)
  slt t0, t1, t0
  sb t0, 24(sp)
  lb t1, 24(sp)
  beqz t1, .L12
  j .L11
.L11:
  lw t1, 8(sp)
  mv a0, t1
  lw t1, 4(sp)
  mv a1, t1
  call gcd
  mv t0, a0
  sw t0, 28(sp)
  lw t1, 28(sp)
  mv t0, t1
  sw t0, 40(sp)
  j .L13
.L12:
  lw t1, 4(sp)
  lw t0, 8(sp)
  rem t0, t1, t0
  sw t0, 32(sp)
  lw t1, 8(sp)
  mv a0, t1
  lw t1, 32(sp)
  mv a1, t1
  call gcd
  mv t0, a0
  sw t0, 36(sp)
  lw t1, 36(sp)
  mv t0, t1
  sw t0, 40(sp)
  j .L13
.L13:
  lw t1, 40(sp)
  mv t0, t1
  sw t0, 44(sp)
  lw t1, 44(sp)
  mv a0, t1
  lw ra, 0(sp)
  li t0, 48
  add sp, sp, t0
  ret
  .text
  .globl lcm
lcm:
.L14:
  li t0, -24
  add sp, sp, t0
  mv t0, a1
  sw t0, 8(sp)
  mv t0, a0
  sw t0, 4(sp)
  sw ra, 0(sp)
  lw t1, 4(sp)
  mv a0, t1
  lw t1, 8(sp)
  mv a1, t1
  call gcd
  mv t0, a0
  sw t0, 12(sp)
  lw t1, 4(sp)
  lw t0, 12(sp)
  div t0, t1, t0
  sw t0, 16(sp)
  lw t1, 16(sp)
  lw t0, 8(sp)
  mul t0, t1, t0
  sw t0, 20(sp)
  j .L15
.L15:
  lw t1, 20(sp)
  mv a0, t1
  lw ra, 0(sp)
  li t0, 24
  add sp, sp, t0
  ret
  .text
  .globl Rand
Rand:
.L16:
  li t0, -112
  add sp, sp, t0
  sw ra, 0(sp)
  li t0, 1
  sw t0, 4(sp)
  j .L17
.L17:
  lw t1, 4(sp)
  mv t0, t1
  sw t0, 8(sp)
  lw t1, 8(sp)
  li t0, 3
  slt t0, t1, t0
  sb t0, 20(sp)
  lb t1, 20(sp)
  beqz t1, .L20
  j .L18
.L18:
  lui t0, %hi(no)
  sw t0, 28(sp)
  lw t1, 28(sp)
  addi t0, t1, %lo(no)
  sw t0, 28(sp)
  lw t1, 28(sp)
  lw t0, 0(t1)
  sw t0, 24(sp)
  lui t0, %hi(aa)
  sw t0, 36(sp)
  lw t1, 36(sp)
  addi t0, t1, %lo(aa)
  sw t0, 36(sp)
  lw t1, 36(sp)
  lw t0, 0(t1)
  sw t0, 32(sp)
  lw t1, 24(sp)
  lw t0, 32(sp)
  mul t0, t1, t0
  sw t0, 40(sp)
  lui t0, %hi(bb)
  sw t0, 48(sp)
  lw t1, 48(sp)
  addi t0, t1, %lo(bb)
  sw t0, 48(sp)
  lw t1, 48(sp)
  lw t0, 0(t1)
  sw t0, 44(sp)
  lw t1, 40(sp)
  lw t0, 44(sp)
  add t0, t1, t0
  sw t0, 52(sp)
  lui t0, %hi(MOD)
  sw t0, 60(sp)
  lw t1, 60(sp)
  addi t0, t1, %lo(MOD)
  sw t0, 60(sp)
  lw t1, 60(sp)
  lw t0, 0(t1)
  sw t0, 56(sp)
  lw t1, 52(sp)
  lw t0, 56(sp)
  rem t0, t1, t0
  sw t0, 64(sp)
  lui t0, %hi(no)
  sw t0, 68(sp)
  lw t1, 68(sp)
  addi t0, t1, %lo(no)
  sw t0, 68(sp)
  lw t1, 68(sp)
  lw t0, 64(sp)
  sw t0, 0(t1)
  j .L19
.L19:
  lw t1, 8(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 12(sp)
  lw t1, 12(sp)
  mv t0, t1
  sw t0, 4(sp)
  j .L17
.L20:
  lui t0, %hi(no)
  sw t0, 76(sp)
  lw t1, 76(sp)
  addi t0, t1, %lo(no)
  sw t0, 76(sp)
  lw t1, 76(sp)
  lw t0, 0(t1)
  sw t0, 72(sp)
  lw t1, 72(sp)
  li t0, 0
  slt t0, t1, t0
  sb t0, 84(sp)
  lb t1, 84(sp)
  beqz t1, .L22
  j .L21
.L21:
  lui t0, %hi(no)
  sw t0, 92(sp)
  lw t1, 92(sp)
  addi t0, t1, %lo(no)
  sw t0, 92(sp)
  lw t1, 92(sp)
  lw t0, 0(t1)
  sw t0, 88(sp)
  li t1, 0
  lw t0, 88(sp)
  sub t0, t1, t0
  sw t0, 96(sp)
  lui t0, %hi(no)
  sw t0, 100(sp)
  lw t1, 100(sp)
  addi t0, t1, %lo(no)
  sw t0, 100(sp)
  lw t1, 100(sp)
  lw t0, 96(sp)
  sw t0, 0(t1)
  j .L22
.L22:
  lui t0, %hi(no)
  sw t0, 108(sp)
  lw t1, 108(sp)
  addi t0, t1, %lo(no)
  sw t0, 108(sp)
  lw t1, 108(sp)
  lw t0, 0(t1)
  sw t0, 104(sp)
  j .L23
.L23:
  lw t1, 104(sp)
  mv a0, t1
  lw ra, 0(sp)
  li t0, 112
  add sp, sp, t0
  ret
  .text
  .globl RandRange
RandRange:
.L24:
  li t0, -36
  add sp, sp, t0
  mv t0, a1
  sw t0, 8(sp)
  mv t0, a0
  sw t0, 4(sp)
  sw ra, 0(sp)
  call Rand
  mv t0, a0
  sw t0, 12(sp)
  lw t1, 8(sp)
  lw t0, 4(sp)
  sub t0, t1, t0
  sw t0, 16(sp)
  lw t1, 16(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 20(sp)
  lw t1, 12(sp)
  lw t0, 20(sp)
  rem t0, t1, t0
  sw t0, 24(sp)
  lw t1, 4(sp)
  lw t0, 24(sp)
  add t0, t1, t0
  sw t0, 28(sp)
  lw t1, 28(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 32(sp)
  j .L25
.L25:
  lw t1, 32(sp)
  mv a0, t1
  lw ra, 0(sp)
  li t0, 36
  add sp, sp, t0
  ret
  .text
  .globl init
init:
.L26:
  li t0, -312
  add sp, sp, t0
  sw ra, 0(sp)
  li t1, 140005
  mv a0, t1
  call newPtrArray
  mv t0, a0
  sw t0, 4(sp)
  li t0, 2
  sw t0, 8(sp)
  j .L27
.L27:
  lw t1, 8(sp)
  mv t0, t1
  sw t0, 12(sp)
  lui t0, %hi(p)
  sw t0, 24(sp)
  lw t1, 24(sp)
  addi t0, t1, %lo(p)
  sw t0, 24(sp)
  lw t1, 24(sp)
  lw t0, 0(t1)
  sw t0, 20(sp)
  lw t1, 12(sp)
  lw t0, 20(sp)
  slt t0, t1, t0
  sb t0, 32(sp)
  lb t1, 32(sp)
  beqz t1, .L30
  j .L28
.L28:
  lw t1, 12(sp)
  slli t0, t1, 2
  sw t0, 36(sp)
  lw t1, 4(sp)
  lw t0, 36(sp)
  add t0, t1, t0
  sw t0, 40(sp)
  lw t1, 40(sp)
  lw t0, 12(sp)
  sw t0, 0(t1)
  j .L29
.L29:
  lw t1, 12(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 16(sp)
  lw t1, 16(sp)
  mv t0, t1
  sw t0, 8(sp)
  j .L27
.L30:
  li t0, 2
  sw t0, 44(sp)
  li t0, 0
  sw t0, 56(sp)
  j .L31
.L31:
  lw t1, 44(sp)
  mv t0, t1
  sw t0, 48(sp)
  lw t1, 56(sp)
  mv t0, t1
  sw t0, 60(sp)
  lui t0, %hi(p)
  sw t0, 72(sp)
  lw t1, 72(sp)
  addi t0, t1, %lo(p)
  sw t0, 72(sp)
  lw t1, 72(sp)
  lw t0, 0(t1)
  sw t0, 68(sp)
  lw t1, 48(sp)
  lw t0, 68(sp)
  slt t0, t1, t0
  sb t0, 80(sp)
  lb t1, 80(sp)
  beqz t1, .L38
  j .L32
.L32:
  li t0, 1
  sw t0, 84(sp)
  j .L33
.L33:
  lw t1, 84(sp)
  mv t0, t1
  sw t0, 64(sp)
  li t1, 15
  lw t0, 64(sp)
  slt t0, t1, t0
  sw t0, 92(sp)
  lw t1, 92(sp)
  xori t0, t1, 1
  sb t0, 96(sp)
  lb t1, 96(sp)
  beqz t1, .L36
  j .L34
.L34:
  lw t1, 48(sp)
  slli t0, t1, 2
  sw t0, 100(sp)
  lw t1, 4(sp)
  lw t0, 100(sp)
  add t0, t1, t0
  sw t0, 104(sp)
  lw t1, 104(sp)
  lw t0, 0(t1)
  sw t0, 108(sp)
  lw t1, 108(sp)
  mv a0, t1
  call square
  mv t0, a0
  sw t0, 112(sp)
  lui t0, %hi(p)
  sw t0, 120(sp)
  lw t1, 120(sp)
  addi t0, t1, %lo(p)
  sw t0, 120(sp)
  lw t1, 120(sp)
  lw t0, 0(t1)
  sw t0, 116(sp)
  lw t1, 112(sp)
  lw t0, 116(sp)
  rem t0, t1, t0
  sw t0, 124(sp)
  lw t1, 48(sp)
  slli t0, t1, 2
  sw t0, 128(sp)
  lw t1, 4(sp)
  lw t0, 128(sp)
  add t0, t1, t0
  sw t0, 132(sp)
  lw t1, 132(sp)
  lw t0, 124(sp)
  sw t0, 0(t1)
  j .L35
.L35:
  lw t1, 64(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 88(sp)
  lw t1, 88(sp)
  mv t0, t1
  sw t0, 84(sp)
  j .L33
.L36:
  j .L37
.L37:
  lw t1, 48(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 52(sp)
  lw t1, 52(sp)
  mv t0, t1
  sw t0, 44(sp)
  lw t1, 64(sp)
  mv t0, t1
  sw t0, 56(sp)
  j .L31
.L38:
  li t0, 2
  sw t0, 136(sp)
  li t0, 0
  sw t0, 148(sp)
  li t0, 0
  sw t0, 160(sp)
  j .L39
.L39:
  lw t1, 136(sp)
  mv t0, t1
  sw t0, 140(sp)
  lw t1, 148(sp)
  mv t0, t1
  sw t0, 152(sp)
  lw t1, 160(sp)
  mv t0, t1
  sw t0, 164(sp)
  lui t0, %hi(p)
  sw t0, 176(sp)
  lw t1, 176(sp)
  addi t0, t1, %lo(p)
  sw t0, 176(sp)
  lw t1, 176(sp)
  lw t0, 0(t1)
  sw t0, 172(sp)
  lw t1, 140(sp)
  lw t0, 172(sp)
  slt t0, t1, t0
  sb t0, 184(sp)
  lb t1, 184(sp)
  beqz t1, .L48
  j .L40
.L40:
  lw t1, 140(sp)
  slli t0, t1, 2
  sw t0, 188(sp)
  lw t1, 4(sp)
  lw t0, 188(sp)
  add t0, t1, t0
  sw t0, 192(sp)
  lw t1, 192(sp)
  lw t0, 0(t1)
  sw t0, 196(sp)
  li t0, 1
  sw t0, 200(sp)
  lw t1, 196(sp)
  mv t0, t1
  sw t0, 208(sp)
  j .L41
.L41:
  lw t1, 200(sp)
  mv t0, t1
  sw t0, 156(sp)
  lw t1, 208(sp)
  mv t0, t1
  sw t0, 212(sp)
  j .L42
.L42:
  lw t1, 212(sp)
  mv a0, t1
  call square
  mv t0, a0
  sw t0, 216(sp)
  lui t0, %hi(p)
  sw t0, 224(sp)
  lw t1, 224(sp)
  addi t0, t1, %lo(p)
  sw t0, 224(sp)
  lw t1, 224(sp)
  lw t0, 0(t1)
  sw t0, 220(sp)
  lw t1, 216(sp)
  lw t0, 220(sp)
  rem t0, t1, t0
  sw t0, 168(sp)
  lui t0, %hi(b)
  sw t0, 232(sp)
  lw t1, 232(sp)
  addi t0, t1, %lo(b)
  sw t0, 232(sp)
  lw t1, 232(sp)
  lw t0, 0(t1)
  sw t0, 228(sp)
  lw t1, 168(sp)
  slli t0, t1, 2
  sw t0, 236(sp)
  lw t1, 228(sp)
  lw t0, 236(sp)
  add t0, t1, t0
  sw t0, 240(sp)
  lw t1, 240(sp)
  li t0, 1
  sw t0, 0(t1)
  lw t1, 140(sp)
  slli t0, t1, 2
  sw t0, 244(sp)
  lw t1, 4(sp)
  lw t0, 244(sp)
  add t0, t1, t0
  sw t0, 248(sp)
  lw t1, 248(sp)
  lw t0, 0(t1)
  sw t0, 252(sp)
  lw t1, 168(sp)
  lw t0, 252(sp)
  sub t0, t1, t0
  sw t0, 256(sp)
  lw t1, 256(sp)
  seqz t0, t1
  sb t0, 260(sp)
  lb t1, 260(sp)
  beqz t1, .L44
  j .L43
.L43:
  j .L46
.L44:
  j .L45
.L45:
  lw t1, 156(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 204(sp)
  lw t1, 204(sp)
  mv t0, t1
  sw t0, 200(sp)
  lw t1, 168(sp)
  mv t0, t1
  sw t0, 208(sp)
  j .L41
.L46:
  lui t0, %hi(L)
  sw t0, 268(sp)
  lw t1, 268(sp)
  addi t0, t1, %lo(L)
  sw t0, 268(sp)
  lw t1, 268(sp)
  lw t0, 0(t1)
  sw t0, 264(sp)
  lw t1, 264(sp)
  mv a0, t1
  lw t1, 156(sp)
  mv a1, t1
  call lcm
  mv t0, a0
  sw t0, 272(sp)
  lui t0, %hi(L)
  sw t0, 276(sp)
  lw t1, 276(sp)
  addi t0, t1, %lo(L)
  sw t0, 276(sp)
  lw t1, 276(sp)
  lw t0, 272(sp)
  sw t0, 0(t1)
  j .L47
.L47:
  lw t1, 140(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 144(sp)
  lw t1, 144(sp)
  mv t0, t1
  sw t0, 136(sp)
  lw t1, 156(sp)
  mv t0, t1
  sw t0, 148(sp)
  lw t1, 168(sp)
  mv t0, t1
  sw t0, 160(sp)
  j .L39
.L48:
  lui t0, %hi(b)
  sw t0, 284(sp)
  lw t1, 284(sp)
  addi t0, t1, %lo(b)
  sw t0, 284(sp)
  lw t1, 284(sp)
  lw t0, 0(t1)
  sw t0, 280(sp)
  li t1, 0
  slli t0, t1, 2
  sw t0, 288(sp)
  lw t1, 280(sp)
  lw t0, 288(sp)
  add t0, t1, t0
  sw t0, 292(sp)
  lw t1, 292(sp)
  li t0, 1
  sw t0, 0(t1)
  lui t0, %hi(b)
  sw t0, 300(sp)
  lw t1, 300(sp)
  addi t0, t1, %lo(b)
  sw t0, 300(sp)
  lw t1, 300(sp)
  lw t0, 0(t1)
  sw t0, 296(sp)
  li t1, 1
  slli t0, t1, 2
  sw t0, 304(sp)
  lw t1, 296(sp)
  lw t0, 304(sp)
  add t0, t1, t0
  sw t0, 308(sp)
  lw t1, 308(sp)
  li t0, 1
  sw t0, 0(t1)
  j .L49
.L49:
  lw ra, 0(sp)
  li t0, 312
  add sp, sp, t0
  ret
  .text
  .globl build
build:
.L50:
  li t0, -764
  add sp, sp, t0
  mv t0, a2
  sw t0, 12(sp)
  mv t0, a1
  sw t0, 8(sp)
  mv t0, a0
  sw t0, 4(sp)
  sw ra, 0(sp)
  lw t1, 8(sp)
  lw t0, 12(sp)
  sub t0, t1, t0
  sw t0, 16(sp)
  lw t1, 16(sp)
  seqz t0, t1
  sb t0, 20(sp)
  lb t1, 20(sp)
  beqz t1, .L66
  j .L51
.L51:
  lui t0, %hi(a)
  sw t0, 28(sp)
  lw t1, 28(sp)
  addi t0, t1, %lo(a)
  sw t0, 28(sp)
  lw t1, 28(sp)
  lw t0, 0(t1)
  sw t0, 24(sp)
  lw t1, 8(sp)
  slli t0, t1, 2
  sw t0, 32(sp)
  lw t1, 24(sp)
  lw t0, 32(sp)
  add t0, t1, t0
  sw t0, 36(sp)
  lui t0, %hi(sum)
  sw t0, 44(sp)
  lw t1, 44(sp)
  addi t0, t1, %lo(sum)
  sw t0, 44(sp)
  lw t1, 44(sp)
  lw t0, 0(t1)
  sw t0, 40(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 48(sp)
  lw t1, 40(sp)
  lw t0, 48(sp)
  add t0, t1, t0
  sw t0, 52(sp)
  lw t1, 36(sp)
  lw t0, 0(t1)
  sw t0, 56(sp)
  lw t1, 52(sp)
  lw t0, 56(sp)
  sw t0, 0(t1)
  lui t0, %hi(a)
  sw t0, 64(sp)
  lw t1, 64(sp)
  addi t0, t1, %lo(a)
  sw t0, 64(sp)
  lw t1, 64(sp)
  lw t0, 0(t1)
  sw t0, 60(sp)
  lw t1, 8(sp)
  slli t0, t1, 2
  sw t0, 68(sp)
  lw t1, 60(sp)
  lw t0, 68(sp)
  add t0, t1, t0
  sw t0, 72(sp)
  lw t1, 72(sp)
  lw t0, 0(t1)
  sw t0, 76(sp)
  lui t0, %hi(p)
  sw t0, 84(sp)
  lw t1, 84(sp)
  addi t0, t1, %lo(p)
  sw t0, 84(sp)
  lw t1, 84(sp)
  lw t0, 0(t1)
  sw t0, 80(sp)
  lw t1, 76(sp)
  lw t0, 80(sp)
  slt t0, t1, t0
  sb t0, 92(sp)
  lb t1, 92(sp)
  beqz t1, .L54
  j .L52
.L52:
  lui t0, %hi(a)
  sw t0, 100(sp)
  lw t1, 100(sp)
  addi t0, t1, %lo(a)
  sw t0, 100(sp)
  lw t1, 100(sp)
  lw t0, 0(t1)
  sw t0, 96(sp)
  lw t1, 8(sp)
  slli t0, t1, 2
  sw t0, 104(sp)
  lw t1, 96(sp)
  lw t0, 104(sp)
  add t0, t1, t0
  sw t0, 108(sp)
  lw t1, 108(sp)
  lw t0, 0(t1)
  sw t0, 112(sp)
  lw t1, 112(sp)
  li t0, 0
  slt t0, t1, t0
  sw t0, 116(sp)
  lw t1, 116(sp)
  xori t0, t1, 1
  sb t0, 120(sp)
  lb t1, 120(sp)
  beqz t1, .L54
  j .L53
.L53:
  li t0, 1
  sb t0, 124(sp)
  j .L55
.L54:
  li t0, 0
  sb t0, 124(sp)
  j .L55
.L55:
  lb t1, 124(sp)
  mv t0, t1
  sb t0, 128(sp)
  lb t1, 128(sp)
  beqz t1, .L58
  j .L56
.L56:
  lui t0, %hi(a)
  sw t0, 136(sp)
  lw t1, 136(sp)
  addi t0, t1, %lo(a)
  sw t0, 136(sp)
  lw t1, 136(sp)
  lw t0, 0(t1)
  sw t0, 132(sp)
  lw t1, 8(sp)
  slli t0, t1, 2
  sw t0, 140(sp)
  lw t1, 132(sp)
  lw t0, 140(sp)
  add t0, t1, t0
  sw t0, 144(sp)
  lw t1, 144(sp)
  lw t0, 0(t1)
  sw t0, 148(sp)
  lui t0, %hi(p)
  sw t0, 156(sp)
  lw t1, 156(sp)
  addi t0, t1, %lo(p)
  sw t0, 156(sp)
  lw t1, 156(sp)
  lw t0, 0(t1)
  sw t0, 152(sp)
  lw t1, 148(sp)
  lw t0, 152(sp)
  rem t0, t1, t0
  sw t0, 160(sp)
  lui t0, %hi(b)
  sw t0, 168(sp)
  lw t1, 168(sp)
  addi t0, t1, %lo(b)
  sw t0, 168(sp)
  lw t1, 168(sp)
  lw t0, 0(t1)
  sw t0, 164(sp)
  lw t1, 160(sp)
  slli t0, t1, 2
  sw t0, 172(sp)
  lw t1, 164(sp)
  lw t0, 172(sp)
  add t0, t1, t0
  sw t0, 176(sp)
  lw t1, 176(sp)
  lw t0, 0(t1)
  sw t0, 180(sp)
  li t1, 0
  lw t0, 180(sp)
  slt t0, t1, t0
  sb t0, 188(sp)
  lb t1, 188(sp)
  beqz t1, .L58
  j .L57
.L57:
  li t0, 1
  sb t0, 192(sp)
  j .L59
.L58:
  li t0, 0
  sb t0, 192(sp)
  j .L59
.L59:
  lb t1, 192(sp)
  mv t0, t1
  sb t0, 196(sp)
  li t0, 0
  sw t0, 372(sp)
  lb t1, 196(sp)
  beqz t1, .L65
  j .L60
.L60:
  lui t0, %hi(flag)
  sw t0, 204(sp)
  lw t1, 204(sp)
  addi t0, t1, %lo(flag)
  sw t0, 204(sp)
  lw t1, 204(sp)
  lw t0, 0(t1)
  sw t0, 200(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 208(sp)
  lw t1, 200(sp)
  lw t0, 208(sp)
  add t0, t1, t0
  sw t0, 212(sp)
  lw t1, 212(sp)
  li t0, 1
  sw t0, 0(t1)
  lui t0, %hi(a)
  sw t0, 220(sp)
  lw t1, 220(sp)
  addi t0, t1, %lo(a)
  sw t0, 220(sp)
  lw t1, 220(sp)
  lw t0, 0(t1)
  sw t0, 216(sp)
  lw t1, 8(sp)
  slli t0, t1, 2
  sw t0, 224(sp)
  lw t1, 216(sp)
  lw t0, 224(sp)
  add t0, t1, t0
  sw t0, 228(sp)
  lui t0, %hi(s)
  sw t0, 236(sp)
  lw t1, 236(sp)
  addi t0, t1, %lo(s)
  sw t0, 236(sp)
  lw t1, 236(sp)
  lw t0, 0(t1)
  sw t0, 232(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 240(sp)
  lw t1, 232(sp)
  lw t0, 240(sp)
  add t0, t1, t0
  sw t0, 244(sp)
  lw t1, 244(sp)
  lw t0, 0(t1)
  sw t0, 248(sp)
  li t1, 0
  slli t0, t1, 2
  sw t0, 252(sp)
  lw t1, 248(sp)
  lw t0, 252(sp)
  add t0, t1, t0
  sw t0, 256(sp)
  lw t1, 228(sp)
  lw t0, 0(t1)
  sw t0, 260(sp)
  lw t1, 256(sp)
  lw t0, 260(sp)
  sw t0, 0(t1)
  li t0, 1
  sw t0, 264(sp)
  j .L61
.L61:
  lw t1, 264(sp)
  mv t0, t1
  sw t0, 268(sp)
  lui t0, %hi(L)
  sw t0, 280(sp)
  lw t1, 280(sp)
  addi t0, t1, %lo(L)
  sw t0, 280(sp)
  lw t1, 280(sp)
  lw t0, 0(t1)
  sw t0, 276(sp)
  lw t1, 268(sp)
  lw t0, 276(sp)
  slt t0, t1, t0
  sb t0, 288(sp)
  lb t1, 288(sp)
  beqz t1, .L64
  j .L62
.L62:
  lui t0, %hi(s)
  sw t0, 296(sp)
  lw t1, 296(sp)
  addi t0, t1, %lo(s)
  sw t0, 296(sp)
  lw t1, 296(sp)
  lw t0, 0(t1)
  sw t0, 292(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 300(sp)
  lw t1, 292(sp)
  lw t0, 300(sp)
  add t0, t1, t0
  sw t0, 304(sp)
  lw t1, 268(sp)
  li t0, 1
  sub t0, t1, t0
  sw t0, 308(sp)
  lw t1, 304(sp)
  lw t0, 0(t1)
  sw t0, 312(sp)
  lw t1, 308(sp)
  slli t0, t1, 2
  sw t0, 316(sp)
  lw t1, 312(sp)
  lw t0, 316(sp)
  add t0, t1, t0
  sw t0, 320(sp)
  lw t1, 320(sp)
  lw t0, 0(t1)
  sw t0, 324(sp)
  lw t1, 324(sp)
  mv a0, t1
  call square
  mv t0, a0
  sw t0, 328(sp)
  lui t0, %hi(p)
  sw t0, 336(sp)
  lw t1, 336(sp)
  addi t0, t1, %lo(p)
  sw t0, 336(sp)
  lw t1, 336(sp)
  lw t0, 0(t1)
  sw t0, 332(sp)
  lw t1, 328(sp)
  lw t0, 332(sp)
  rem t0, t1, t0
  sw t0, 340(sp)
  lui t0, %hi(s)
  sw t0, 348(sp)
  lw t1, 348(sp)
  addi t0, t1, %lo(s)
  sw t0, 348(sp)
  lw t1, 348(sp)
  lw t0, 0(t1)
  sw t0, 344(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 352(sp)
  lw t1, 344(sp)
  lw t0, 352(sp)
  add t0, t1, t0
  sw t0, 356(sp)
  lw t1, 356(sp)
  lw t0, 0(t1)
  sw t0, 360(sp)
  lw t1, 268(sp)
  slli t0, t1, 2
  sw t0, 364(sp)
  lw t1, 360(sp)
  lw t0, 364(sp)
  add t0, t1, t0
  sw t0, 368(sp)
  lw t1, 368(sp)
  lw t0, 340(sp)
  sw t0, 0(t1)
  j .L63
.L63:
  lw t1, 268(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 272(sp)
  lw t1, 272(sp)
  mv t0, t1
  sw t0, 264(sp)
  j .L61
.L64:
  lw t1, 268(sp)
  mv t0, t1
  sw t0, 372(sp)
  j .L65
.L65:
  lw t1, 372(sp)
  mv t0, t1
  sw t0, 376(sp)
  lui t0, %hi(now)
  sw t0, 384(sp)
  lw t1, 384(sp)
  addi t0, t1, %lo(now)
  sw t0, 384(sp)
  lw t1, 384(sp)
  lw t0, 0(t1)
  sw t0, 380(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 388(sp)
  lw t1, 380(sp)
  lw t0, 388(sp)
  add t0, t1, t0
  sw t0, 392(sp)
  lw t1, 392(sp)
  li t0, 0
  sw t0, 0(t1)
  lw t1, 376(sp)
  mv t0, t1
  sw t0, 716(sp)
  lb t1, 128(sp)
  mv t0, t1
  sb t0, 724(sp)
  lb t1, 196(sp)
  mv t0, t1
  sb t0, 732(sp)
  li t0, 0
  sw t0, 740(sp)
  li t0, 0
  sw t0, 748(sp)
  li t0, 0
  sw t0, 756(sp)
  j .L73
.L66:
  lw t1, 4(sp)
  li t0, 2
  mul t0, t1, t0
  sw t0, 396(sp)
  lw t1, 4(sp)
  li t0, 2
  mul t0, t1, t0
  sw t0, 400(sp)
  lw t1, 400(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 404(sp)
  lw t1, 8(sp)
  lw t0, 12(sp)
  add t0, t1, t0
  sw t0, 408(sp)
  lw t1, 408(sp)
  li t0, 2
  div t0, t1, t0
  sw t0, 412(sp)
  lw t1, 396(sp)
  mv a0, t1
  lw t1, 8(sp)
  mv a1, t1
  lw t1, 412(sp)
  mv a2, t1
  call build
  lw t1, 412(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 416(sp)
  lw t1, 404(sp)
  mv a0, t1
  lw t1, 416(sp)
  mv a1, t1
  lw t1, 12(sp)
  mv a2, t1
  call build
  lui t0, %hi(sum)
  sw t0, 424(sp)
  lw t1, 424(sp)
  addi t0, t1, %lo(sum)
  sw t0, 424(sp)
  lw t1, 424(sp)
  lw t0, 0(t1)
  sw t0, 420(sp)
  lw t1, 396(sp)
  slli t0, t1, 2
  sw t0, 428(sp)
  lw t1, 420(sp)
  lw t0, 428(sp)
  add t0, t1, t0
  sw t0, 432(sp)
  lui t0, %hi(sum)
  sw t0, 440(sp)
  lw t1, 440(sp)
  addi t0, t1, %lo(sum)
  sw t0, 440(sp)
  lw t1, 440(sp)
  lw t0, 0(t1)
  sw t0, 436(sp)
  lw t1, 404(sp)
  slli t0, t1, 2
  sw t0, 444(sp)
  lw t1, 436(sp)
  lw t0, 444(sp)
  add t0, t1, t0
  sw t0, 448(sp)
  lw t1, 432(sp)
  lw t0, 0(t1)
  sw t0, 452(sp)
  lw t1, 448(sp)
  lw t0, 0(t1)
  sw t0, 456(sp)
  lw t1, 452(sp)
  lw t0, 456(sp)
  add t0, t1, t0
  sw t0, 460(sp)
  lui t0, %hi(sum)
  sw t0, 468(sp)
  lw t1, 468(sp)
  addi t0, t1, %lo(sum)
  sw t0, 468(sp)
  lw t1, 468(sp)
  lw t0, 0(t1)
  sw t0, 464(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 472(sp)
  lw t1, 464(sp)
  lw t0, 472(sp)
  add t0, t1, t0
  sw t0, 476(sp)
  lw t1, 476(sp)
  lw t0, 460(sp)
  sw t0, 0(t1)
  lui t0, %hi(flag)
  sw t0, 484(sp)
  lw t1, 484(sp)
  addi t0, t1, %lo(flag)
  sw t0, 484(sp)
  lw t1, 484(sp)
  lw t0, 0(t1)
  sw t0, 480(sp)
  lw t1, 396(sp)
  slli t0, t1, 2
  sw t0, 488(sp)
  lw t1, 480(sp)
  lw t0, 488(sp)
  add t0, t1, t0
  sw t0, 492(sp)
  lui t0, %hi(flag)
  sw t0, 500(sp)
  lw t1, 500(sp)
  addi t0, t1, %lo(flag)
  sw t0, 500(sp)
  lw t1, 500(sp)
  lw t0, 0(t1)
  sw t0, 496(sp)
  lw t1, 404(sp)
  slli t0, t1, 2
  sw t0, 504(sp)
  lw t1, 496(sp)
  lw t0, 504(sp)
  add t0, t1, t0
  sw t0, 508(sp)
  lw t1, 492(sp)
  lw t0, 0(t1)
  sw t0, 512(sp)
  lw t1, 508(sp)
  lw t0, 0(t1)
  sw t0, 516(sp)
  lw t1, 512(sp)
  lw t0, 516(sp)
  and t0, t1, t0
  sw t0, 520(sp)
  lui t0, %hi(flag)
  sw t0, 528(sp)
  lw t1, 528(sp)
  addi t0, t1, %lo(flag)
  sw t0, 528(sp)
  lw t1, 528(sp)
  lw t0, 0(t1)
  sw t0, 524(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 532(sp)
  lw t1, 524(sp)
  lw t0, 532(sp)
  add t0, t1, t0
  sw t0, 536(sp)
  lw t1, 536(sp)
  lw t0, 520(sp)
  sw t0, 0(t1)
  lui t0, %hi(flag)
  sw t0, 544(sp)
  lw t1, 544(sp)
  addi t0, t1, %lo(flag)
  sw t0, 544(sp)
  lw t1, 544(sp)
  lw t0, 0(t1)
  sw t0, 540(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 548(sp)
  lw t1, 540(sp)
  lw t0, 548(sp)
  add t0, t1, t0
  sw t0, 552(sp)
  lw t1, 552(sp)
  lw t0, 0(t1)
  sw t0, 556(sp)
  li t1, 0
  lw t0, 556(sp)
  slt t0, t1, t0
  sb t0, 564(sp)
  li t0, 0
  sw t0, 708(sp)
  lb t1, 564(sp)
  beqz t1, .L72
  j .L67
.L67:
  li t0, 0
  sw t0, 568(sp)
  j .L68
.L68:
  lw t1, 568(sp)
  mv t0, t1
  sw t0, 572(sp)
  lui t0, %hi(L)
  sw t0, 584(sp)
  lw t1, 584(sp)
  addi t0, t1, %lo(L)
  sw t0, 584(sp)
  lw t1, 584(sp)
  lw t0, 0(t1)
  sw t0, 580(sp)
  lw t1, 572(sp)
  lw t0, 580(sp)
  slt t0, t1, t0
  sb t0, 592(sp)
  lb t1, 592(sp)
  beqz t1, .L71
  j .L69
.L69:
  lui t0, %hi(s)
  sw t0, 600(sp)
  lw t1, 600(sp)
  addi t0, t1, %lo(s)
  sw t0, 600(sp)
  lw t1, 600(sp)
  lw t0, 0(t1)
  sw t0, 596(sp)
  lw t1, 396(sp)
  slli t0, t1, 2
  sw t0, 604(sp)
  lw t1, 596(sp)
  lw t0, 604(sp)
  add t0, t1, t0
  sw t0, 608(sp)
  lw t1, 608(sp)
  lw t0, 0(t1)
  sw t0, 612(sp)
  lw t1, 572(sp)
  slli t0, t1, 2
  sw t0, 616(sp)
  lw t1, 612(sp)
  lw t0, 616(sp)
  add t0, t1, t0
  sw t0, 620(sp)
  lui t0, %hi(s)
  sw t0, 628(sp)
  lw t1, 628(sp)
  addi t0, t1, %lo(s)
  sw t0, 628(sp)
  lw t1, 628(sp)
  lw t0, 0(t1)
  sw t0, 624(sp)
  lw t1, 404(sp)
  slli t0, t1, 2
  sw t0, 632(sp)
  lw t1, 624(sp)
  lw t0, 632(sp)
  add t0, t1, t0
  sw t0, 636(sp)
  lw t1, 636(sp)
  lw t0, 0(t1)
  sw t0, 640(sp)
  lw t1, 572(sp)
  slli t0, t1, 2
  sw t0, 644(sp)
  lw t1, 640(sp)
  lw t0, 644(sp)
  add t0, t1, t0
  sw t0, 648(sp)
  lw t1, 620(sp)
  lw t0, 0(t1)
  sw t0, 652(sp)
  lw t1, 648(sp)
  lw t0, 0(t1)
  sw t0, 656(sp)
  lw t1, 652(sp)
  lw t0, 656(sp)
  add t0, t1, t0
  sw t0, 660(sp)
  lui t0, %hi(s)
  sw t0, 668(sp)
  lw t1, 668(sp)
  addi t0, t1, %lo(s)
  sw t0, 668(sp)
  lw t1, 668(sp)
  lw t0, 0(t1)
  sw t0, 664(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 672(sp)
  lw t1, 664(sp)
  lw t0, 672(sp)
  add t0, t1, t0
  sw t0, 676(sp)
  lw t1, 676(sp)
  lw t0, 0(t1)
  sw t0, 680(sp)
  lw t1, 572(sp)
  slli t0, t1, 2
  sw t0, 684(sp)
  lw t1, 680(sp)
  lw t0, 684(sp)
  add t0, t1, t0
  sw t0, 688(sp)
  lw t1, 688(sp)
  lw t0, 660(sp)
  sw t0, 0(t1)
  j .L70
.L70:
  lw t1, 572(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 576(sp)
  lw t1, 576(sp)
  mv t0, t1
  sw t0, 568(sp)
  j .L68
.L71:
  lui t0, %hi(now)
  sw t0, 696(sp)
  lw t1, 696(sp)
  addi t0, t1, %lo(now)
  sw t0, 696(sp)
  lw t1, 696(sp)
  lw t0, 0(t1)
  sw t0, 692(sp)
  li t1, 0
  slli t0, t1, 2
  sw t0, 700(sp)
  lw t1, 692(sp)
  lw t0, 700(sp)
  add t0, t1, t0
  sw t0, 704(sp)
  lw t1, 704(sp)
  li t0, 0
  sw t0, 0(t1)
  lw t1, 572(sp)
  mv t0, t1
  sw t0, 708(sp)
  j .L72
.L72:
  lw t1, 708(sp)
  mv t0, t1
  sw t0, 712(sp)
  lw t1, 712(sp)
  mv t0, t1
  sw t0, 716(sp)
  li t0, 0
  sb t0, 724(sp)
  li t0, 0
  sb t0, 732(sp)
  lw t1, 396(sp)
  mv t0, t1
  sw t0, 740(sp)
  lw t1, 404(sp)
  mv t0, t1
  sw t0, 748(sp)
  lw t1, 412(sp)
  mv t0, t1
  sw t0, 756(sp)
  j .L73
.L73:
  lw t1, 716(sp)
  mv t0, t1
  sw t0, 720(sp)
  lb t1, 724(sp)
  mv t0, t1
  sb t0, 728(sp)
  lb t1, 732(sp)
  mv t0, t1
  sb t0, 736(sp)
  lw t1, 740(sp)
  mv t0, t1
  sw t0, 744(sp)
  lw t1, 748(sp)
  mv t0, t1
  sw t0, 752(sp)
  lw t1, 756(sp)
  mv t0, t1
  sw t0, 760(sp)
  j .L74
.L74:
  lw ra, 0(sp)
  li t0, 764
  add sp, sp, t0
  ret
  .text
  .globl pushdown
pushdown:
.L75:
  li t0, -504
  add sp, sp, t0
  mv t0, a0
  sw t0, 4(sp)
  sw ra, 0(sp)
  lui t0, %hi(t)
  sw t0, 12(sp)
  lw t1, 12(sp)
  addi t0, t1, %lo(t)
  sw t0, 12(sp)
  lw t1, 12(sp)
  lw t0, 0(t1)
  sw t0, 8(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 16(sp)
  lw t1, 8(sp)
  lw t0, 16(sp)
  add t0, t1, t0
  sw t0, 20(sp)
  lw t1, 20(sp)
  lw t0, 0(t1)
  sw t0, 24(sp)
  li t1, 0
  lw t0, 24(sp)
  slt t0, t1, t0
  sb t0, 32(sp)
  li t0, 0
  sw t0, 488(sp)
  li t0, 0
  sw t0, 496(sp)
  lb t1, 32(sp)
  beqz t1, .L77
  j .L76
.L76:
  lw t1, 4(sp)
  li t0, 2
  mul t0, t1, t0
  sw t0, 36(sp)
  lw t1, 4(sp)
  li t0, 2
  mul t0, t1, t0
  sw t0, 40(sp)
  lw t1, 40(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 44(sp)
  lui t0, %hi(now)
  sw t0, 52(sp)
  lw t1, 52(sp)
  addi t0, t1, %lo(now)
  sw t0, 52(sp)
  lw t1, 52(sp)
  lw t0, 0(t1)
  sw t0, 48(sp)
  lw t1, 36(sp)
  slli t0, t1, 2
  sw t0, 56(sp)
  lw t1, 48(sp)
  lw t0, 56(sp)
  add t0, t1, t0
  sw t0, 60(sp)
  lui t0, %hi(t)
  sw t0, 68(sp)
  lw t1, 68(sp)
  addi t0, t1, %lo(t)
  sw t0, 68(sp)
  lw t1, 68(sp)
  lw t0, 0(t1)
  sw t0, 64(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 72(sp)
  lw t1, 64(sp)
  lw t0, 72(sp)
  add t0, t1, t0
  sw t0, 76(sp)
  lw t1, 60(sp)
  lw t0, 0(t1)
  sw t0, 80(sp)
  lw t1, 76(sp)
  lw t0, 0(t1)
  sw t0, 84(sp)
  lw t1, 80(sp)
  lw t0, 84(sp)
  add t0, t1, t0
  sw t0, 88(sp)
  lui t0, %hi(L)
  sw t0, 96(sp)
  lw t1, 96(sp)
  addi t0, t1, %lo(L)
  sw t0, 96(sp)
  lw t1, 96(sp)
  lw t0, 0(t1)
  sw t0, 92(sp)
  lw t1, 88(sp)
  lw t0, 92(sp)
  rem t0, t1, t0
  sw t0, 100(sp)
  lui t0, %hi(now)
  sw t0, 108(sp)
  lw t1, 108(sp)
  addi t0, t1, %lo(now)
  sw t0, 108(sp)
  lw t1, 108(sp)
  lw t0, 0(t1)
  sw t0, 104(sp)
  lw t1, 36(sp)
  slli t0, t1, 2
  sw t0, 112(sp)
  lw t1, 104(sp)
  lw t0, 112(sp)
  add t0, t1, t0
  sw t0, 116(sp)
  lw t1, 116(sp)
  lw t0, 100(sp)
  sw t0, 0(t1)
  lui t0, %hi(s)
  sw t0, 124(sp)
  lw t1, 124(sp)
  addi t0, t1, %lo(s)
  sw t0, 124(sp)
  lw t1, 124(sp)
  lw t0, 0(t1)
  sw t0, 120(sp)
  lw t1, 36(sp)
  slli t0, t1, 2
  sw t0, 128(sp)
  lw t1, 120(sp)
  lw t0, 128(sp)
  add t0, t1, t0
  sw t0, 132(sp)
  lui t0, %hi(now)
  sw t0, 140(sp)
  lw t1, 140(sp)
  addi t0, t1, %lo(now)
  sw t0, 140(sp)
  lw t1, 140(sp)
  lw t0, 0(t1)
  sw t0, 136(sp)
  lw t1, 36(sp)
  slli t0, t1, 2
  sw t0, 144(sp)
  lw t1, 136(sp)
  lw t0, 144(sp)
  add t0, t1, t0
  sw t0, 148(sp)
  lw t1, 132(sp)
  lw t0, 0(t1)
  sw t0, 152(sp)
  lw t1, 148(sp)
  lw t0, 0(t1)
  sw t0, 156(sp)
  lw t1, 156(sp)
  slli t0, t1, 2
  sw t0, 160(sp)
  lw t1, 152(sp)
  lw t0, 160(sp)
  add t0, t1, t0
  sw t0, 164(sp)
  lui t0, %hi(sum)
  sw t0, 172(sp)
  lw t1, 172(sp)
  addi t0, t1, %lo(sum)
  sw t0, 172(sp)
  lw t1, 172(sp)
  lw t0, 0(t1)
  sw t0, 168(sp)
  lw t1, 36(sp)
  slli t0, t1, 2
  sw t0, 176(sp)
  lw t1, 168(sp)
  lw t0, 176(sp)
  add t0, t1, t0
  sw t0, 180(sp)
  lw t1, 164(sp)
  lw t0, 0(t1)
  sw t0, 184(sp)
  lw t1, 180(sp)
  lw t0, 184(sp)
  sw t0, 0(t1)
  lui t0, %hi(t)
  sw t0, 192(sp)
  lw t1, 192(sp)
  addi t0, t1, %lo(t)
  sw t0, 192(sp)
  lw t1, 192(sp)
  lw t0, 0(t1)
  sw t0, 188(sp)
  lw t1, 36(sp)
  slli t0, t1, 2
  sw t0, 196(sp)
  lw t1, 188(sp)
  lw t0, 196(sp)
  add t0, t1, t0
  sw t0, 200(sp)
  lui t0, %hi(t)
  sw t0, 208(sp)
  lw t1, 208(sp)
  addi t0, t1, %lo(t)
  sw t0, 208(sp)
  lw t1, 208(sp)
  lw t0, 0(t1)
  sw t0, 204(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 212(sp)
  lw t1, 204(sp)
  lw t0, 212(sp)
  add t0, t1, t0
  sw t0, 216(sp)
  lw t1, 200(sp)
  lw t0, 0(t1)
  sw t0, 220(sp)
  lw t1, 216(sp)
  lw t0, 0(t1)
  sw t0, 224(sp)
  lw t1, 220(sp)
  lw t0, 224(sp)
  add t0, t1, t0
  sw t0, 228(sp)
  lui t0, %hi(L)
  sw t0, 236(sp)
  lw t1, 236(sp)
  addi t0, t1, %lo(L)
  sw t0, 236(sp)
  lw t1, 236(sp)
  lw t0, 0(t1)
  sw t0, 232(sp)
  lw t1, 228(sp)
  lw t0, 232(sp)
  rem t0, t1, t0
  sw t0, 240(sp)
  lui t0, %hi(t)
  sw t0, 248(sp)
  lw t1, 248(sp)
  addi t0, t1, %lo(t)
  sw t0, 248(sp)
  lw t1, 248(sp)
  lw t0, 0(t1)
  sw t0, 244(sp)
  lw t1, 36(sp)
  slli t0, t1, 2
  sw t0, 252(sp)
  lw t1, 244(sp)
  lw t0, 252(sp)
  add t0, t1, t0
  sw t0, 256(sp)
  lw t1, 256(sp)
  lw t0, 240(sp)
  sw t0, 0(t1)
  lui t0, %hi(now)
  sw t0, 264(sp)
  lw t1, 264(sp)
  addi t0, t1, %lo(now)
  sw t0, 264(sp)
  lw t1, 264(sp)
  lw t0, 0(t1)
  sw t0, 260(sp)
  lw t1, 44(sp)
  slli t0, t1, 2
  sw t0, 268(sp)
  lw t1, 260(sp)
  lw t0, 268(sp)
  add t0, t1, t0
  sw t0, 272(sp)
  lui t0, %hi(t)
  sw t0, 280(sp)
  lw t1, 280(sp)
  addi t0, t1, %lo(t)
  sw t0, 280(sp)
  lw t1, 280(sp)
  lw t0, 0(t1)
  sw t0, 276(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 284(sp)
  lw t1, 276(sp)
  lw t0, 284(sp)
  add t0, t1, t0
  sw t0, 288(sp)
  lw t1, 272(sp)
  lw t0, 0(t1)
  sw t0, 292(sp)
  lw t1, 288(sp)
  lw t0, 0(t1)
  sw t0, 296(sp)
  lw t1, 292(sp)
  lw t0, 296(sp)
  add t0, t1, t0
  sw t0, 300(sp)
  lui t0, %hi(L)
  sw t0, 308(sp)
  lw t1, 308(sp)
  addi t0, t1, %lo(L)
  sw t0, 308(sp)
  lw t1, 308(sp)
  lw t0, 0(t1)
  sw t0, 304(sp)
  lw t1, 300(sp)
  lw t0, 304(sp)
  rem t0, t1, t0
  sw t0, 312(sp)
  lui t0, %hi(now)
  sw t0, 320(sp)
  lw t1, 320(sp)
  addi t0, t1, %lo(now)
  sw t0, 320(sp)
  lw t1, 320(sp)
  lw t0, 0(t1)
  sw t0, 316(sp)
  lw t1, 44(sp)
  slli t0, t1, 2
  sw t0, 324(sp)
  lw t1, 316(sp)
  lw t0, 324(sp)
  add t0, t1, t0
  sw t0, 328(sp)
  lw t1, 328(sp)
  lw t0, 312(sp)
  sw t0, 0(t1)
  lui t0, %hi(s)
  sw t0, 336(sp)
  lw t1, 336(sp)
  addi t0, t1, %lo(s)
  sw t0, 336(sp)
  lw t1, 336(sp)
  lw t0, 0(t1)
  sw t0, 332(sp)
  lw t1, 44(sp)
  slli t0, t1, 2
  sw t0, 340(sp)
  lw t1, 332(sp)
  lw t0, 340(sp)
  add t0, t1, t0
  sw t0, 344(sp)
  lui t0, %hi(now)
  sw t0, 352(sp)
  lw t1, 352(sp)
  addi t0, t1, %lo(now)
  sw t0, 352(sp)
  lw t1, 352(sp)
  lw t0, 0(t1)
  sw t0, 348(sp)
  lw t1, 44(sp)
  slli t0, t1, 2
  sw t0, 356(sp)
  lw t1, 348(sp)
  lw t0, 356(sp)
  add t0, t1, t0
  sw t0, 360(sp)
  lw t1, 344(sp)
  lw t0, 0(t1)
  sw t0, 364(sp)
  lw t1, 360(sp)
  lw t0, 0(t1)
  sw t0, 368(sp)
  lw t1, 368(sp)
  slli t0, t1, 2
  sw t0, 372(sp)
  lw t1, 364(sp)
  lw t0, 372(sp)
  add t0, t1, t0
  sw t0, 376(sp)
  lui t0, %hi(sum)
  sw t0, 384(sp)
  lw t1, 384(sp)
  addi t0, t1, %lo(sum)
  sw t0, 384(sp)
  lw t1, 384(sp)
  lw t0, 0(t1)
  sw t0, 380(sp)
  lw t1, 44(sp)
  slli t0, t1, 2
  sw t0, 388(sp)
  lw t1, 380(sp)
  lw t0, 388(sp)
  add t0, t1, t0
  sw t0, 392(sp)
  lw t1, 376(sp)
  lw t0, 0(t1)
  sw t0, 396(sp)
  lw t1, 392(sp)
  lw t0, 396(sp)
  sw t0, 0(t1)
  lui t0, %hi(t)
  sw t0, 404(sp)
  lw t1, 404(sp)
  addi t0, t1, %lo(t)
  sw t0, 404(sp)
  lw t1, 404(sp)
  lw t0, 0(t1)
  sw t0, 400(sp)
  lw t1, 44(sp)
  slli t0, t1, 2
  sw t0, 408(sp)
  lw t1, 400(sp)
  lw t0, 408(sp)
  add t0, t1, t0
  sw t0, 412(sp)
  lui t0, %hi(t)
  sw t0, 420(sp)
  lw t1, 420(sp)
  addi t0, t1, %lo(t)
  sw t0, 420(sp)
  lw t1, 420(sp)
  lw t0, 0(t1)
  sw t0, 416(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 424(sp)
  lw t1, 416(sp)
  lw t0, 424(sp)
  add t0, t1, t0
  sw t0, 428(sp)
  lw t1, 412(sp)
  lw t0, 0(t1)
  sw t0, 432(sp)
  lw t1, 428(sp)
  lw t0, 0(t1)
  sw t0, 436(sp)
  lw t1, 432(sp)
  lw t0, 436(sp)
  add t0, t1, t0
  sw t0, 440(sp)
  lui t0, %hi(L)
  sw t0, 448(sp)
  lw t1, 448(sp)
  addi t0, t1, %lo(L)
  sw t0, 448(sp)
  lw t1, 448(sp)
  lw t0, 0(t1)
  sw t0, 444(sp)
  lw t1, 440(sp)
  lw t0, 444(sp)
  rem t0, t1, t0
  sw t0, 452(sp)
  lui t0, %hi(t)
  sw t0, 460(sp)
  lw t1, 460(sp)
  addi t0, t1, %lo(t)
  sw t0, 460(sp)
  lw t1, 460(sp)
  lw t0, 0(t1)
  sw t0, 456(sp)
  lw t1, 44(sp)
  slli t0, t1, 2
  sw t0, 464(sp)
  lw t1, 456(sp)
  lw t0, 464(sp)
  add t0, t1, t0
  sw t0, 468(sp)
  lw t1, 468(sp)
  lw t0, 452(sp)
  sw t0, 0(t1)
  lui t0, %hi(t)
  sw t0, 476(sp)
  lw t1, 476(sp)
  addi t0, t1, %lo(t)
  sw t0, 476(sp)
  lw t1, 476(sp)
  lw t0, 0(t1)
  sw t0, 472(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 480(sp)
  lw t1, 472(sp)
  lw t0, 480(sp)
  add t0, t1, t0
  sw t0, 484(sp)
  lw t1, 484(sp)
  li t0, 0
  sw t0, 0(t1)
  lw t1, 36(sp)
  mv t0, t1
  sw t0, 488(sp)
  lw t1, 44(sp)
  mv t0, t1
  sw t0, 496(sp)
  j .L77
.L77:
  lw t1, 488(sp)
  mv t0, t1
  sw t0, 492(sp)
  lw t1, 496(sp)
  mv t0, t1
  sw t0, 500(sp)
  j .L78
.L78:
  lw ra, 0(sp)
  li t0, 504
  add sp, sp, t0
  ret
  .text
  .globl update
update:
.L79:
  li t0, -1048
  add sp, sp, t0
  mv t0, a2
  sw t0, 12(sp)
  mv t0, a1
  sw t0, 8(sp)
  mv t0, a0
  sw t0, 4(sp)
  sw ra, 0(sp)
  lui t0, %hi(pl)
  sw t0, 20(sp)
  lw t1, 20(sp)
  addi t0, t1, %lo(pl)
  sw t0, 20(sp)
  lw t1, 20(sp)
  lw t0, 0(t1)
  sw t0, 16(sp)
  lw t1, 8(sp)
  lw t0, 16(sp)
  slt t0, t1, t0
  sw t0, 24(sp)
  lw t1, 24(sp)
  xori t0, t1, 1
  sb t0, 28(sp)
  lb t1, 28(sp)
  beqz t1, .L82
  j .L80
.L80:
  lui t0, %hi(pr)
  sw t0, 36(sp)
  lw t1, 36(sp)
  addi t0, t1, %lo(pr)
  sw t0, 36(sp)
  lw t1, 36(sp)
  lw t0, 0(t1)
  sw t0, 32(sp)
  lw t1, 32(sp)
  lw t0, 12(sp)
  slt t0, t1, t0
  sw t0, 40(sp)
  lw t1, 40(sp)
  xori t0, t1, 1
  sb t0, 44(sp)
  lb t1, 44(sp)
  beqz t1, .L82
  j .L81
.L81:
  li t0, 1
  sb t0, 48(sp)
  j .L83
.L82:
  li t0, 0
  sb t0, 48(sp)
  j .L83
.L83:
  lb t1, 48(sp)
  mv t0, t1
  sb t0, 52(sp)
  lb t1, 52(sp)
  beqz t1, .L86
  j .L84
.L84:
  lui t0, %hi(flag)
  sw t0, 60(sp)
  lw t1, 60(sp)
  addi t0, t1, %lo(flag)
  sw t0, 60(sp)
  lw t1, 60(sp)
  lw t0, 0(t1)
  sw t0, 56(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 64(sp)
  lw t1, 56(sp)
  lw t0, 64(sp)
  add t0, t1, t0
  sw t0, 68(sp)
  lw t1, 68(sp)
  lw t0, 0(t1)
  sw t0, 72(sp)
  li t1, 0
  lw t0, 72(sp)
  slt t0, t1, t0
  sb t0, 80(sp)
  lb t1, 80(sp)
  beqz t1, .L86
  j .L85
.L85:
  li t0, 1
  sb t0, 84(sp)
  j .L87
.L86:
  li t0, 0
  sb t0, 84(sp)
  j .L87
.L87:
  lb t1, 84(sp)
  mv t0, t1
  sb t0, 88(sp)
  lb t1, 88(sp)
  beqz t1, .L89
  j .L88
.L88:
  lui t0, %hi(now)
  sw t0, 96(sp)
  lw t1, 96(sp)
  addi t0, t1, %lo(now)
  sw t0, 96(sp)
  lw t1, 96(sp)
  lw t0, 0(t1)
  sw t0, 92(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 100(sp)
  lw t1, 92(sp)
  lw t0, 100(sp)
  add t0, t1, t0
  sw t0, 104(sp)
  lw t1, 104(sp)
  lw t0, 0(t1)
  sw t0, 108(sp)
  lw t1, 108(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 112(sp)
  lui t0, %hi(L)
  sw t0, 120(sp)
  lw t1, 120(sp)
  addi t0, t1, %lo(L)
  sw t0, 120(sp)
  lw t1, 120(sp)
  lw t0, 0(t1)
  sw t0, 116(sp)
  lw t1, 112(sp)
  lw t0, 116(sp)
  rem t0, t1, t0
  sw t0, 124(sp)
  lui t0, %hi(now)
  sw t0, 132(sp)
  lw t1, 132(sp)
  addi t0, t1, %lo(now)
  sw t0, 132(sp)
  lw t1, 132(sp)
  lw t0, 0(t1)
  sw t0, 128(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 136(sp)
  lw t1, 128(sp)
  lw t0, 136(sp)
  add t0, t1, t0
  sw t0, 140(sp)
  lw t1, 140(sp)
  lw t0, 124(sp)
  sw t0, 0(t1)
  lui t0, %hi(s)
  sw t0, 148(sp)
  lw t1, 148(sp)
  addi t0, t1, %lo(s)
  sw t0, 148(sp)
  lw t1, 148(sp)
  lw t0, 0(t1)
  sw t0, 144(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 152(sp)
  lw t1, 144(sp)
  lw t0, 152(sp)
  add t0, t1, t0
  sw t0, 156(sp)
  lui t0, %hi(now)
  sw t0, 164(sp)
  lw t1, 164(sp)
  addi t0, t1, %lo(now)
  sw t0, 164(sp)
  lw t1, 164(sp)
  lw t0, 0(t1)
  sw t0, 160(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 168(sp)
  lw t1, 160(sp)
  lw t0, 168(sp)
  add t0, t1, t0
  sw t0, 172(sp)
  lw t1, 156(sp)
  lw t0, 0(t1)
  sw t0, 176(sp)
  lw t1, 172(sp)
  lw t0, 0(t1)
  sw t0, 180(sp)
  lw t1, 180(sp)
  slli t0, t1, 2
  sw t0, 184(sp)
  lw t1, 176(sp)
  lw t0, 184(sp)
  add t0, t1, t0
  sw t0, 188(sp)
  lui t0, %hi(sum)
  sw t0, 196(sp)
  lw t1, 196(sp)
  addi t0, t1, %lo(sum)
  sw t0, 196(sp)
  lw t1, 196(sp)
  lw t0, 0(t1)
  sw t0, 192(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 200(sp)
  lw t1, 192(sp)
  lw t0, 200(sp)
  add t0, t1, t0
  sw t0, 204(sp)
  lw t1, 188(sp)
  lw t0, 0(t1)
  sw t0, 208(sp)
  lw t1, 204(sp)
  lw t0, 208(sp)
  sw t0, 0(t1)
  lui t0, %hi(t)
  sw t0, 216(sp)
  lw t1, 216(sp)
  addi t0, t1, %lo(t)
  sw t0, 216(sp)
  lw t1, 216(sp)
  lw t0, 0(t1)
  sw t0, 212(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 220(sp)
  lw t1, 212(sp)
  lw t0, 220(sp)
  add t0, t1, t0
  sw t0, 224(sp)
  lw t1, 224(sp)
  lw t0, 0(t1)
  sw t0, 228(sp)
  lw t1, 228(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 232(sp)
  lui t0, %hi(L)
  sw t0, 240(sp)
  lw t1, 240(sp)
  addi t0, t1, %lo(L)
  sw t0, 240(sp)
  lw t1, 240(sp)
  lw t0, 0(t1)
  sw t0, 236(sp)
  lw t1, 232(sp)
  lw t0, 236(sp)
  rem t0, t1, t0
  sw t0, 244(sp)
  lui t0, %hi(t)
  sw t0, 252(sp)
  lw t1, 252(sp)
  addi t0, t1, %lo(t)
  sw t0, 252(sp)
  lw t1, 252(sp)
  lw t0, 0(t1)
  sw t0, 248(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 256(sp)
  lw t1, 248(sp)
  lw t0, 256(sp)
  add t0, t1, t0
  sw t0, 260(sp)
  lw t1, 260(sp)
  lw t0, 244(sp)
  sw t0, 0(t1)
  li t0, 0
  sw t0, 1008(sp)
  li t0, 0
  sw t0, 1016(sp)
  li t0, 0
  sw t0, 1024(sp)
  li t0, 0
  sw t0, 1032(sp)
  li t0, 0
  sw t0, 1040(sp)
  j .L110
.L89:
  lw t1, 8(sp)
  lw t0, 12(sp)
  sub t0, t1, t0
  sw t0, 264(sp)
  lw t1, 264(sp)
  seqz t0, t1
  sb t0, 268(sp)
  lb t1, 268(sp)
  beqz t1, .L97
  j .L90
.L90:
  lui t0, %hi(sum)
  sw t0, 276(sp)
  lw t1, 276(sp)
  addi t0, t1, %lo(sum)
  sw t0, 276(sp)
  lw t1, 276(sp)
  lw t0, 0(t1)
  sw t0, 272(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 280(sp)
  lw t1, 272(sp)
  lw t0, 280(sp)
  add t0, t1, t0
  sw t0, 284(sp)
  lw t1, 284(sp)
  lw t0, 0(t1)
  sw t0, 288(sp)
  lw t1, 288(sp)
  mv a0, t1
  call square
  mv t0, a0
  sw t0, 292(sp)
  lui t0, %hi(p)
  sw t0, 300(sp)
  lw t1, 300(sp)
  addi t0, t1, %lo(p)
  sw t0, 300(sp)
  lw t1, 300(sp)
  lw t0, 0(t1)
  sw t0, 296(sp)
  lw t1, 292(sp)
  lw t0, 296(sp)
  rem t0, t1, t0
  sw t0, 304(sp)
  lui t0, %hi(sum)
  sw t0, 312(sp)
  lw t1, 312(sp)
  addi t0, t1, %lo(sum)
  sw t0, 312(sp)
  lw t1, 312(sp)
  lw t0, 0(t1)
  sw t0, 308(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 316(sp)
  lw t1, 308(sp)
  lw t0, 316(sp)
  add t0, t1, t0
  sw t0, 320(sp)
  lw t1, 320(sp)
  lw t0, 304(sp)
  sw t0, 0(t1)
  lui t0, %hi(sum)
  sw t0, 328(sp)
  lw t1, 328(sp)
  addi t0, t1, %lo(sum)
  sw t0, 328(sp)
  lw t1, 328(sp)
  lw t0, 0(t1)
  sw t0, 324(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 332(sp)
  lw t1, 324(sp)
  lw t0, 332(sp)
  add t0, t1, t0
  sw t0, 336(sp)
  lui t0, %hi(b)
  sw t0, 344(sp)
  lw t1, 344(sp)
  addi t0, t1, %lo(b)
  sw t0, 344(sp)
  lw t1, 344(sp)
  lw t0, 0(t1)
  sw t0, 340(sp)
  lw t1, 336(sp)
  lw t0, 0(t1)
  sw t0, 348(sp)
  lw t1, 348(sp)
  slli t0, t1, 2
  sw t0, 352(sp)
  lw t1, 340(sp)
  lw t0, 352(sp)
  add t0, t1, t0
  sw t0, 356(sp)
  lw t1, 356(sp)
  lw t0, 0(t1)
  sw t0, 360(sp)
  li t1, 0
  lw t0, 360(sp)
  slt t0, t1, t0
  sb t0, 368(sp)
  li t0, 0
  sw t0, 544(sp)
  lb t1, 368(sp)
  beqz t1, .L96
  j .L91
.L91:
  lui t0, %hi(flag)
  sw t0, 376(sp)
  lw t1, 376(sp)
  addi t0, t1, %lo(flag)
  sw t0, 376(sp)
  lw t1, 376(sp)
  lw t0, 0(t1)
  sw t0, 372(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 380(sp)
  lw t1, 372(sp)
  lw t0, 380(sp)
  add t0, t1, t0
  sw t0, 384(sp)
  lw t1, 384(sp)
  li t0, 1
  sw t0, 0(t1)
  lui t0, %hi(sum)
  sw t0, 392(sp)
  lw t1, 392(sp)
  addi t0, t1, %lo(sum)
  sw t0, 392(sp)
  lw t1, 392(sp)
  lw t0, 0(t1)
  sw t0, 388(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 396(sp)
  lw t1, 388(sp)
  lw t0, 396(sp)
  add t0, t1, t0
  sw t0, 400(sp)
  lui t0, %hi(s)
  sw t0, 408(sp)
  lw t1, 408(sp)
  addi t0, t1, %lo(s)
  sw t0, 408(sp)
  lw t1, 408(sp)
  lw t0, 0(t1)
  sw t0, 404(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 412(sp)
  lw t1, 404(sp)
  lw t0, 412(sp)
  add t0, t1, t0
  sw t0, 416(sp)
  lw t1, 416(sp)
  lw t0, 0(t1)
  sw t0, 420(sp)
  li t1, 0
  slli t0, t1, 2
  sw t0, 424(sp)
  lw t1, 420(sp)
  lw t0, 424(sp)
  add t0, t1, t0
  sw t0, 428(sp)
  lw t1, 400(sp)
  lw t0, 0(t1)
  sw t0, 432(sp)
  lw t1, 428(sp)
  lw t0, 432(sp)
  sw t0, 0(t1)
  li t0, 1
  sw t0, 436(sp)
  j .L92
.L92:
  lw t1, 436(sp)
  mv t0, t1
  sw t0, 440(sp)
  lui t0, %hi(L)
  sw t0, 452(sp)
  lw t1, 452(sp)
  addi t0, t1, %lo(L)
  sw t0, 452(sp)
  lw t1, 452(sp)
  lw t0, 0(t1)
  sw t0, 448(sp)
  lw t1, 440(sp)
  lw t0, 448(sp)
  slt t0, t1, t0
  sb t0, 460(sp)
  lb t1, 460(sp)
  beqz t1, .L95
  j .L93
.L93:
  lui t0, %hi(s)
  sw t0, 468(sp)
  lw t1, 468(sp)
  addi t0, t1, %lo(s)
  sw t0, 468(sp)
  lw t1, 468(sp)
  lw t0, 0(t1)
  sw t0, 464(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 472(sp)
  lw t1, 464(sp)
  lw t0, 472(sp)
  add t0, t1, t0
  sw t0, 476(sp)
  lw t1, 440(sp)
  li t0, 1
  sub t0, t1, t0
  sw t0, 480(sp)
  lw t1, 476(sp)
  lw t0, 0(t1)
  sw t0, 484(sp)
  lw t1, 480(sp)
  slli t0, t1, 2
  sw t0, 488(sp)
  lw t1, 484(sp)
  lw t0, 488(sp)
  add t0, t1, t0
  sw t0, 492(sp)
  lw t1, 492(sp)
  lw t0, 0(t1)
  sw t0, 496(sp)
  lw t1, 496(sp)
  mv a0, t1
  call square
  mv t0, a0
  sw t0, 500(sp)
  lui t0, %hi(p)
  sw t0, 508(sp)
  lw t1, 508(sp)
  addi t0, t1, %lo(p)
  sw t0, 508(sp)
  lw t1, 508(sp)
  lw t0, 0(t1)
  sw t0, 504(sp)
  lw t1, 500(sp)
  lw t0, 504(sp)
  rem t0, t1, t0
  sw t0, 512(sp)
  lui t0, %hi(s)
  sw t0, 520(sp)
  lw t1, 520(sp)
  addi t0, t1, %lo(s)
  sw t0, 520(sp)
  lw t1, 520(sp)
  lw t0, 0(t1)
  sw t0, 516(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 524(sp)
  lw t1, 516(sp)
  lw t0, 524(sp)
  add t0, t1, t0
  sw t0, 528(sp)
  lw t1, 528(sp)
  lw t0, 0(t1)
  sw t0, 532(sp)
  lw t1, 440(sp)
  slli t0, t1, 2
  sw t0, 536(sp)
  lw t1, 532(sp)
  lw t0, 536(sp)
  add t0, t1, t0
  sw t0, 540(sp)
  lw t1, 540(sp)
  lw t0, 512(sp)
  sw t0, 0(t1)
  j .L94
.L94:
  lw t1, 440(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 444(sp)
  lw t1, 444(sp)
  mv t0, t1
  sw t0, 436(sp)
  j .L92
.L95:
  lw t1, 440(sp)
  mv t0, t1
  sw t0, 544(sp)
  j .L96
.L96:
  lw t1, 544(sp)
  mv t0, t1
  sw t0, 548(sp)
  lw t1, 548(sp)
  mv t0, t1
  sw t0, 1008(sp)
  li t0, 0
  sw t0, 1016(sp)
  li t0, 0
  sw t0, 1024(sp)
  li t0, 0
  sw t0, 1032(sp)
  li t0, 0
  sw t0, 1040(sp)
  j .L110
.L97:
  lui t0, %hi(t)
  sw t0, 556(sp)
  lw t1, 556(sp)
  addi t0, t1, %lo(t)
  sw t0, 556(sp)
  lw t1, 556(sp)
  lw t0, 0(t1)
  sw t0, 552(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 560(sp)
  lw t1, 552(sp)
  lw t0, 560(sp)
  add t0, t1, t0
  sw t0, 564(sp)
  lw t1, 564(sp)
  lw t0, 0(t1)
  sw t0, 568(sp)
  li t1, 0
  lw t0, 568(sp)
  slt t0, t1, t0
  sb t0, 576(sp)
  lb t1, 576(sp)
  beqz t1, .L99
  j .L98
.L98:
  lw t1, 4(sp)
  mv a0, t1
  call pushdown
  j .L99
.L99:
  lw t1, 4(sp)
  li t0, 2
  mul t0, t1, t0
  sw t0, 580(sp)
  lw t1, 4(sp)
  li t0, 2
  mul t0, t1, t0
  sw t0, 584(sp)
  lw t1, 584(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 588(sp)
  lw t1, 8(sp)
  lw t0, 12(sp)
  add t0, t1, t0
  sw t0, 592(sp)
  lw t1, 592(sp)
  li t0, 2
  div t0, t1, t0
  sw t0, 596(sp)
  lui t0, %hi(pl)
  sw t0, 604(sp)
  lw t1, 604(sp)
  addi t0, t1, %lo(pl)
  sw t0, 604(sp)
  lw t1, 604(sp)
  lw t0, 0(t1)
  sw t0, 600(sp)
  lw t1, 596(sp)
  lw t0, 600(sp)
  slt t0, t1, t0
  sw t0, 608(sp)
  lw t1, 608(sp)
  xori t0, t1, 1
  sb t0, 612(sp)
  lb t1, 612(sp)
  beqz t1, .L101
  j .L100
.L100:
  lw t1, 580(sp)
  mv a0, t1
  lw t1, 8(sp)
  mv a1, t1
  lw t1, 596(sp)
  mv a2, t1
  call update
  j .L101
.L101:
  lw t1, 596(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 616(sp)
  lui t0, %hi(pr)
  sw t0, 624(sp)
  lw t1, 624(sp)
  addi t0, t1, %lo(pr)
  sw t0, 624(sp)
  lw t1, 624(sp)
  lw t0, 0(t1)
  sw t0, 620(sp)
  lw t1, 620(sp)
  lw t0, 616(sp)
  slt t0, t1, t0
  sw t0, 628(sp)
  lw t1, 628(sp)
  xori t0, t1, 1
  sb t0, 632(sp)
  lb t1, 632(sp)
  beqz t1, .L103
  j .L102
.L102:
  lw t1, 596(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 636(sp)
  lw t1, 588(sp)
  mv a0, t1
  lw t1, 636(sp)
  mv a1, t1
  lw t1, 12(sp)
  mv a2, t1
  call update
  j .L103
.L103:
  lui t0, %hi(sum)
  sw t0, 644(sp)
  lw t1, 644(sp)
  addi t0, t1, %lo(sum)
  sw t0, 644(sp)
  lw t1, 644(sp)
  lw t0, 0(t1)
  sw t0, 640(sp)
  lw t1, 580(sp)
  slli t0, t1, 2
  sw t0, 648(sp)
  lw t1, 640(sp)
  lw t0, 648(sp)
  add t0, t1, t0
  sw t0, 652(sp)
  lui t0, %hi(sum)
  sw t0, 660(sp)
  lw t1, 660(sp)
  addi t0, t1, %lo(sum)
  sw t0, 660(sp)
  lw t1, 660(sp)
  lw t0, 0(t1)
  sw t0, 656(sp)
  lw t1, 588(sp)
  slli t0, t1, 2
  sw t0, 664(sp)
  lw t1, 656(sp)
  lw t0, 664(sp)
  add t0, t1, t0
  sw t0, 668(sp)
  lw t1, 652(sp)
  lw t0, 0(t1)
  sw t0, 672(sp)
  lw t1, 668(sp)
  lw t0, 0(t1)
  sw t0, 676(sp)
  lw t1, 672(sp)
  lw t0, 676(sp)
  add t0, t1, t0
  sw t0, 680(sp)
  lui t0, %hi(sum)
  sw t0, 688(sp)
  lw t1, 688(sp)
  addi t0, t1, %lo(sum)
  sw t0, 688(sp)
  lw t1, 688(sp)
  lw t0, 0(t1)
  sw t0, 684(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 692(sp)
  lw t1, 684(sp)
  lw t0, 692(sp)
  add t0, t1, t0
  sw t0, 696(sp)
  lw t1, 696(sp)
  lw t0, 680(sp)
  sw t0, 0(t1)
  lui t0, %hi(flag)
  sw t0, 704(sp)
  lw t1, 704(sp)
  addi t0, t1, %lo(flag)
  sw t0, 704(sp)
  lw t1, 704(sp)
  lw t0, 0(t1)
  sw t0, 700(sp)
  lw t1, 580(sp)
  slli t0, t1, 2
  sw t0, 708(sp)
  lw t1, 700(sp)
  lw t0, 708(sp)
  add t0, t1, t0
  sw t0, 712(sp)
  lui t0, %hi(flag)
  sw t0, 720(sp)
  lw t1, 720(sp)
  addi t0, t1, %lo(flag)
  sw t0, 720(sp)
  lw t1, 720(sp)
  lw t0, 0(t1)
  sw t0, 716(sp)
  lw t1, 588(sp)
  slli t0, t1, 2
  sw t0, 724(sp)
  lw t1, 716(sp)
  lw t0, 724(sp)
  add t0, t1, t0
  sw t0, 728(sp)
  lw t1, 712(sp)
  lw t0, 0(t1)
  sw t0, 732(sp)
  lw t1, 728(sp)
  lw t0, 0(t1)
  sw t0, 736(sp)
  lw t1, 732(sp)
  lw t0, 736(sp)
  and t0, t1, t0
  sw t0, 740(sp)
  lui t0, %hi(flag)
  sw t0, 748(sp)
  lw t1, 748(sp)
  addi t0, t1, %lo(flag)
  sw t0, 748(sp)
  lw t1, 748(sp)
  lw t0, 0(t1)
  sw t0, 744(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 752(sp)
  lw t1, 744(sp)
  lw t0, 752(sp)
  add t0, t1, t0
  sw t0, 756(sp)
  lw t1, 756(sp)
  lw t0, 740(sp)
  sw t0, 0(t1)
  lui t0, %hi(flag)
  sw t0, 764(sp)
  lw t1, 764(sp)
  addi t0, t1, %lo(flag)
  sw t0, 764(sp)
  lw t1, 764(sp)
  lw t0, 0(t1)
  sw t0, 760(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 768(sp)
  lw t1, 760(sp)
  lw t0, 768(sp)
  add t0, t1, t0
  sw t0, 772(sp)
  lw t1, 772(sp)
  lw t0, 0(t1)
  sw t0, 776(sp)
  li t1, 0
  lw t0, 776(sp)
  slt t0, t1, t0
  sb t0, 784(sp)
  li t0, 0
  sw t0, 1000(sp)
  lb t1, 784(sp)
  beqz t1, .L109
  j .L104
.L104:
  li t0, 0
  sw t0, 788(sp)
  j .L105
.L105:
  lw t1, 788(sp)
  mv t0, t1
  sw t0, 792(sp)
  lui t0, %hi(L)
  sw t0, 804(sp)
  lw t1, 804(sp)
  addi t0, t1, %lo(L)
  sw t0, 804(sp)
  lw t1, 804(sp)
  lw t0, 0(t1)
  sw t0, 800(sp)
  lw t1, 792(sp)
  lw t0, 800(sp)
  slt t0, t1, t0
  sb t0, 812(sp)
  lb t1, 812(sp)
  beqz t1, .L108
  j .L106
.L106:
  lui t0, %hi(s)
  sw t0, 820(sp)
  lw t1, 820(sp)
  addi t0, t1, %lo(s)
  sw t0, 820(sp)
  lw t1, 820(sp)
  lw t0, 0(t1)
  sw t0, 816(sp)
  lw t1, 580(sp)
  slli t0, t1, 2
  sw t0, 824(sp)
  lw t1, 816(sp)
  lw t0, 824(sp)
  add t0, t1, t0
  sw t0, 828(sp)
  lui t0, %hi(now)
  sw t0, 836(sp)
  lw t1, 836(sp)
  addi t0, t1, %lo(now)
  sw t0, 836(sp)
  lw t1, 836(sp)
  lw t0, 0(t1)
  sw t0, 832(sp)
  lw t1, 580(sp)
  slli t0, t1, 2
  sw t0, 840(sp)
  lw t1, 832(sp)
  lw t0, 840(sp)
  add t0, t1, t0
  sw t0, 844(sp)
  lw t1, 844(sp)
  lw t0, 0(t1)
  sw t0, 848(sp)
  lw t1, 792(sp)
  lw t0, 848(sp)
  add t0, t1, t0
  sw t0, 852(sp)
  lui t0, %hi(L)
  sw t0, 860(sp)
  lw t1, 860(sp)
  addi t0, t1, %lo(L)
  sw t0, 860(sp)
  lw t1, 860(sp)
  lw t0, 0(t1)
  sw t0, 856(sp)
  lw t1, 852(sp)
  lw t0, 856(sp)
  rem t0, t1, t0
  sw t0, 864(sp)
  lw t1, 828(sp)
  lw t0, 0(t1)
  sw t0, 868(sp)
  lw t1, 864(sp)
  slli t0, t1, 2
  sw t0, 872(sp)
  lw t1, 868(sp)
  lw t0, 872(sp)
  add t0, t1, t0
  sw t0, 876(sp)
  lui t0, %hi(s)
  sw t0, 884(sp)
  lw t1, 884(sp)
  addi t0, t1, %lo(s)
  sw t0, 884(sp)
  lw t1, 884(sp)
  lw t0, 0(t1)
  sw t0, 880(sp)
  lw t1, 588(sp)
  slli t0, t1, 2
  sw t0, 888(sp)
  lw t1, 880(sp)
  lw t0, 888(sp)
  add t0, t1, t0
  sw t0, 892(sp)
  lui t0, %hi(now)
  sw t0, 900(sp)
  lw t1, 900(sp)
  addi t0, t1, %lo(now)
  sw t0, 900(sp)
  lw t1, 900(sp)
  lw t0, 0(t1)
  sw t0, 896(sp)
  lw t1, 588(sp)
  slli t0, t1, 2
  sw t0, 904(sp)
  lw t1, 896(sp)
  lw t0, 904(sp)
  add t0, t1, t0
  sw t0, 908(sp)
  lw t1, 908(sp)
  lw t0, 0(t1)
  sw t0, 912(sp)
  lw t1, 792(sp)
  lw t0, 912(sp)
  add t0, t1, t0
  sw t0, 916(sp)
  lui t0, %hi(L)
  sw t0, 924(sp)
  lw t1, 924(sp)
  addi t0, t1, %lo(L)
  sw t0, 924(sp)
  lw t1, 924(sp)
  lw t0, 0(t1)
  sw t0, 920(sp)
  lw t1, 916(sp)
  lw t0, 920(sp)
  rem t0, t1, t0
  sw t0, 928(sp)
  lw t1, 892(sp)
  lw t0, 0(t1)
  sw t0, 932(sp)
  lw t1, 928(sp)
  slli t0, t1, 2
  sw t0, 936(sp)
  lw t1, 932(sp)
  lw t0, 936(sp)
  add t0, t1, t0
  sw t0, 940(sp)
  lw t1, 876(sp)
  lw t0, 0(t1)
  sw t0, 944(sp)
  lw t1, 940(sp)
  lw t0, 0(t1)
  sw t0, 948(sp)
  lw t1, 944(sp)
  lw t0, 948(sp)
  add t0, t1, t0
  sw t0, 952(sp)
  lui t0, %hi(s)
  sw t0, 960(sp)
  lw t1, 960(sp)
  addi t0, t1, %lo(s)
  sw t0, 960(sp)
  lw t1, 960(sp)
  lw t0, 0(t1)
  sw t0, 956(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 964(sp)
  lw t1, 956(sp)
  lw t0, 964(sp)
  add t0, t1, t0
  sw t0, 968(sp)
  lw t1, 968(sp)
  lw t0, 0(t1)
  sw t0, 972(sp)
  lw t1, 792(sp)
  slli t0, t1, 2
  sw t0, 976(sp)
  lw t1, 972(sp)
  lw t0, 976(sp)
  add t0, t1, t0
  sw t0, 980(sp)
  lw t1, 980(sp)
  lw t0, 952(sp)
  sw t0, 0(t1)
  j .L107
.L107:
  lw t1, 792(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 796(sp)
  lw t1, 796(sp)
  mv t0, t1
  sw t0, 788(sp)
  j .L105
.L108:
  lui t0, %hi(now)
  sw t0, 988(sp)
  lw t1, 988(sp)
  addi t0, t1, %lo(now)
  sw t0, 988(sp)
  lw t1, 988(sp)
  lw t0, 0(t1)
  sw t0, 984(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 992(sp)
  lw t1, 984(sp)
  lw t0, 992(sp)
  add t0, t1, t0
  sw t0, 996(sp)
  lw t1, 996(sp)
  li t0, 0
  sw t0, 0(t1)
  lw t1, 792(sp)
  mv t0, t1
  sw t0, 1000(sp)
  j .L109
.L109:
  lw t1, 1000(sp)
  mv t0, t1
  sw t0, 1004(sp)
  li t0, 0
  sw t0, 1008(sp)
  lw t1, 580(sp)
  mv t0, t1
  sw t0, 1016(sp)
  lw t1, 588(sp)
  mv t0, t1
  sw t0, 1024(sp)
  lw t1, 596(sp)
  mv t0, t1
  sw t0, 1032(sp)
  lw t1, 1004(sp)
  mv t0, t1
  sw t0, 1040(sp)
  j .L110
.L110:
  lw t1, 1008(sp)
  mv t0, t1
  sw t0, 1012(sp)
  lw t1, 1016(sp)
  mv t0, t1
  sw t0, 1020(sp)
  lw t1, 1024(sp)
  mv t0, t1
  sw t0, 1028(sp)
  lw t1, 1032(sp)
  mv t0, t1
  sw t0, 1036(sp)
  lw t1, 1040(sp)
  mv t0, t1
  sw t0, 1044(sp)
  lw ra, 0(sp)
  li t0, 1048
  add sp, sp, t0
  ret
  .text
  .globl query
query:
.L111:
  li t0, -260
  add sp, sp, t0
  mv t0, a2
  sw t0, 12(sp)
  mv t0, a1
  sw t0, 8(sp)
  mv t0, a0
  sw t0, 4(sp)
  sw ra, 0(sp)
  lui t0, %hi(pl)
  sw t0, 20(sp)
  lw t1, 20(sp)
  addi t0, t1, %lo(pl)
  sw t0, 20(sp)
  lw t1, 20(sp)
  lw t0, 0(t1)
  sw t0, 16(sp)
  lw t1, 8(sp)
  lw t0, 16(sp)
  slt t0, t1, t0
  sw t0, 24(sp)
  lw t1, 24(sp)
  xori t0, t1, 1
  sb t0, 28(sp)
  lb t1, 28(sp)
  beqz t1, .L114
  j .L112
.L112:
  lui t0, %hi(pr)
  sw t0, 36(sp)
  lw t1, 36(sp)
  addi t0, t1, %lo(pr)
  sw t0, 36(sp)
  lw t1, 36(sp)
  lw t0, 0(t1)
  sw t0, 32(sp)
  lw t1, 32(sp)
  lw t0, 12(sp)
  slt t0, t1, t0
  sw t0, 40(sp)
  lw t1, 40(sp)
  xori t0, t1, 1
  sb t0, 44(sp)
  lb t1, 44(sp)
  beqz t1, .L114
  j .L113
.L113:
  li t0, 1
  sb t0, 48(sp)
  j .L115
.L114:
  li t0, 0
  sb t0, 48(sp)
  j .L115
.L115:
  lb t1, 48(sp)
  mv t0, t1
  sb t0, 52(sp)
  lb t1, 52(sp)
  beqz t1, .L117
  j .L116
.L116:
  lui t0, %hi(sum)
  sw t0, 60(sp)
  lw t1, 60(sp)
  addi t0, t1, %lo(sum)
  sw t0, 60(sp)
  lw t1, 60(sp)
  lw t0, 0(t1)
  sw t0, 56(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 64(sp)
  lw t1, 56(sp)
  lw t0, 64(sp)
  add t0, t1, t0
  sw t0, 68(sp)
  lw t1, 68(sp)
  lw t0, 0(t1)
  sw t0, 72(sp)
  lw t1, 72(sp)
  mv t0, t1
  sw t0, 220(sp)
  li t0, 0
  sw t0, 228(sp)
  li t0, 0
  sw t0, 236(sp)
  li t0, 0
  sw t0, 244(sp)
  li t0, 0
  sw t0, 252(sp)
  j .L124
.L117:
  lw t1, 4(sp)
  li t0, 2
  mul t0, t1, t0
  sw t0, 76(sp)
  lw t1, 4(sp)
  li t0, 2
  mul t0, t1, t0
  sw t0, 80(sp)
  lw t1, 80(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 84(sp)
  lw t1, 8(sp)
  lw t0, 12(sp)
  add t0, t1, t0
  sw t0, 88(sp)
  lw t1, 88(sp)
  li t0, 2
  div t0, t1, t0
  sw t0, 92(sp)
  lui t0, %hi(t)
  sw t0, 100(sp)
  lw t1, 100(sp)
  addi t0, t1, %lo(t)
  sw t0, 100(sp)
  lw t1, 100(sp)
  lw t0, 0(t1)
  sw t0, 96(sp)
  lw t1, 4(sp)
  slli t0, t1, 2
  sw t0, 104(sp)
  lw t1, 96(sp)
  lw t0, 104(sp)
  add t0, t1, t0
  sw t0, 108(sp)
  lw t1, 108(sp)
  lw t0, 0(t1)
  sw t0, 112(sp)
  li t1, 0
  lw t0, 112(sp)
  slt t0, t1, t0
  sb t0, 120(sp)
  lb t1, 120(sp)
  beqz t1, .L119
  j .L118
.L118:
  lw t1, 4(sp)
  mv a0, t1
  call pushdown
  j .L119
.L119:
  lui t0, %hi(pl)
  sw t0, 128(sp)
  lw t1, 128(sp)
  addi t0, t1, %lo(pl)
  sw t0, 128(sp)
  lw t1, 128(sp)
  lw t0, 0(t1)
  sw t0, 124(sp)
  lw t1, 92(sp)
  lw t0, 124(sp)
  slt t0, t1, t0
  sw t0, 132(sp)
  lw t1, 132(sp)
  xori t0, t1, 1
  sb t0, 136(sp)
  li t0, 0
  sw t0, 160(sp)
  lb t1, 136(sp)
  beqz t1, .L121
  j .L120
.L120:
  lw t1, 76(sp)
  mv a0, t1
  lw t1, 8(sp)
  mv a1, t1
  lw t1, 92(sp)
  mv a2, t1
  call query
  mv t0, a0
  sw t0, 140(sp)
  li t1, 0
  lw t0, 140(sp)
  add t0, t1, t0
  sw t0, 144(sp)
  lui t0, %hi(MOD)
  sw t0, 152(sp)
  lw t1, 152(sp)
  addi t0, t1, %lo(MOD)
  sw t0, 152(sp)
  lw t1, 152(sp)
  lw t0, 0(t1)
  sw t0, 148(sp)
  lw t1, 144(sp)
  lw t0, 148(sp)
  rem t0, t1, t0
  sw t0, 156(sp)
  lw t1, 156(sp)
  mv t0, t1
  sw t0, 160(sp)
  j .L121
.L121:
  lw t1, 160(sp)
  mv t0, t1
  sw t0, 164(sp)
  lw t1, 92(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 168(sp)
  lui t0, %hi(pr)
  sw t0, 176(sp)
  lw t1, 176(sp)
  addi t0, t1, %lo(pr)
  sw t0, 176(sp)
  lw t1, 176(sp)
  lw t0, 0(t1)
  sw t0, 172(sp)
  lw t1, 172(sp)
  lw t0, 168(sp)
  slt t0, t1, t0
  sw t0, 180(sp)
  lw t1, 180(sp)
  xori t0, t1, 1
  sb t0, 184(sp)
  lw t1, 164(sp)
  mv t0, t1
  sw t0, 212(sp)
  lb t1, 184(sp)
  beqz t1, .L123
  j .L122
.L122:
  lw t1, 92(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 188(sp)
  lw t1, 84(sp)
  mv a0, t1
  lw t1, 188(sp)
  mv a1, t1
  lw t1, 12(sp)
  mv a2, t1
  call query
  mv t0, a0
  sw t0, 192(sp)
  lw t1, 164(sp)
  lw t0, 192(sp)
  add t0, t1, t0
  sw t0, 196(sp)
  lui t0, %hi(MOD)
  sw t0, 204(sp)
  lw t1, 204(sp)
  addi t0, t1, %lo(MOD)
  sw t0, 204(sp)
  lw t1, 204(sp)
  lw t0, 0(t1)
  sw t0, 200(sp)
  lw t1, 196(sp)
  lw t0, 200(sp)
  rem t0, t1, t0
  sw t0, 208(sp)
  lw t1, 208(sp)
  mv t0, t1
  sw t0, 212(sp)
  j .L123
.L123:
  lw t1, 212(sp)
  mv t0, t1
  sw t0, 216(sp)
  lw t1, 216(sp)
  mv t0, t1
  sw t0, 220(sp)
  lw t1, 76(sp)
  mv t0, t1
  sw t0, 228(sp)
  lw t1, 84(sp)
  mv t0, t1
  sw t0, 236(sp)
  lw t1, 92(sp)
  mv t0, t1
  sw t0, 244(sp)
  lw t1, 216(sp)
  mv t0, t1
  sw t0, 252(sp)
  j .L124
.L124:
  lw t1, 220(sp)
  mv t0, t1
  sw t0, 224(sp)
  lw t1, 228(sp)
  mv t0, t1
  sw t0, 232(sp)
  lw t1, 236(sp)
  mv t0, t1
  sw t0, 240(sp)
  lw t1, 244(sp)
  mv t0, t1
  sw t0, 248(sp)
  lw t1, 252(sp)
  mv t0, t1
  sw t0, 256(sp)
  lw t1, 224(sp)
  mv a0, t1
  lw ra, 0(sp)
  li t0, 260
  add sp, sp, t0
  ret
  .text
  .globl main
main:
.L125:
  li t0, -324
  add sp, sp, t0
  sw ra, 0(sp)
  call init_
  li t0, 4
  add t0, sp, t0
  sw t0, 12(sp)
  li t0, 8
  add t0, sp, t0
  sw t0, 16(sp)
  call getInt
  mv t0, a0
  sw t0, 20(sp)
  lui t0, %hi(n)
  sw t0, 24(sp)
  lw t1, 24(sp)
  addi t0, t1, %lo(n)
  sw t0, 24(sp)
  lw t1, 24(sp)
  lw t0, 20(sp)
  sw t0, 0(t1)
  call getInt
  mv t0, a0
  sw t0, 28(sp)
  lui t0, %hi(m)
  sw t0, 32(sp)
  lw t1, 32(sp)
  addi t0, t1, %lo(m)
  sw t0, 32(sp)
  lw t1, 32(sp)
  lw t0, 28(sp)
  sw t0, 0(t1)
  call getInt
  mv t0, a0
  sw t0, 36(sp)
  lui t0, %hi(p)
  sw t0, 40(sp)
  lw t1, 40(sp)
  addi t0, t1, %lo(p)
  sw t0, 40(sp)
  lw t1, 40(sp)
  lw t0, 36(sp)
  sw t0, 0(t1)
  lw t1, 16(sp)
  li t0, 1
  sw t0, 0(t1)
  lw t1, 16(sp)
  li t0, 1
  sw t0, 0(t1)
  j .L126
.L126:
  lw t1, 16(sp)
  lw t0, 0(t1)
  sw t0, 44(sp)
  lui t0, %hi(n)
  sw t0, 52(sp)
  lw t1, 52(sp)
  addi t0, t1, %lo(n)
  sw t0, 52(sp)
  lw t1, 52(sp)
  lw t0, 0(t1)
  sw t0, 48(sp)
  lw t1, 48(sp)
  lw t0, 44(sp)
  slt t0, t1, t0
  sw t0, 56(sp)
  lw t1, 56(sp)
  xori t0, t1, 1
  sb t0, 60(sp)
  lb t1, 60(sp)
  beqz t1, .L129
  j .L127
.L127:
  lui t0, %hi(p)
  sw t0, 68(sp)
  lw t1, 68(sp)
  addi t0, t1, %lo(p)
  sw t0, 68(sp)
  lw t1, 68(sp)
  lw t0, 0(t1)
  sw t0, 64(sp)
  li t1, 0
  mv a0, t1
  lw t1, 64(sp)
  mv a1, t1
  call RandRange
  mv t0, a0
  sw t0, 72(sp)
  lui t0, %hi(a)
  sw t0, 80(sp)
  lw t1, 80(sp)
  addi t0, t1, %lo(a)
  sw t0, 80(sp)
  lw t1, 80(sp)
  lw t0, 0(t1)
  sw t0, 76(sp)
  lw t1, 16(sp)
  lw t0, 0(t1)
  sw t0, 84(sp)
  lw t1, 84(sp)
  slli t0, t1, 2
  sw t0, 88(sp)
  lw t1, 76(sp)
  lw t0, 88(sp)
  add t0, t1, t0
  sw t0, 92(sp)
  lw t1, 92(sp)
  lw t0, 72(sp)
  sw t0, 0(t1)
  j .L128
.L128:
  lw t1, 16(sp)
  lw t0, 0(t1)
  sw t0, 96(sp)
  lw t1, 96(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 100(sp)
  lw t1, 16(sp)
  lw t0, 100(sp)
  sw t0, 0(t1)
  j .L126
.L129:
  call init
  lui t0, %hi(n)
  sw t0, 108(sp)
  lw t1, 108(sp)
  addi t0, t1, %lo(n)
  sw t0, 108(sp)
  lw t1, 108(sp)
  lw t0, 0(t1)
  sw t0, 104(sp)
  li t1, 1
  mv a0, t1
  li t1, 1
  mv a1, t1
  lw t1, 104(sp)
  mv a2, t1
  call build
  j .L130
.L130:
  lui t0, %hi(m)
  sw t0, 116(sp)
  lw t1, 116(sp)
  addi t0, t1, %lo(m)
  sw t0, 116(sp)
  lw t1, 116(sp)
  lw t0, 0(t1)
  sw t0, 112(sp)
  li t1, 0
  lw t0, 112(sp)
  slt t0, t1, t0
  sb t0, 124(sp)
  lb t1, 124(sp)
  beqz t1, .L139
  j .L131
.L131:
  call Rand
  mv t0, a0
  sw t0, 128(sp)
  lw t1, 128(sp)
  li t0, 2
  rem t0, t1, t0
  sw t0, 132(sp)
  lui t0, %hi(op)
  sw t0, 136(sp)
  lw t1, 136(sp)
  addi t0, t1, %lo(op)
  sw t0, 136(sp)
  lw t1, 136(sp)
  lw t0, 132(sp)
  sw t0, 0(t1)
  lui t0, %hi(n)
  sw t0, 144(sp)
  lw t1, 144(sp)
  addi t0, t1, %lo(n)
  sw t0, 144(sp)
  lw t1, 144(sp)
  lw t0, 0(t1)
  sw t0, 140(sp)
  li t1, 1
  mv a0, t1
  lw t1, 140(sp)
  mv a1, t1
  call RandRange
  mv t0, a0
  sw t0, 148(sp)
  lui t0, %hi(pl)
  sw t0, 152(sp)
  lw t1, 152(sp)
  addi t0, t1, %lo(pl)
  sw t0, 152(sp)
  lw t1, 152(sp)
  lw t0, 148(sp)
  sw t0, 0(t1)
  lui t0, %hi(n)
  sw t0, 160(sp)
  lw t1, 160(sp)
  addi t0, t1, %lo(n)
  sw t0, 160(sp)
  lw t1, 160(sp)
  lw t0, 0(t1)
  sw t0, 156(sp)
  li t1, 1
  mv a0, t1
  lw t1, 156(sp)
  mv a1, t1
  call RandRange
  mv t0, a0
  sw t0, 164(sp)
  lui t0, %hi(pr)
  sw t0, 168(sp)
  lw t1, 168(sp)
  addi t0, t1, %lo(pr)
  sw t0, 168(sp)
  lw t1, 168(sp)
  lw t0, 164(sp)
  sw t0, 0(t1)
  j .L132
.L132:
  lui t0, %hi(pr)
  sw t0, 176(sp)
  lw t1, 176(sp)
  addi t0, t1, %lo(pr)
  sw t0, 176(sp)
  lw t1, 176(sp)
  lw t0, 0(t1)
  sw t0, 172(sp)
  lui t0, %hi(pl)
  sw t0, 184(sp)
  lw t1, 184(sp)
  addi t0, t1, %lo(pl)
  sw t0, 184(sp)
  lw t1, 184(sp)
  lw t0, 0(t1)
  sw t0, 180(sp)
  lw t1, 180(sp)
  lw t0, 172(sp)
  slt t0, t1, t0
  sw t0, 188(sp)
  lw t1, 188(sp)
  xori t0, t1, 1
  sb t0, 192(sp)
  lb t1, 192(sp)
  beqz t1, .L134
  j .L133
.L133:
  lui t0, %hi(n)
  sw t0, 200(sp)
  lw t1, 200(sp)
  addi t0, t1, %lo(n)
  sw t0, 200(sp)
  lw t1, 200(sp)
  lw t0, 0(t1)
  sw t0, 196(sp)
  li t1, 1
  mv a0, t1
  lw t1, 196(sp)
  mv a1, t1
  call RandRange
  mv t0, a0
  sw t0, 204(sp)
  lui t0, %hi(pr)
  sw t0, 208(sp)
  lw t1, 208(sp)
  addi t0, t1, %lo(pr)
  sw t0, 208(sp)
  lw t1, 208(sp)
  lw t0, 204(sp)
  sw t0, 0(t1)
  j .L132
.L134:
  lui t0, %hi(op)
  sw t0, 216(sp)
  lw t1, 216(sp)
  addi t0, t1, %lo(op)
  sw t0, 216(sp)
  lw t1, 216(sp)
  lw t0, 0(t1)
  sw t0, 212(sp)
  lw t1, 212(sp)
  li t0, 0
  sub t0, t1, t0
  sw t0, 220(sp)
  lw t1, 220(sp)
  seqz t0, t1
  sb t0, 224(sp)
  lb t1, 224(sp)
  beqz t1, .L136
  j .L135
.L135:
  lui t0, %hi(n)
  sw t0, 232(sp)
  lw t1, 232(sp)
  addi t0, t1, %lo(n)
  sw t0, 232(sp)
  lw t1, 232(sp)
  lw t0, 0(t1)
  sw t0, 228(sp)
  li t1, 1
  mv a0, t1
  li t1, 1
  mv a1, t1
  lw t1, 228(sp)
  mv a2, t1
  call update
  j .L136
.L136:
  lui t0, %hi(op)
  sw t0, 240(sp)
  lw t1, 240(sp)
  addi t0, t1, %lo(op)
  sw t0, 240(sp)
  lw t1, 240(sp)
  lw t0, 0(t1)
  sw t0, 236(sp)
  lw t1, 236(sp)
  li t0, 1
  sub t0, t1, t0
  sw t0, 244(sp)
  lw t1, 244(sp)
  seqz t0, t1
  sb t0, 248(sp)
  lb t1, 248(sp)
  beqz t1, .L138
  j .L137
.L137:
  lui t0, %hi(n)
  sw t0, 256(sp)
  lw t1, 256(sp)
  addi t0, t1, %lo(n)
  sw t0, 256(sp)
  lw t1, 256(sp)
  lw t0, 0(t1)
  sw t0, 252(sp)
  li t1, 1
  mv a0, t1
  li t1, 1
  mv a1, t1
  lw t1, 252(sp)
  mv a2, t1
  call query
  mv t0, a0
  sw t0, 260(sp)
  lui t0, %hi(ans)
  sw t0, 268(sp)
  lw t1, 268(sp)
  addi t0, t1, %lo(ans)
  sw t0, 268(sp)
  lw t1, 268(sp)
  lw t0, 0(t1)
  sw t0, 264(sp)
  lw t1, 264(sp)
  lw t0, 260(sp)
  add t0, t1, t0
  sw t0, 272(sp)
  lui t0, %hi(MOD)
  sw t0, 280(sp)
  lw t1, 280(sp)
  addi t0, t1, %lo(MOD)
  sw t0, 280(sp)
  lw t1, 280(sp)
  lw t0, 0(t1)
  sw t0, 276(sp)
  lw t1, 272(sp)
  lw t0, 276(sp)
  rem t0, t1, t0
  sw t0, 284(sp)
  lui t0, %hi(ans)
  sw t0, 288(sp)
  lw t1, 288(sp)
  addi t0, t1, %lo(ans)
  sw t0, 288(sp)
  lw t1, 288(sp)
  lw t0, 284(sp)
  sw t0, 0(t1)
  j .L138
.L138:
  lui t0, %hi(m)
  sw t0, 296(sp)
  lw t1, 296(sp)
  addi t0, t1, %lo(m)
  sw t0, 296(sp)
  lw t1, 296(sp)
  lw t0, 0(t1)
  sw t0, 292(sp)
  lw t1, 292(sp)
  li t0, 1
  sub t0, t1, t0
  sw t0, 300(sp)
  lui t0, %hi(m)
  sw t0, 304(sp)
  lw t1, 304(sp)
  addi t0, t1, %lo(m)
  sw t0, 304(sp)
  lw t1, 304(sp)
  lw t0, 300(sp)
  sw t0, 0(t1)
  j .L130
.L139:
  lui t0, %hi(ans)
  sw t0, 312(sp)
  lw t1, 312(sp)
  addi t0, t1, %lo(ans)
  sw t0, 312(sp)
  lw t1, 312(sp)
  lw t0, 0(t1)
  sw t0, 308(sp)
  lw t1, 308(sp)
  mv a0, t1
  call toString
  mv t0, a0
  sw t0, 316(sp)
  lw t1, 316(sp)
  mv a0, t1
  call print
  lw t1, 12(sp)
  li t0, 0
  sw t0, 0(t1)
  j .L140
.L140:
  lw t1, 12(sp)
  lw t0, 0(t1)
  sw t0, 320(sp)
  lw t1, 320(sp)
  mv a0, t1
  lw ra, 0(sp)
  li t0, 324
  add sp, sp, t0
  ret
