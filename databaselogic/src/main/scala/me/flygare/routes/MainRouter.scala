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
  val addressHandler = new AddressHandler
  val personHandler = new PersonHandler
  val profileHandler = new ProfileHandler

  val routes =
    respondWithDefaultHeaders(RawHeader("Access-Control-Allow-Origin", "*"), RawHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE"), RawHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept")) {
      pathPrefix("dblogic") {
        delete {
          profileHandler.deleteProfiles
          personHandler.deletePersons
          profileHandler.deleteProfiles
          complete("Cassandra truncated")
        }
        path("persons") {
          get {
            parameters('rows) {
              (rows) =>
                complete(personHandler.getPersons(rows.toInt))
            }
          } ~
            post {
              entity(as[Person]) {
                person => {
                  personHandler.createPerson(person)
                  complete(s"The person you sent were: $person")
                }
              }
            }
        } ~
          path("addresses") {
            get {
              parameters('rows) {
                (rows) =>
                  complete(addressHandler.getAddresses(rows.toInt))
              }
            } ~
              post {
                entity(as[Address]) {
                  address => complete(s"The address you sent were: $address")
                }
              }
          } ~
          path("profiles") {
            get {
              parameters('rows) {
                (rows) =>
                  complete(profileHandler.getProfiles(0))
              }
            } ~
              post {
                entity(as[Profile]) {
                  profile => complete(s"The profile you sent were: $profile")
                }
              }
          }
      }
    }
}
