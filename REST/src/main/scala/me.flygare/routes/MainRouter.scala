package me.flygare.routes

import akka.http.scaladsl.server.Directives._

object MainRouter {
  val routes = ApiRoute.route~TestRoute.route
}
