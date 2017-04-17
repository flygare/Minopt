package me.flygare.handlers

import me.flygare.models.{KeyValueFive, KeyValueTen, KeyValueTwo}
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.cassandra._


class KeyValueHandler {
  val spark = SparkSession.builder.getOrCreate()
  import spark.implicits._

  val Keyspace = "minopt"
  val DatasetFormat = "org.apache.spark.sql.cassandra"

  /*
   * CREATE
   */
  val TableNameTwo = "key_value_two"
  val TableOptionTwo = Map("table" -> TableNameTwo, "keyspace" -> Keyspace)

  def createKVTwo(col1: String, col2: String): KeyValueTwo = {
    val UUID = java.util.UUID.randomUUID.toString

    val createdKV = KeyValueTwo(UUID, col1, col2)

    Seq(createdKV)
      .toDS()
      .write
      .format(DatasetFormat)
      .options(TableOptionTwo)
      .mode("append")
      .save()

    createdKV
  }

  val TableNameFive = "key_value_five"
  val TableOptionFive = Map("table" -> TableNameFive, "keyspace" -> Keyspace)

  def createKVFive(col1: String, col2: String, col3: String, col4: String, col5: String): KeyValueFive = {
    val UUID = java.util.UUID.randomUUID.toString

    val createdKV = KeyValueFive(UUID, col1, col2, col3, col4, col5)

    Seq(createdKV)
      .toDS()
      .write
      .format(DatasetFormat)
      .options(TableOptionFive)
      .mode("append")
      .save()

    createdKV
  }

  val TableNameTen = "key_value_ten"
  val TableOptionTen = Map("table" -> TableNameTen, "keyspace" -> Keyspace)

  def createKVTen(col1: String, col2: String, col3: String, col4: String, col5: String,
                  col6: String, col7: String, col8: String, col9: String, col10: String): KeyValueTen = {
    val UUID = java.util.UUID.randomUUID.toString

    val createdKV = KeyValueTen(UUID, col1, col2, col3, col4, col5, col6, col7, col8, col9, col10)

    Seq(createdKV)
      .toDS()
      .write
      .format(DatasetFormat)
      .options(TableOptionTen)
      .mode("append")
      .save()

    createdKV
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
