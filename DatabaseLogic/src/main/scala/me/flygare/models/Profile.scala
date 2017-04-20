package me.flygare.models

case class Profile(key: String,
                   firstName: String, lastName: String, phonenumber: String, email: String, username: String,
                   password: String, description: String, website: String, lastIp: String, lastLogin: String)
