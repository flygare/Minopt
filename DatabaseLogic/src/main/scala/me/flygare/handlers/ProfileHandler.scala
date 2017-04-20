package me.flygare.handlers

import me.flygare.models.{Address, Profile, Person}
import org.apache.spark.sql.{Row, SparkSession}
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
  def createProfile(firstName: String, lastName: String, phonenumber: String, email: String, username: String, password: String, description: String, website: String, lastIp: String, lastLogin: String): Profile = {
    val UUID = java.util.UUID.randomUUID.toString

    val profile = Profile(UUID, firstName, lastName, phonenumber, email, username, password, description, website, lastIp, lastLogin)

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
  def getProfile(key: String): Profile = {
    val profile =
      spark
        .read
        .options(TableOption)
        .cassandraFormat(TableName, Keyspace)
        .load()
        .filter(row => row.getAs[String]("key").equals(key))
        .map(row => Profile(
          row.getAs[String]("key"),
          row.getAs[String]("first_name"), row.getAs[String]("last_name"), row.getAs[String]("phonenumber"), row.getAs[String]("email"), row.getAs[String]("username"),
          row.getAs[String]("password"), row.getAs[String]("description"), row.getAs[String]("website"), row.getAs[String]("last_ip"), row.getAs[String]("last_login")
        ))
        .collect()

    profile(0)
  }

  def getProfiles: Array[Profile] = {
    val profiles =
      spark
        .read
        .options(TableOption)
        .cassandraFormat(TableName, Keyspace)
        .load()
        .map(row => Profile(
          row.getAs[String]("key"),
          row.getAs[String]("first_name"), row.getAs[String]("last_name"), row.getAs[String]("phonenumber"), row.getAs[String]("email"), row.getAs[String]("username"),
          row.getAs[String]("password"), row.getAs[String]("description"), row.getAs[String]("website"), row.getAs[String]("last_ip"), row.getAs[String]("last_login")
        ))
        .collect()

    profiles
  }
}
