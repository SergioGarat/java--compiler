.global main
.extern printf, scanf
.data
T27: .asciz "FACTORIAL "
T28: .asciz "! :"
T30: .asciz "\n"
T31: .asciz "Inserta un número: "
T34: .asciz "Inserta un número para calcular la suma recursiva: "
T33: .asciz "\n"
T36: .asciz "Número insertado: "
T38: .asciz "RECURSIVE ADDING "
T37: .asciz "\n"
T39: .asciz " :"
T41: .asciz "\n"
T47: .asciz "\n"
T48: .asciz "He entrado: "
T52: .asciz "\nHE SALIDO\n"
T12: .asciz "\n"
T11: .asciz "NUM: "
T16: .asciz "\n"
T15: .asciz "Invalid number: "
format_int: .asciz "%d"
true_label : .asciz "true"
false_label : .asciz "false"
.text
# PROC_sumaDosNumeros: skip
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

# T1 = a_0_1 + b_0_1
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

# PROC_factorial: skip
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

# T4 = num_1_1 == T3
movl 16(%rbp), %edi
movl -48(%rbp), %esi
xor %rax, %rax # clean return value register
call CMP_EQ_NUM
movw %ax,-64(%rbp) # get return value

# if T4 == true goto ETIQ_0
cmpw $1,-64(%rbp)
je ETIQ_0

# goto ETIQ_1
jmp ETIQ_1

# ETIQ_0: skip
ETIQ_0:

# T5 = 1
movl $1, %edi
movl %edi, -80(%rbp)

# result_1_2 = T5
movl -80(%rbp), %edi
movl %edi, -32(%rbp)

# goto ETIQ_2
jmp ETIQ_2

# ETIQ_1: skip
ETIQ_1:

# T6 = 1
movl $1, %edi
movl %edi, -96(%rbp)

# T7 = num_1_1 - T6
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

# T9 = num_1_1 * T8
movl 16(%rbp), %edi
movl -128(%rbp), %eax
imull %eax, %edi
movl %edi, -144(%rbp)

# result_1_2 = T9
movl -144(%rbp), %edi
movl %edi, -32(%rbp)

# ETIQ_2: skip
ETIQ_2:

# rtn result_1_2
# Moving function result into %eax or %ax
movl -32(%rbp), %eax
# Delete all reserved space
addq $144, %rsp
leave
ret

# PROC_recursiveAdding: skip
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

# T11 = "NUM: "
# print T11
mov $T11, %rdi
xor %rax, %rax
call printf

# print num_1_1
mov $format_int, %rdi
xor %rsi, %rsi
movl 16(%rbp), %esi
xor %rax, %rax
call printf

# T12 = "\n"
# print T12
mov $T12, %rdi
xor %rax, %rax
call printf

# T13 = 0
movl $0, %edi
movl %edi, -48(%rbp)

# T14 = num_1_1 < T13
movl 16(%rbp), %edi
movl -48(%rbp), %esi
xor %rax, %rax # clean return value register
call CMP_LT
movw %ax,-64(%rbp) # get return value

# if T14 == true goto ETIQ_3
cmpw $1,-64(%rbp)
je ETIQ_3

# goto ETIQ_4
jmp ETIQ_4

# ETIQ_3: skip
ETIQ_3:

# T15 = "Invalid number: "
# print T15
mov $T15, %rdi
xor %rax, %rax
call printf

# print num_1_1
mov $format_int, %rdi
xor %rsi, %rsi
movl 16(%rbp), %esi
xor %rax, %rax
call printf

# T16 = "\n"
# print T16
mov $T16, %rdi
xor %rax, %rax
call printf

# T17 = 0
movl $0, %edi
movl %edi, -80(%rbp)

# result_2_2 = T17
movl -80(%rbp), %edi
movl %edi, -32(%rbp)

# goto ETIQ_5
jmp ETIQ_5

# ETIQ_4: skip
ETIQ_4:

# T18 = 1
movl $1, %edi
movl %edi, -96(%rbp)

