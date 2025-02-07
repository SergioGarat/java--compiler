.global main
.extern printf, scanf
.data
T65: .asciz ": Es un número grande\n"
T64: .asciz ": Es un número mediano\n"
T66: .asciz "HE ACABADO\n"
T25: .asciz ": "
T27: .asciz "Inserta un número (entre 0 y 15) para calcular el factorial: "
T29: .asciz "Número insertado: "
T30: .asciz "\n"
T36: .asciz "! = "
T38: .asciz "\n"
T39: .asciz " es demasiado grande.\n"
T43: .asciz ", "
T47: .asciz "Inserta un número a: "
T46: .asciz "PUM\n"
T49: .asciz "Número insertado: "
T6: .asciz "fizz"
T9: .asciz "buzz"
T50: .asciz "\n"
T51: .asciz "Inserta un número b: "
T10: .asciz "\n"
T54: .asciz "\n"
T53: .asciz "Número insertado: "
T58: .asciz ": Es un número pequeño\n"
format_int: .asciz "%d"
true_label : .asciz "true"
false_label : .asciz "false"
.text
# PROC_fizzbuzz: skip
PROC_fizzbuzz:

# pmb PROC_fizzbuzz
push %rbp        # Guardamos el regustro que utilizaremos como a apuntador de la pila.
mov %rsp, %rbp
sub $176, %rsp

# T0 = 3
movl $3, %edi
movl %edi, -16(%rbp)

# T1 = n_0_1 % T0
movl 16(%rbp), %eax
cdq
movl -16(%rbp), %edi
idivl %edi
movl %edx, -32(%rbp)

# div3_0_2 = T1
movl -32(%rbp), %edi
movl %edi, -48(%rbp)

# T2 = 5
movl $5, %edi
movl %edi, -64(%rbp)

# T3 = n_0_1 % T2
movl 16(%rbp), %eax
cdq
movl -64(%rbp), %edi
idivl %edi
movl %edx, -80(%rbp)

# div5_0_2 = T3
movl -80(%rbp), %edi
movl %edi, -96(%rbp)

# T4 = 0
movl $0, %edi
movl %edi, -112(%rbp)

# T5 = div3_0_2 == T4
movl -48(%rbp), %edi
movl -112(%rbp), %esi
xor %rax, %rax # clean return value register
call CMP_EQ_NUM
movw %ax,-128(%rbp) # get return value

# if T5 == true goto ETIQ_0
cmpw $1,-128(%rbp)
je ETIQ_0

# goto ETIQ_1
jmp ETIQ_1

# ETIQ_0: skip
ETIQ_0:

# T6 = "fizz"
# print T6
mov $T6, %rdi
xor %rax, %rax
call printf

# ETIQ_1: skip
ETIQ_1:

# T7 = 0
movl $0, %edi
movl %edi, -144(%rbp)

# T8 = div5_0_2 == T7
movl -96(%rbp), %edi
movl -144(%rbp), %esi
xor %rax, %rax # clean return value register
call CMP_EQ_NUM
movw %ax,-160(%rbp) # get return value

# if T8 == true goto ETIQ_2
cmpw $1,-160(%rbp)
je ETIQ_2

# goto ETIQ_3
jmp ETIQ_3

# ETIQ_2: skip
ETIQ_2:

# T9 = "buzz"
# print T9
mov $T9, %rdi
xor %rax, %rax
call printf

# ETIQ_3: skip
ETIQ_3:

# T10 = "\n"
# print T10
mov $T10, %rdi
xor %rax, %rax
call printf

# T11 = 0
movl $0, %edi
movl %edi, -176(%rbp)

# rtn T11
# Moving function result into %eax or %ax
movl -176(%rbp), %eax
# Delete all reserved space
addq $176, %rsp
leave
ret

# PROC_factorial: skip
PROC_factorial:

# pmb PROC_factorial
push %rbp        # Guardamos el regustro que utilizaremos como a apuntador de la pila.
mov %rsp, %rbp
sub $144, %rsp

# T12 = 0
movl $0, %edi
movl %edi, -16(%rbp)

# result_1_2 = T12
movl -16(%rbp), %edi
movl %edi, -32(%rbp)

# T13 = 0
movl $0, %edi
movl %edi, -48(%rbp)

# T14 = num_1_1 == T13
movl 16(%rbp), %edi
movl -48(%rbp), %esi
xor %rax, %rax # clean return value register
call CMP_EQ_NUM
movw %ax,-64(%rbp) # get return value

