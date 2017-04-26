#!/bin/sh

sbt assembly
cd ProfileService
docker build -t minopt-profile-service .
cd ..
