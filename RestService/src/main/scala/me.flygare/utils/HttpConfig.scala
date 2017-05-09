package me.flygare.utils

trait HttpConfig{

  val interface = "0.0.0.0"
  val port = 3000

  // http://address for docker bridge
  val remoteAddressHost = "http://address"
  val remoteAddressPort = 3001
  // http://person for docker bridge
  val remotePersonHost = "http://person"
  val remotePersonPort = 3002
  // http://profile for docker bridge
  val remoteProfileHost = "http://profile"
  val remoteProfilePort = 3003
  val remotePath = "/dblogic"
}
