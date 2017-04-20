package me.flygare.handlers

import me.flygare.models.{Address, Person}
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
  def createAddress(street: String, zipCode: Int, city: String, county: String, country: String): Address = {
    val UUID = java.util.UUID.randomUUID.toString

    val address = Address(UUID, street, zipCode, city, county, country)

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
  def getAddress(key: String): Address = {
    val address =
      spark
        .read
        .options(TableOption)
        .cassandraFormat(TableName, Keyspace)
        .load()
        .filter(row => row.getAs[String]("key").equals(key))
        .map(row => Address(
          row.getAs[String]("key"),
          row.getAs[String]("street"), row.getAs[Int]("zip_code"), row.getAs[String]("city"), row.getAs[String]("county"), row.getAs[String]("country")
        ))
        .collect()

    address(0)
  }

  def getAddresses: Array[Address] = {
    val addresses =
      spark
        .read
        .options(TableOption)
        .cassandraFormat(TableName, Keyspace)
        .load()
        .map(row => Address(
          row.getAs[String]("key"),
          row.getAs[String]("street"), row.getAs[Int]("zip_code"), row.getAs[String]("city"), row.getAs[String]("county"), row.getAs[String]("country")
        ))
        .collect()

    addresses
  }
}