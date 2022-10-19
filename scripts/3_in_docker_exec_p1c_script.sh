cd /usr/local/hadoop || exit
bin/hadoop jar hadoopProject.jar p1c project/input/P1C_AverageComputing project/output/P1C_AverageComputing 3
bin/hadoop fs -cat /user/root/project/output/P1C_AverageComputing/*