# if T14 == true goto ETIQ_4
cmpw $1,-64(%rbp)
je ETIQ_4

# goto ETIQ_5
jmp ETIQ_5

# ETIQ_4: skip
ETIQ_4:

# T15 = 1
movl $1, %edi
movl %edi, -80(%rbp)

# result_1_2 = T15
movl -80(%rbp), %edi
movl %edi, -32(%rbp)

# goto ETIQ_6
jmp ETIQ_6

# ETIQ_5: skip
ETIQ_5:

# T16 = 1
movl $1, %edi
movl %edi, -96(%rbp)

# T17 = num_1_1 - T16
movl 16(%rbp), %edi
movl -96(%rbp), %eax
subl %eax, %edi
movl %edi, -112(%rbp)

# param PROC_factorial(T17)
movslq -112(%rbp), %rdx
push %rdx

# call PROC_factorial
xor %rax, %rax   # clean return register
call PROC_factorial
# pop all params
pop %rdx

# T18 = return PROC_factorial
movl %eax, -128(%rbp)

# T19 = num_1_1 * T18
movl 16(%rbp), %edi
movl -128(%rbp), %eax
imull %eax, %edi
movl %edi, -144(%rbp)

# result_1_2 = T19
movl -144(%rbp), %edi
movl %edi, -32(%rbp)

# ETIQ_6: skip
ETIQ_6:

# rtn result_1_2
# Moving function result into %eax or %ax
movl -32(%rbp), %eax
# Delete all reserved space
addq $144, %rsp
leave
ret

# main: skip
main:

# pmb PROC_main
push %rbp        # Guardamos el regustro que utilizaremos como a apuntador de la pila.
mov %rsp, %rbp
sub $560, %rsp

# T20 = 1
movl $1, %edi
movl %edi, -16(%rbp)

# i_2_2 = T20
movl -16(%rbp), %edi
movl %edi, -32(%rbp)

# ETIQ_7: skip
ETIQ_7:

# T21 = 20
movl $20, %edi
movl %edi, -48(%rbp)

# T22 = i_2_2 <= T21
movl -32(%rbp), %edi
movl -48(%rbp), %esi
xor %rax, %rax # clean return value register
call CMP_LE
movw %ax,-64(%rbp) # get return value

# if T22 == true goto ETIQ_8
cmpw $1,-64(%rbp)
je ETIQ_8

# goto ETIQ_9
jmp ETIQ_9

# ETIQ_10: skip
ETIQ_10:

# T23 = 1
movl $1, %edi
movl %edi, -80(%rbp)

# T24 = i_2_2 + T23
movl -32(%rbp), %edi
movl -80(%rbp), %eax
addl %eax, %edi
movl %edi, -96(%rbp)

# i_2_2 = T24
movl -96(%rbp), %edi
movl %edi, -32(%rbp)

# goto ETIQ_7
jmp ETIQ_7

# ETIQ_8: skip
ETIQ_8:

# print i_2_2
mov $format_int, %rdi
xor %rsi, %rsi
movl -32(%rbp), %esi
xor %rax, %rax
call printf

# T25 = ": "
# print T25
mov $T25, %rdi
xor %rax, %rax
call printf

# param PROC_fizzbuzz(i_2_2)
movslq -32(%rbp), %rdx
push %rdx

# call PROC_fizzbuzz
xor %rax, %rax   # clean return register
call PROC_fizzbuzz
# pop all params
pop %rdx

# T26 = return PROC_fizzbuzz
movl %eax, -112(%rbp)

# any_2_3 = T26
movl -112(%rbp), %edi
movl %edi, -128(%rbp)

# goto ETIQ_10
jmp ETIQ_10

# ETIQ_9: skip
ETIQ_9:

# T27 = "Inserta un número (entre 0 y 15) para calcular el factorial: "
# print T27
mov $T27, %rdi
xor %rax, %rax
call printf

# T28 = read
xor %rax, %rax
mov $format_int, %rdi
leaq -144(%rbp), %rsi
call scanf

# fact_2_2 = T28
movl -144(%rbp), %edi
movl %edi, -160(%rbp)

# T29 = "Número insertado: "
# print T29
mov $T29, %rdi
xor %rax, %rax
call printf

# print fact_2_2
mov $format_int, %rdi
xor %rsi, %rsi
movl -160(%rbp), %esi
xor %rax, %rax
call printf

