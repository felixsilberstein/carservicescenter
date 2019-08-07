#!/bin/bash


docker_rm() {
  CONTAINER=`docker ps -aq -f "name=$1"` && [[ -n "${CONTAINER// }" ]] && docker rm "$(docker stop $CONTAINER)"
}
docker_rm "$1"
HOME=/Users/felix/Projects/projects/carservicecenter/springboot
cd $HOME
mvn clean package
echo "Application compiled"
cd $HOME/docker
docker build -t carservices-appointments:latest .
docker run --name=api -v/Users/felix/Projects/projects/carservicecenter/logs:/var/log/Application/ --net=appointments-net -p 8080:8080 -d carservices-appointments:latest
