package me.flygare.traits

import com.datastax.driver.core.Session
import com.datastax.spark.connector.cql.CassandraConnector
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

trait SparkConnection {
  val warehouseLocation = "file:${system:user.dir}/spark-warehouse"
  val conf = new SparkConf(true)
    .setAppName("Minopt")
    .setMaster("local[2]")
    .set("spark.cassandra.connection.host", "0.0.0.0")
    .set("spark.cassandra.auth.username", "cassandra")
    .set("spark.cassandra.auth.password", "cassandra")

  val spark = SparkSession
    .builder()
    .appName("Minopt")
    .config(conf)
    .getOrCreate()

  val connector = CassandraConnector(spark.sparkContext)
  var session = connector.openSession()

  val Keyspace = "minopt"

  session.execute("CREATE KEYSPACE IF NOT EXISTS " + Keyspace + " WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1 };")
  session.execute("CREATE TABLE IF NOT EXISTS " + Keyspace + ".key_value_two(key uuid PRIMARY KEY, " +
    "col1 TEXT, col2 TEXT);")
  session.execute("CREATE TABLE IF NOT EXISTS " + Keyspace + ".key_value_five(key uuid PRIMARY KEY, " +
    "col1 TEXT, col2 TEXT, col3 TEXT, col4 TEXT, col5 TEXT);")
  session.execute("CREATE TABLE IF NOT EXISTS " + Keyspace + ".key_value_ten(key uuid PRIMARY KEY, " +
    "col1 TEXT, col2 TEXT, col3 TEXT, col4 TEXT, col5 TEXT, col6 TEXT, col7 TEXT, col8 TEXT, col9 TEXT, col10 TEXT);")

  session.close()
}
