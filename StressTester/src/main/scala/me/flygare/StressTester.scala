package me.flygare
import org.apache.spark.sql.cassandra._
import com.datastax.spark.connector._
import org.apache.spark.sql.Dataset
import org.apache.spark.sql.execution.streaming.FileStreamSource.Timestamp
import org.joda.time.{DateTime, DateTimeZone}
import scala.util.Random


case class KeyValue(id: Int, ts: String)

object StressTester extends App with SparkConnection{
  val datasetFormat = "org.apache.spark.sql.cassandra"

  import spark.implicits._

  var keyValue: Seq[KeyValue] = Seq.empty
  var rows: Int = 0
  while (rows < 10000) {
    keyValue ++= Seq(KeyValue(Random.nextInt(1000), DateTime.now(DateTimeZone.UTC).toString()))
    rows += 1
  }




  keyValue.toDS()
    .write
    .format(datasetFormat)
    .options(Map( "table" -> "kv", "keyspace" -> "minopt"))
    .mode("overwrite")
    .cassandraFormat("keyValue", "minopt")
    .save()

  val df = spark
    .read
    .format("org.apache.spark.sql.cassandra")
    .options(Map( "table" -> "keyValue", "keyspace" -> "minopt"))
    .load().show()
}
