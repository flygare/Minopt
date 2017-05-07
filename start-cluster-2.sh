#!/bin/sh

# Address variables
ADDRESS_IMAGE=minopt-address-service
ADDRESS_CONTAINER_NAME=minopt-address-service
# Person variables
PERSON_IMAGE=minopt-person-service
PERSON_CONTAINER_NAME=minopt-person-service
# Profile variables
PROFILE_IMAGE=minopt-profile-service
PROFILE_CONTAINER_NAME=minopt-profile-service

############################################################################

# Stop, remove and start profile service
docker stop $PROFILE_CONTAINER_NAME 2>/dev/null
docker rm $PROFILE_CONTAINER_NAME 2>/dev/null
docker run --network=host --name $PROFILE_CONTAINER_NAME -d -p 3003:3003 $PROFILE_IMAGE

# Stop, remove and start address service
docker stop $ADDRESS_CONTAINER_NAME 2>/dev/null
docker rm $ADDRESS_CONTAINER_NAME 2>/dev/null
docker run --network=host --name $ADDRESS_CONTAINER_NAME -d -p 3001:3001 $ADDRESS_IMAGE

# Stop, remove and start person service
docker stop $PERSON_CONTAINER_NAME 2>/dev/null
docker rm $PERSON_CONTAINER_NAME 2>/dev/null
docker run --network=host --name $PERSON_CONTAINER_NAME -d -p 3002:3002 $PERSON_IMAGE

