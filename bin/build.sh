#!/bin/bash

source /etc/profile

echo "确定要拉取代码吗? (Y/n)"
read answer

if [ "$answer" == "y" ] || [ "$answer" == "Y" ] || [ "$answer" == "" ]; then
    git pull
fi

echo "确定要重新构建代码吗? (Y/n)"
read answer

if [ "$answer" == "y" ] || [ "$answer" == "Y" ] || [ "$answer" == "" ]; then
    mvn clean install package "-Dmaven.test.skip=true"
else
    echo "确定要重新构建docker吗? (Y/n)"
    read answer
fi


if [ "$answer" == "y" ] || [ "$answer" == "Y" ] || [ "$answer" == "" ]; then
    docker compose build

    docker compose down

    docker compose up -d
fi
