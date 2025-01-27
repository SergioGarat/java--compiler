.global main
.extern printf, scanf
.data
T25: .asciz "FACTORIAL "
T26: .asciz "! :"
T29: .asciz "RECURSIVE ADDING "
T28: .asciz "\n"
T30: .asciz " :"
T32: .asciz "\n"
T43: .asciz "He entrado: "
T42: .asciz "\n"
T44: .asciz "\nHE SALIDO\n"
T14: .asciz "\n"
T13: .asciz "Invalid number: "
format_int: .asciz "%d"
true_label : .asciz "true"
false_label : .asciz "false"
.text
# PROC_sumaDosNumeros:skip
PROC_sumaDosNumeros:

# pmb PROC_sumaDosNumeros
push %rbp        # Guardem el registre que utilitzarem com a apuntador de la pila.
mov %rsp, %rbp
sub $12, %rsp

# T0 = 0
movl $0, %edi
movl %edi, -4(%rbp)

# result_0_2 = T0
movl -4(%rbp), %edi
movl %edi, -8(%rbp)

# T1 = a_0_1 add b_0_1
movl 16(%rbp), %edi
movl 32(%rbp), %eax
addl %eax, %edi
movl %edi, -12(%rbp)

# result_0_2 = T1
movl -12(%rbp), %edi
movl %edi, -8(%rbp)

# rtn result_0_2
# Moving function result into %eax or %ax
movl -8(%rbp), %eax
# Delete all reserved space
addq $12, %rsp
leave
ret

# PROC_factorial:skip
PROC_factorial:

# pmb PROC_factorial
push %rbp        # Guardem el registre que utilitzarem com a apuntador de la pila.
mov %rsp, %rbp
sub $34, %rsp

# T2 = 0
movl $0, %edi
movl %edi, -4(%rbp)

# result_1_2 = T2
movl -4(%rbp), %edi
movl %edi, -8(%rbp)

# T3 = 0
movl $0, %edi
movl %edi, -12(%rbp)

# T4 = num_1_1 EQ T3
movl -12(%rbp), %edi
movl 16(%rbp), %esi
xor %rax, %rax # clean return value register
call CMP_EQ_NUM
movw %ax,-14(%rbp) # get return value

# if T4=true goto LABEL_0
cmpw $1,-14(%rbp)
je LABEL_0

# go_to LABEL_1
jmp LABEL_1

# LABEL_0:skip
LABEL_0:

# T5 = 1
movl $1, %edi
movl %edi, -18(%rbp)

# result_1_2 = T5
movl -18(%rbp), %edi
movl %edi, -8(%rbp)

# go_to LABEL_2
jmp LABEL_2

# LABEL_1:skip
LABEL_1:

# T6 = 1
movl $1, %edi
movl %edi, -22(%rbp)

# T7 = num_1_1 sub T6
movl 16(%rbp), %edi
movl -22(%rbp), %eax
subl %eax, %edi
movl %edi, -26(%rbp)

# param PROC_factorial(T7)
movslq -26(%rbp), %rdx
push %rdx

# call PROC_factorial
xor %rax, %rax   # clean return register
call PROC_factorial
# pop all params
pop %rdx

# T8 = return PROC_factorial
movl %eax, -30(%rbp)

# T9 = num_1_1 prod T8
movl 16(%rbp), %edi
movl -30(%rbp), %eax
imull %eax, %edi
movl %edi, -34(%rbp)

# result_1_2 = T9
movl -34(%rbp), %edi
movl %edi, -8(%rbp)

# LABEL_2:skip
LABEL_2:

# rtn result_1_2
# Moving function result into %eax or %ax
movl -8(%rbp), %eax
# Delete all reserved space
addq $34, %rsp
leave
ret

# PROC_recursiveAdding:skip
PROC_recursiveAdding:

# pmb PROC_recursiveAdding
push %rbp        # Guardem el registre que utilitzarem com a apuntador de la pila.
mov %rsp, %rbp
sub $44, %rsp

# T10 = 0
movl $0, %edi
movl %edi, -4(%rbp)

# result_2_2 = T10
movl -4(%rbp), %edi
movl %edi, -8(%rbp)

# T11 = 1
movl $1, %edi
movl %edi, -12(%rbp)

# T12 = num_1_1 LT T11
movl -12(%rbp), %edi
movl 16(%rbp), %esi
xor %rax, %rax # clean return value register
call CMP_LT
movw %ax,-14(%rbp) # get return value

# if T12=true goto LABEL_3
cmpw $1,-14(%rbp)
je LABEL_3

# go_to LABEL_4
jmp LABEL_4

# LABEL_3:skip
LABEL_3:

# T13 = "Invalid number: "
# output T13
mov $T13, %rdi
xor %rax, %rax
call printf

# output num_1_1
mov $format_int, %rdi
xor %rsi, %rsi
movl 16(%rbp), %esi
xor %rax, %rax
call printf

# T14 = "\n"
# output T14
mov $T14, %rdi
xor %rax, %rax
call printf

# T15 = 0
movl $0, %edi
movl %edi, -18(%rbp)

