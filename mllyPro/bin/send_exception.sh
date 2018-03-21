#!/bin/sh
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
  
  startLineNum=`tail -60000 $path/logs/api_stdout.log | egrep -n "$theY"+" $theTime10" `

  echo "-----------------------------------------startLineNum="$startLineNum
	
  totalNum=`wc -l $path/logs/api_stdout.log | cut -f1 -d" "`
  echo "-----------------------------------------totalNum="$totalNum
  
  tailNum=`expr $totalNum - $startLineNum`;
  echo "-----------------------------------------tailNum="$tailNum
  
  if [ $tailNum -gt 0 ]
  then
	  echo "-----------------------------------------ok"
	  echo "" > $path"/logs/exception.log"
	  tail -n $tailNum $path/logs/api_stdout.log | grep Exception -C 5 | head -10 | cut -c 1-200  > $path"/logs/exception.log"
  fi
  
  cat $path/logs/exception.log;
	
}

function sendEmailAlarm()
{
  if [ -f "$myFile" ]; then  
     touch "$myFile"  
  else
     exit;
  fi 
  contentBytes=$(wc -c $path"/logs/exception.log" | awk '{print $1}')
 
  if [ "$contentBytes" -gt "30" ];
  then
    ip=$( ifconfig eth0 | grep 'inet' | awk '{print $2}' | sed -e "s/addr\://" )
	$path"/bin/smtp.sh"  "lekut_server@sinofriends.com" "$nowTime API($ip) exception" $path"/logs/exception.log"
  fi;

}
function main()
{
  init  
  getException
  sendEmailAlarm
}

main


