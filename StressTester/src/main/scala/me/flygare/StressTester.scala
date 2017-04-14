package me.flygare

object StressTester extends App with SparkConnection{
  val keyValueHandler = new KeyValueHandler;

  val kv = keyValueHandler.createKeyValue("Hello World")

  println(kv.toString)
  println("-----------------------------------------------------")
  println(keyValueHandler.getKeyValue(kv.key).toString)
}
