#!/bin/sh

CURRENT_DIR=`dirname $0`
API_HOME=`cd "$CURRENT_DIR/.." >/dev/null; pwd`
cp=".:$API_HOME/lib/*"
cd $API_HOME
#============================
# create PID                #  
#============================
echo $$ > $API_HOME/api.pid
exec $JAVA_HOME/jre/bin/java \
        -Xms512m -Xmx512m \
        -XX:-UseGCOverheadLimit \
        -verbose:gc \
        -Xloggc:jvm-gc.log \
        -Djava.util.logging.config.file="$API_HOME/conf/logging.properties" \
        -Dlog4j.configuration="$API_HOME/conf/log4j.properties" \
        -classpath $cp com.digu.api.main.OpenApiMain >> $API_HOME/logs/api_stdout.log 





