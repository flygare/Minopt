#!/bin/sh

docker network create minopt 2>/dev/null

docker stop minopt-cassandra 2>/dev/null
docker rm minopt-cassandra 2>/dev/null
docker run --network=minopt --network-alias=cassandra --name minopt-cassandra -p 9042:9042 -d cassandra:3.0

sleep 5

docker stop minopt-profile-service 2>/dev/null
docker rm minopt-profile-service 2>/dev/null
docker run --network=minopt --network-alias=profile --name minopt-profile-service -d profile-service

# docker run --name minopt-person-service
# docker run --name minopt-address-service
# docker run --name minopt-profile-service
