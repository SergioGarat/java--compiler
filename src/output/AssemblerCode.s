.global main
.extern printf, scanf
.data
T25: .asciz "FACTORIAL "
T26: .asciz "! :"
T29: .asciz "Inserta un número: "
T28: .asciz "\n"
T32: .asciz "\n"
T31: .asciz "Número insertado: "
T33: .asciz "Inserta un número: "
T36: .asciz "RECURSIVE ADDING "
T35: .asciz "\n"
T37: .asciz " :"
T39: .asciz "\n"
T52: .asciz "He entrado: "
T51: .asciz "\n"
T53: .asciz "\nHE SALIDO\n"
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
sub $48, %rsp

# T0 = 0
movl $0, %edi
movl %edi, -16(%rbp)

# result_0_2 = T0
movl -16(%rbp), %edi
movl %edi, -32(%rbp)

# T1 = a_0_1 add b_0_1
movl 16(%rbp), %edi
movl 32(%rbp), %eax
addl %eax, %edi
movl %edi, -48(%rbp)

# result_0_2 = T1
movl -48(%rbp), %edi
movl %edi, -32(%rbp)

# rtn result_0_2
# Moving function result into %eax or %ax
movl -32(%rbp), %eax
# Delete all reserved space
addq $48, %rsp
leave
ret

# PROC_factorial:skip
PROC_factorial:

# pmb PROC_factorial
push %rbp        # Guardem el registre que utilitzarem com a apuntador de la pila.
mov %rsp, %rbp
sub $144, %rsp

# T2 = 0
movl $0, %edi
movl %edi, -16(%rbp)

# result_1_2 = T2
movl -16(%rbp), %edi
movl %edi, -32(%rbp)

# T3 = 0
movl $0, %edi
movl %edi, -48(%rbp)

# T4 = num_1_1 EQ T3
movl -48(%rbp), %edi
movl 16(%rbp), %esi
xor %rax, %rax # clean return value register
call CMP_EQ_NUM
movw %ax,-64(%rbp) # get return value

# if T4=true goto LABEL_0
cmpw $1,-64(%rbp)
je LABEL_0

# go_to LABEL_1
jmp LABEL_1

# LABEL_0:skip
LABEL_0:

# T5 = 1
movl $1, %edi
movl %edi, -80(%rbp)

# result_1_2 = T5
movl -80(%rbp), %edi
movl %edi, -32(%rbp)

# go_to LABEL_2
jmp LABEL_2

# LABEL_1:skip
LABEL_1:

# T6 = 1
movl $1, %edi
movl %edi, -96(%rbp)

# T7 = num_1_1 sub T6
movl 16(%rbp), %edi
movl -96(%rbp), %eax
subl %eax, %edi
movl %edi, -112(%rbp)

# param PROC_factorial(T7)
movslq -112(%rbp), %rdx
push %rdx

# call PROC_factorial
xor %rax, %rax   # clean return register
call PROC_factorial
# pop all params
pop %rdx

# T8 = return PROC_factorial
movl %eax, -128(%rbp)

# T9 = num_1_1 prod T8
movl 16(%rbp), %edi
movl -128(%rbp), %eax
imull %eax, %edi
movl %edi, -144(%rbp)

# result_1_2 = T9
movl -144(%rbp), %edi
movl %edi, -32(%rbp)

# LABEL_2:skip
LABEL_2:

# rtn result_1_2
# Moving function result into %eax or %ax
movl -32(%rbp), %eax
# Delete all reserved space
addq $144, %rsp
leave
ret

# PROC_recursiveAdding:skip
PROC_recursiveAdding:

# pmb PROC_recursiveAdding
push %rbp        # Guardem el registre que utilitzarem com a apuntador de la pila.
mov %rsp, %rbp
sub $192, %rsp

# T10 = 0
movl $0, %edi
movl %edi, -16(%rbp)

# result_2_2 = T10
movl -16(%rbp), %edi
movl %edi, -32(%rbp)

# T11 = 1
movl $1, %edi
movl %edi, -48(%rbp)

# T12 = num_1_1 LT T11
movl -48(%rbp), %edi
movl 16(%rbp), %esi
xor %rax, %rax # clean return value register
call CMP_LT
movw %ax,-64(%rbp) # get return value

# if T12=true goto LABEL_3
cmpw $1,-64(%rbp)
je LABEL_3

# go_to LABEL_4
jmp LABEL_4

# LABEL_3:skip
LABEL_3:

# T13 = "Invalid number: "
# print T13
mov $T13, %rdi
xor %rax, %rax
call printf

# print num_1_1
mov $format_int, %rdi
xor %rsi, %rsi
movl 16(%rbp), %esi
xor %rax, %rax
call printf

# T14 = "\n"
# print T14
mov $T14, %rdi
xor %rax, %rax
call printf

