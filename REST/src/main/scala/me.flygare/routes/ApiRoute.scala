package me.flygare.routes

import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.server.Directives.{complete, get, path}

object ApiRoute {
 val route =
  path("api") {
   get {
    complete{
     val json = Http().singleRequest(HttpRequest(uri = "http://jsonplaceholder.typicode.com/posts/1"))

     json
    }
   }
  }
}
