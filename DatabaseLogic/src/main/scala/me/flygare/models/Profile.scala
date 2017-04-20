package me.flygare.models

case class Profile(key: String,
                   firstname: String, lastname: String, phonenumber: String, email: String, username: String,
                   password: String, description: String, website: String, lastip: String, lastlogin: String)
