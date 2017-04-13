package me.flygare

object StressTester extends App with SparkConnection{
  print("Hello")
  session.execute("INSERT INTO test.kv ")
  val rdd = sc.cassandraTable("test", "kv")
  println(rdd.count)
}
