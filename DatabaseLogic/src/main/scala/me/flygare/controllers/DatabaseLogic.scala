package me.flygare.controllers

import akka.http.scaladsl.Http
import me.flygare.routes.MainRouter
import me.flygare.traits.SparkConnection
import me.flygare.utils.{HttpConfig, HttpConnection}

object DatabaseLogic extends App with SparkConnection with HttpConnection with HttpConfig {
  Http().bindAndHandle(MainRouter.routes,"localhost",port)
}
