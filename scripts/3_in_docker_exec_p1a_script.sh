cd /usr/local/hadoop || exit
bin/hadoop jar hadoopProject.jar p1a project/input/P1A_WordCount project/output/P1A_WordCount
bin/hadoop fs -cat /user/root/project/output/P1A_WordCount/*