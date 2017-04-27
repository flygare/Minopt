package me.flygare.routes

import me.flygare.utils.{HttpConfig, HttpConnection}
import me.flygare.handlers.{AddressHandler, PersonHandler, ProfileHandler}
import me.flygare.models.{Address, Person, Profile}
import akka.http.scaladsl.server.Directives._
import me.flygare.utils.HttpConnection
import me.flygare.utils.JsonSupport._
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

object ApiRoute extends HttpConnection with HttpConfig {
  val personHandler = new PersonHandler
  val addressHandler = new AddressHandler
  val profileHandler = new ProfileHandler

  val route =
    pathPrefix("api") {
      path("persons") {
        get {
          parameters('rows) {
            (rows) =>
              complete(personHandler.getPersons(rows.toInt))
          }
        } ~
          post {
            entity(as[Person]) {
              person =>
                personHandler.createPerson(person)
                complete(s"The person you sent were: $person")
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
                address =>
                  addressHandler.createAddress(address)
                  complete(s"The address you sent were: $address")
              }
            }
        } ~
        path("profiles") {
          get {
            parameters('rows) {
              (rows) =>
                complete(profileHandler.getProfiles(rows.toInt))
            }
          } ~
            post {
              entity(as[Profile]) {
                profile =>
                  profileHandler.createProfile(profile)
                  complete(s"The profile you sent were: $profile")
              }
            }
        }
    }
}
