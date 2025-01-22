#!/bin/sh
rm -f ./program
gcc -no-pie ./output/AssemblerCode.s -o program
FILE=./program
if [ -f "$FILE" ]; then
  $FILE
fi