# T30 = "\n"
# print T30
mov $T30, %rdi
xor %rax, %rax
call printf

# T31 = 0
movl $0, %edi
movl %edi, -176(%rbp)

# T32 = fact_2_2 >= T31
movl -160(%rbp), %edi
movl -176(%rbp), %esi
xor %rax, %rax # clean return value register
call CMP_GE
movw %ax,-192(%rbp) # get return value

# T33 = 15
movl $15, %edi
movl %edi, -208(%rbp)

# T34 = fact_2_2 <= T33
movl -160(%rbp), %edi
movl -208(%rbp), %esi
xor %rax, %rax # clean return value register
call CMP_LE
movw %ax,-224(%rbp) # get return value

# T35 = T32 && T34
movw -192(%rbp), %di
movw -224(%rbp), %ax
andw %ax, %di
movw %di, -240(%rbp)

# if T35 == true goto ETIQ_11
cmpw $1,-240(%rbp)
je ETIQ_11

# goto ETIQ_12
jmp ETIQ_12

# ETIQ_11: skip
ETIQ_11:

# print fact_2_2
mov $format_int, %rdi
xor %rsi, %rsi
movl -160(%rbp), %esi
xor %rax, %rax
call printf

# T36 = "! = "
# print T36
mov $T36, %rdi
xor %rax, %rax
call printf

# param PROC_factorial(fact_2_2)
movslq -160(%rbp), %rdx
push %rdx

# call PROC_factorial
xor %rax, %rax   # clean return register
call PROC_factorial
# pop all params
pop %rdx

# T37 = return PROC_factorial
movl %eax, -256(%rbp)

# print T37
mov $format_int, %rdi
xor %rsi, %rsi
movl -256(%rbp), %esi
xor %rax, %rax
call printf

# T38 = "\n"
# print T38
mov $T38, %rdi
xor %rax, %rax
call printf

# goto ETIQ_13
jmp ETIQ_13

# ETIQ_12: skip
ETIQ_12:

# print fact_2_2
mov $format_int, %rdi
xor %rsi, %rsi
movl -160(%rbp), %esi
xor %rax, %rax
call printf

# T39 = " es demasiado grande.\n"
# print T39
mov $T39, %rdi
xor %rax, %rax
call printf

# ETIQ_13: skip
ETIQ_13:

# T40 = 10
movl $10, %edi
movl %edi, -272(%rbp)

# contador_2_2 = T40
movl -272(%rbp), %edi
movl %edi, -288(%rbp)

# ETIQ_14: skip
ETIQ_14:

# T41 = 0
movl $0, %edi
movl %edi, -304(%rbp)

# T42 = contador_2_2 > T41
movl -288(%rbp), %edi
movl -304(%rbp), %esi
xor %rax, %rax # clean return value register
call CMP_GT
movw %ax,-320(%rbp) # get return value

# if T42 == true goto ETIQ_15
cmpw $1,-320(%rbp)
je ETIQ_15

# goto ETIQ_16
jmp ETIQ_16

# ETIQ_15: skip
ETIQ_15:

# print contador_2_2
mov $format_int, %rdi
xor %rsi, %rsi
movl -288(%rbp), %esi
xor %rax, %rax
call printf

# T43 = ", "
# print T43
mov $T43, %rdi
xor %rax, %rax
call printf

# T44 = 1
movl $1, %edi
movl %edi, -336(%rbp)

# T45 = contador_2_2 - T44
movl -288(%rbp), %edi
movl -336(%rbp), %eax
subl %eax, %edi
movl %edi, -352(%rbp)

# contador_2_2 = T45
movl -352(%rbp), %edi
movl %edi, -288(%rbp)

# goto ETIQ_14
jmp ETIQ_14

# ETIQ_16: skip
ETIQ_16:

# T46 = "PUM\n"
# print T46
mov $T46, %rdi
xor %rax, %rax
call printf

# T47 = "Inserta un número a: "
# print T47
mov $T47, %rdi
xor %rax, %rax
call printf

# T48 = read
xor %rax, %rax
mov $format_int, %rdi
leaq -368(%rbp), %rsi
call scanf

# a_2_2 = T48
movl -368(%rbp), %edi
movl %edi, -384(%rbp)

# T49 = "Número insertado: "
# print T49
mov $T49, %rdi
xor %rax, %rax
call printf

