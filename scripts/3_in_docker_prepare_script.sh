cd /usr/local/hadoop || exit
bin/hadoop fs -mkdir /user/root/project /user/root/project/input
cp /usr/share/hadoop/hadoopProject.jar /usr/local/hadoop
bin/hadoop fs -put /usr/share/hadoop/input/* /user/root/project/input
