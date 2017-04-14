package me.flygare.controllers

import me.flygare.handlers.{KeyValueHandler, SongHandler}
import me.flygare.traits.SparkConnection

object StressTester extends App with SparkConnection{
  val keyValueHandler = new KeyValueHandler
  val songHandler = new SongHandler

  val kv = keyValueHandler.createKeyValue("Hello World")

  println(kv.toString)
  println("-----------------------------------------------------")
  println(keyValueHandler.getKeyValue(kv.key).toString)

  val s = songHandler.createSong("I want your food.", "Lenny Birdy")

  println(s.toString)
  println("-----------------------------------------------------")
  println(songHandler.getSong(s.key))
}
