package me.flygare

import akka.http.scaladsl.Http
import me.flygare.utils.{HttpConfig, HttpConnection}

object RestAPI extends App with HttpConnection with HttpConfig{

  Http().bindAndHandle(MainRouter.routes,"localhost",port)
  println(s"Server started at $port")


}
