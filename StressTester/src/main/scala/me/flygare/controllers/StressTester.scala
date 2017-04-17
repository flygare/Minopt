package me.flygare.controllers

import me.flygare.handlers.KeyValueHandler
import me.flygare.traits.SparkConnection

object StressTester extends App with SparkConnection{
  val keyValueHandler = new KeyValueHandler

  val kv = keyValueHandler.createKVTwo("Hello World", "Hello World 2")

  println(kv.toString)
  println("-----------------------------------------------------")
  println(keyValueHandler.getKVTwo(kv.key).toString)
}
