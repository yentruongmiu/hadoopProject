cd /usr/local/hadoop || exit
bin/hadoop jar hadoopProject.jar p1b project/input/P1B project/output/P1B 3
bin/hadoop fs -cat /user/root/project/output/P1B/*