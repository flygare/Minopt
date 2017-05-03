#!/bin/sh

# Stop and clean cassandra
service cassandra stop
rm -rf /var/lib/cassandra/data/system/*

# Start cassandra
service cassandra start 2> cassandra.log

sleep 15

# Build and execute Monolithic fat jar
cd Monolithic/
java -cp target/scala-2.11/monolithic.jar me.flygare.Monolithic 2>monolithic.log
cd ..
