package me.flygare

import org.apache.spark.sql.cassandra._
import me.flygare.StressTester.spark
import org.apache.spark.sql.Row
import spark.implicits._

case class KeyValue(key: String, content: String)

class KeyValueHandler {
  val TableName = "key_value"
  val Keyspace = "minopt"
  val TableOption = Map("table" -> TableName, "keyspace" -> Keyspace)

  val DatasetFormat: String = "org.apache.spark.sql.cassandra"


  def createKeyValue(content: String): KeyValue = {
    def UUID: String = java.util.UUID.randomUUID.toString

    val createdKeyValue = KeyValue(UUID, content)

    Seq(createdKeyValue)
      .toDS()
      .write
      .format(DatasetFormat)
      .options(TableOption)
      .mode("append")
      .save()

    createdKeyValue
  }

  def getKeyValue(key: String): KeyValue = {
    val get =
      spark
        .read
        .options(TableOption)
        .cassandraFormat(TableName, Keyspace)
        .load()
        .filter(row => row.getAs[String]("key").equals(key))
        .collectAsList()

    val keyValue: Row = get.get(0)
    KeyValue(keyValue.getAs[String](0), keyValue.getAs[String](1))
  }
}
