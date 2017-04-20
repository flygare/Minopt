package me.flygare

import org.scalatest.{Matchers, WordSpec}
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.http.scaladsl.server._
import me.flygare.utils.HttpConfig

class TestKit extends WordSpec with Matchers with ScalatestRouteTest with HttpConfig {

  "The service" should {

    "return a greeting for GET requests to the test path" in {
      // tests:
      Get("/rammus") ~> MainRouter.routes ~> check {
        responseAs[String] shouldEqual "Ok"
      }
    }

    "leave GET requests to other paths unhandled" in {
      // tests:
      Get("/errorpath") ~> MainRouter.routes ~> check {
        handled shouldBe false
      }
    }

    "return a success code for get request at api/kv/2" in {
      // tests:
      Get("/api/kv/2") ~> MainRouter.routes ~> check {
        status === StatusCodes.Success
      }
    }

    "return a success code for get request at api/kv/5" in {
      // tests:
      Get("/api/kv/5") ~> MainRouter.routes ~> check {
        status === StatusCodes.Success
      }
    }

    "return a success code for get request at api/kv/10" in {
      // tests:
      Get("/api/kv/10") ~> MainRouter.routes ~> check {
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
