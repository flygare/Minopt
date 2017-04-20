package me.flygare.routes

import akka.http.scaladsl.server.Directives._
import me.flygare.utils.HttpConnection

object MainRouter extends HttpConnection{
  val routes =
    pathPrefix("dblogic") {
      delete {
        // TODO truncate cassandra
        complete("Cassandra truncated")
      }
      path("persons"){
        get {
          parameters('rows) {
            (rows) =>
              complete("cassandra happy")
          }
        }~
          post {
            entity(as[String]){
              data => complete(s"The data you sent were: $data")
            }
          }
      }~
        path("addresses"){
          get {
            parameters('rows) {
              (rows) =>
                complete("cassandra happy")
            }
          }~
            post {
              entity(as[String]){
                data => complete(s"The data you sent were: $data")
              }
            }
        }~
        path("profiles"){
          get {
            parameters('rows) {
              (rows) =>
                complete("cassandra happy")
            }
          }~
            post {
              entity(as[String]){
                data => complete(s"The data you sent were: $data")
              }
            }
        }
    }
}
