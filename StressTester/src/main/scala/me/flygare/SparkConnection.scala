package me.flygare

import com.datastax.driver.core.Session
import com.datastax.spark.connector.cql.CassandraConnector
import org.apache.spark.{SparkConf, SparkContext}

trait SparkConnection {
  val conf: SparkConf = new SparkConf(true)
    .setAppName("Minopt")
    .setMaster("local[2]")
    .set("spark.cassandra.connection.host", "0.0.0.0")
    .set("spark.cassandra.auth.username", "cassandra")
    .set("spark.cassandra.auth.password", "cassandra")

  val sc: SparkContext = new SparkContext(conf)

  val connector: CassandraConnector = CassandraConnector(sc.getConf)
  var session: Session = connector.openSession()

  val keyspace = "minopt"

  session.execute("CREATE KEYSPACE IF NOT EXISTS " + keyspace + " WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1 };")
  session.execute("CREATE TABLE IF NOT EXISTS " + keyspace + ".kv(id INT PRIMARY KEY, ts timestamp);")
}
