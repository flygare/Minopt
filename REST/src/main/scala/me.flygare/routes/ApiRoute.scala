package me.flygare.routes

import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.server.Directives._
import me.flygare.utils.{HttpConfig, HttpConnection}
import akka.http.scaladsl.model.HttpMethods._

object ApiRoute extends HttpConnection with HttpConfig{
 val route =
  pathPrefix("api") {
    path("persons"){
      get {
        parameters('rows) {
          (rows) =>
            complete(Http().singleRequest(HttpRequest(uri = s"$remoteHost:$remotePort/dblogic/persons?rows=$rows")))
        }
      }~
      post {
        entity(as[String]){
          data => complete(Http().singleRequest(HttpRequest(POST, uri = s"$remoteHost:$remotePort/dblogic/persons", entity = data)))
        }
      }
    }~
    path("addresses"){
      get {
        parameters('rows) {
          (rows) =>
            complete(Http().singleRequest(HttpRequest(uri = s"$remoteHost:$remotePort/dblogic/addresses?rows=$rows")))
        }
      }~
        post {
          entity(as[String]){
            data => complete(Http().singleRequest(HttpRequest(POST, uri = s"$remoteHost:$remotePort/dblogic/addresses", entity = data)))
          }
        }
    }~
    path("profiles"){
      get {
        parameters('rows) {
          (rows) =>
            complete(Http().singleRequest(HttpRequest(uri = s"$remoteHost:$remotePort/dblogic/profiles?rows=$rows")))
        }
      }~
        post {
          entity(as[String]){
            data => complete(Http().singleRequest(HttpRequest(POST, uri = s"$remoteHost:$remotePort/dblogic/profiles", entity = data)))
          }
        }
    }
  }
}
