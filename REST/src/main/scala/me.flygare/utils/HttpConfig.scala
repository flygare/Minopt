package me.flygare.utils

import com.typesafe.config.ConfigFactory

trait HttpConfig{
  val port = ConfigFactory.load.getInt("http.local.port")
  val interface = ConfigFactory.load.getString("http.local.interface")
  val remoteHost = ConfigFactory.load.getString("http.remote.interface")
}
