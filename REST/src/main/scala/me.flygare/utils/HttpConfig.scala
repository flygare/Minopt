package me.flygare.utils

import com.typesafe.config.ConfigFactory

trait HttpConfig{
    val port = ConfigFactory.load.getInt("http.port")
    val interface = ConfigFactory.load.getString("http.interface")
}
