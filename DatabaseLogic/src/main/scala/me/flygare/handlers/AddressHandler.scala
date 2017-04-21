package me.flygare.handlers

import me.flygare.models.AddressDB
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.cassandra._

class AddressHandler {
  private val spark = SparkSession.builder.getOrCreate()

  import spark.implicits._

  private val Keyspace = "minopt"
  private val TableName = "address"
  private val TableOption = Map("table" -> TableName, "keyspace" -> Keyspace)
  private val DatasetFormat = "org.apache.spark.sql.cassandra"

  /*
   * CREATE
   */
  def createAddress(street: String, zipcode: String, city: String, county: String, country: String): AddressDB = {
    val UUID = java.util.UUID.randomUUID.toString

    val address = AddressDB(UUID, street, zipcode, city, county, country)

    Seq(address)
      .toDS()
      .write
      .format(DatasetFormat)
      .options(TableOption)
      .mode("append")
      .save()

    address
  }

  /*
   * GET
   */
  def getAddress(key: String): AddressDB = {
    val address =
      spark
        .read
        .options(TableOption)
        .cassandraFormat(TableName, Keyspace)
        .load()
        .filter(row => row.getAs[String]("key").equals(key))
        .map(row => AddressDB(
          row.getAs[String]("key"),
          row.getAs[String]("street"), row.getAs[String]("zipcode"), row.getAs[String]("city"), row.getAs[String]("county"), row.getAs[String]("country")
        ))
        .collect()

    address(0)
  }

  def getAddresses(rows: Int): Array[AddressDB] = {
    val addresses =
      spark
        .read
        .options(TableOption)
        .cassandraFormat(TableName, Keyspace)
        .load()
        .limit(rows)
        .map(row => AddressDB(
          row.getAs[String]("key"),
          row.getAs[String]("street"), row.getAs[String]("zipcode"), row.getAs[String]("city"), row.getAs[String]("county"), row.getAs[String]("country")
        ))
        .collect()

    addresses
  }

  def getAllAddresses: Array[AddressDB] = {
    val addresses =
      spark
        .read
        .options(TableOption)
        .cassandraFormat(TableName, Keyspace)
        .load()
        .map(row => AddressDB(
          row.getAs[String]("key"),
          row.getAs[String]("street"), row.getAs[String]("zipcode"), row.getAs[String]("city"), row.getAs[String]("county"), row.getAs[String]("country")
        ))
        .collect()

    addresses
  }

  /*
   * DELETE
   */
  def deleteAddresses(): Unit = {
    spark.sql(s"TRUNCATE $Keyspace.$TableName;")
  }
}