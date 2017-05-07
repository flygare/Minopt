#!/bin/sh

# Stop and clean cassandra
service cassandra stop &> cassandra.log &
rm -rf /var/lib/cassandra/data/system/* &> /dev/null

# Start cassandra
service cassandra start &> cassandra.log &

# sleep 15

# Build and execute Monolithic fat jar
cd Monolithic/
java -cp target/scala-2.11/monolithic.jar me.flygare.Monolithic &> mono.log &
cd ..
