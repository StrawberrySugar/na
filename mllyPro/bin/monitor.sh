 #!/bin/sh

source /etc/profile
HOST_IP=`/sbin/ifconfig eth1| awk '/inet addr/ {print $2}' | awk -F: '{print $2}'`
AlARM_ID=0

CURRENT_DIR=`dirname $0`
API_HOME=`cd "$CURRENT_DIR/.." >/dev/null; pwd`
filePath=$API_HOME"/bin/monitor.log";
port=8081

i=0
while [ $i -lt 2 ]
do
	curl --connect-timeout 2 --head http://localhost:$port/1.1/statuses/public_timeline.xml > $filePath
    sleep 1
	contentBytes=$(wc -c $filePath | awk '{print $1}')
	if [ $contentBytes -gt 1 ] 
	then
       i=10;
	else
		i=`expr $i + 1`;
	fi
done

if [ $i -lt 5 ] 
then
   contents="api_restart_"$ip"//";	
   sleep 1
   run_cmd="$CURRENT_DIR/restart.sh "$*
   exec $run_cmd &
   echo "...... started api ......"
   wget -q "http://10.10.20.21:8081/a/addAlarmTest.jsp?id=$AlARM_ID&content=$1&ip=$HOST_IP&password=lekusms" -O /dev/null 2>&1
fi


















