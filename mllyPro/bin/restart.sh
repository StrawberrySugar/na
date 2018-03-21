#!/bin/sh

CURRENT_DIR=`dirname  $0`
API_HOME=`cd "$CURRENT_DIR/.." >/dev/null; pwd`

PID2=$( ps -ef | grep "$API_HOME/lib" | grep -v "grep" | awk '{ print $2 }' )
echo "kill process PID:"$PID2
kill -9 $PID2 > /dev/null 2>&1

run_cmd="$CURRENT_DIR/api.sh "$*
exec $run_cmd &
echo "...... started api ......"