#!/bin/bash


BIN_DIR=`dirname $0`
BIN_DIR=`cd "$BIN_DIR"; pwd`
DASHBOARD_HOME=$BIN_DIR/..

export JAVA_HOME=$JAVA_HOME

export DASHBOARD_CONF_DIR=$DASHBOARD_HOME/conf
export DASHBOARD_LIB_JARS=$DASHBOARD_HOME/lib/*

export DASHBOARD_OPTS="-server -Xmx1g -Xms1g -Xss512k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70"
export STOP_TIMEOUT=5

CLASS=com.mlamp.dashboard.dao.script.InitDBRunner

exec_command="$DASHBOARD_OPTS -classpath $DASHBOARD_CONF_DIR:$DASHBOARD_LIB_JARS $CLASS"

cd $DASHBOARD_HOME
$JAVA_HOME/bin/java $exec_command
