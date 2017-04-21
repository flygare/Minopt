package me.flygare.utils

import _root_.me.flygare.models._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

object JsonSupport extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val profileFormat = jsonFormat10(Profile)
  implicit val profileDB = jsonFormat11(ProfileDB)

  implicit val personFormat = jsonFormat2(Person)
  implicit val personDB = jsonFormat3(PersonDB)

  implicit val addressFormat = jsonFormat5(Address)
  implicit val addressDB = jsonFormat6(AddressDB)
}
