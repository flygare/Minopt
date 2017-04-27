#!/bin/sh

sbt assembly

cd ProfileService
docker build -t minopt-profile-service .
cd ..

cd PersonService
docker build -t minopt-person-service .
cd ..

cd AddressService
docker build -t minopt-address-service .
cd ..

cd RestService
docker build -t minopt-rest-service .
cd ..

