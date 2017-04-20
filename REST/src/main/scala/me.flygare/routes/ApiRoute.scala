package me.flygare.routes

import akka.http.scaladsl.server.Directives.{complete, get, path}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.server.Directives._
import me.flygare.utils.HttpConnection

object ApiRoute extends HttpConnection{
 val route =
  pathPrefix("api") {
    pathPrefix("kv") {
      path("2"){
        get {
          // Send request to get all data of 2 columns
          complete("2 columns")
        }~
        post {
          entity(as[String]){
            data => complete(s"The data you sent were: $data")
          }
        }
      }~
      path("5"){
        // Send request to get all data of  5 columns
        complete("5 columns")
      }~
      path("10"){
        // Send request to get all data of 10 columns
        complete("10 columns")
      }
    }
  }
}
