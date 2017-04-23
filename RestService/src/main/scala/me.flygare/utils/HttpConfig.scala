package me.flygare.utils

import java.io.File
import com.typesafe.config.ConfigFactory

trait HttpConfig{
  val parsedConfig = ConfigFactory.parseFile(new File("RestService/src/main/resources/application.conf"))
  val conf = ConfigFactory.load(parsedConfig)

  val interface = conf.getString("http.local.interface")
  val port = conf.getInt("http.local.port")

  val remoteHost = conf.getString("http.remote.interface")
  val remoteAddressPort = conf.getInt("http.remote.addressPort")
  val remotePersonPort = conf.getInt("http.remote.personPort")
  val remoteProfilePort = conf.getInt("http.remote.profilePort")
  val remotePath = conf.getString("http.remote.path")
}
