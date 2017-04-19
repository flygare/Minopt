package me.flygare.utils

import java.io.File

import com.typesafe.config.ConfigFactory

trait HttpConfig{
  val parsedConfig = ConfigFactory.parseFile(new File("src/main/resources/application.conf"))

  val port = parsedConfig.getInt("http.local.port")
  val interface = parsedConfig.getString("http.local.interface")
  val remoteHost = parsedConfig.getString("http.remote.interface")
}
