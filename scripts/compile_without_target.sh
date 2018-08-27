#! /bin/bash

#!/bin/bash

cp=`cat cp.txt`


find .. -name "*.java" ! -wholename "*/target/*" ! -name "Main.java" > source.txt

mkdir bin
javac -cp $cp @source.txt -d bin

