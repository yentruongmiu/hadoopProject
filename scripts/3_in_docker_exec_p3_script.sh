cd /usr/local/hadoop || exit
bin/hadoop jar hadoopProject.jar p3 project/input/P2_4 project/output/P3
bin/hadoop fs -cat /user/root/project/output/P3/*