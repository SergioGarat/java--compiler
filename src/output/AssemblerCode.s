.global main
.extern printf, scanf
.data
T5: .asciz "He entrado: "
T6: .asciz "\n"
T8: .asciz "HE SALIDO"
format_int: .asciz "%d"
true_label : .asciz "true"
false_label : .asciz "false"
.text
# main:skip
main:

# pmb PROC_main
push %rbp        # Guardem el registre que utilitzarem com a apuntador de la pila.
mov %rsp, %rbp
sub $32, %rsp

# T0 = 0
movl $0, %edi
movl %edi, -4(%rbp)

# i_0_2 = T0
movl -4(%rbp), %edi
movl %edi, -8(%rbp)

# LABEL_0:skip
LABEL_0:

# T1 = 3
movl $3, %edi
movl %edi, -12(%rbp)

# T2 = i_0_2 LT T1
movl -12(%rbp), %edi
movl -8(%rbp), %esi
xor %rax, %rax # clean return value register
call CMP_LT
movw %ax,-14(%rbp) # get return value

# if T2=true goto LABEL_1
cmpw $1,-14(%rbp)
je LABEL_1

# go_to LABEL_2
jmp LABEL_2

# LABEL_3:skip
LABEL_3:

# T3 = 1
movl $1, %edi
movl %edi, -18(%rbp)

# T4 = i_0_2 add T3
movl -8(%rbp), %edi
movl -18(%rbp), %eax
addl %eax, %edi
movl %edi, -22(%rbp)

# i_0_2 = T4
movl -22(%rbp), %edi
movl %edi, -8(%rbp)

# go_to LABEL_0
jmp LABEL_0

# LABEL_1:skip
LABEL_1:

# T5 = "He entrado: "
# print T5
mov $T5, %rdi
xor %rax, %rax
call printf

# print i_0_2
mov $format_int, %rdi
xor %rsi, %rsi
movl -8(%rbp), %esi
xor %rax, %rax
call printf

# T6 = "\n"
# print T6
mov $T6, %rdi
xor %rax, %rax
call printf

# go_to LABEL_3
jmp LABEL_3

# LABEL_2:skip
LABEL_2:

# T7 = read
xor %rax, %rax
mov $format_int, %rdi
leaq -26(%rbp), %rsi
call scanf

# entrada_0_2 = T7
movl -26(%rbp), %edi
movl %edi, -30(%rbp)

# print entrada_0_2
mov $format_int, %rdi
xor %rsi, %rsi
movl -30(%rbp), %esi
xor %rax, %rax
call printf

# T8 = "HE SALIDO"
# print T8
mov $T8, %rdi
xor %rax, %rax
call printf

# rtn
# Delete all reserved space
addq $30, %rsp
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
