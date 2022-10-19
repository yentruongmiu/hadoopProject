cd /usr/local/hadoop || exit
bin/hadoop jar hadoopProject.jar p1d project/input/P1D project/output/P1D 2
bin/hadoop fs -cat /user/root/project/output/P1D/*