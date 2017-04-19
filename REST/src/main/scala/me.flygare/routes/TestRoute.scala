package me.flygare.routes

import akka.http.scaladsl.server.Directives.{complete, get, path}

object TestRoute {
  val route =
    path("rammus") {
      get {
        complete("Ok")
      }
    }
}
