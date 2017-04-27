package me.flygare.utils

trait HttpConfig{

  val interface = "0.0.0.0"
  val port = 3000

  val remoteAddressHost = "http://address"
  val remoteAddressPort = 3001
  val remotePersonHost = "http://person"
  val remotePersonPort = 3002
  val remoteProfileHost = "http://profile"
  val remoteProfilePort = 3003
  val remotePath = "/dblogic"
}
