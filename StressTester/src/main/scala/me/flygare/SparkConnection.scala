package me.flygare

import com.datastax.driver.core.Session
import com.datastax.spark.connector.cql.CassandraConnector
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

trait SparkConnection {
  val warehouseLocation = "file:${system:user.dir}/spark-warehouse"
  val conf: SparkConf = new SparkConf(true)
    .setAppName("Minopt")
    .setMaster("local[2]")
    .set("spark.cassandra.connection.host", "0.0.0.0")
    .set("spark.cassandra.auth.username", "cassandra")
    .set("spark.cassandra.auth.password", "cassandra")

  val spark: SparkSession = SparkSession
    .builder()
    .appName("Minopt")
    .config(conf)
    .getOrCreate()

  val connector: CassandraConnector = CassandraConnector(spark.sparkContext)
  var session: Session = connector.openSession()

  val keyspace = "minopt"

  session.execute("CREATE KEYSPACE IF NOT EXISTS " + keyspace + " WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1 };")
  session.execute("CREATE TABLE IF NOT EXISTS " + keyspace + ".key_value(key uuid PRIMARY KEY, content TEXT);")

  session.close()
}
