package me.flygare

import me.flygare.handlers.AddressHandler
import me.flygare.models.AddressDB
import me.flygare.traits.SparkConnection
import org.scalatest._

class AddressSpec extends FunSpec with SparkConnection {
  val addressHandler = new AddressHandler();

  /*
   * CREATE
   */
  describe("createAddress()") {
    it("should return the created address") {
      val address = addressHandler.createAddress("Bourbon Street 1", "13423", "Pretend Town", "Blekinge", "Sweden")

      assert(address.key != null)
      assert(address.street == "Bourbon Street 1")
      assert(address.zipcode == "13423")
      assert(address.city == "Pretend Town")
      assert(address.county == "Blekinge")
      assert(address.country == "Sweden")
    }
  }

  /*
   * GET
   */
  describe("getAddress()") {
    it("should return the specified address with a key") {
      val createdAddress = addressHandler.createAddress("Bourbon Street 1", "13423", "Pretend Town", "Blekinge", "Sweden")
      val fetchedAddress = addressHandler.getAddress(createdAddress.key);

      assert(fetchedAddress == createdAddress)
    }
  }

  describe("getAddresses()") {
    it("should return an array with addresses with DB format") {
      val address = addressHandler.createAddress("Bourbon Street 1", "13423", "Pretend Town", "Blekinge", "Sweden")
      val addresses = addressHandler.getAllAddresses

      assert(addresses.getClass == new Array[AddressDB](0).getClass)
    }
  }

}
