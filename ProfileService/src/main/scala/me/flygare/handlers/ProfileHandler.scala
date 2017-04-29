package me.flygare.handlers

import me.flygare.models.{Profile, ProfileDB}
import me.flygare.utils.Encryption
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.cassandra._

class ProfileHandler extends Serializable{
  private val spark = SparkSession.builder.getOrCreate()

  import spark.implicits._

  private val Keyspace = "minopt"
  private val EncryptionKey = "minopt"
  private val TableName = "profile"
  private val TableOption = Map("table" -> TableName, "keyspace" -> Keyspace)
  private val DatasetFormat = "org.apache.spark.sql.cassandra"

  /*
   * CREATE
   */
  def createProfile(profile: Profile): ProfileDB = {
    val UUID = java.util.UUID.randomUUID.toString

    val profileDB = ProfileDB(UUID, Encryption.encrypt(EncryptionKey, profile.firstname), Encryption.encrypt(EncryptionKey, profile.lastname), Encryption.encrypt(EncryptionKey, profile.phonenumber), Encryption.encrypt(EncryptionKey, profile.email), Encryption.encrypt(EncryptionKey, profile.username), Encryption.encrypt(EncryptionKey, profile.password), Encryption.encrypt(EncryptionKey, profile.description), Encryption.encrypt(EncryptionKey, profile.website), Encryption.encrypt(EncryptionKey, profile.lastip), Encryption.encrypt(EncryptionKey, profile.lastlogin))

    Seq(profile)
      .toDS()
      .write
      .format(DatasetFormat)
      .options(TableOption)
      .mode("append")
      .save()

    profileDB
  }

  def createProfile(firstname: String, lastname: String, phonenumber: String, email: String, username: String, password: String, description: String, website: String, lastip: String, lastlogin: String): ProfileDB = {
    val UUID = java.util.UUID.randomUUID.toString

    val profile = ProfileDB(UUID, Encryption.encrypt(EncryptionKey, firstname), Encryption.encrypt(EncryptionKey, lastname), Encryption.encrypt(EncryptionKey, phonenumber), Encryption.encrypt(EncryptionKey, email), Encryption.encrypt(EncryptionKey, username), Encryption.encrypt(EncryptionKey, password), Encryption.encrypt(EncryptionKey, description), Encryption.encrypt(EncryptionKey, website), Encryption.encrypt(EncryptionKey, lastip), Encryption.encrypt(EncryptionKey, lastlogin))

    Seq(profile)
      .toDS()
      .write
      .format(DatasetFormat)
      .options(TableOption)
      .mode("append")
      .save()

    profile
  }

  /*
   * GET
   */
  def getProfile(key: String): ProfileDB = {
    val profile =
      spark
        .read
        .options(TableOption)
        .cassandraFormat(TableName, Keyspace)
        .load()
        .filter(row => row.getAs[String]("key").equals(key))
        .map(row => ProfileDB(
          row.getAs[String]("key"),
          Encryption.decrypt(EncryptionKey, row.getAs[String]("firstname")), Encryption.decrypt(EncryptionKey, row.getAs[String]("lastname")), Encryption.decrypt(EncryptionKey, row.getAs[String]("phonenumber")),
          Encryption.decrypt(EncryptionKey, row.getAs[String]("email")), Encryption.decrypt(EncryptionKey, row.getAs[String]("username")),
          Encryption.decrypt(EncryptionKey, row.getAs[String]("password")), Encryption.decrypt(EncryptionKey, row.getAs[String]("description")), Encryption.decrypt(EncryptionKey, row.getAs[String]("website")), Encryption.decrypt(EncryptionKey, row.getAs[String]("lastip")), Encryption.decrypt(EncryptionKey, row.getAs[String]("lastlogin"))
        ))
        .collect()

    profile(0)
  }

  def getProfiles(rows: Int): Array[ProfileDB] = {
    val profiles =
      spark
        .read
        .options(TableOption)
        .cassandraFormat(TableName, Keyspace)
        .load()
        .limit(rows)
        .map(row => ProfileDB(
          row.getAs[String]("key"),
          Encryption.decrypt(EncryptionKey, row.getAs[String]("firstname")), Encryption.decrypt(EncryptionKey, row.getAs[String]("lastname")), Encryption.decrypt(EncryptionKey, row.getAs[String]("phonenumber")),
          Encryption.decrypt(EncryptionKey, row.getAs[String]("email")), Encryption.decrypt(EncryptionKey, row.getAs[String]("username")),
          Encryption.decrypt(EncryptionKey, row.getAs[String]("password")), Encryption.decrypt(EncryptionKey, row.getAs[String]("description")), Encryption.decrypt(EncryptionKey, row.getAs[String]("website")), Encryption.decrypt(EncryptionKey, row.getAs[String]("lastip")), Encryption.decrypt(EncryptionKey, row.getAs[String]("lastlogin"))
        ))
        .collect()

    profiles
  }

  def getAllProfiles: Array[ProfileDB] = {
    val profiles =
      spark
        .read
        .options(TableOption)
        .cassandraFormat(TableName, Keyspace)
        .load()
        .map(row => ProfileDB(
          row.getAs[String]("key"),
          Encryption.decrypt(EncryptionKey, row.getAs[String]("firstname")), Encryption.decrypt(EncryptionKey, row.getAs[String]("lastname")), Encryption.decrypt(EncryptionKey, row.getAs[String]("phonenumber")),
          Encryption.decrypt(EncryptionKey, row.getAs[String]("email")), Encryption.decrypt(EncryptionKey, row.getAs[String]("username")),
          Encryption.decrypt(EncryptionKey, row.getAs[String]("password")), Encryption.decrypt(EncryptionKey, row.getAs[String]("description")), Encryption.decrypt(EncryptionKey, row.getAs[String]("website")), Encryption.decrypt(EncryptionKey, row.getAs[String]("lastip")), Encryption.decrypt(EncryptionKey, row.getAs[String]("lastlogin"))
        ))
        .collect()

    profiles
  }
}
