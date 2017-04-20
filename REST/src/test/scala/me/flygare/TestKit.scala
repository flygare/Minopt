package me.flygare

import org.scalatest.{Matchers, WordSpec}
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.http.scaladsl.server._
import me.flygare.utils.HttpConfig

class TestKit extends WordSpec with Matchers with ScalatestRouteTest with HttpConfig {

  "The service" should {

    "return a greeting for GET requests to the test/ok path" in {
      // tests:
      Get("/test/ok") ~> MainRouter.routes ~> check {
        responseAs[String] shouldEqual "Ok"
      }
    }

    "return a success code for GET requests to the test/json path" in {
      // tests:
      Get("/test/json") ~> MainRouter.routes ~> check {
        status === StatusCodes.Success
      }
    }

    "leave GET requests to other paths unhandled" in {
      // tests:
      Get("/errorpath") ~> MainRouter.routes ~> check {
        handled shouldBe false
      }
    }

    "return a success code for get request at api/person" in {
      // tests:
      Get("/api/person") ~> MainRouter.routes ~> check {
        status === StatusCodes.Success
      }
    }

    "return a success code for get request at api/address" in {
      // tests:
      Get("/api/address") ~> MainRouter.routes ~> check {
        status === StatusCodes.Success
      }
    }

    "return a success code for get request at api/profile" in {
      // tests:
      Get("/api/profile") ~> MainRouter.routes ~> check {
        status === StatusCodes.Success
      }
    }

    "return a MethodNotAllowed error for PUT requests to the root path" in {
      // tests:
      Put() ~> Route.seal(MainRouter.routes) ~> check {
        status === StatusCodes.MethodNotAllowed
      }
    }
  }
}
