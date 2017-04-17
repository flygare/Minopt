#!/bin/sh

docker stop minopt-cassandra 2>/dev/null
docker rm minopt-cassandra 2>/dev/null
docker run --name minopt-cassandra -p 9042:9042 -d cassandra:3.0