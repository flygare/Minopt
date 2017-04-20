package me.flygare

import org.scalatest.{Matchers, WordSpec}
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.http.scaladsl.server._
import me.flygare.utils.HttpConfig

class RestSpec extends WordSpec with Matchers with ScalatestRouteTest with HttpConfig {

  "The ApiRoute should" should {

    "return a success code for GET request at api/persons path with query" in {
      Get("/api/persons?properties=2") ~> MainRouter.routes ~> check {
        status === StatusCodes.Success
      }
    }

    "return a success code for GET request at api/addresses path with query" in {
      Get("/api/addresses?properties=5") ~> MainRouter.routes ~> check {
        status === StatusCodes.Success
      }
    }

    "return a success code for GET request at api/profiles path with query" in {
      Get("/api/profiles?properties=10") ~> MainRouter.routes ~> check {
        status === StatusCodes.Success
      }
    }

    "return a greeting for GET request at test/ok path" in {
      Get("/test/ok") ~> MainRouter.routes ~> check {
        responseAs[String] shouldEqual "Ok"
      }
    }

    "return a success code for POST requests to the api/persons path with query" in {
      Post("/api/persons?properties=10", "string") ~> MainRouter.routes ~> check {
        //TODO add check for data json format
        status === StatusCodes.Success
      }
    }

    "return a success code for POST requests to the api/addresses path with query" in {
      Post("/api/addresses?properties=10", "string") ~> MainRouter.routes ~> check {
        //TODO add check for data json format
        status === StatusCodes.Success
      }
    }

    "return a success code for POST requests to the api/profiles path with query" in {
      Post("/api/profiles?properties=2", "string") ~> MainRouter.routes ~> check {
        //TODO add check for data json format
        status === StatusCodes.Success
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
