#!/usr/bin/env bash
mvn clean compile install spring-boot:repackage
cp target/mz-0.0.1-SNAPSHOT-spring-boot.jar ~/develop/docker-projects/mz-app/back-app/app.jar
cd /home/az/develop/docker-projects/mz-app/
./restart-docker.sh