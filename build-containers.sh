#!/bin/sh

sbt assembly
pushd ProfileService
docker build -t profile_service .
popd
