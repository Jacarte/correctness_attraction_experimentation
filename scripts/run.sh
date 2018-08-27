#!/bin/bash
cp=`cat cp.txt`

./compile.sh
java -cp $cp:./bin entrypoint.Main -r $1 $2 $3 $4 $5 $6 $7 $8 $9 ${10} ${11} ${12} ${13}