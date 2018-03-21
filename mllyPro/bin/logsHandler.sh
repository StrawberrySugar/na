#!/bin/sh
source /etc/profile
  	w=`date '+%w'`
        h=`date '+%H'`
cd ../
cp=`/bin/pwd`
basepath=$cp
filename=$basepath"/logs/api_stdout_"$w"_"$h".log"
name=$basepath"/logs/api_stdout.log"; 

if [ -e $filename ] then 
   /bin/rm -f $filename;
fi

/bin/cp $name $filename;
/bin/echo "">$name;



	