# print a_2_2
mov $format_int, %rdi
xor %rsi, %rsi
movl -384(%rbp), %esi
xor %rax, %rax
call printf

# T50 = "\n"
# print T50
mov $T50, %rdi
xor %rax, %rax
call printf

# T51 = "Inserta un número b: "
# print T51
mov $T51, %rdi
xor %rax, %rax
call printf

# T52 = read
xor %rax, %rax
mov $format_int, %rdi
leaq -400(%rbp), %rsi
call scanf

# b_2_2 = T52
movl -400(%rbp), %edi
movl %edi, -416(%rbp)

# T53 = "Número insertado: "
# print T53
mov $T53, %rdi
xor %rax, %rax
call printf

# print b_2_2
mov $format_int, %rdi
xor %rsi, %rsi
movl -416(%rbp), %esi
xor %rax, %rax
call printf

# T54 = "\n"
# print T54
mov $T54, %rdi
xor %rax, %rax
call printf

# T55 = a_2_2 + b_2_2
movl -384(%rbp), %edi
movl -416(%rbp), %eax
addl %eax, %edi
movl %edi, -432(%rbp)

# suma_2_2 = T55
movl -432(%rbp), %edi
movl %edi, -448(%rbp)

# print suma_2_2
mov $format_int, %rdi
xor %rsi, %rsi
movl -448(%rbp), %esi
xor %rax, %rax
call printf

# T56 = 100
movl $100, %edi
movl %edi, -464(%rbp)

# T57 = suma_2_2 < T56
movl -448(%rbp), %edi
movl -464(%rbp), %esi
xor %rax, %rax # clean return value register
call CMP_LT
movw %ax,-480(%rbp) # get return value

# if T57 == true goto ETIQ_17
cmpw $1,-480(%rbp)
je ETIQ_17

# goto ETIQ_18
jmp ETIQ_18

# ETIQ_17: skip
ETIQ_17:

# T58 = ": Es un número pequeño\n"
# print T58
mov $T58, %rdi
xor %rax, %rax
call printf

# goto ETIQ_19
jmp ETIQ_19

# ETIQ_18: skip
ETIQ_18:

# T59 = 100
movl $100, %edi
movl %edi, -496(%rbp)

# T60 = suma_2_2 >= T59
movl -448(%rbp), %edi
movl -496(%rbp), %esi
xor %rax, %rax # clean return value register
call CMP_GE
movw %ax,-512(%rbp) # get return value

# T61 = 1000
movl $1000, %edi
movl %edi, -528(%rbp)

# T62 = suma_2_2 < T61
movl -448(%rbp), %edi
movl -528(%rbp), %esi
xor %rax, %rax # clean return value register
call CMP_LT
movw %ax,-544(%rbp) # get return value

# T63 = T60 && T62
movw -512(%rbp), %di
movw -544(%rbp), %ax
andw %ax, %di
movw %di, -560(%rbp)

# if T63 == true goto ETIQ_20
cmpw $1,-560(%rbp)
je ETIQ_20

# goto ETIQ_21
jmp ETIQ_21

# ETIQ_20: skip
ETIQ_20:

# T64 = ": Es un número mediano\n"
# print T64
mov $T64, %rdi
xor %rax, %rax
call printf

# ETIQ_19: skip
ETIQ_19:

# goto ETIQ_22
jmp ETIQ_22

# ETIQ_21: skip
ETIQ_21:

# T65 = ": Es un número grande\n"
# print T65
mov $T65, %rdi
xor %rax, %rax
call printf

# ETIQ_22: skip
ETIQ_22:

# T66 = "HE ACABADO\n"
# print T66
mov $T66, %rdi
xor %rax, %rax
call printf

# rtn
# Delete all reserved space
addq $560, %rsp
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

# CMP GE comparison
CMP_GE:
	cmp %esi, %edi
	jl CMP_GE_FALSE
	mov $1, %ax
	ret
CMP_GE_FALSE:
	mov $0, %ax
	ret

# CMP GT comparison
CMP_GT:
	cmp %esi, %edi
	jle CMP_GT_FALSE
	mov $1, %ax
	ret
CMP_GT_FALSE:
	mov $0, %ax
	ret

# CMP LE comparison
CMP_LE:
	cmp %esi, %edi
	jg CMP_LE_FALSE
	mov $1, %ax
	ret
CMP_LE_FALSE:
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
