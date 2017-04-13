#!/bin/sh

# Stop and clean cassandra
service cassandra stop
rm -rf /var/lib/cassandra/data/system/*
