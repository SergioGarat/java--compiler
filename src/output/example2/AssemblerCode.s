.global main
.extern printf, scanf
.data
T6: .asciz "\n"
T0: .asciz "He entrado: "
format_int: .asciz "%d"
true_label : .asciz "true"
false_label : .asciz "false"
.text
# main: skip
main:

# pmb PROC_main
push %rbp        # Guardem el registre que utilitzarem com a apuntador de la pila.
mov %rsp, %rbp
sub $240, %rsp

# T0 = "He entrado: "
# print T0
mov $T0, %rdi
xor %rax, %rax
call printf

# T1 = 0
movl $0, %edi
movl %edi, -16(%rbp)

# i_0_2 = T1
movl -16(%rbp), %edi
movl %edi, -32(%rbp)

# ETIQ_0: skip
ETIQ_0:

# T2 = 3
movl $3, %edi
movl %edi, -48(%rbp)

# T3 = i_0_2 < T2
movl -32(%rbp), %edi
movl -48(%rbp), %esi
xor %rax, %rax # clean return value register
call CMP_LT
movw %ax,-64(%rbp) # get return value

# if T3 == true goto ETIQ_1
cmpw $1,-64(%rbp)
je ETIQ_1

# goto ETIQ_2
jmp ETIQ_2

# ETIQ_3: skip
ETIQ_3:

# T4 = 1
movl $1, %edi
movl %edi, -80(%rbp)

# T5 = i_0_2 + T4
movl -32(%rbp), %edi
movl -80(%rbp), %eax
addl %eax, %edi
movl %edi, -96(%rbp)

# i_0_2 = T5
movl -96(%rbp), %edi
movl %edi, -32(%rbp)

# goto ETIQ_0
jmp ETIQ_0

# ETIQ_1: skip
ETIQ_1:

# print i_0_2
mov $format_int, %rdi
xor %rsi, %rsi
movl -32(%rbp), %esi
xor %rax, %rax
call printf

# T6 = "\n"
# print T6
mov $T6, %rdi
xor %rax, %rax
call printf

# goto ETIQ_3
jmp ETIQ_3

# ETIQ_2: skip
ETIQ_2:

# T7 = 1
movl $1, %edi
movl %edi, -160(%rbp)

# T8 = true
movw $1, %di
movw %di, -176(%rbp)

# T9 = 2
movl $2, %edi
movl %edi, -192(%rbp)

# id3_0_2 = T7
movl -160(%rbp), %edi
movl %edi, -144(%rbp)

# id2_0_2 = T8
movw -176(%rbp), %di
movw %di, -128(%rbp)

# id3_0_2 = T9
movl -192(%rbp), %edi
movl %edi, -144(%rbp)

# rtn
# Delete all reserved space
addq $240, %rsp
leave
ret

# exit

# auxiliar functions
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
