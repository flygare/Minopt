#!/bin/sh

# Cassandra variables
CASSANDRA_IMAGE=cassandra:3.0
CASSANDRA_CONTAINER_NAME=minopt-cassandra
# Rest variables
REST_IMAGE=minopt-rest-service
REST_CONTAINER_NAME=minopt-rest-service

############################################################################

# Stop, remove and start cassandra container
docker stop $CASSANDRA_CONTAINER_NAME 2>/dev/null
docker rm $CASSANDRA_CONTAINER_NAME 2>/dev/null
docker run --network=host --name $CASSANDRA_CONTAINER_NAME -d -p 9042:9042 $CASSANDRA_IMAGE

# Stop, remove and start rest service
docker stop $REST_CONTAINER_NAME 2>/dev/null
docker rm $REST_CONTAINER_NAME 2>/dev/null
docker run --network=host --name $REST_CONTAINER_NAME -d -p 3000:3000 $REST_IMAGE

