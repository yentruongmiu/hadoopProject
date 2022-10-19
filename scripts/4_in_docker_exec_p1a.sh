cd /usr/local/hadoop || exit
bin/hadoop jar hadoopProject.jar p1a project/input/P1A project/output/P1A 4
bin/hadoop fs -cat project/output/P1A/*