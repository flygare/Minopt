#!/bin/sh

MONO_CONTAINER_NAME=minopt-mono
MONO_IMAGE=minopt-mono

docker stop $MONO_CONTAINER_NAME 2>/dev/null
docker rm $MONO_CONTAINER_NAME 2>/dev/null
docker run --name $MONO_CONTAINER_NAME -p 3000:3000 -d $MONO_IMAGE
