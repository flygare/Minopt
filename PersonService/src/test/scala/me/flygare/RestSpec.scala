package me.flygare

import org.scalatest.{Matchers, WordSpec}
import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import _root_.me.flygare.utils.HttpConfig
import _root_.me.flygare.routes.MainRouter
import akka.http.scaladsl.server.Route
import me.flygare.traits.SparkConnection

class RestSpec extends WordSpec with Matchers with ScalatestRouteTest with HttpConfig with SparkConnection {

  val testPerson = "{\n  \"firstname\": \"Claire\",\n  \"lastname\": \"Kunde\"\n}"

  "The Route should" should {

    "return a success code for GET request at dblogic/persons path with query" in {
      Get("/dblogic/persons?rows=10") ~> MainRouter.routes ~> check {
        status === StatusCodes.Success
      }
    }

    "return a success code for POST requests to the dblogic/persons path with valid json" in {
      Post("/dblogic/persons", HttpEntity(MediaTypes.`application/json`, testPerson)) ~> MainRouter.routes ~> check {
        status === StatusCodes.Success
      }
    }
  }

  "The dblogic" should {

    "leave GET requests to other paths unhandled" in {
      Get("/errorpath") ~> MainRouter.routes ~> check {
        handled shouldBe false
      }
    }

    "return a MethodNotAllowed error for PUT requests to the root path" in {
      Put() ~> Route.seal(MainRouter.routes) ~> check {
        status === StatusCodes.MethodNotAllowed
      }
    }
  }
}

