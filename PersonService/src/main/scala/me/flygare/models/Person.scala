package me.flygare.models

abstract class Human {
  def firstname: String
  def lastname: String
}
case class Person(firstname: String, lastname: String) extends Human

case class PersonDB(key: String, firstname: String, lastname: String) extends Human