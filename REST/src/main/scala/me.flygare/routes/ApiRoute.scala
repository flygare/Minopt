package me.flygare.routes

import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpEntity, HttpRequest, MediaTypes}
import akka.http.scaladsl.server.Directives._
import me.flygare.utils.{HttpConfig, HttpConnection}
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model.headers.RawHeader

object ApiRoute extends HttpConnection with HttpConfig {
  val route =
    pathPrefix("api") {
      delete {
        complete(Http().singleRequest(HttpRequest(DELETE, uri = s"$remoteHost:$remotePort$remotePath")))
      }
      path("persons") {
        get {
          parameters('rows) {
            (rows) =>
              complete(Http().singleRequest(HttpRequest(uri = s"$remoteHost:$remotePort$remotePath/persons?rows=$rows")))
          }
        } ~
          post {
            entity(as[String]) {
              data => complete(Http().singleRequest(HttpRequest(POST, uri = s"$remoteHost:$remotePort$remotePath/persons", entity = HttpEntity(MediaTypes.`application/json`, data))))
            }
          }
      } ~
        path("addresses") {
          get {
            parameters('rows) {
              (rows) =>
                complete(Http().singleRequest(HttpRequest(uri = s"$remoteHost:$remotePort$remotePath/addresses?rows=$rows")))
            }
          } ~
            post {
              entity(as[String]) {
                data => complete(Http().singleRequest(HttpRequest(POST, uri = s"$remoteHost:$remotePort$remotePath/addresses", entity = HttpEntity(MediaTypes.`application/json`, data))))
              }
            }
        } ~
        path("profiles") {
          get {
            parameters('rows) {
              (rows) =>
                complete(Http().singleRequest(HttpRequest(uri = s"$remoteHost:$remotePort$remotePath/profiles?rows=$rows")))
            }
          } ~
            post {
              entity(as[String]) {
                data => complete(Http().singleRequest(HttpRequest(POST, uri = s"$remoteHost:$remotePort$remotePath/profiles", entity = HttpEntity(MediaTypes.`application/json`, data))))
              }
            }
        }
    }
}
