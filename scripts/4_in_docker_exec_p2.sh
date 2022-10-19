cd /usr/local/hadoop || exit
bin/hadoop jar hadoopProject.jar p2 project/input/P2_3 project/output/P2_FrequenciesPairs
bin/hadoop fs -cat /user/root/project/output/P2_FrequenciesPairs/*