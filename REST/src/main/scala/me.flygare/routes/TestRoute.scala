package me.flygare.routes

import akka.http.scaladsl.server.Directives.{complete, get, path}
import akka.http.scaladsl.server.Directives._

object TestRoute {
  val route =
    path("rammus") {
      get {
        complete("Ok")
      }
    }
}
