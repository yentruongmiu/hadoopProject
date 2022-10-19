BASEDIR=$(dirname $0)
BASEDIR=$(dirname "$BASEDIR")
BASEDIR=$(builtin cd $BASEDIR; pwd)
docker run -dit --name hadoop-2 -p 50070:50070 -v "${BASEDIR}/out/artifacts/hadoop_jar/:/usr/share/hadoop" sequenceiq/hadoop-docker:2.7.1 /etc/bootstrap.sh -bash