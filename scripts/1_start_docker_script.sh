docker run -dit --name hadoop-2 -v "$(pwd)/../out/artifacts/hadoop_jar/:/usr/share/hadoop" sequenceiq/hadoop-docker:2.7.1 /etc/bootstrap.sh -bash