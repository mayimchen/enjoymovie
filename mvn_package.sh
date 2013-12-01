#!/bin/bash
#android client package 
package_lib="/home/jdappandroid/work/apks/"
work_lib="/home/jdappandroid/work/androidJd-main"
cat parteners | while read line
cd $work_lib
mvn clean >>~/mvn_log.log
do
if [ X$STR = "X" ]
then
 echo $line
	mvn -P$line package >>~/mvn_log.log
	mv $work_lib/target/*.apk $package_lib
else
	break
fi
done