@b = dso_local global ptr null
@now = dso_local global ptr null
@t = dso_local global ptr null
@a = dso_local global ptr null
@n = dso_local global i32 0
@m = dso_local global i32 0
@p = dso_local global i32 0
@op = dso_local global i32 0
@L = dso_local global i32 1
@flag = dso_local global ptr null
@s = dso_local global ptr null
@sum = dso_local global ptr null
@ans = dso_local global i32 0
@aa = dso_local global i32 13131
@bb = dso_local global i32 5353
@MOD = dso_local global i32 0
@no = dso_local global i32 1
@pl = dso_local global i32 0
@pr = dso_local global i32 0

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
define void @global_init() {
entry_0:
  %.1 = call ptr @__newPtrArray(i32 2000024, i32 500005)
  store ptr %.1, ptr @b
  %.3 = call ptr @__newPtrArray(i32 2000024, i32 500005)
  store ptr %.3, ptr @now
  %.5 = call ptr @__newPtrArray(i32 2000024, i32 500005)
  store ptr %.5, ptr @t
  %.7 = call ptr @__newPtrArray(i32 800024, i32 200005)
  store ptr %.7, ptr @a
  %.14 = call ptr @__newPtrArray(i32 2000024, i32 500005)
  store ptr %.14, ptr @flag
  %.16 = call ptr @__newPtrArray(i32 2000024, i32 500005)
  br label %for.cond_2
for.cond_2:
  %.755 = phi i32 [ 0, %entry_0 ], [ %.23, %for.step_4 ]
  %.18 = icmp slt i32 %.755, 500005
  br i1 %.18, label %for.loop_3, label %for.end_5
for.loop_3:
  %.20 = call ptr @__newPtrArray(i32 324, i32 80)
  %.21 = getelementptr ptr, ptr %.16, i32 %.755
  store ptr %.20, ptr %.21
  br label %for.step_4
for.step_4:
  %.23 = add i32 %.755, 1
  br label %for.cond_2
for.end_5:
  store ptr %.16, ptr @s
  %.26 = call ptr @__newPtrArray(i32 2000024, i32 500005)
  store ptr %.26, ptr @sum
  store i32 32761, ptr @MOD
  br label %return_1
return_1:
  ret void
}

define i32 @square(i32 %.35) {
entry_0:
  %.39 = load i32, ptr @p
  %.40 = srem i32 %.35, %.39
  %.42 = load i32, ptr @p
  %.43 = srem i32 %.35, %.42
  %.44 = mul i32 %.40, %.43
  br label %return_1
return_1:
  ret i32 %.44
}

define i32 @gcd(i32 %.46, i32 %.48) {
entry_0:
  %.52 = icmp eq i32 %.48, 0
  br i1 %.52, label %if.then_3, label %if.end_2
if.then_3:
  br label %return_1
if.end_2:
  %.56 = icmp slt i32 %.46, %.48
  br i1 %.56, label %if.then_5, label %if.else_6
if.then_5:
  %.59 = call i32 @gcd(i32 %.48, i32 %.46)
  br label %return_1
if.else_6:
  %.62 = srem i32 %.46, %.48
  %.64 = call i32 @gcd(i32 %.48, i32 %.62)
  br label %return_1
return_1:
  %.756 = phi i32 [ %.64, %if.else_6 ], [ %.59, %if.then_5 ], [ %.46, %if.then_3 ]
  ret i32 %.756
}

define i32 @lcm(i32 %.66, i32 %.68) {
entry_0:
  %.73 = call i32 @gcd(i32 %.66, i32 %.68)
  %.75 = sdiv i32 %.66, %.73
  %.77 = mul i32 %.75, %.68
  br label %return_1
return_1:
  ret i32 %.77
}

define i32 @Rand() {
entry_0:
  br label %for.cond_2
for.cond_2:
  %.757 = phi i32 [ 1, %entry_0 ], [ %.91, %for.step_4 ]
  %.82 = icmp slt i32 %.757, 3
  br i1 %.82, label %for.loop_3, label %for.end_5
for.loop_3:
  %.83 = load i32, ptr @no
  %.84 = load i32, ptr @aa
  %.85 = mul i32 %.83, %.84
  %.86 = load i32, ptr @bb
  %.87 = add i32 %.85, %.86
  %.88 = load i32, ptr @MOD
  %.89 = srem i32 %.87, %.88
  store i32 %.89, ptr @no
  br label %for.step_4
for.step_4:
  %.91 = add i32 %.757, 1
  br label %for.cond_2
for.end_5:
  %.92 = load i32, ptr @no
  %.93 = icmp slt i32 %.92, 0
  br i1 %.93, label %if.then_7, label %if.end_6
if.then_7:
  %.95 = load i32, ptr @no
  %.94 = sub i32 0, %.95
  store i32 %.94, ptr @no
  br label %if.end_6
if.end_6:
  %.96 = load i32, ptr @no
  br label %return_1
return_1:
  ret i32 %.96
}

