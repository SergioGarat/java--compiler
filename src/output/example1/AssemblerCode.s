.global main
.extern printf, scanf
.data
T4: .asciz "\n"
str_0_2: .asciz "HOLA MUNDO!"
T2: .asciz "\n"
T3: .asciz "\n"
format_int: .asciz "%d"
true_label : .asciz "true"
false_label : .asciz "false"
.text
# main: skip
main:

# pmb PROC_main
push %rbp        # Guardem el registre que utilitzarem com a apuntador de la pila.
mov %rsp, %rbp
sub $64, %rsp

# T0 = 100
movl $100, %edi
movl %edi, -16(%rbp)

# num_0_2 = T0
movl -16(%rbp), %edi
movl %edi, -32(%rbp)

# T1 = true
movw $1, %di
movw %di, -48(%rbp)

# b_0_2 = T1
movw -48(%rbp), %di
movw %di, -64(%rbp)

# print num_0_2
mov $format_int, %rdi
xor %rsi, %rsi
movl -32(%rbp), %esi
xor %rax, %rax
call printf

# T2 = "\n"
# print T2
mov $T2, %rdi
xor %rax, %rax
call printf

# print str_0_2
mov $str_0_2, %rdi
xor %rax, %rax
call printf

# T3 = "\n"
# print T3
mov $T3, %rdi
xor %rax, %rax
call printf

# print b_0_2
movw -64(%rbp), %di
call print_bool

# T4 = "\n"
# print T4
mov $T4, %rdi
xor %rax, %rax
call printf

# rtn
# Delete all reserved space
addq $64, %rsp
leave
ret

# exit

# auxiliar functions
print_bool :
cmpw $0,%di
je print_false
mov $true_label, %rdi
jmp print_bool_val
print_false : mov $false_label, %rdi
print_bool_val : xor %rax, %rax
call printf
ret
