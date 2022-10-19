cd /usr/local/hadoop || exit
bin/hadoop jar hadoopProject.jar p1a project/input/P1A_WordCount project/output/P1A_WordCount 4
bin/hadoop fs -cat project/output/P1A_WordCount/*