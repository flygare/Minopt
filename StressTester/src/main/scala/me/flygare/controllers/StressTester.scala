package me.flygare.controllers

import me.flygare.handlers.KeyValueHandler
import me.flygare.traits.SparkConnection

object StressTester extends App with SparkConnection{
  val keyValueHandler = new KeyValueHandler;

  val kv = keyValueHandler.createKeyValue("Hello World")

  println(kv.toString)
  println("-----------------------------------------------------")
  println(keyValueHandler.getKeyValue(kv.key).toString)
}