# T19 = num_1_1 == T18
movl 16(%rbp), %edi
movl -96(%rbp), %esi
xor %rax, %rax # clean return value register
call CMP_EQ_NUM
movw %ax,-112(%rbp) # get return value

# if T19 == true goto ETIQ_6
cmpw $1,-112(%rbp)
je ETIQ_6

# goto ETIQ_7
jmp ETIQ_7

# ETIQ_6: skip
ETIQ_6:

# T20 = 1
movl $1, %edi
movl %edi, -128(%rbp)

# result_2_2 = T20
movl -128(%rbp), %edi
movl %edi, -32(%rbp)

# ETIQ_5: skip
ETIQ_5:

# goto ETIQ_8
jmp ETIQ_8

# ETIQ_7: skip
ETIQ_7:

# T21 = 1
movl $1, %edi
movl %edi, -144(%rbp)

# T22 = num_1_1 - T21
movl 16(%rbp), %edi
movl -144(%rbp), %eax
subl %eax, %edi
movl %edi, -160(%rbp)

# param PROC_recursiveAdding(T22)
movslq -160(%rbp), %rdx
push %rdx

# call PROC_recursiveAdding
xor %rax, %rax   # clean return register
call PROC_recursiveAdding
# pop all params
pop %rdx

# T23 = return PROC_recursiveAdding
movl %eax, -176(%rbp)

# T24 = num_1_1 + T23
movl 16(%rbp), %edi
movl -176(%rbp), %eax
addl %eax, %edi
movl %edi, -192(%rbp)

# result_2_2 = T24
movl -192(%rbp), %edi
movl %edi, -32(%rbp)

# ETIQ_8: skip
ETIQ_8:

# rtn result_2_2
# Moving function result into %eax or %ax
movl -32(%rbp), %eax
# Delete all reserved space
addq $192, %rsp
leave
ret

# main: skip
main:

# pmb PROC_main
push %rbp        # Guardem el registre que utilitzarem com a apuntador de la pila.
mov %rsp, %rbp
sub $384, %rsp

# T25 = 5
movl $5, %edi
movl %edi, -16(%rbp)

# numA_3_2 = T25
movl -16(%rbp), %edi
movl %edi, -32(%rbp)

# T26 = 10
movl $10, %edi
movl %edi, -48(%rbp)

# numB_3_2 = T26
movl -48(%rbp), %edi
movl %edi, -64(%rbp)

# T27 = "FACTORIAL "
# print T27
mov $T27, %rdi
xor %rax, %rax
call printf

# print numA_3_2
mov $format_int, %rdi
xor %rsi, %rsi
movl -32(%rbp), %esi
xor %rax, %rax
call printf

# T28 = "! :"
# print T28
mov $T28, %rdi
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

# T29 = return PROC_factorial
movl %eax, -80(%rbp)

# print T29
mov $format_int, %rdi
xor %rsi, %rsi
movl -80(%rbp), %esi
xor %rax, %rax
call printf

# T30 = "\n"
# print T30
mov $T30, %rdi
xor %rax, %rax
call printf

# T31 = "Inserta un número: "
# print T31
mov $T31, %rdi
xor %rax, %rax
call printf

# T32 = read
xor %rax, %rax
mov $format_int, %rdi
leaq -96(%rbp), %rsi
call scanf

# print T32
mov $format_int, %rdi
xor %rsi, %rsi
movl -96(%rbp), %esi
xor %rax, %rax
call printf

# T33 = "\n"
# print T33
mov $T33, %rdi
xor %rax, %rax
call printf

# T34 = "Inserta un número para calcular la suma recursiva: "
# print T34
mov $T34, %rdi
xor %rax, %rax
call printf

# T35 = read
xor %rax, %rax
mov $format_int, %rdi
leaq -112(%rbp), %rsi
call scanf

# numZ_3_2 = T35
movl -112(%rbp), %edi
movl %edi, -128(%rbp)

# T36 = "Número insertado: "
# print T36
mov $T36, %rdi
xor %rax, %rax
call printf

