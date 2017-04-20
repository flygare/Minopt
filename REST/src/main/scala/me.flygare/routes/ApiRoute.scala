package me.flygare.routes

import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.server.Directives._
import me.flygare.utils.HttpConnection

object ApiRoute extends HttpConnection{
 val route =
  pathPrefix("api") {
    path("persons"){
      get {
        parameters('properties) {
          (properties) =>
            complete(Http().singleRequest(HttpRequest(uri = s"$remoteHost:$remotePort/dblogic/persons/properties=$properties")))
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
        parameters('properties) {
          (properties) =>
            complete(Http().singleRequest(HttpRequest(uri = s"$remoteHost:$remotePort/dblogic/addresses?properties=$properties")))
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
        parameters('properties) {
          (properties) =>
            complete(Http().singleRequest(HttpRequest(uri = s"$remoteHost:$remotePort/dblogic/profiles?properties=$properties")))
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
