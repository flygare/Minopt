package me.flygare.handlers

import me.flygare.models.{KeyValueFive, KeyValueTen, KeyValueTwo}
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.cassandra._


class KeyValueHandler {
  val spark = SparkSession.builder.getOrCreate()

  import spark.implicits._

  val Keyspace = "minopt"
  val DatasetFormat = "org.apache.spark.sql.cassandra"

  val TableNameTwo = "key_value_two"
  val TableOptionTwo = Map("table" -> TableNameTwo, "keyspace" -> Keyspace)

  val TableNameFive = "key_value_five"
  val TableOptionFive = Map("table" -> TableNameFive, "keyspace" -> Keyspace)

  val TableNameTen = "key_value_ten"
  val TableOptionTen = Map("table" -> TableNameTen, "keyspace" -> Keyspace)

  /*
   * CREATE
   */
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

  def getKVTwo(key: String): KeyValueTwo = {
    val dbObj =
      spark
        .read
        .options(TableOptionTwo)
        .cassandraFormat(TableNameTwo, Keyspace)
        .load()
        .filter(row => row.getAs[String]("key").equals(key))
        .map(row => KeyValueTwo(row.getAs[String]("key"), row.getAs[String]("col1"), row.getAs[String]("col2")))
        .collect()

    dbObj(0)
  }

  def getAllKVTwo: Array[KeyValueTwo] = {
    val dbObj =
      spark
        .read
        .options(TableOptionTwo)
        .cassandraFormat(TableNameTwo, Keyspace)
        .load()
        .map(row => KeyValueTwo(row.getAs[String]("key"), row.getAs[String]("col1"), row.getAs[String]("col2")))
        .collect()

    dbObj
  }

  def getKVFive(key: String): KeyValueFive = {
    val dbObj =
      spark
        .read
        .options(TableOptionFive)
        .cassandraFormat(TableNameFive, Keyspace)
        .load()
        .filter(row => row.getAs[String]("key").equals(key))
        .map(row => KeyValueFive(
          row.getAs[String]("key"),
          row.getAs[String]("col1"), row.getAs[String]("col2"), row.getAs[String]("col3"), row.getAs[String]("col4"), row.getAs[String]("col5")
        ))
        .collect()

    dbObj(0)
  }

  def getAllKVFive: Array[KeyValueFive] = {
    val dbObj =
      spark
        .read
        .options(TableOptionFive)
        .cassandraFormat(TableNameFive, Keyspace)
        .load()
        .map(row => KeyValueFive(
          row.getAs[String]("key"),
          row.getAs[String]("col1"), row.getAs[String]("col2"), row.getAs[String]("col3"), row.getAs[String]("col4"), row.getAs[String]("col5")
        ))
        .collect()

    dbObj
  }

  def getKVTen(key: String): KeyValueTen = {
    val dbObj =
      spark
        .read
        .options(TableOptionTen)
        .cassandraFormat(TableNameTen, Keyspace)
        .load()
        .filter(row => row.getAs[String]("key").equals(key))
        .map(row => KeyValueTen(
          row.getAs[String]("key"),
          row.getAs[String]("col1"), row.getAs[String]("col2"), row.getAs[String]("col3"), row.getAs[String]("col4"), row.getAs[String]("col5"),
          row.getAs[String]("col6"), row.getAs[String]("col7"), row.getAs[String]("col8"), row.getAs[String]("col9"), row.getAs[String]("col10")
        ))
        .collect()

    dbObj(0)
  }

  def getAllKVTen: Array[KeyValueTen] = {
    val dbObj =
      spark
        .read
        .options(TableOptionTen)
        .cassandraFormat(TableNameTen, Keyspace)
        .load()
        .map(row => KeyValueTen(
          row.getAs[String]("key"),
          row.getAs[String]("col1"), row.getAs[String]("col2"), row.getAs[String]("col3"), row.getAs[String]("col4"), row.getAs[String]("col5"),
          row.getAs[String]("col6"), row.getAs[String]("col7"), row.getAs[String]("col8"), row.getAs[String]("col9"), row.getAs[String]("col10")
        ))
        .collect()

    dbObj
  }
}
