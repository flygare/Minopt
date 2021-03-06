package me.flygare.models

abstract class Addressable

case class Address(street: String, zipcode: String, city: String, county: String, country: String) extends Addressable

case class AddressDB(key: String, street: String, zipcode: String, city: String, county: String, country: String) extends Addressable