package me.flygare

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest

object RestAPI extends App{

  val port = 3000

  implicit val actorSystem = ActorSystem("system")
  implicit val actorMaterializer = ActorMaterializer()

  Http().bindAndHandle(MainRouter.routes,"localhost",port)
  println(s"Server started at $port")
}
