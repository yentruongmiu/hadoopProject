BASEDIR=$(dirname $0)
BASEDIR=$(dirname "$BASEDIR")
BASEDIR=$(builtin cd $BASEDIR; pwd)
docker run -dit --name hadoop-2 -p 10020:10020 -p 8032:8032 -p 50070:50070 -p 50075:50075 -p 50090:50090 -v "${BASEDIR}/out/artifacts/hadoop_jar/:/usr/share/hadoop" sequenceiq/hadoop-docker:2.7.1 /etc/bootstrap.sh -bash