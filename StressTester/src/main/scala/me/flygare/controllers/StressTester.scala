package me.flygare.controllers

import me.flygare.handlers.KeyValueHandler
import me.flygare.traits.SparkConnection

object StressTester extends App with SparkConnection {
  val keyValueHandler = new KeyValueHandler

  val kv2 = keyValueHandler.createKVTwo("H", "e")
  val kv5 = keyValueHandler.createKVFive("H", "e", "l", "l", "o")
  val kv10   = keyValueHandler.createKVTen("H", "e", "l", "l", "o", "W", "o", "r", "l", "d")

  println(kv2.toString)
  println("-----------------------------------------------------")
  println(kv5.toString)
  println("-----------------------------------------------------")
  println(kv10.toString)
  println("-----------------------------------------------------")
}