define i32 @RandRange(i32 %.98, i32 %.100) {
entry_0:
  %.103 = call i32 @Rand()
  %.106 = sub i32 %.100, %.98
  %.107 = add i32 %.106, 1
  %.108 = srem i32 %.103, %.107
  %.110 = add i32 %.98, %.108
  %.111 = add i32 %.110, 1
  br label %return_1
return_1:
  ret i32 %.111
}

define void @init() {
entry_0:
  %.113 = call ptr @__newPtrArray(i32 560024, i32 140005)
  br label %for.cond_2
for.cond_2:
  %.759 = phi i32 [ 2, %entry_0 ], [ %.123, %for.step_4 ]
  %.117 = load i32, ptr @p
  %.118 = icmp slt i32 %.759, %.117
  br i1 %.118, label %for.loop_3, label %for.end_5
for.loop_3:
  %.120 = getelementptr i32, ptr %.113, i32 %.759
  store i32 %.759, ptr %.120
  br label %for.step_4
for.step_4:
  %.123 = add i32 %.759, 1
  br label %for.cond_2
for.end_5:
  br label %for.cond_6
for.cond_6:
  %.760 = phi i32 [ 2, %for.end_5 ], [ %.142, %for.step_8 ]
  %.762 = phi i32 [ 0, %for.end_5 ], [ %.761, %for.step_8 ]
  %.126 = load i32, ptr @p
  %.127 = icmp slt i32 %.760, %.126
  br i1 %.127, label %for.loop_7, label %for.end_9
for.loop_7:
  br label %for.cond_10
for.cond_10:
  %.761 = phi i32 [ 1, %for.loop_7 ], [ %.140, %for.step_12 ]
  %.129 = icmp sle i32 %.761, 15
  br i1 %.129, label %for.loop_11, label %for.end_13
for.loop_11:
  %.131 = getelementptr i32, ptr %.113, i32 %.760
  %.133 = load i32, ptr %.131
  %.134 = call i32 @square(i32 %.133)
  %.135 = load i32, ptr @p
  %.136 = srem i32 %.134, %.135
  %.138 = getelementptr i32, ptr %.113, i32 %.760
  store i32 %.136, ptr %.138
  br label %for.step_12
for.step_12:
  %.140 = add i32 %.761, 1
  br label %for.cond_10
for.end_13:
  br label %for.step_8
for.step_8:
  %.142 = add i32 %.760, 1
  br label %for.cond_6
for.end_9:
  br label %for.cond_14
for.cond_14:
  %.758 = phi i32 [ 2, %for.end_9 ], [ %.171, %for.step_16 ]
  %.764 = phi i32 [ 0, %for.end_9 ], [ %.763, %for.step_16 ]
  %.765 = phi i32 [ 0, %for.end_9 ], [ %.156, %for.step_16 ]
  %.145 = load i32, ptr @p
  %.146 = icmp slt i32 %.758, %.145
  br i1 %.146, label %for.loop_15, label %for.end_17
for.loop_15:
  %.150 = getelementptr i32, ptr %.113, i32 %.758
  %.152 = load i32, ptr %.150
  br label %for.cond_18
for.cond_18:
  %.763 = phi i32 [ 1, %for.loop_15 ], [ %.166, %for.step_20 ]
  %.766 = phi i32 [ %.152, %for.loop_15 ], [ %.156, %for.step_20 ]
  br label %for.loop_19
for.loop_19:
  %.154 = call i32 @square(i32 %.766)
  %.155 = load i32, ptr @p
  %.156 = srem i32 %.154, %.155
  %.157 = load ptr, ptr @b
  %.158 = getelementptr i32, ptr %.157, i32 %.156
  store i32 1, ptr %.158
  %.161 = getelementptr i32, ptr %.113, i32 %.758
  %.164 = load i32, ptr %.161
  %.165 = icmp eq i32 %.156, %.164
  br i1 %.165, label %if.then_23, label %if.end_22
if.then_23:
  br label %for.end_21
if.end_22:
  br label %for.step_20
for.step_20:
  %.166 = add i32 %.763, 1
  br label %for.cond_18
for.end_21:
  %.168 = load i32, ptr @L
  %.170 = call i32 @lcm(i32 %.168, i32 %.763)
  store i32 %.170, ptr @L
  br label %for.step_16
for.step_16:
  %.171 = add i32 %.758, 1
  br label %for.cond_14
for.end_17:
  %.173 = load ptr, ptr @b
  %.174 = getelementptr i32, ptr %.173, i32 0
  store i32 1, ptr %.174
  %.175 = load ptr, ptr @b
  %.176 = getelementptr i32, ptr %.175, i32 1
  store i32 1, ptr %.176
  br label %return_1
return_1:
  ret void
}

