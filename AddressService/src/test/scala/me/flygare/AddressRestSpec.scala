package me.flygare

import org.scalatest.{Matchers, WordSpec}
import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import me.flygare.utils.HttpConfig
import me.flygare.routes.MainRouter
import akka.http.scaladsl.server.Route
import me.flygare.traits.SparkConnection

class AddressRestSpec extends WordSpec with Matchers with ScalatestRouteTest with HttpConfig with SparkConnection {

  val testAddress = "{\n  \"street\": \"Jerde Fords\",\n  \"zipcode\": \"89834-4106\",\n  \"city\": \"Mariebury\",\n  \"county\": \"Borders\",\n  \"country\": \"Faroe Islands\"\n}"


  "The Route should" should {

    "return a success code for GET request at dblogic/addresses path with query" in {
      Get("/dblogic/addresses?rows=10") ~> MainRouter.routes ~> check {
        status === StatusCodes.Success
      }
    }

    "return a success code for POST requests to the dblogic/addresses path with valid json" in {
      Post("/dblogic/addresses", HttpEntity(MediaTypes.`application/json`, testAddress)) ~> MainRouter.routes ~> check {
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