package me.flygare

import akka.http.scaladsl.Http
import me.flygare.routes.MainRouter
import me.flygare.traits.SparkConnection
import me.flygare.utils.{HttpConfig, HttpConnection}

object ProfileService extends App with SparkConnection with HttpConnection with HttpConfig {
  Http().bindAndHandle(MainRouter.routes, "localhost", port)

  println(s"Profile service started at $port")
}
