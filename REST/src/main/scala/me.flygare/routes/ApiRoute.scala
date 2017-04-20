package me.flygare.routes

import akka.http.scaladsl.server.Directives.{complete, get, path}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.server.Directives._
import me.flygare.utils.HttpConnection

object ApiRoute extends HttpConnection{
 val route =
  pathPrefix("api") {
    path("person"){
      get {
        // Send request to get all data of persons
        complete(Http().singleRequest(HttpRequest(uri = s"$remoteHost:$remotePort/dblogic/person")))
      }~
      post {
        entity(as[String]){
          data => complete(s"The data you sent were: $data")
        }
      }
    }~
    path("address"){
      get {
        // Send request to get all data of adresses
        complete(Http().singleRequest(HttpRequest(uri = s"$remoteHost:$remotePort/dblogic/address")))
      }~
        post {
          entity(as[String]){
            data => complete(s"The data you sent were: $data")
          }
        }
    }~
    path("profile"){
      get {
        // Send request to get all data of profiles
        complete(Http().singleRequest(HttpRequest(uri = s"$remoteHost:$remotePort/dblogic/profile")))
      }~
        post {
          entity(as[String]){
            data => complete(s"The data you sent were: $data")
          }
        }
    }
  }
}
