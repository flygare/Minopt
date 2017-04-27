package me.flygare.routes

import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.server.Directives._
import me.flygare.utils.HttpConnection
import me.flygare.handlers._
import me.flygare.models._
import me.flygare.utils.JsonSupport._

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

object MainRouter {
  val routes =  {
    respondWithDefaultHeaders(RawHeader("Access-Control-Allow-Origin", "*"), RawHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE"), RawHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept")) {
      ApiRoute.route ~ TestRoute.route
    }
  }
}
