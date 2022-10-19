cd /usr/local/hadoop || exit
bin/hadoop jar hadoopProject.jar p1c project/input/P1C project/output/P1C 3
bin/hadoop fs -cat /user/root/project/output/P1C/*