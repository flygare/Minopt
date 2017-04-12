CREATE KEYSPACE minopt WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1 };
CREATE TABLE minopt.kv(key text PRIMARY KEY, value int);
