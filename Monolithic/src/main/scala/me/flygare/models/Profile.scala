package me.flygare.models

abstract class User{

}

case class Profile(firstname: String, lastname: String, phonenumber: String, email: String, username: String,
                   password: String, description: String, website: String, lastip: String, lastlogin: String) extends User

case class ProfileDB(key: String, firstname: String, lastname: String, phonenumber: String,
                     email: String, username: String, password: String, description: String,
                     website: String, lastip: String, lastlogin: String) extends User
