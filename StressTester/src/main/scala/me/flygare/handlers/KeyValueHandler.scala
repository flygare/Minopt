package me.flygare.handlers

import me.flygare.models.KeyValueTwo
import me.flygare.traits.SparkConnection
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.cassandra._


class KeyValueHandler {
  val spark = SparkSession.builder.getOrCreate()
  import spark.implicits._

  val TableNameTwo = "key_value_two"
  val Keyspace = "minopt"
  val TableOptionTwo = Map("table" -> TableNameTwo, "keyspace" -> Keyspace)

  val DatasetFormat = "org.apache.spark.sql.cassandra"

  def createKVTwo(col1: String, col2: String): KeyValueTwo = {
    val UUID = java.util.UUID.randomUUID.toString

    val createdKeyValue = KeyValueTwo(UUID, col1, col2)

    Seq(createdKeyValue)
      .toDS()
      .write
      .format(DatasetFormat)
      .options(TableOptionTwo)
      .mode("append")
      .save()

    createdKeyValue
  }

  def getKVTwo(key: String) = {
    val dbObj =
      spark
        .read
        .options(TableOptionTwo)
        .cassandraFormat(TableNameTwo, Keyspace)
        .load()
        .filter(row => row.getAs[String]("key").equals(key))
        .collectAsList()

    val keyValue = dbObj.get(0)
    KeyValueTwo(keyValue.getAs[String](0), keyValue.getAs[String](1), keyValue.getAs[String](2))
  }
}
