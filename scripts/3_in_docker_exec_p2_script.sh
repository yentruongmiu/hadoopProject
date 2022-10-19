cd /usr/local/hadoop || exit
bin/hadoop jar hadoopProject.jar p2 project/input/P2_4 project/output/P2
bin/hadoop fs -cat /user/root/project/output/P2/*