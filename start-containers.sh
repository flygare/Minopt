#!/bin/sh

NETWORK_NAME=minopt
# Cassandra variables
CASSANDRA_IMAGE=cassandra:3.0
CASSANDRA_CONTAINER_NAME=minopt-cassandra
CASSANDRA_ALIAS=cassandra
# Address variables
ADDRESS_IMAGE=minopt-address-service
ADDRESS_CONTAINER_NAME=minopt-address-service
ADDRESS_ALIAS=address
# Person variables
PERSON_IMAGE=minopt-person-service
PERSON_CONTAINER_NAME=minopt-person-service
PERSON_ALIAS=person
# Profile variables
PROFILE_IMAGE=minopt-profile-service
PROFILE_CONTAINER_NAME=minopt-profile-service
PROFILE_ALIAS=profile

############################################################################
############################################################################
############################################################################

# Create network
docker network create $NETWORK_NAME 2>/dev/null

# Stop, remove and start cassandra container
docker stop $CASSANDRA_CONTAINER_NAME 2>/dev/null
docker rm $CASSANDRA_CONTAINER_NAME 2>/dev/null
docker run --network=$NETWORK_NAME --network-alias=$CASSANDRA_ALIAS --name $CASSANDRA_CONTAINER_NAME -d $CASSANDRA_IMAGE

sleep 15

############################################################################

# Stop, remove and start profile service
docker stop $PROFILE_CONTAINER_NAME 2>/dev/null
docker rm $PROFILE_CONTAINER_NAME 2>/dev/null
docker run --network=$NETWORK_NAME --network-alias=$PROFILE_ALIAS --link=$CASSANDRA_CONTAINER_NAME:$CASSANDRA_ALIAS --name $PROFILE_CONTAINER_NAME -d $PROFILE_IMAGE

############################################################################

# Stop, remove and start address service
docker stop $ADDRESS_CONTAINER_NAME 2>/dev/null
docker rm $ADDRESS_CONTAINER_NAME 2>/dev/null
docker run --network=$NETWORK_NAME --network-alias=$ADDRESS_ALIAS --link=$CASSANDRA_CONTAINER_NAME:$CASSANDRA_ALIAS --name $ADDRESS_CONTAINER_NAME -d $ADDRESS_IMAGE

# Stop, remove and start person service
docker stop $PERSON_CONTAINER_NAME 2>/dev/null
docker rm $PERSON_CONTAINER_NAME 2>/dev/null
docker run --network=$NETWORK_NAME --network-alias=$PERSON_ALIAS --link=$CASSANDRA_CONTAINER_NAME:$CASSANDRA_ALIAS --name $PERSON_CONTAINER_NAME -d $PERSON_IMAGE