define void @build(i32 %.178, i32 %.180, i32 %.182) {
entry_0:
  %.186 = icmp eq i32 %.180, %.182
  br i1 %.186, label %if.then_3, label %if.else_18
if.then_3:
  %.187 = load ptr, ptr @a
  %.188 = getelementptr i32, ptr %.187, i32 %.180
  %.190 = load ptr, ptr @sum
  %.191 = getelementptr i32, ptr %.190, i32 %.178
  %.193 = load i32, ptr %.188
  store i32 %.193, ptr %.191
  %.194 = load ptr, ptr @a
  %.195 = getelementptr i32, ptr %.194, i32 %.180
  %.197 = load i32, ptr %.195
  %.198 = load i32, ptr @p
  %.199 = icmp slt i32 %.197, %.198
  br i1 %.199, label %rhsBlock_4, label %falseBlock_6
rhsBlock_4:
  %.201 = load ptr, ptr @a
  %.202 = getelementptr i32, ptr %.201, i32 %.180
  %.204 = load i32, ptr %.202
  %.205 = icmp sge i32 %.204, 0
  br i1 %.205, label %trueBlock_5, label %falseBlock_6
trueBlock_5:
  br label %shortCir.end_7
falseBlock_6:
  br label %shortCir.end_7
shortCir.end_7:
  %.772 = phi i1 [ 1, %trueBlock_5 ], [ 0, %falseBlock_6 ]
  br i1 %.772, label %rhsBlock_8, label %falseBlock_10
rhsBlock_8:
  %.208 = load ptr, ptr @a
  %.209 = getelementptr i32, ptr %.208, i32 %.180
  %.211 = load i32, ptr %.209
  %.212 = load i32, ptr @p
  %.213 = srem i32 %.211, %.212
  %.214 = load ptr, ptr @b
  %.215 = getelementptr i32, ptr %.214, i32 %.213
  %.216 = load i32, ptr %.215
  %.217 = icmp sgt i32 %.216, 0
  br i1 %.217, label %trueBlock_9, label %falseBlock_10
trueBlock_9:
  br label %shortCir.end_11
falseBlock_10:
  br label %shortCir.end_11
shortCir.end_11:
  %.774 = phi i1 [ 1, %trueBlock_9 ], [ 0, %falseBlock_10 ]
  br i1 %.774, label %if.then_13, label %if.end_12
if.then_13:
  %.219 = load ptr, ptr @flag
  %.220 = getelementptr i32, ptr %.219, i32 %.178
  store i32 1, ptr %.220
  %.222 = load ptr, ptr @a
  %.223 = getelementptr i32, ptr %.222, i32 %.180
  %.225 = load ptr, ptr @s
  %.226 = getelementptr ptr, ptr %.225, i32 %.178
  %.228 = load ptr, ptr %.226
  %.229 = getelementptr i32, ptr %.228, i32 0
  %.230 = load i32, ptr %.223
  store i32 %.230, ptr %.229
  br label %for.cond_14
for.cond_14:
  %.768 = phi i32 [ 1, %if.then_13 ], [ %.251, %for.step_16 ]
  %.232 = load i32, ptr @L
  %.233 = icmp slt i32 %.768, %.232
  br i1 %.233, label %for.loop_15, label %for.end_17
for.loop_15:
  %.234 = load ptr, ptr @s
  %.235 = getelementptr ptr, ptr %.234, i32 %.178
  %.238 = sub i32 %.768, 1
  %.239 = load ptr, ptr %.235
  %.240 = getelementptr i32, ptr %.239, i32 %.238
  %.241 = load i32, ptr %.240
  %.242 = call i32 @square(i32 %.241)
  %.243 = load i32, ptr @p
  %.244 = srem i32 %.242, %.243
  %.245 = load ptr, ptr @s
  %.246 = getelementptr ptr, ptr %.245, i32 %.178
  %.248 = load ptr, ptr %.246
  %.249 = getelementptr i32, ptr %.248, i32 %.768
  store i32 %.244, ptr %.249
  br label %for.step_16
for.step_16:
  %.251 = add i32 %.768, 1
  br label %for.cond_14
for.end_17:
  br label %if.end_12
if.end_12:
  %.767 = phi i32 [ 0, %shortCir.end_11 ], [ %.768, %for.end_17 ]
  %.253 = load ptr, ptr @now
  %.254 = getelementptr i32, ptr %.253, i32 %.178
  store i32 0, ptr %.254
  br label %if.end_2
if.else_18:
  %.258 = mul i32 %.178, 2
  %.261 = mul i32 %.178, 2
  %.262 = add i32 %.261, 1
  %.266 = add i32 %.180, %.182
  %.267 = sdiv i32 %.266, 2
  call void @build(i32 %.258, i32 %.180, i32 %.267)
  %.272 = add i32 %.267, 1
  call void @build(i32 %.262, i32 %.272, i32 %.182)
  %.275 = load ptr, ptr @sum
  %.276 = getelementptr i32, ptr %.275, i32 %.258
  %.278 = load ptr, ptr @sum
  %.279 = getelementptr i32, ptr %.278, i32 %.262
  %.281 = load i32, ptr %.276
  %.282 = load i32, ptr %.279
  %.283 = add i32 %.281, %.282
  %.284 = load ptr, ptr @sum
  %.285 = getelementptr i32, ptr %.284, i32 %.178
  store i32 %.283, ptr %.285
  %.287 = load ptr, ptr @flag
  %.288 = getelementptr i32, ptr %.287, i32 %.258
  %.290 = load ptr, ptr @flag
  %.291 = getelementptr i32, ptr %.290, i32 %.262
  %.293 = load i32, ptr %.288
  %.294 = load i32, ptr %.291
  %.295 = and i32 %.293, %.294
  %.296 = load ptr, ptr @flag
  %.297 = getelementptr i32, ptr %.296, i32 %.178
  store i32 %.295, ptr %.297
  %.299 = load ptr, ptr @flag
  %.300 = getelementptr i32, ptr %.299, i32 %.178
  %.302 = load i32, ptr %.300
  %.303 = icmp sgt i32 %.302, 0
  br i1 %.303, label %if.then_20, label %if.end_19
if.then_20:
  br label %for.cond_21
for.cond_21:
  %.770 = phi i32 [ 0, %if.then_20 ], [ %.328, %for.step_23 ]
  %.305 = load i32, ptr @L
  %.306 = icmp slt i32 %.770, %.305
  br i1 %.306, label %for.loop_22, label %for.end_24
for.loop_22:
  %.307 = load ptr, ptr @s
  %.308 = getelementptr ptr, ptr %.307, i32 %.258
  %.310 = load ptr, ptr %.308
  %.311 = getelementptr i32, ptr %.310, i32 %.770
  %.313 = load ptr, ptr @s
  %.314 = getelementptr ptr, ptr %.313, i32 %.262
  %.316 = load ptr, ptr %.314
  %.317 = getelementptr i32, ptr %.316, i32 %.770
  %.319 = load i32, ptr %.311
  %.320 = load i32, ptr %.317
  %.321 = add i32 %.319, %.320
  %.322 = load ptr, ptr @s
  %.323 = getelementptr ptr, ptr %.322, i32 %.178
  %.325 = load ptr, ptr %.323
  %.326 = getelementptr i32, ptr %.325, i32 %.770
  store i32 %.321, ptr %.326
  br label %for.step_23
for.step_23:
  %.328 = add i32 %.770, 1
  br label %for.cond_21
for.end_24:
  %.330 = load ptr, ptr @now
  %.331 = getelementptr i32, ptr %.330, i32 0
  store i32 0, ptr %.331
  br label %if.end_19
if.end_19:
  %.769 = phi i32 [ 0, %if.else_18 ], [ %.770, %for.end_24 ]
  br label %if.end_2
if.end_2:
  %.771 = phi i32 [ %.769, %if.end_19 ], [ %.767, %if.end_12 ]
  %.773 = phi i1 [ 0, %if.end_19 ], [ %.772, %if.end_12 ]
  %.775 = phi i1 [ 0, %if.end_19 ], [ %.774, %if.end_12 ]
  %.776 = phi i32 [ %.258, %if.end_19 ], [ 0, %if.end_12 ]
  %.777 = phi i32 [ %.262, %if.end_19 ], [ 0, %if.end_12 ]
  %.778 = phi i32 [ %.267, %if.end_19 ], [ 0, %if.end_12 ]
  br label %return_1
return_1:
  ret void
}

