#!/bin/sh
#
#Date:2009-7-14
#Usage:
#

source /etc/profile
cd ../
cp=`/bin/pwd`
path=$cp
function init()
{ 
  theY=`date +"%Y-%m-%d"` 
  theTime1=`date +"%H:%M" -d "1 minute ago "`":" 
  theTime2=`date +"%H:%M" -d "2 minute ago "`":" 
  theTime3=`date +"%H:%M" -d "3 minute ago "`":" 
  theTime4=`date +"%H:%M" -d "4 minute ago "`":" 
  theTime5=`date +"%H:%M" -d "5 minute ago "`":" 
  theTime6=`date +"%H:%M" -d "6 minute ago "`":" 
  theTime7=`date +"%H:%M" -d "7 minute ago "`":" 
  theTime8=`date +"%H:%M" -d "8 minute ago "`":" 
  theTime9=`date +"%H:%M" -d "9 minute ago "`":" 
  theTime10=`date +"%H:%M" -d "10 minute ago "`":" 
  
  nowTime=`date +"%Y-%m-%d %H:%M"` 
}

function getException()
{  
#echo $theY"|"$theTime1"|"$theTime2"|"$theTime3"|"$theTime4"|"$theTime5"|"$theTime6"|"$theTime7"|"$theTime8"|"$theTime9"|"$theTime10
  #tail -1000 $path"/logs/sql.log" |  grep "$theTime" -C 5  > $path"/logs/sqlTimeout.log"
  #tail -10000 $path"/logs/sql.log" |  awk '{if($2=="$theY" && $3~/^["$theTime1"|"$theTime2"|"$theTime3"|"$theTime4"|"$theTime5"|"$theTime6"|"$theTime7"|"$theTime8"|"$theTime9"|"$theTime10"]/) print $0;}' | head -10  > $path"/logs/sqlTimeout.log"
  tail -10000 $path"/logs/sql.log" |  egrep  "$theY"+" ($theTime1|$theTime2|$theTime3|$theTime4|$theTime5|$theTime6|$theTime7|$theTime8|$theTime9|$theTime10)" | head -10 | cut -c 1-200  > $path"/logs/sqlTimeout.log"
}


function sendEmailAlarm()
{
  contentBytes=$(wc -c $path"/logs/sqlTimeout.log" | awk '{print $1}')
  if [ "$contentBytes" -gt "20" ];
  then
    ip=$( ifconfig eth0 | grep 'inet' | awk '{print $2}' | sed -e "s/addr\://" )
	$path/bin/smtp.sh  "server@sinofriends.com"   "$nowTime API($ip) SQL Timeout" $path/logs/sqlTimeout.log
  fi;

}
function main()
{
  init  
  getException
  sendEmailAlarm
}

main


