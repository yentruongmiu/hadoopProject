cd /usr/local/hadoop || exit
bin/hadoop jar hadoopProject.jar p3 project/input/P2_3 project/output/P3_FrequenciesStripe
bin/hadoop fs -cat /user/root/project/output/P3_FrequenciesStripe/*