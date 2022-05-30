#!/bin/bash

sudo chmod +x /home/ec2-user/app/build/libs/*.jar

cd /home/ec2-user/app

LOG_PATH="/home/ec2-user/log"

if [ ! -d $LOG_PATH ]
then
  echo `mkdir $LOG_PATH`
fi

TODAY=`date '+%Y-%m-%dT%H:%M:%S'`
LOG_NAME="airbnb-$TODAY"


sudo docker-compose down
sudo docker-compose build
echo `nohup sudo docker-compose up > $LOG_PATH/$LOG_NAME.log 2> $LOG_PATH/$LOG_NAME.error &`
