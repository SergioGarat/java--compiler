#!/bin/sh

gcc -no-pie -g ./output/AssemblerCode.s -o program
gdb ./program