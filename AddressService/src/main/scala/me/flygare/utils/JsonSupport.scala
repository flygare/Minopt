package me.flygare.utils

import _root_.me.flygare.models._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

object JsonSupport extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val addressFormat = jsonFormat5(Address)
  implicit val addressDB = jsonFormat6(AddressDB)
}
