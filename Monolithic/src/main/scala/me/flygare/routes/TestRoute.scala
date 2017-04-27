package me.flygare.routes

import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import me.flygare.utils.HttpConnection
import akka.http.scaladsl.server.Directives._

object TestRoute extends HttpConnection {
  val route =
    pathPrefix("test") {
      path("ok") {
        complete("Ok")
      } ~
        path("json") {
          complete(Http().singleRequest(HttpRequest(uri = s"http://jsonplaceholder.typicode.com/posts/1")))
        }
    }
}
