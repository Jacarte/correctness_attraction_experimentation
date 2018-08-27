#!/bin/bash

cp=`cat cp.txt`

find .. -name "*.java" > source.txt

mkdir bin
javac -cp $cp @source.txt -d bin
