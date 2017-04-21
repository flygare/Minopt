package me.flygare

package me.flygare

import org.scalatest.{Matchers, WordSpec}
import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import _root_.me.flygare.utils.HttpConfig
import _root_.me.flygare.routes.MainRouter
import akka.http.scaladsl.server.Route

class RestSpec extends WordSpec with Matchers with ScalatestRouteTest with HttpConfig {

  val testPerson = "{\n  \"firstname\": \"Claire\",\n  \"lastname\": \"Kunde\"\n}"
  val testAddress = "{\n  \"street\": \"Jerde Fords\",\n  \"zipcode\": \"89834-4106\",\n  \"city\": \"Mariebury\",\n  \"county\": \"Borders\",\n  \"country\": \"Faroe Islands\"\n}"
  val testProfile = "{\n  \"firstname\": \"Jeremie\",\n  \"lastname\": \"Kassulke\",\n  \"phonenumber\": \"1-671-751-4138 x40895\",\n  \"email\": \"Felix84@yahoo.com\",\n  \"username\": \"Ashtyn_Haley24\",\n  \"password\": \"shgyMZJOCJXuDkdfLcIP\",\n  \"description\": \"Dignissimos fuga cumque temporibus voluptas.\",\n  \"website\": \"http://terrance.com\",\n  \"lastip\": \"93.86.125.24\",\n  \"lastlogin\": \"2017-04-20T11:48:01.856Z\"\n}"


  "The Route should" should {

    "return a success code for GET request at dblogic/persons path with query" in {
      Get("/dblogic/persons?rows=10") ~> MainRouter.routes ~> check {
        status === StatusCodes.Success
      }
    }

    "return a success code for GET request at dblogic/addresses path with query" in {
      Get("/dblogic/addresses?rows=10") ~> MainRouter.routes ~> check {
        status === StatusCodes.Success
      }
    }

    "return a success code for GET request at dblogic/profiles path with query" in {
      Get("/dblogic/profiles?rows=10") ~> MainRouter.routes ~> check {
        status === StatusCodes.Success
      }
    }

    "return a success code for POST requests to the dblogic/persons path with valid json" in {
      Post("/dblogic/persons", HttpEntity(MediaTypes.`application/json`, testPerson)) ~> MainRouter.routes ~> check {
        status === StatusCodes.Success
      }
    }

    "return a success code for POST requests to the dblogic/addresses path with valid json" in {
      Post("/dblogic/addresses", HttpEntity(MediaTypes.`application/json`, testAddress)) ~> MainRouter.routes ~> check {
        status === StatusCodes.Success
      }
    }

    "return a success code for POST requests to the dblogic/profiles path with valid json" in {
      Post("/dblogic/profiles", HttpEntity(MediaTypes.`application/json`, testProfile)) ~> MainRouter.routes ~> check {
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

