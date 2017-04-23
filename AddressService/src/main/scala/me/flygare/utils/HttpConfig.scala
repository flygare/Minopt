package me.flygare.utils

import java.io.File

import com.typesafe.config.ConfigFactory

trait HttpConfig{
  val parsedConfig = ConfigFactory.parseFile(new File("AddressService/src/main/resources/application.conf"))
  val httpconf = ConfigFactory.load(parsedConfig)

  val interface = httpconf.getString("http.local.interface")
  val port = httpconf.getInt("http.local.port")

}
