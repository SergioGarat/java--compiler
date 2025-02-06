#!/bin/sh
rm -f ./program
gcc -no-pie ./output/example3/AssemblerCode.s -o program
FILE=./program
if [ -f "$FILE" ]; then
  $FILE
fi