# result_2_2 = T15
movl -18(%rbp), %edi
movl %edi, -8(%rbp)

# go_to LABEL_5
jmp LABEL_5

# LABEL_4:skip
LABEL_4:

# T16 = 1
movl $1, %edi
movl %edi, -22(%rbp)

# T17 = num_1_1 EQ T16
movl -22(%rbp), %edi
movl 16(%rbp), %esi
xor %rax, %rax # clean return value register
call CMP_EQ_NUM
movw %ax,-24(%rbp) # get return value

# if T17=true goto LABEL_6
cmpw $1,-24(%rbp)
je LABEL_6

# go_to LABEL_7
jmp LABEL_7

# LABEL_6:skip
LABEL_6:

# T18 = 1
movl $1, %edi
movl %edi, -28(%rbp)

# result_2_2 = T18
movl -28(%rbp), %edi
movl %edi, -8(%rbp)

# LABEL_5:skip
LABEL_5:

# go_to LABEL_8
jmp LABEL_8

# LABEL_7:skip
LABEL_7:

# T19 = 1
movl $1, %edi
movl %edi, -32(%rbp)

# T20 = num_1_1 sub T19
movl 16(%rbp), %edi
movl -32(%rbp), %eax
subl %eax, %edi
movl %edi, -36(%rbp)

# param PROC_recursiveAdding(T20)
movslq -36(%rbp), %rdx
push %rdx

# call PROC_recursiveAdding
xor %rax, %rax   # clean return register
call PROC_recursiveAdding
# pop all params
pop %rdx

# T21 = return PROC_recursiveAdding
movl %eax, -40(%rbp)

# T22 = num_1_1 add T21
movl 16(%rbp), %edi
movl -40(%rbp), %eax
addl %eax, %edi
movl %edi, -44(%rbp)

# result_2_2 = T22
movl -44(%rbp), %edi
movl %edi, -8(%rbp)

# LABEL_8:skip
LABEL_8:

# rtn result_2_2
# Moving function result into %eax or %ax
movl -8(%rbp), %eax
# Delete all reserved space
addq $44, %rsp
leave
ret

# main:skip
main:

# pmb PROC_main
push %rbp        # Guardem el registre que utilitzarem com a apuntador de la pila.
mov %rsp, %rbp
sub $70, %rsp

# T23 = 5
movl $5, %edi
movl %edi, -4(%rbp)

# numA_3_2 = T23
movl -4(%rbp), %edi
movl %edi, -8(%rbp)

# T24 = 10
movl $10, %edi
movl %edi, -12(%rbp)

# numB_3_2 = T24
movl -12(%rbp), %edi
movl %edi, -16(%rbp)

# T25 = "FACTORIAL "
# output T25
mov $T25, %rdi
xor %rax, %rax
call printf

# output numA_3_2
mov $format_int, %rdi
xor %rsi, %rsi
movl -8(%rbp), %esi
xor %rax, %rax
call printf

# T26 = "! :"
# output T26
mov $T26, %rdi
xor %rax, %rax
call printf

# param PROC_factorial(numA_3_2)
movslq -8(%rbp), %rdx
push %rdx

# call PROC_factorial
xor %rax, %rax   # clean return register
call PROC_factorial
# pop all params
pop %rdx

# T27 = return PROC_factorial
movl %eax, -20(%rbp)

# output T27
mov $format_int, %rdi
xor %rsi, %rsi
movl -20(%rbp), %esi
xor %rax, %rax
call printf

# T28 = "\n"
# output T28
mov $T28, %rdi
xor %rax, %rax
call printf

# T29 = "RECURSIVE ADDING "
# output T29
mov $T29, %rdi
xor %rax, %rax
call printf

# output numB_3_2
mov $format_int, %rdi
xor %rsi, %rsi
movl -16(%rbp), %esi
xor %rax, %rax
call printf

# T30 = " :"
# output T30
mov $T30, %rdi
xor %rax, %rax
call printf

# param PROC_recursiveAdding(numB_3_2)
movslq -16(%rbp), %rdx
push %rdx

# call PROC_recursiveAdding
xor %rax, %rax   # clean return register
call PROC_recursiveAdding
# pop all params
pop %rdx

# T31 = return PROC_recursiveAdding
movl %eax, -24(%rbp)

# output T31
mov $format_int, %rdi
xor %rsi, %rsi
movl -24(%rbp), %esi
xor %rax, %rax
call printf

# T32 = "\n"
# output T32
mov $T32, %rdi
xor %rax, %rax
call printf

# T33 = 100
movl $100, %edi
movl %edi, -28(%rbp)

# numE_3_2 = T33
movl -28(%rbp), %edi
movl %edi, -32(%rbp)

# numD_3_2 = T33
movw -28(%rbp), %di
movw %di, -32(%rbp)

# numC_3_2 = T33
movw -28(%rbp), %di
movw %di, -32(%rbp)

# T34 = 0
movl $0, %edi
movl %edi, -36(%rbp)

# numF_3_2 = T34
movl -36(%rbp), %edi
movl %edi, -40(%rbp)

