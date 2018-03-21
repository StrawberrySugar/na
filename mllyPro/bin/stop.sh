#!/bin/sh

CURRENT_DIR=`dirname  $0`
API_HOME=`cd "$CURRENT_DIR/.." >/dev/null; pwd`
PID=`cat $API_HOME/api.pid `
PID2=$( ps -ef | grep "$API_HOME/lib" | grep -v "grep" | awk '{ print $2 }' )
#==========================================
# The older killer mybe kill all process! # 
#==========================================
echo "kill process PID:"$PID2
kill -9 $PID2 > /dev/null 2>&1

