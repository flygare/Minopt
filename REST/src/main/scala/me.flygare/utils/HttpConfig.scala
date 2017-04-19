package me.flygare.utils

import java.io.File

import com.typesafe.config.ConfigFactory

trait HttpConfig{
  val parsedConfig = ConfigFactory.parseFile(new File("src/main/resources/application.conf"))
  val conf = ConfigFactory.load(parsedConfig)

  val port = conf.getInt("http.local.port")
  val interface = conf.getString("http.local.interface")
  val remoteHost = conf.getString("http.remote.interface")
}
