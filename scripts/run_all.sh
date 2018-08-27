./run_
mkdir results

./run.sh -target mx -size 100 -time 10000 -type pone > results/maximum.txt
./run.sh -target qs -size 20 -time 10000 -type pone > results/quicksort.txt
./run.sh -target md5 -size 50 -time 10000 -type pone > results/md5.txt
./run.sh -target su -size 50 -time 10000 -type pone -grid ./grid.txt > results/sudoku.txt