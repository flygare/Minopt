package me.flygare

import me.flygare.routes.{ApiRoute, TestRoute}
import akka.http.scaladsl.server.Directives._

object MainRouter {
  val routes = ApiRoute.route~TestRoute.route
}
