package me.flygare.routes

import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpEntity, HttpRequest, MediaTypes}
import akka.http.scaladsl.server.Directives._
import me.flygare.utils.{HttpConfig, HttpConnection}
import akka.http.scaladsl.model.HttpMethods._

object ApiRoute extends HttpConnection with HttpConfig {
  val route =
    pathPrefix("api") {
      path("persons") {
        get {
          parameters('rows) {
            (rows) =>
              complete(Http().singleRequest(HttpRequest(uri = s"$remotePersonHost:$remotePersonPort$remotePath/persons?rows=$rows")))
          }
        } ~
          post {
            entity(as[String]) {
              data => complete(Http().singleRequest(HttpRequest(POST, uri = s"$remotePersonHost:$remotePersonPort$remotePath/persons", entity = HttpEntity(MediaTypes.`application/json`, data))))
            }
          } ~
        delete {
          complete(Http().singleRequest(HttpRequest(DELETE, uri = s"$remotePersonHost:$remotePersonPort$remotePath")))
        }
      } ~
        path("addresses") {
          get {
            parameters('rows) {
              (rows) =>
                complete(Http().singleRequest(HttpRequest(uri = s"$remoteAddressHost:$remoteAddressPort$remotePath/addresses?rows=$rows")))
            }
          } ~
            post {
              entity(as[String]) {
                data => complete(Http().singleRequest(HttpRequest(POST, uri = s"$remoteAddressHost:$remoteAddressPort$remotePath/addresses", entity = HttpEntity(MediaTypes.`application/json`, data))))
              }
            } ~
          delete {
            complete(Http().singleRequest(HttpRequest(DELETE, uri = s"$remoteAddressHost:$remoteAddressPort$remotePath")))
          }
        } ~
        path("profiles") {
          get {
            parameters('rows) {
              (rows) =>
                complete(Http().singleRequest(HttpRequest(uri = s"$remoteProfileHost:$remoteProfilePort$remotePath/profiles?rows=$rows")))
            }
          } ~
            post {
              entity(as[String]) {
                data => complete(Http().singleRequest(HttpRequest(POST, uri = s"$remoteProfileHost:$remoteProfilePort$remotePath/profiles", entity = HttpEntity(MediaTypes.`application/json`, data))))
              }
            } ~
          delete {
            complete(Http().singleRequest(HttpRequest(DELETE, uri = s"$remoteProfileHost:$remoteProfilePort$remotePath")))
          }
        }
    }
}
