package me.flygare

import org.scalatest.{Matchers, WordSpec}
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.http.scaladsl.server._
import me.flygare.utils.HttpConfig

class TestKit extends WordSpec with Matchers with ScalatestRouteTest with HttpConfig {

  "The ApiRoute should" should {

    "return a success code for get request at api/person" in {
      Get("/api/person") ~> MainRouter.routes ~> check {
        status === StatusCodes.Success
      }
    }

    "return a success code for get request at api/address" in {
      Get("/api/address") ~> MainRouter.routes ~> check {
        status === StatusCodes.Success
      }
    }

    "return a success code for get request at api/profile" in {
      Get("/api/profile") ~> MainRouter.routes ~> check {
        status === StatusCodes.Success
      }
    }

    "return a greeting for GET requests to the test/ok path" in {
      Get("/test/ok") ~> MainRouter.routes ~> check {
        responseAs[String] shouldEqual "Ok"
      }
    }
  }

  "The test route should" should{

    "return a success code for GET requests to the test/json path" in {
      Get("/test/json") ~> MainRouter.routes ~> check {
        status === StatusCodes.Success
      }
    }
  }

  "The api" should {

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
