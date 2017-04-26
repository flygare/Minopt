#!/bin/sh

docker stop minopt-cassandra 2>/dev/null
docker rm minopt-cassandra 2>/dev/null
docker run --name minopt-cassandra -p 9042:9042 -d cassandra:3.0
docker stop minopt-person-service
docker rm minopt-person-service
docker run --net=host minopt-person-service -d

# docker run --name minopt-person-service
# docker run --name minopt-address-service
# docker run --name minopt-profile-service
