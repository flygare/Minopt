package me.flygare

import me.flygare.handlers.KeyValueHandler
import me.flygare.models.{KeyValueFive, KeyValueTen, KeyValueTwo}
import me.flygare.traits.SparkConnection
import org.scalatest.FlatSpec

class KeyValueSpec extends FlatSpec with SparkConnection {
  val kVHandler = new KeyValueHandler()

  /*
   * CREATE
   */
  "createKVTwo" should "return the created object" in {
    val createObj = kVHandler.createKVTwo("a", "b")

    assert(createObj.key != null)
    assert(createObj.col1 == "a")
    assert(createObj.col2 == "b")
  }

  "createKVFive" should "return the created object" in {
    val createObj = kVHandler.createKVFive("a", "b", "c", "d", "e")

    assert(createObj.key != null)
    assert(createObj.col1 == "a")
    assert(createObj.col2 == "b")
    assert(createObj.col3 == "c")
    assert(createObj.col4 == "d")
    assert(createObj.col5 == "e")
  }

  "createKVTen" should "return the created object" in {
    val createObj = kVHandler.createKVTen("a", "b", "c", "d", "e", "a", "b", "c", "d", "e")

    assert(createObj.key != null)
    assert(createObj.col1 == "a")
    assert(createObj.col2 == "b")
    assert(createObj.col3 == "c")
    assert(createObj.col4 == "d")
    assert(createObj.col5 == "e")
    assert(createObj.col6 == "a")
    assert(createObj.col7 == "b")
    assert(createObj.col8 == "c")
    assert(createObj.col9 == "d")
    assert(createObj.col10 == "e")
  }

  /*
   * GET
   */
  "getKVTwo" should "return the specified object with a key" in {
    val createObj = kVHandler.createKVTwo("a", "b")
    val getObj = kVHandler.getKVTwo(createObj.key)

    assert(getObj.key == createObj.key)
    assert(getObj.col1 == createObj.col1)
    assert(getObj.col2 == createObj.col2)
  }

  "getAllKVTwo" should "return an array with KeyValueTwo objects" in {
    val createObj = kVHandler.createKVTwo("a", "b")
    val kVTwoArray = kVHandler.getAllKVTwo

    assert(kVTwoArray.getClass == new Array[KeyValueTwo](0).getClass)
  }

  "getKVFive" should "return the specified object with a key" in {
    val createObj = kVHandler.createKVFive("a", "b", "c", "d", "e")
    val getObj = kVHandler.getKVFive(createObj.key)

    assert(getObj.key == createObj.key)
    assert(getObj.col1 == createObj.col1)
    assert(getObj.col2 == createObj.col2)
    assert(getObj.col3 == createObj.col3)
    assert(getObj.col4 == createObj.col4)
    assert(getObj.col5 == createObj.col5)
  }

  "getAllKVFive" should "return an array with KeyValueFive objects" in {
    val createObj = kVHandler.createKVFive("a", "b", "c", "d", "e")
    val kVFiveArray = kVHandler.getAllKVFive

    assert(kVFiveArray.getClass == new Array[KeyValueFive](0).getClass)
  }

  "getKVTen" should "return the specified object with a key" in {
    val createObj = kVHandler.createKVTen("a", "b", "c", "d", "e", "f", "g", "h", "i", "j")
    val getObj = kVHandler.getKVTen(createObj.key)
    
    assert(getObj.key == createObj.key)
    assert(getObj.col1 == createObj.col1)
    assert(getObj.col2 == createObj.col2)
    assert(getObj.col3 == createObj.col3)
  }

  "getAllKVTen" should "return an array with KeyValueTen objects" in {
    val createObj = kVHandler.createKVTen("a", "b", "c", "d", "e", "f", "g", "h", "i", "j")
    val kVTenArray = kVHandler.getAllKVTen

    assert(kVTenArray.getClass == new Array[KeyValueTen](0).getClass)
  }
}
