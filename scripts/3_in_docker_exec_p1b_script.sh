cd /usr/local/hadoop || exit
bin/hadoop jar hadoopProject.jar p1b project/input/P1B_InMapperCombiningWordCount project/output/P1B_InMapperCombiningWordCount 1
bin/hadoop fs -cat /user/root/project/output/P1B_InMapperCombiningWordCount/*