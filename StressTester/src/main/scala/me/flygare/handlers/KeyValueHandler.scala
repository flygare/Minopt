package me.flygare.handlers

import me.flygare.models.KeyValue
import me.flygare.traits.SparkConnection
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.cassandra._


class KeyValueHandler {
  val spark = SparkSession.builder.getOrCreate()
  import spark.implicits._

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
    val dbObj =
      spark
        .read
        .options(TableOption)
        .cassandraFormat(TableName, Keyspace)
        .load()
        .filter(row => row.getAs[String]("key").equals(key))
        .collectAsList()

    val keyValue: Row = dbObj.get(0)
    KeyValue(keyValue.getAs[String](0), keyValue.getAs[String](1))
  }
}
