package me.flygare
import akka.http.scaladsl.server.Directives._
import me.flygare.routes._

object MainRouter {
  val routes = ApiRoute.route~TestRoute.route
}
