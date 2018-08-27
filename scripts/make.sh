#!/bin/bash

cp=`cat cp.txt`

./compile_without_target.sh
java -cp $cp:./bin entrypoint.Instrumentation -b ../src/target