define void @pushdown(i32 %.333) {
entry_0:
  %.334 = load ptr, ptr @t
  %.335 = getelementptr i32, ptr %.334, i32 %.333
  %.337 = load i32, ptr %.335
  %.338 = icmp sgt i32 %.337, 0
  br i1 %.338, label %if.then_3, label %if.end_2
if.then_3:
  %.341 = mul i32 %.333, 2
  %.344 = mul i32 %.333, 2
  %.345 = add i32 %.344, 1
  %.346 = load ptr, ptr @now
  %.347 = getelementptr i32, ptr %.346, i32 %.341
  %.349 = load ptr, ptr @t
  %.350 = getelementptr i32, ptr %.349, i32 %.333
  %.352 = load i32, ptr %.347
  %.353 = load i32, ptr %.350
  %.354 = add i32 %.352, %.353
  %.355 = load i32, ptr @L
  %.356 = srem i32 %.354, %.355
  %.357 = load ptr, ptr @now
  %.358 = getelementptr i32, ptr %.357, i32 %.341
  store i32 %.356, ptr %.358
  %.360 = load ptr, ptr @s
  %.361 = getelementptr ptr, ptr %.360, i32 %.341
  %.363 = load ptr, ptr @now
  %.364 = getelementptr i32, ptr %.363, i32 %.341
  %.366 = load ptr, ptr %.361
  %.368 = load i32, ptr %.364
  %.367 = getelementptr i32, ptr %.366, i32 %.368
  %.369 = load ptr, ptr @sum
  %.370 = getelementptr i32, ptr %.369, i32 %.341
  %.372 = load i32, ptr %.367
  store i32 %.372, ptr %.370
  %.373 = load ptr, ptr @t
  %.374 = getelementptr i32, ptr %.373, i32 %.341
  %.376 = load ptr, ptr @t
  %.377 = getelementptr i32, ptr %.376, i32 %.333
  %.379 = load i32, ptr %.374
  %.380 = load i32, ptr %.377
  %.381 = add i32 %.379, %.380
  %.382 = load i32, ptr @L
  %.383 = srem i32 %.381, %.382
  %.384 = load ptr, ptr @t
  %.385 = getelementptr i32, ptr %.384, i32 %.341
  store i32 %.383, ptr %.385
  %.387 = load ptr, ptr @now
  %.388 = getelementptr i32, ptr %.387, i32 %.345
  %.390 = load ptr, ptr @t
  %.391 = getelementptr i32, ptr %.390, i32 %.333
  %.393 = load i32, ptr %.388
  %.394 = load i32, ptr %.391
  %.395 = add i32 %.393, %.394
  %.396 = load i32, ptr @L
  %.397 = srem i32 %.395, %.396
  %.398 = load ptr, ptr @now
  %.399 = getelementptr i32, ptr %.398, i32 %.345
  store i32 %.397, ptr %.399
  %.401 = load ptr, ptr @s
  %.402 = getelementptr ptr, ptr %.401, i32 %.345
  %.404 = load ptr, ptr @now
  %.405 = getelementptr i32, ptr %.404, i32 %.345
  %.407 = load ptr, ptr %.402
  %.409 = load i32, ptr %.405
  %.408 = getelementptr i32, ptr %.407, i32 %.409
  %.410 = load ptr, ptr @sum
  %.411 = getelementptr i32, ptr %.410, i32 %.345
  %.413 = load i32, ptr %.408
  store i32 %.413, ptr %.411
  %.414 = load ptr, ptr @t
  %.415 = getelementptr i32, ptr %.414, i32 %.345
  %.417 = load ptr, ptr @t
  %.418 = getelementptr i32, ptr %.417, i32 %.333
  %.420 = load i32, ptr %.415
  %.421 = load i32, ptr %.418
  %.422 = add i32 %.420, %.421
  %.423 = load i32, ptr @L
  %.424 = srem i32 %.422, %.423
  %.425 = load ptr, ptr @t
  %.426 = getelementptr i32, ptr %.425, i32 %.345
  store i32 %.424, ptr %.426
  %.428 = load ptr, ptr @t
  %.429 = getelementptr i32, ptr %.428, i32 %.333
  store i32 0, ptr %.429
  br label %if.end_2
if.end_2:
  %.779 = phi i32 [ 0, %entry_0 ], [ %.341, %if.then_3 ]
  %.780 = phi i32 [ 0, %entry_0 ], [ %.345, %if.then_3 ]
  br label %return_1
return_1:
  ret void
}

