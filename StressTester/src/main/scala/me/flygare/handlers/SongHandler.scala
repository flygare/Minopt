package me.flygare.handlers

import me.flygare.models.Song
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.cassandra._

class SongHandler {
  val spark: SparkSession = SparkSession.builder.getOrCreate()

  import spark.implicits._

  val TableName = "song"
  val Keyspace = "minopt"
  val TableOption = Map("table" -> TableName, "keyspace" -> Keyspace)

  val DatasetFormat: String = "org.apache.spark.sql.cassandra"


  def createSong(title: String, artist: String): Song = {
    def UUID: String = java.util.UUID.randomUUID.toString

    val createdSong = Song(UUID, title, artist)

    Seq(createdSong)
      .toDS()
      .write
      .format(DatasetFormat)
      .options(TableOption)
      .mode("append")
      .save()

    createdSong
  }

  def getSong(key: String): Song = {
    val dbObj =
      spark
        .read
        .options(TableOption)
        .cassandraFormat(TableName, Keyspace)
        .load()
        .filter(row => row.getAs[String]("key").equals(key))
        .collectAsList()

    val song: Row = dbObj.get(0)
    Song(song.getAs[String](0), song.getAs[String](1), song.getAs[String](2))
  }
}
