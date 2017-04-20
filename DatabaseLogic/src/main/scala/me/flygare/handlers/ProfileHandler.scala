package me.flygare.handlers

import me.flygare.models.{Address, Person, Profile}
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.cassandra._
import org.joda.time.DateTime


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
  def createProfile(firstname: String, lastname: String, phonenumber: String, email: String, username: String, password: String, description: String, website: String, lastip: String, lastlogin: String): Profile = {
    val UUID = java.util.UUID.randomUUID.toString

    val profile = Profile(UUID, firstname, lastname, phonenumber, email, username, password, description, website, lastip, lastlogin)

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
          row.getAs[String]("firstname"), row.getAs[String]("lastname"), row.getAs[String]("phonenumber"), row.getAs[String]("email"), row.getAs[String]("username"),
          row.getAs[String]("password"), row.getAs[String]("description"), row.getAs[String]("website"), row.getAs[String]("lastip"), row.getAs[String]("lastlogin")
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
          row.getAs[String]("firstname"), row.getAs[String]("lastname"), row.getAs[String]("phonenumber"), row.getAs[String]("email"), row.getAs[String]("username"),
          row.getAs[String]("password"), row.getAs[String]("description"), row.getAs[String]("website"), row.getAs[String]("lastip"), row.getAs[String]("lastlogin")
        ))
        .collect()

    profiles
  }
}