define void @update(i32 %.432, i32 %.434, i32 %.436) {
entry_0:
  %.437 = load i32, ptr @pl
  %.439 = icmp sle i32 %.437, %.434
  br i1 %.439, label %rhsBlock_2, label %falseBlock_4
rhsBlock_2:
  %.441 = load i32, ptr @pr
  %.443 = icmp sge i32 %.441, %.436
  br i1 %.443, label %trueBlock_3, label %falseBlock_4
trueBlock_3:
  br label %shortCir.end_5
falseBlock_4:
  br label %shortCir.end_5
shortCir.end_5:
  %.781 = phi i1 [ 1, %trueBlock_3 ], [ 0, %falseBlock_4 ]
  br i1 %.781, label %rhsBlock_6, label %falseBlock_8
rhsBlock_6:
  %.446 = load ptr, ptr @flag
  %.447 = getelementptr i32, ptr %.446, i32 %.432
  %.449 = load i32, ptr %.447
  %.450 = icmp sgt i32 %.449, 0
  br i1 %.450, label %trueBlock_7, label %falseBlock_8
trueBlock_7:
  br label %shortCir.end_9
falseBlock_8:
  br label %shortCir.end_9
shortCir.end_9:
  %.782 = phi i1 [ 1, %trueBlock_7 ], [ 0, %falseBlock_8 ]
  br i1 %.782, label %if.then_11, label %if.end_10
if.then_11:
  %.452 = load ptr, ptr @now
  %.453 = getelementptr i32, ptr %.452, i32 %.432
  %.455 = load i32, ptr %.453
  %.456 = add i32 %.455, 1
  %.457 = load i32, ptr @L
  %.458 = srem i32 %.456, %.457
  %.459 = load ptr, ptr @now
  %.460 = getelementptr i32, ptr %.459, i32 %.432
  store i32 %.458, ptr %.460
  %.462 = load ptr, ptr @s
  %.463 = getelementptr ptr, ptr %.462, i32 %.432
  %.465 = load ptr, ptr @now
  %.466 = getelementptr i32, ptr %.465, i32 %.432
  %.468 = load ptr, ptr %.463
  %.470 = load i32, ptr %.466
  %.469 = getelementptr i32, ptr %.468, i32 %.470
  %.471 = load ptr, ptr @sum
  %.472 = getelementptr i32, ptr %.471, i32 %.432
  %.474 = load i32, ptr %.469
  store i32 %.474, ptr %.472
  %.475 = load ptr, ptr @t
  %.476 = getelementptr i32, ptr %.475, i32 %.432
  %.478 = load i32, ptr %.476
  %.479 = add i32 %.478, 1
  %.480 = load i32, ptr @L
  %.481 = srem i32 %.479, %.480
  %.482 = load ptr, ptr @t
  %.483 = getelementptr i32, ptr %.482, i32 %.432
  store i32 %.481, ptr %.483
  br label %return_1
if.end_10:
  %.487 = icmp eq i32 %.434, %.436
  br i1 %.487, label %if.then_13, label %if.end_12
if.then_13:
  %.488 = load ptr, ptr @sum
  %.489 = getelementptr i32, ptr %.488, i32 %.432
  %.491 = load i32, ptr %.489
  %.492 = call i32 @square(i32 %.491)
  %.493 = load i32, ptr @p
  %.494 = srem i32 %.492, %.493
  %.495 = load ptr, ptr @sum
  %.496 = getelementptr i32, ptr %.495, i32 %.432
  store i32 %.494, ptr %.496
  %.498 = load ptr, ptr @sum
  %.499 = getelementptr i32, ptr %.498, i32 %.432
  %.501 = load ptr, ptr @b
  %.503 = load i32, ptr %.499
  %.502 = getelementptr i32, ptr %.501, i32 %.503
  %.504 = load i32, ptr %.502
  %.505 = icmp sgt i32 %.504, 0
  br i1 %.505, label %if.then_15, label %if.end_14
if.then_15:
  %.506 = load ptr, ptr @flag
  %.507 = getelementptr i32, ptr %.506, i32 %.432
  store i32 1, ptr %.507
  %.509 = load ptr, ptr @sum
  %.510 = getelementptr i32, ptr %.509, i32 %.432
  %.512 = load ptr, ptr @s
  %.513 = getelementptr ptr, ptr %.512, i32 %.432
  %.515 = load ptr, ptr %.513
  %.516 = getelementptr i32, ptr %.515, i32 0
  %.517 = load i32, ptr %.510
  store i32 %.517, ptr %.516
  br label %for.cond_16
for.cond_16:
  %.783 = phi i32 [ 1, %if.then_15 ], [ %.539, %for.step_18 ]
  %.520 = load i32, ptr @L
  %.521 = icmp slt i32 %.783, %.520
  br i1 %.521, label %for.loop_17, label %for.end_19
for.loop_17:
  %.522 = load ptr, ptr @s
  %.523 = getelementptr ptr, ptr %.522, i32 %.432
  %.526 = sub i32 %.783, 1
  %.527 = load ptr, ptr %.523
  %.528 = getelementptr i32, ptr %.527, i32 %.526
  %.529 = load i32, ptr %.528
  %.530 = call i32 @square(i32 %.529)
  %.531 = load i32, ptr @p
  %.532 = srem i32 %.530, %.531
  %.533 = load ptr, ptr @s
  %.534 = getelementptr ptr, ptr %.533, i32 %.432
  %.536 = load ptr, ptr %.534
  %.537 = getelementptr i32, ptr %.536, i32 %.783
  store i32 %.532, ptr %.537
  br label %for.step_18
for.step_18:
  %.539 = add i32 %.783, 1
  br label %for.cond_16
for.end_19:
  br label %if.end_14
if.end_14:
  %.784 = phi i32 [ 0, %if.then_13 ], [ %.783, %for.end_19 ]
  br label %return_1
if.end_12:
  %.541 = load ptr, ptr @t
  %.542 = getelementptr i32, ptr %.541, i32 %.432
  %.544 = load i32, ptr %.542
  %.545 = icmp sgt i32 %.544, 0
  br i1 %.545, label %if.then_21, label %if.end_20
if.then_21:
  call void @pushdown(i32 %.432)
  br label %if.end_20
if.end_20:
  %.549 = mul i32 %.432, 2
  %.552 = mul i32 %.432, 2
  %.553 = add i32 %.552, 1
  %.557 = add i32 %.434, %.436
  %.558 = sdiv i32 %.557, 2
  %.559 = load i32, ptr @pl
  %.561 = icmp sle i32 %.559, %.558
  br i1 %.561, label %if.then_23, label %if.end_22
if.then_23:
  call void @update(i32 %.549, i32 %.434, i32 %.558)
  br label %if.end_22
if.end_22:
  %.566 = add i32 %.558, 1
  %.567 = load i32, ptr @pr
  %.568 = icmp sge i32 %.567, %.566
  br i1 %.568, label %if.then_25, label %if.end_24
if.then_25:
  %.570 = add i32 %.558, 1
  call void @update(i32 %.553, i32 %.570, i32 %.436)
  br label %if.end_24
if.end_24:
  %.573 = load ptr, ptr @sum
  %.574 = getelementptr i32, ptr %.573, i32 %.549
  %.576 = load ptr, ptr @sum
  %.577 = getelementptr i32, ptr %.576, i32 %.553
  %.579 = load i32, ptr %.574
  %.580 = load i32, ptr %.577
  %.581 = add i32 %.579, %.580
  %.582 = load ptr, ptr @sum
  %.583 = getelementptr i32, ptr %.582, i32 %.432
  store i32 %.581, ptr %.583
  %.585 = load ptr, ptr @flag
  %.586 = getelementptr i32, ptr %.585, i32 %.549
  %.588 = load ptr, ptr @flag
  %.589 = getelementptr i32, ptr %.588, i32 %.553
  %.591 = load i32, ptr %.586
  %.592 = load i32, ptr %.589
  %.593 = and i32 %.591, %.592
  %.594 = load ptr, ptr @flag
  %.595 = getelementptr i32, ptr %.594, i32 %.432
  store i32 %.593, ptr %.595
  %.597 = load ptr, ptr @flag
  %.598 = getelementptr i32, ptr %.597, i32 %.432
  %.600 = load i32, ptr %.598
  %.601 = icmp sgt i32 %.600, 0
  br i1 %.601, label %if.then_27, label %if.end_26
if.then_27:
  br label %for.cond_28
for.cond_28:
  %.789 = phi i32 [ 0, %if.then_27 ], [ %.641, %for.step_30 ]
  %.604 = load i32, ptr @L
  %.605 = icmp slt i32 %.789, %.604
  br i1 %.605, label %for.loop_29, label %for.end_31
for.loop_29:
  %.606 = load ptr, ptr @s
  %.607 = getelementptr ptr, ptr %.606, i32 %.549
  %.609 = load ptr, ptr @now
  %.610 = getelementptr i32, ptr %.609, i32 %.549
  %.613 = load i32, ptr %.610
  %.614 = add i32 %.789, %.613
  %.615 = load i32, ptr @L
  %.616 = srem i32 %.614, %.615
  %.617 = load ptr, ptr %.607
  %.618 = getelementptr i32, ptr %.617, i32 %.616
  %.619 = load ptr, ptr @s
  %.620 = getelementptr ptr, ptr %.619, i32 %.553
  %.622 = load ptr, ptr @now
  %.623 = getelementptr i32, ptr %.622, i32 %.553
  %.626 = load i32, ptr %.623
  %.627 = add i32 %.789, %.626
  %.628 = load i32, ptr @L
  %.629 = srem i32 %.627, %.628
  %.630 = load ptr, ptr %.620
  %.631 = getelementptr i32, ptr %.630, i32 %.629
  %.632 = load i32, ptr %.618
  %.633 = load i32, ptr %.631
  %.634 = add i32 %.632, %.633
  %.635 = load ptr, ptr @s
  %.636 = getelementptr ptr, ptr %.635, i32 %.432
  %.638 = load ptr, ptr %.636
  %.639 = getelementptr i32, ptr %.638, i32 %.789
  store i32 %.634, ptr %.639
  br label %for.step_30
for.step_30:
  %.641 = add i32 %.789, 1
  br label %for.cond_28
for.end_31:
  %.643 = load ptr, ptr @now
  %.644 = getelementptr i32, ptr %.643, i32 %.432
  store i32 0, ptr %.644
  br label %if.end_26
if.end_26:
  %.790 = phi i32 [ 0, %if.end_24 ], [ %.789, %for.end_31 ]
  br label %return_1
return_1:
  %.785 = phi i32 [ 0, %if.end_26 ], [ %.784, %if.end_14 ], [ 0, %if.then_11 ]
  %.786 = phi i32 [ %.549, %if.end_26 ], [ 0, %if.end_14 ], [ 0, %if.then_11 ]
  %.787 = phi i32 [ %.553, %if.end_26 ], [ 0, %if.end_14 ], [ 0, %if.then_11 ]
  %.788 = phi i32 [ %.558, %if.end_26 ], [ 0, %if.end_14 ], [ 0, %if.then_11 ]
  %.791 = phi i32 [ %.790, %if.end_26 ], [ 0, %if.end_14 ], [ 0, %if.then_11 ]
  ret void
}