# param PROC_sumaDosNumeros(numC_3_2)
movslq -32(%rbp), %rdx
push %rdx

# param PROC_sumaDosNumeros(numC_3_2)
movslq -32(%rbp), %rdx
push %rdx

# call PROC_sumaDosNumeros
xor %rax, %rax   # clean return register
call PROC_sumaDosNumeros
# pop all params
pop %rdx
pop %rdx

# T35 = return PROC_sumaDosNumeros
movl %eax, -44(%rbp)

# output T35
mov $format_int, %rdi
xor %rsi, %rsi
movl -44(%rbp), %esi
xor %rax, %rax
call printf

# param PROC_sumaDosNumeros(numC_3_2)
movslq -32(%rbp), %rdx
push %rdx

# param PROC_sumaDosNumeros(numC_3_2)
movslq -32(%rbp), %rdx
push %rdx

# call PROC_sumaDosNumeros
xor %rax, %rax   # clean return register
call PROC_sumaDosNumeros
# pop all params
pop %rdx
pop %rdx

# T36 = return PROC_sumaDosNumeros
movl %eax, -48(%rbp)

# numF_3_2 = T36
movl -48(%rbp), %edi
movl %edi, -40(%rbp)

# T37 = 0
movl $0, %edi
movl %edi, -52(%rbp)

# i_3_2 = T37
movl -52(%rbp), %edi
movl %edi, -56(%rbp)

# LABEL_9:skip
LABEL_9:

# T38 = 3
movl $3, %edi
movl %edi, -60(%rbp)

# T39 = i_3_2 LT T38
movl -60(%rbp), %edi
movl -56(%rbp), %esi
xor %rax, %rax # clean return value register
call CMP_LT
movw %ax,-62(%rbp) # get return value

# if T39=true goto LABEL_10
cmpw $1,-62(%rbp)
je LABEL_10

# go_to LABEL_11
jmp LABEL_11

# LABEL_12:skip
LABEL_12:

# T40 = 1
movl $1, %edi
movl %edi, -66(%rbp)

# T41 = i_3_2 add T40
movl -56(%rbp), %edi
movl -66(%rbp), %eax
addl %eax, %edi
movl %edi, -70(%rbp)

# i_3_2 = T41
movl -70(%rbp), %edi
movl %edi, -56(%rbp)

# go_to LABEL_9
jmp LABEL_9

# LABEL_10:skip
LABEL_10:

# T42 = "\n"
# output T42
mov $T42, %rdi
xor %rax, %rax
call printf

# T43 = "He entrado: "
# output T43
mov $T43, %rdi
xor %rax, %rax
call printf

# output i_3_2
mov $format_int, %rdi
xor %rsi, %rsi
movl -56(%rbp), %esi
xor %rax, %rax
call printf

# go_to LABEL_12
jmp LABEL_12

# LABEL_11:skip
LABEL_11:

# T44 = "\nHE SALIDO\n"
# output T44
mov $T44, %rdi
xor %rax, %rax
call printf

# rtn
# Delete all reserved space
addq $70, %rsp
leave
ret

# exit

# auxiliar functions
# boolean value assignation EQ
CMP_EQ :
	cmp %di, %si
	jne CMP_EQ_NE
	mov $1, %rax
	ret
CMP_EQ_NE :
	mov $0, %rax
	ret

# boolean value assignation NE
CMP_NE :
	cmp %di, %si
	je CMP_NE_E
	mov $1, %rax
	ret
CMP_NE_E :
	mov $0, %rax
	ret

# boolean value assignation GT
CMP_GT :
	cmp %edi, %esi
	jle CMP_GT_LE
	mov $1, %ax
	ret
CMP_GT_LE :
	mov $0, %ax
	ret

# boolean value assignation GE
CMP_GE :
	cmp %edi, %esi
	jl CMP_GE_L
	mov $1, %ax
	ret
CMP_GE_L :
	mov $0, %ax
	ret

# boolean value assignation LE
CMP_LE :
	cmp %edi, %esi
	jg CMP_LE_G
	mov $1, %ax
	ret
CMP_LE_G :
	mov $0, %ax
	ret

# boolean value assignation LT
CMP_LT :
	cmp %edi, %esi
	jge CMP_LT_GE
	mov $1, %ax
	ret
CMP_LT_GE :
	mov $0, %ax
	ret

# boolean value assignation NE num
CMP_NE_NUM :
	cmp %edi, %esi
	je CMP_NE_E_NUM
	mov $1, %ax
	ret
CMP_NE_E_NUM :
	mov $0, %ax
	ret

# boolean value assignation EQ num
CMP_EQ_NUM :
	cmp %edi, %esi
	jne CMP_EQ_NE_NUM
	mov $1, %ax
	ret
CMP_EQ_NE_NUM :
	mov $0, %ax
	ret

print_bool :
cmpw $0,%di
je print_false
mov $true_label, %rdi
jmp print_bool_val
print_false : mov $false_label, %rdi
print_bool_val : xor %rax, %rax
call printf
ret
