package me.flygare.utils

trait HttpConfig{

  val interface = "0.0.0.0"
  val port = 3000

  // http://address for docker bridge
  val remoteAddressHost = "http://192.168.0.200"
  val remoteAddressPort = 3001
  // http://person for docker bridge
  val remotePersonHost = "http://192.168.0.200"
  val remotePersonPort = 3002
  // http://profile for docker bridge
  val remoteProfileHost = "http://192.168.0.200"
  val remoteProfilePort = 3003
  val remotePath = "/dblogic"
}
