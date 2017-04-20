package me.flygare.routes

import akka.http.scaladsl.server.Directives._
import me.flygare.utils.HttpConnection
import me.flygare.handlers._
import me.flygare.models._
import me.flygare.utils.JsonSupport._

object MainRouter extends HttpConnection{
  val addressHandler = new AddressHandler
  val personHandler = new PersonHandler
  val profileHandler = new ProfileHandler

  val routes =
    pathPrefix("dblogic") {
      delete {
        profileHandler.deleteProfiles
        personHandler.deletePersons
        profileHandler.deleteProfiles
        complete("Cassandra truncated")
      }
      path("persons"){
        get {
          parameters('rows) {
            (rows) =>
              complete(personHandler.getPersons.toString)
          }
        }~
          post {
            entity(as[Person]){
              person => {
                personHandler.createPerson(person)
                complete(s"The person you sent were: $person")
              }
            }
          }
      }~
        path("addresses"){
          get {
            parameters('rows) {
              (rows) =>
                complete("cassandra happy")
            }
          }~
            post {
              entity(as[Address]){
                address => complete(s"The address you sent were: $address")
              }
            }
        }~
        path("profiles"){
          get {
            parameters('rows) {
              (rows) =>
                complete("cassandra happy")
            }
          }~
            post {
              entity(as[Profile]){
                profile => complete(s"The profile you sent were: $profile")
              }
            }
        }
    }
}
