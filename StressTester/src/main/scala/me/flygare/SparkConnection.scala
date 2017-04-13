package me.flygare

import com.datastax.spark.connector.cql.CassandraConnector
import org.apache.spark.{SparkConf, SparkContext}

trait SparkConnection {
  val conf = new SparkConf(true)
    .setMaster("local[*]")
    .set("spark.cassandra.connection.host", "127.0.0.1")
    .set("spark.cassandra.auth.username", "cassandra")
    .set("spark.cassandra.auth.password", "cassandra")

  val sc = new SparkContext("spark://127.0.0.1:7077", "test", conf)

  val connector = CassandraConnector(sc.getConf)
  var session = connector.openSession()
  session.execute("CREATE KEYSPACE test WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1 };")
  session.execute("CREATE TABLE IF NOT EXISTS test.kv(id INT PRIMARY KEY, ts timestamp);")
}
