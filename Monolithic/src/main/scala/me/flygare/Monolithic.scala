package me.flygare

import akka.http.scaladsl.Http
import me.flygare.routes.MainRouter
import me.flygare.traits.SparkConnection
import me.flygare.utils.{HttpConfig, HttpConnection}

object Monolithic extends App with SparkConnection with HttpConnection with HttpConfig {

  Http().bindAndHandle(MainRouter.routes, s"$interface", port)

  println(s"Server started at $port")
}
