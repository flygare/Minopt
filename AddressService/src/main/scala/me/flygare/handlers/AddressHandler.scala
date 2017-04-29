package me.flygare.handlers

import me.flygare.models.{Address, AddressDB}
import me.flygare.utils.Encryption
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.cassandra._

class AddressHandler extends Serializable{

  private val spark = SparkSession.builder.getOrCreate()

  import spark.implicits._

  private val Keyspace = "minopt"
  private val EncryptionKey = "minopt"
  private val TableName = "address"
  private val TableOption = Map("table" -> TableName, "keyspace" -> Keyspace)
  private val DatasetFormat = "org.apache.spark.sql.cassandra"

  /*
   * CREATE
   */
  def createAddress(street: String, zipcode: String, city: String, county: String, country: String): AddressDB = {
    val UUID = java.util.UUID.randomUUID.toString

    val address = AddressDB(UUID, Encryption.encrypt(EncryptionKey, street), Encryption.encrypt(EncryptionKey, zipcode), Encryption.encrypt(EncryptionKey, city), Encryption.encrypt(EncryptionKey, county), Encryption.encrypt(EncryptionKey, country))

    Seq(address)
      .toDS()
      .write
      .format(DatasetFormat)
      .options(TableOption)
      .mode("append")
      .save()

    address
  }

  def createAddress(address: Address): AddressDB = {
    val UUID = java.util.UUID.randomUUID.toString

    val addressDB = AddressDB(UUID, Encryption.encrypt(EncryptionKey, address.street), Encryption.encrypt(EncryptionKey, address.zipcode), Encryption.encrypt(EncryptionKey, address.city), Encryption.encrypt(EncryptionKey, address.county), Encryption.encrypt(EncryptionKey, address.country))

    Seq(addressDB)
      .toDS()
      .write
      .format(DatasetFormat)
      .options(TableOption)
      .mode("append")
      .save()

    addressDB
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
          Encryption.decrypt(EncryptionKey, row.getAs[String]("street")), Encryption.decrypt(EncryptionKey, row.getAs[String]("zipcode")), row.getAs[String]("city"), Encryption.decrypt(EncryptionKey, row.getAs[String]("county")), Encryption.decrypt(EncryptionKey, row.getAs[String]("country"))
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
          Encryption.decrypt(EncryptionKey, row.getAs[String]("street")), Encryption.decrypt(EncryptionKey, row.getAs[String]("zipcode")), row.getAs[String]("city"), Encryption.decrypt(EncryptionKey, row.getAs[String]("county")), Encryption.decrypt(EncryptionKey, row.getAs[String]("country"))
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
          Encryption.decrypt(EncryptionKey, row.getAs[String]("street")), Encryption.decrypt(EncryptionKey, row.getAs[String]("zipcode")), row.getAs[String]("city"), Encryption.decrypt(EncryptionKey, row.getAs[String]("county")), Encryption.decrypt(EncryptionKey, row.getAs[String]("country"))
        ))
        .collect()

    addresses
  }
}