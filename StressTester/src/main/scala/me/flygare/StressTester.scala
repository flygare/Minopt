package me.flygare
import com.datastax.spark.connector._

import com.datastax.spark.connector._

object StressTester extends App with SparkConnection{
  println("Hello")

  val rdd = sc.cassandraTable("minopt", "kv")
  println("Cassanda rows: " + rdd.count)
}