define i32 @query(i32 %.647, i32 %.649, i32 %.651) {
entry_0:
  %.654 = load i32, ptr @pl
  %.656 = icmp sle i32 %.654, %.649
  br i1 %.656, label %rhsBlock_2, label %falseBlock_4
rhsBlock_2:
  %.658 = load i32, ptr @pr
  %.660 = icmp sge i32 %.658, %.651
  br i1 %.660, label %trueBlock_3, label %falseBlock_4
trueBlock_3:
  br label %shortCir.end_5
falseBlock_4:
  br label %shortCir.end_5
shortCir.end_5:
  %.793 = phi i1 [ 1, %trueBlock_3 ], [ 0, %falseBlock_4 ]
  br i1 %.793, label %if.then_7, label %if.end_6
if.then_7:
  %.662 = load ptr, ptr @sum
  %.663 = getelementptr i32, ptr %.662, i32 %.647
  %.665 = load i32, ptr %.663
  br label %return_1
if.end_6:
  %.668 = mul i32 %.647, 2
  %.671 = mul i32 %.647, 2
  %.672 = add i32 %.671, 1
  %.676 = add i32 %.649, %.651
  %.677 = sdiv i32 %.676, 2
  %.679 = load ptr, ptr @t
  %.680 = getelementptr i32, ptr %.679, i32 %.647
  %.682 = load i32, ptr %.680
  %.683 = icmp sgt i32 %.682, 0
  br i1 %.683, label %if.then_9, label %if.end_8
if.then_9:
  call void @pushdown(i32 %.647)
  br label %if.end_8
if.end_8:
  %.685 = load i32, ptr @pl
  %.687 = icmp sle i32 %.685, %.677
  br i1 %.687, label %if.then_11, label %if.end_10
if.then_11:
  %.691 = call i32 @query(i32 %.668, i32 %.649, i32 %.677)
  %.693 = add i32 0, %.691
  %.694 = load i32, ptr @MOD
  %.695 = srem i32 %.693, %.694
  br label %if.end_10
if.end_10:
  %.797 = phi i32 [ 0, %if.end_8 ], [ %.695, %if.then_11 ]
  %.697 = add i32 %.677, 1
  %.698 = load i32, ptr @pr
  %.699 = icmp sge i32 %.698, %.697
  br i1 %.699, label %if.then_13, label %if.end_12
if.then_13:
  %.701 = add i32 %.677, 1
  %.704 = call i32 @query(i32 %.672, i32 %.701, i32 %.651)
  %.706 = add i32 %.797, %.704
  %.707 = load i32, ptr @MOD
  %.708 = srem i32 %.706, %.707
  br label %if.end_12
if.end_12:
  %.798 = phi i32 [ %.797, %if.end_10 ], [ %.708, %if.then_13 ]
  br label %return_1
return_1:
  %.792 = phi i32 [ %.798, %if.end_12 ], [ %.665, %if.then_7 ]
  %.794 = phi i32 [ %.668, %if.end_12 ], [ 0, %if.then_7 ]
  %.795 = phi i32 [ %.672, %if.end_12 ], [ 0, %if.then_7 ]
  %.796 = phi i32 [ %.677, %if.end_12 ], [ 0, %if.then_7 ]
  %.799 = phi i32 [ %.798, %if.end_12 ], [ 0, %if.then_7 ]
  ret i32 %.792
}

