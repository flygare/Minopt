package me.flygare

package me.flygare

import org.scalatest.{Matchers, WordSpec}
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import _root_.me.flygare.utils.HttpConfig
import _root_.me.flygare.routes.MainRouter
import akka.http.scaladsl.server.Route

class RestSpec extends WordSpec with Matchers with ScalatestRouteTest with HttpConfig {

  "The Route should" should {

    "return a success code for GET request at dblogic/persons path with query" in {
      Get("/dblogic/persons?rows=2") ~> MainRouter.routes ~> check {
        status === StatusCodes.Success
      }
    }

    "return a success code for GET request at dblogic/addresses path with query" in {
      Get("/dblogic/addresses?rows=5") ~> MainRouter.routes ~> check {
        status === StatusCodes.Success
      }
    }

    "return a success code for GET request at dblogic/profiles path with query" in {
      Get("/dblogic/profiles?rows=10") ~> MainRouter.routes ~> check {
        status === StatusCodes.Success
      }
    }

    "return a greeting for GET request at test/ok path" in {
      Get("/test/ok") ~> MainRouter.routes ~> check {
        responseAs[String] shouldEqual "Ok"
      }
    }

    "return a success code for POST requests to the dblogic/persons path with query" in {
      Post("/dblogic/persons?rows=10", "string") ~> MainRouter.routes ~> check {
        //TODO add check for data json format
        status === StatusCodes.Success
      }
    }

    "return a success code for POST requests to the dblogic/addresses path with query" in {
      Post("/dblogic/addresses?rows=10", "string") ~> MainRouter.routes ~> check {
        //TODO add check for data json format
        status === StatusCodes.Success
      }
    }

    "return a success code for POST requests to the dblogic/profiles path with query" in {
      Post("/dblogic/profiles?rows=2", "string") ~> MainRouter.routes ~> check {
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

