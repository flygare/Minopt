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
    .set("spark.cassandra.connection.host", "127.0.0.1")
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

  session.execute("CREATE TABLE IF NOT EXISTS " + Keyspace + ".person(key uuid PRIMARY KEY, " +
    "firstname TEXT, lastname TEXT);")

  session.execute("CREATE TABLE IF NOT EXISTS " + Keyspace + ".address(key uuid PRIMARY KEY, " +
    "street TEXT, zipcode INT, city TEXT, county TEXT, country TEXT);")

  session.execute("CREATE TABLE IF NOT EXISTS " + Keyspace + ".profile(key uuid PRIMARY KEY, " +
    "firstname TEXT, lastname TEXT, phonenumber TEXT, email TEXT, username TEXT, password TEXT, description TEXT, website TEXT, lastip TEXT, lastlogin TEXT);")

  session.close()
}
