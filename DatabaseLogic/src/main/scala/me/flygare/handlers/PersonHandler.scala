package me.flygare.handlers

import me.flygare.models.Person
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.cassandra._

class PersonHandler {
  private val spark = SparkSession.builder.getOrCreate()

  import spark.implicits._

  private val Keyspace = "minopt"
  private val TableName = "person"
  private val TableOption = Map("table" -> TableName, "keyspace" -> Keyspace)
  private val DatasetFormat = "org.apache.spark.sql.cassandra"

  /*
  * CREATE
  */
  def createPerson(firstname: String, lastname: String): Person = {
    val UUID = java.util.UUID.randomUUID.toString

    val person = Person(UUID, firstname, lastname)

    Seq(person)
      .toDS()
      .write
      .format(DatasetFormat)
      .options(TableOption)
      .mode("append")
      .save()

    person
  }

  /*
   * GET
   */
  def getPerson(key: String): Person = {
    val person =
      spark
        .read
        .options(TableOption)
        .cassandraFormat(TableName, Keyspace)
        .load()
        .filter(row => row.getAs[String]("key").equals(key))
        .map(row => Person(row.getAs[String]("key"), row.getAs[String]("firstname"), row.getAs[String]("lastname")))
        .collect()

    person(0)
  }

  def getPersons: Array[Person] = {
    val persons =
      spark
        .read
        .options(TableOption)
        .cassandraFormat(TableName, Keyspace)
        .load()
        .map(row => Person(row.getAs[String]("key"), row.getAs[String]("firstname"), row.getAs[String]("lastname")))
        .collect()

    persons
  }
}
