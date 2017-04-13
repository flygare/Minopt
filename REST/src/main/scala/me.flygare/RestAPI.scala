package me.flygare

import colossus._
import core._
import service._
import protocols.http._
import UrlParsing._
import HttpMethod._
import akka.actor.ActorSystem

class HelloService(context: ServerContext) extends HttpService(context) {
  def handle = {
    case request @ Get on Root / "hello" => {
      Callback.successful(request.ok("Hello World!"))
    }
  }
}

class HelloInitializer(worker: WorkerRef) extends Initializer(worker) {

  def onConnect = context => new HelloService(context)

}

object RestAPI extends App {

  implicit val actorSystem = ActorSystem()
  implicit val io = IOSystem()

  Server.start("hello-world", 9000){ worker => new HelloInitializer(worker) }

}
