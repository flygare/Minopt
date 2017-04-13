package me.flygare
import org.apache.spark.sql.cassandra._

import scala.util.Random

object StressTester extends App with SparkConnection{
  import spark.implicits._

  val datasetFormat = "org.apache.spark.sql.cassandra"

  def uuid: String = java.util.UUID.randomUUID.toString

  var keyValue: Seq[KeyValue] = Seq.empty
  var rows: Int = 0
  while (rows < 100) {
    keyValue ++= Seq(KeyValue(uuid, Random.alphanumeric.toString))
    rows += 1
  }

  keyValue.toDS()
  .write
  .format(datasetFormat)
  .options(Map("table" -> "key_value", "keyspace" -> "minopt"))
  .mode("overwrite")
  .save()

  spark
    .read
    .cassandraFormat("key_value", "minopt")
    .load()
    .show(truncate = false)


}
