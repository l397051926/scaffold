#!/bin/sh

usage="Usage: dashboard-daemon.sh (start|stop)"

# if no args specified, show usage
if [ $# -le 0 ]; then
  echo $usage
  exit 1
fi

startStop=$1
shift

echo "Begin $startStop dashboard server......"

BIN_DIR=`dirname $0`
BIN_DIR=`cd "$BIN_DIR"; pwd`
TS_HOME=$BIN_DIR/..

source /etc/profile
export JAVA_HOME=$JAVA_HOME
export HOSTNAME=`hostname`

export TS_PID_DIR=/tmp/
export TS_LOG_DIR=$TS_HOME/logs
export TS_PID_DIR=$TS_HOME/pid
export TS_CONF_DIR=$TS_HOME/conf
export TS_LIB_JARS=$TS_HOME/lib/*

export TS_OPTS="-server -Xmx1024m -Xms512m -Xss1024k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70"
export STOP_TIMEOUT=5

if [ ! -d "$TS_LOG_DIR" ]; then
  mkdir $TS_LOG_DIR
fi

log=$TS_LOG_DIR/dashboard-$HOSTNAME.out
pid=$TS_PID_DIR/dashboard.pid

cd $TS_HOME

CLASS=com.mlamp.dashboard.api.ApiDashboardServer
#LOG_FILE="-Dlogback.configurationFile=conf/logback-api.xml"
startDashboard(){
    [ -w "$TS_PID_DIR" ] ||  mkdir -p "$TS_PID_DIR"

    if [ -f $pid ]; then
      if kill -0 `cat $pid` > /dev/null 2>&1; then
        echo dashboard server running as process `cat $pid`.  Stop it first.
        exit 1
      fi
    fi

    echo starting dashboard server, logging to $log

    exec_command="$TS_OPTS -classpath $TS_CONF_DIR:$TS_LIB_JARS $CLASS"

    echo "nohup $JAVA_HOME/bin/java $exec_command > $log 2>&1 < /dev/null &"
    nohup $JAVA_HOME/bin/java $exec_command > $log 2>&1 < /dev/null &
    echo $! > $pid
}

stopDashboard(){
    if [ -f $pid ]; then
      TARGET_PID=`cat $pid`
      if kill -0 $TARGET_PID > /dev/null 2>&1; then
        echo stopping dashboard server
        kill $TARGET_PID
        sleep $STOP_TIMEOUT
        if kill -0 $TARGET_PID > /dev/null 2>&1; then
          echo "dashboard server did not stop gracefully after $STOP_TIMEOUT seconds: killing with kill -9"
          kill -9 $TARGET_PID
        fi
      else
        echo nothing to stop
      fi
      rm -f $pid
    else
      echo nothing to stop
    fi

}

case $startStop in
  (start)
    startDashboard
    ;;
  (stop)
    stopDashboard
    ;;
  (restart)
    stopDashboard
    sleep 5
    startDashboard
    ;;
  (*)
    echo $usage
    exit 1
    ;;

esac

echo "End $startStop dashboard server."
