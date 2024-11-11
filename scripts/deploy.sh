#!/bin/bash

REPOSITORY=/home/ubuntu/deploy
APP_NAME=gamsa

cd $REPOSITORY

echo "> 현재 구동 중인 애플리케이션 pid 확인"
CURRENT_PID=$(lsof -i :8080 -t)

echo "> 현재 구동 중인 애플리케이션 pid: $CURRENT_PID"
if [ -z "$CURRENT_PID" ]; then
        echo "> 구동 중인 애플리케이션이 없습니다."
else
        echo "> kill -15 $CURRENT_PID"
        kill -15 $CURRENT_PID
        sleep 5
fi

echo "> 새 애플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/ | grep jar | head -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME에 실행권한 추가"
chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"
nohup java -jar \
        -Dspring.config.location=/home/ubuntu/prod/application-prod-db.yml,/home/ubuntu/prod/application-jwt.yml,/home/ubuntu/prod/application-data.yml \
        -Dspring.profiles.active=prod \
        $REPOSITORY/$JAR_NAME 2>&1 &