# T15 = 0
movl $0, %edi
movl %edi, -80(%rbp)

# result_2_2 = T15
movl -80(%rbp), %edi
movl %edi, -32(%rbp)

# go_to LABEL_5
jmp LABEL_5

# LABEL_4:skip
LABEL_4:

# T16 = 1
movl $1, %edi
movl %edi, -96(%rbp)

# T17 = num_1_1 EQ T16
movl -96(%rbp), %edi
movl 16(%rbp), %esi
xor %rax, %rax # clean return value register
call CMP_EQ_NUM
movw %ax,-112(%rbp) # get return value

# if T17=true goto LABEL_6
cmpw $1,-112(%rbp)
je LABEL_6

# go_to LABEL_7
jmp LABEL_7

# LABEL_6:skip
LABEL_6:

# T18 = 1
movl $1, %edi
movl %edi, -128(%rbp)

# result_2_2 = T18
movl -128(%rbp), %edi
movl %edi, -32(%rbp)

# LABEL_5:skip
LABEL_5:

# go_to LABEL_8
jmp LABEL_8

# LABEL_7:skip
LABEL_7:

# T19 = 1
movl $1, %edi
movl %edi, -144(%rbp)

# T20 = num_1_1 sub T19
movl 16(%rbp), %edi
movl -144(%rbp), %eax
subl %eax, %edi
movl %edi, -160(%rbp)

# param PROC_recursiveAdding(T20)
movslq -160(%rbp), %rdx
push %rdx

# call PROC_recursiveAdding
xor %rax, %rax   # clean return register
call PROC_recursiveAdding
# pop all params
pop %rdx

# T21 = return PROC_recursiveAdding
movl %eax, -176(%rbp)

# T22 = num_1_1 add T21
movl 16(%rbp), %edi
movl -176(%rbp), %eax
addl %eax, %edi
movl %edi, -192(%rbp)

# result_2_2 = T22
movl -192(%rbp), %edi
movl %edi, -32(%rbp)

# LABEL_8:skip
LABEL_8:

# rtn result_2_2
# Moving function result into %eax or %ax
movl -32(%rbp), %eax
# Delete all reserved space
addq $192, %rsp
leave
ret

# main:skip
main:

# pmb PROC_main
push %rbp        # Guardem el registre que utilitzarem com a apuntador de la pila.
mov %rsp, %rbp
sub $400, %rsp

# T23 = 5
movl $5, %edi
movl %edi, -16(%rbp)

# numA_3_2 = T23
movl -16(%rbp), %edi
movl %edi, -32(%rbp)

# T24 = 10
movl $10, %edi
movl %edi, -48(%rbp)

# numB_3_2 = T24
movl -48(%rbp), %edi
movl %edi, -64(%rbp)

# T25 = "FACTORIAL "
# print T25
mov $T25, %rdi
xor %rax, %rax
call printf

# print numA_3_2
mov $format_int, %rdi
xor %rsi, %rsi
movl -32(%rbp), %esi
xor %rax, %rax
call printf

# T26 = "! :"
# print T26
mov $T26, %rdi
xor %rax, %rax
call printf

# param PROC_factorial(numA_3_2)
movslq -32(%rbp), %rdx
push %rdx

# call PROC_factorial
xor %rax, %rax   # clean return register
call PROC_factorial
# pop all params
pop %rdx

# T27 = return PROC_factorial
movl %eax, -80(%rbp)

# print T27
mov $format_int, %rdi
xor %rsi, %rsi
movl -80(%rbp), %esi
xor %rax, %rax
call printf

# T28 = "\n"
# print T28
mov $T28, %rdi
xor %rax, %rax
call printf

# T29 = "Inserta un número: "
# print T29
mov $T29, %rdi
xor %rax, %rax
call printf

# T30 = read
xor %rax, %rax
mov $format_int, %rdi
leaq -96(%rbp), %rsi
call scanf

# numZ_3_2 = T30
movl -96(%rbp), %edi
movl %edi, -112(%rbp)

# T31 = "Número insertado: "
# print T31
mov $T31, %rdi
xor %rax, %rax
call printf

# print numZ_3_2
mov $format_int, %rdi
xor %rsi, %rsi
movl -112(%rbp), %esi
xor %rax, %rax
call printf

# T32 = "\n"
# print T32
mov $T32, %rdi
xor %rax, %rax
call printf

# T33 = "Inserta un número: "
# print T33
mov $T33, %rdi
xor %rax, %rax
call printf

# T34 = read
xor %rax, %rax
mov $format_int, %rdi
leaq -128(%rbp), %rsi
call scanf

# print T34
mov $format_int, %rdi
xor %rsi, %rsi
movl -128(%rbp), %esi
xor %rax, %rax
call printf

# T35 = "\n"
# print T35
mov $T35, %rdi
xor %rax, %rax
call printf

