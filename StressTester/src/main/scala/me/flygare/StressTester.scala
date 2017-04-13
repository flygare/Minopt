package me.flygare

import com.datastax.spark.connector._

object StressTester extends App with SparkConnection{
  print("Hello")
  val rdd = sc.cassandraTable("test", "kv")
  println(rdd.count)
}
