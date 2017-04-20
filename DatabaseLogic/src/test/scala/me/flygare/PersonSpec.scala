package me.flygare

import _root_.me.flygare.handlers.PersonHandler
import _root_.me.flygare.models.Person
import _root_.me.flygare.traits.SparkConnection
import org.scalatest._

class PersonSpec extends FunSpec with SparkConnection {
  val personHandler = new PersonHandler()

  /*
   * CREATE
   */
  describe("createPerson()") {
    it("should return the created person") {
      val person = personHandler.createPerson("Lars", "Larsson")

      assert(person.key != null)
      assert(person.firstname == "Lars")
      assert(person.lastname == "Larsson")
    }
  }

  /*
   * GET
   */
  describe("getPerson()") {
    it("should return the specified person with a key") {
      val createdPerson = personHandler.createPerson("Lars", "Larsson")
      val fetchedPerson = personHandler.getPerson(createdPerson.key)

      assert(fetchedPerson == createdPerson)
    }
  }

  describe("getPersons()") {
    it("should return an array with persons") {
      val person = personHandler.createPerson("Lars", "Larsson")
      val persons = personHandler.getPersons

      assert(persons.getClass == new Array[Person](0).getClass)
    }
  }
}