# T36 = "RECURSIVE ADDING "
# print T36
mov $T36, %rdi
xor %rax, %rax
call printf

# print numB_3_2
mov $format_int, %rdi
xor %rsi, %rsi
movl -64(%rbp), %esi
xor %rax, %rax
call printf

# T37 = " :"
# print T37
mov $T37, %rdi
xor %rax, %rax
call printf

# param PROC_recursiveAdding(numB_3_2)
movslq -64(%rbp), %rdx
push %rdx

# call PROC_recursiveAdding
xor %rax, %rax   # clean return register
call PROC_recursiveAdding
# pop all params
pop %rdx

# T38 = return PROC_recursiveAdding
movl %eax, -144(%rbp)

# print T38
mov $format_int, %rdi
xor %rsi, %rsi
movl -144(%rbp), %esi
xor %rax, %rax
call printf

# T39 = "\n"
# print T39
mov $T39, %rdi
xor %rax, %rax
call printf

# T40 = 100
movl $100, %edi
movl %edi, -160(%rbp)

# numC_3_2 = T40
movl -160(%rbp), %edi
movl %edi, -176(%rbp)

# T41 = 1200
movl $1200, %edi
movl %edi, -192(%rbp)

# numD_3_2 = T41
movl -192(%rbp), %edi
movl %edi, -208(%rbp)

# T42 = 35000
movl $35000, %edi
movl %edi, -224(%rbp)

# numE_3_2 = T42
movl -224(%rbp), %edi
movl %edi, -240(%rbp)

# T43 = 0
movl $0, %edi
movl %edi, -256(%rbp)

# numF_3_2 = T43
movl -256(%rbp), %edi
movl %edi, -272(%rbp)

# param PROC_sumaDosNumeros(numC_3_2)
movslq -176(%rbp), %rdx
push %rdx

# param PROC_sumaDosNumeros(numD_3_2)
movslq -208(%rbp), %rdx
push %rdx

# call PROC_sumaDosNumeros
xor %rax, %rax   # clean return register
call PROC_sumaDosNumeros
# pop all params
pop %rdx
pop %rdx

# T44 = return PROC_sumaDosNumeros
movl %eax, -288(%rbp)

# print T44
mov $format_int, %rdi
xor %rsi, %rsi
movl -288(%rbp), %esi
xor %rax, %rax
call printf

# param PROC_sumaDosNumeros(numC_3_2)
movslq -176(%rbp), %rdx
push %rdx

# param PROC_sumaDosNumeros(numE_3_2)
movslq -240(%rbp), %rdx
push %rdx

# call PROC_sumaDosNumeros
xor %rax, %rax   # clean return register
call PROC_sumaDosNumeros
# pop all params
pop %rdx
pop %rdx

# T45 = return PROC_sumaDosNumeros
movl %eax, -304(%rbp)

# numF_3_2 = T45
movl -304(%rbp), %edi
movl %edi, -272(%rbp)

# print numF_3_2
mov $format_int, %rdi
xor %rsi, %rsi
movl -272(%rbp), %esi
xor %rax, %rax
call printf

# T46 = 0
movl $0, %edi
movl %edi, -320(%rbp)

# i_3_2 = T46
movl -320(%rbp), %edi
movl %edi, -336(%rbp)

# LABEL_9:skip
LABEL_9:

# T47 = 3
movl $3, %edi
movl %edi, -352(%rbp)

# T48 = i_3_2 LT T47
movl -352(%rbp), %edi
movl -336(%rbp), %esi
xor %rax, %rax # clean return value register
call CMP_LT
movw %ax,-368(%rbp) # get return value

# if T48=true goto LABEL_10
cmpw $1,-368(%rbp)
je LABEL_10

# go_to LABEL_11
jmp LABEL_11

# LABEL_12:skip
LABEL_12:

# T49 = 1
movl $1, %edi
movl %edi, -384(%rbp)

# T50 = i_3_2 add T49
movl -336(%rbp), %edi
movl -384(%rbp), %eax
addl %eax, %edi
movl %edi, -400(%rbp)

# i_3_2 = T50
movl -400(%rbp), %edi
movl %edi, -336(%rbp)

# go_to LABEL_9
jmp LABEL_9

# LABEL_10:skip
LABEL_10:

# T51 = "\n"
# print T51
mov $T51, %rdi
xor %rax, %rax
call printf

# T52 = "He entrado: "
# print T52
mov $T52, %rdi
xor %rax, %rax
call printf

# print i_3_2
mov $format_int, %rdi
xor %rsi, %rsi
movl -336(%rbp), %esi
xor %rax, %rax
call printf

# go_to LABEL_12
jmp LABEL_12

# LABEL_11:skip
LABEL_11:

# T53 = "\nHE SALIDO\n"
# print T53
mov $T53, %rdi
xor %rax, %rax
call printf

# rtn
# Delete all reserved space
addq $400, %rsp
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
