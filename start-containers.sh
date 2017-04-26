#!/bin/sh

docker stop minopt-cassandra 2>/dev/null
docker rm minopt-cassandra 2>/dev/null
docker run --name minopt-cassandra -p 9042:9042 -d cassandra:3.0
docker stop profile-service-cass
docker rm profile-service-cass
docker run --name profile-service-cass --link minopt-cassandra:cassandra_profile_service -d profile_service

# docker run --name minopt-person-service
# docker run --name minopt-address-service
# docker run --name minopt-profile-service
