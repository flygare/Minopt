package me.flygare.handlers

import me.flygare.models.{Person, PersonDB}
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
  def createPerson(person: Person): PersonDB = {
    val UUID = java.util.UUID.randomUUID.toString

    val personDB = PersonDB(UUID, person.firstname, person.lastname)

    Seq(personDB)
    .toDS()
    .write
    .format(DatasetFormat)
    .options(TableOption)
    .mode("append")
    .save()

    personDB
  }
  def createPerson(firstname: String, lastname: String): PersonDB = {
    val UUID = java.util.UUID.randomUUID.toString

    val person = PersonDB(UUID, firstname, lastname)

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
  def getPerson(key: String): PersonDB = {
    val person =
      spark
        .read
        .options(TableOption)
        .cassandraFormat(TableName, Keyspace)
        .load()
        .filter(row => row.getAs[String]("key").equals(key))
        .map(row => PersonDB(row.getAs[String]("key"), row.getAs[String]("firstname"), row.getAs[String]("lastname")))
        .collect()

    person(0)
  }

  def getPersons(rows: Int): Array[PersonDB] = {
    val persons =
      spark
        .read
        .options(TableOption)
        .cassandraFormat(TableName, Keyspace)
        .load()
        .limit(rows)
        .map(row => PersonDB(row.getAs[String]("key"), row.getAs[String]("firstname"), row.getAs[String]("lastname")))
        .collect()

    persons
  }

  def getAllPersons: Array[PersonDB] = {
    val persons =
      spark
        .read
        .options(TableOption)
        .cassandraFormat(TableName, Keyspace)
        .load()
        .map(row => PersonDB(row.getAs[String]("key"), row.getAs[String]("firstname"), row.getAs[String]("lastname")))
        .collect()

    persons
  }

  /*
  * DELETE
  */
  def deletePersons(): Unit = {
    spark.sql(s"TRUNCATE $Keyspace.$TableName;")
  }
}