define i32 @main() {
entry_0:
  call void @global_init()
  %retval = alloca i32
  %.715 = alloca i32
  %.712 = call i32 @getInt()
  store i32 %.712, ptr @n
  %.713 = call i32 @getInt()
  store i32 %.713, ptr @m
  %.714 = call i32 @getInt()
  store i32 %.714, ptr @p
  store i32 1, ptr %.715
  store i32 1, ptr %.715
  br label %for.cond_2
for.cond_2:
  %.716 = load i32, ptr %.715
  %.717 = load i32, ptr @n
  %.718 = icmp sle i32 %.716, %.717
  br i1 %.718, label %for.loop_3, label %for.end_5
for.loop_3:
  %.719 = load i32, ptr @p
  %.720 = call i32 @RandRange(i32 0, i32 %.719)
  %.721 = load ptr, ptr @a
  %.723 = load i32, ptr %.715
  %.722 = getelementptr i32, ptr %.721, i32 %.723
  store i32 %.720, ptr %.722
  br label %for.step_4
for.step_4:
  %.725 = load i32, ptr %.715
  %.724 = add i32 %.725, 1
  store i32 %.724, ptr %.715
  br label %for.cond_2
for.end_5:
  call void @init()
  %.726 = load i32, ptr @n
  call void @build(i32 1, i32 1, i32 %.726)
  br label %while.cond_6
while.cond_6:
  %.727 = load i32, ptr @m
  %.728 = icmp sgt i32 %.727, 0
  br i1 %.728, label %while.loop_7, label %while.end_8
while.loop_7:
  %.729 = call i32 @Rand()
  %.730 = srem i32 %.729, 2
  store i32 %.730, ptr @op
  %.731 = load i32, ptr @n
  %.732 = call i32 @RandRange(i32 1, i32 %.731)
  store i32 %.732, ptr @pl
  %.733 = load i32, ptr @n
  %.734 = call i32 @RandRange(i32 1, i32 %.733)
  store i32 %.734, ptr @pr
  br label %while.cond_9
while.cond_9:
  %.735 = load i32, ptr @pr
  %.736 = load i32, ptr @pl
  %.737 = icmp sle i32 %.735, %.736
  br i1 %.737, label %while.loop_10, label %while.end_11
while.loop_10:
  %.738 = load i32, ptr @n
  %.739 = call i32 @RandRange(i32 1, i32 %.738)
  store i32 %.739, ptr @pr
  br label %while.cond_9
while.end_11:
  %.740 = load i32, ptr @op
  %.741 = icmp eq i32 %.740, 0
  br i1 %.741, label %if.then_13, label %if.end_12
if.then_13:
  %.742 = load i32, ptr @n
  call void @update(i32 1, i32 1, i32 %.742)
  br label %if.end_12
if.end_12:
  %.743 = load i32, ptr @op
  %.744 = icmp eq i32 %.743, 1
  br i1 %.744, label %if.then_15, label %if.end_14
if.then_15:
  %.745 = load i32, ptr @n
  %.746 = call i32 @query(i32 1, i32 1, i32 %.745)
  %.747 = load i32, ptr @ans
  %.748 = add i32 %.747, %.746
  %.749 = load i32, ptr @MOD
  %.750 = srem i32 %.748, %.749
  store i32 %.750, ptr @ans
  br label %if.end_14
if.end_14:
  %.751 = load i32, ptr @m
  %.752 = sub i32 %.751, 1
  store i32 %.752, ptr @m
  br label %while.cond_6
while.end_8:
  %.753 = load i32, ptr @ans
  %.754 = call ptr @toString(i32 %.753)
  call void @print(ptr %.754)
  store i32 0, ptr %retval
  br label %return_1
return_1:
  %.711 = load i32, ptr %retval
  ret i32 %.711
}