# print numZ_3_2
mov $format_int, %rdi
xor %rsi, %rsi
movl -128(%rbp), %esi
xor %rax, %rax
call printf

# T37 = "\n"
# print T37
mov $T37, %rdi
xor %rax, %rax
call printf

# T38 = "RECURSIVE ADDING "
# print T38
mov $T38, %rdi
xor %rax, %rax
call printf

# print numZ_3_2
mov $format_int, %rdi
xor %rsi, %rsi
movl -128(%rbp), %esi
xor %rax, %rax
call printf

# T39 = " :"
# print T39
mov $T39, %rdi
xor %rax, %rax
call printf

# param PROC_recursiveAdding(numZ_3_2)
movslq -128(%rbp), %rdx
push %rdx

# call PROC_recursiveAdding
xor %rax, %rax   # clean return register
call PROC_recursiveAdding
# pop all params
pop %rdx

# T40 = return PROC_recursiveAdding
movl %eax, -144(%rbp)

# print T40
mov $format_int, %rdi
xor %rsi, %rsi
movl -144(%rbp), %esi
xor %rax, %rax
call printf

# T41 = "\n"
# print T41
mov $T41, %rdi
xor %rax, %rax
call printf

# T42 = 0
movl $0, %edi
movl %edi, -160(%rbp)

# i_3_2 = T42
movl -160(%rbp), %edi
movl %edi, -176(%rbp)

# ETIQ_9: skip
ETIQ_9:

# T43 = 3
movl $3, %edi
movl %edi, -192(%rbp)

# T44 = i_3_2 < T43
movl -176(%rbp), %edi
movl -192(%rbp), %esi
xor %rax, %rax # clean return value register
call CMP_LT
movw %ax,-208(%rbp) # get return value

# if T44 == true goto ETIQ_10
cmpw $1,-208(%rbp)
je ETIQ_10

# goto ETIQ_11
jmp ETIQ_11

# ETIQ_12: skip
ETIQ_12:

# T45 = 1
movl $1, %edi
movl %edi, -224(%rbp)

# T46 = i_3_2 + T45
movl -176(%rbp), %edi
movl -224(%rbp), %eax
addl %eax, %edi
movl %edi, -240(%rbp)

# i_3_2 = T46
movl -240(%rbp), %edi
movl %edi, -176(%rbp)

# goto ETIQ_9
jmp ETIQ_9

# ETIQ_10: skip
ETIQ_10:

# T47 = "\n"
# print T47
mov $T47, %rdi
xor %rax, %rax
call printf

# T48 = "He entrado: "
# print T48
mov $T48, %rdi
xor %rax, %rax
call printf

# print i_3_2
mov $format_int, %rdi
xor %rsi, %rsi
movl -176(%rbp), %esi
xor %rax, %rax
call printf

# goto ETIQ_12
jmp ETIQ_12

# ETIQ_11: skip
ETIQ_11:

# T49 = 1
movl $1, %edi
movl %edi, -304(%rbp)

# T50 = true
movw $1, %di
movw %di, -320(%rbp)

# T51 = 2
movl $2, %edi
movl %edi, -336(%rbp)

# id3_3_2 = T49
movl -304(%rbp), %edi
movl %edi, -288(%rbp)

# id2_3_2 = T50
movw -320(%rbp), %di
movw %di, -272(%rbp)

# id3_3_2 = T51
movl -336(%rbp), %edi
movl %edi, -288(%rbp)

# T52 = "\nHE SALIDO\n"
# print T52
mov $T52, %rdi
xor %rax, %rax
call printf

# rtn
# Delete all reserved space
addq $384, %rsp
leave
ret

# exit

# auxiliar functions
# CMP EQ NUM comparison
CMP_EQ_NUM:
	cmp %edi, %esi
	jne CMP_EQ_NUM_FALSE
	mov $1, %ax
	ret
CMP_EQ_NUM_FALSE:
	mov $0, %ax
	ret

# CMP LT comparison
CMP_LT:
	cmp %esi, %edi
	jge CMP_LT_FALSE
	mov $1, %ax
	ret
CMP_LT_FALSE:
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
