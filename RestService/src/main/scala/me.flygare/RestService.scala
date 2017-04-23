package me.flygare

import akka.http.scaladsl.Http
import me.flygare.routes.MainRouter
import me.flygare.utils.{HttpConfig, HttpConnection}

object RestService extends App with HttpConnection with HttpConfig{

  Http().bindAndHandle(MainRouter.routes,"localhost",port)

  println(s"Server started at $port")

}
