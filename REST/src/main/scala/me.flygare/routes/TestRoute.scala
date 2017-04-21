package me.flygare.routes

import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.server.Directives.{complete, get, path}
import akka.http.scaladsl.server.Directives._
import me.flygare.utils.HttpConnection

object TestRoute extends HttpConnection {
  val route =
    pathPrefix("test") {
      respondWithDefaultHeaders(RawHeader("Access-Control-Allow-Origin", "*"), RawHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE"), RawHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept")) {
        path("ok") {
          complete("Ok")
        } ~
          path("json") {
            complete(Http().singleRequest(HttpRequest(uri = s"http://jsonplaceholder.typicode.com/posts/1")))
          }
      }
    }
}
