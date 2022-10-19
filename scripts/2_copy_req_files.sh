BASEDIR=$(dirname $0)
BASEDIR=$(dirname "$BASEDIR")
BASEDIR=$(builtin cd $BASEDIR; pwd)
docker cp ${BASEDIR}/scripts/4_in_docker_prepare.sh hadoop-2:/usr/share/hadoop
docker cp ${BASEDIR}/scripts/4_in_docker_exec_p1a.sh hadoop-2:/usr/share/hadoop
docker cp ${BASEDIR}/scripts/4_in_docker_exec_p1b.sh hadoop-2:/usr/share/hadoop
docker cp ${BASEDIR}/scripts/4_in_docker_exec_p1c.sh hadoop-2:/usr/share/hadoop
docker cp ${BASEDIR}/scripts/4_in_docker_exec_p1d.sh hadoop-2:/usr/share/hadoop
docker cp ${BASEDIR}/scripts/4_in_docker_exec_p2.sh hadoop-2:/usr/share/hadoop
docker cp ${BASEDIR}/scripts/4_in_docker_exec_p3.sh hadoop-2:/usr/share/hadoop
docker cp ${BASEDIR}/scripts/4_in_docker_exec_p4.sh hadoop-2:/usr/share/hadoop
docker cp ${BASEDIR}/input hadoop-2:/usr/share/hadoop