package me.flygare.routes

import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.server.Directives._
import me.flygare.utils.HttpConnection
import me.flygare.handlers._
import me.flygare.models._
import me.flygare.utils.JsonSupport._

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

object MainRouter extends HttpConnection {
  val profileHandler = new ProfileHandler

  val routes =
    respondWithDefaultHeaders(RawHeader("Access-Control-Allow-Origin", "*"), RawHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE"), RawHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept")) {
      pathPrefix("dblogic") {
        path("profiles") {
          get {
            parameters('rows) {
              (rows) =>
                complete(profileHandler.getProfiles(rows.toInt))
            }
          } ~
            post {
              entity(as[Profile]) {
                profile => {
                  profileHandler.createProfile(profile)
                  complete(s"The profile you sent were: $profile")
                }
              }
            }
        }
      }
    }
}
