package me.flygare
import com.datastax.spark.connector._

object StressTester extends App with SparkConnection{
  print("Hello")

  val rdd = Seq("a", "b", "c")
  println(rdd.length)
}
