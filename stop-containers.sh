#!/bin/sh

# Cassandra variables
CASSANDRA_CONTAINER_NAME=minopt-cassandra
# Address variables
ADDRESS_CONTAINER_NAME=minopt-address-service
# Person variables
PERSON_CONTAINER_NAME=minopt-person-service
# Profile variables
PROFILE_CONTAINER_NAME=minopt-profile-service
# Rest variables
REST_CONTAINER_NAME=minopt-rest-service

############################################################################

# Stop and remove cassandra container
docker stop $CASSANDRA_CONTAINER_NAME 2>/dev/null
docker rm $CASSANDRA_CONTAINER_NAME 2>/dev/null

############################################################################

# Stop and remove profile service
docker stop $PROFILE_CONTAINER_NAME 2>/dev/null
docker rm $PROFILE_CONTAINER_NAME 2>/dev/null

# Stop and remove address service
docker stop $ADDRESS_CONTAINER_NAME 2>/dev/null
docker rm $ADDRESS_CONTAINER_NAME 2>/dev/null

# Stop and remove person service
docker stop $PERSON_CONTAINER_NAME 2>/dev/null
docker rm $PERSON_CONTAINER_NAME 2>/dev/null

############################################################################

# Stop and remove rest service
docker stop $REST_CONTAINER_NAME 2>/dev/null
docker rm $REST_CONTAINER_NAME 2>/dev/null

