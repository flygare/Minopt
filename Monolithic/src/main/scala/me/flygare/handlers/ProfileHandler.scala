package me.flygare.handlers

import me.flygare.models.{Profile, ProfileDB}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.cassandra._

class ProfileHandler {
  private val spark = SparkSession.builder.getOrCreate()

  import spark.implicits._

  private val Keyspace = "minopt"
  private val TableName = "profile"
  private val TableOption = Map("table" -> TableName, "keyspace" -> Keyspace)
  private val DatasetFormat = "org.apache.spark.sql.cassandra"

  /*
   * CREATE
   */
  def createProfile(profile: Profile): ProfileDB = {
    val UUID = java.util.UUID.randomUUID.toString

    val profileDB = ProfileDB(UUID, profile.firstname, profile.lastname, profile.phonenumber, profile.email, profile.username, profile.password, profile.description, profile.website, profile.lastip, profile.lastlogin)

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

    val profile = ProfileDB(UUID, firstname, lastname, phonenumber, email, username, password, description, website, lastip, lastlogin)

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
          row.getAs[String]("firstname"), row.getAs[String]("lastname"), row.getAs[String]("phonenumber"), row.getAs[String]("email"), row.getAs[String]("username"),
          row.getAs[String]("password"), row.getAs[String]("description"), row.getAs[String]("website"), row.getAs[String]("lastip"), row.getAs[String]("lastlogin")
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
          row.getAs[String]("firstname"), row.getAs[String]("lastname"), row.getAs[String]("phonenumber"), row.getAs[String]("email"), row.getAs[String]("username"),
          row.getAs[String]("password"), row.getAs[String]("description"), row.getAs[String]("website"), row.getAs[String]("lastip"), row.getAs[String]("lastlogin")
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
          row.getAs[String]("firstname"), row.getAs[String]("lastname"), row.getAs[String]("phonenumber"), row.getAs[String]("email"), row.getAs[String]("username"),
          row.getAs[String]("password"), row.getAs[String]("description"), row.getAs[String]("website"), row.getAs[String]("lastip"), row.getAs[String]("lastlogin")
        ))
        .collect()

    profiles
  }
}
