cd /usr/local/hadoop || exit
bin/hadoop jar hadoopProject.jar p4 project/input/P4 project/output/P4
bin/hadoop fs -cat /user/root/project/output/P4/*