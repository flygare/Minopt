package me.flygare.utils

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

trait HttpConnection extends HttpConfig{
  implicit val actorSystem = ActorSystem("system")
  implicit val actorMaterializer = ActorMaterializer()

}
