#!/bin/sh

# Stop and clean cassandra
service cassandra stop
rm -rf /var/lib/cassandra/data/system/*

# Start cassandra
service cassandra start

# Build and execute Monolithic fat jar
cd Monolithic
sbt assembly
java -jar /target/scala-2.11/monolithic.jar &> start-mono.log &
cd ..
