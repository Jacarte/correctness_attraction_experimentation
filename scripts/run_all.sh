./run_
mkdir results


# ./run.sh -target mx -size 100 -time 10000 -type pbool
./run.sh -target qs -size 20 -time 10000 -type pbool

./run.sh -target md5 -size 50 -time 10000 -type pbool

./run.sh -target su -size 50 -time 10000 -type pbool -grid ./grid.txt

source plot/bin/activate
python plot_qs.py