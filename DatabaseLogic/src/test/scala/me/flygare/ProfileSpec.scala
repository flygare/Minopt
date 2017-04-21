package me.flygare

import java.text.SimpleDateFormat
import java.util.Calendar

import _root_.me.flygare.handlers.ProfileHandler
import _root_.me.flygare.models.ProfileDB
import _root_.me.flygare.traits.SparkConnection
import org.scalatest._

class ProfileSpec extends FunSpec with SparkConnection {
  val profileHandler = new ProfileHandler()

  /*
   * CREATE
   */
  describe("createProfile()") {
    it("should return the created profile") {
      val dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'")
      val currentDate = dateFormat.format(Calendar.getInstance().getTime)

      val profile = profileHandler.createProfile("Lars", "Larsson", "0603225533", "larslarsson@gmail.com", "larstheboss", "LampaGolvMatta45", "I'm Lars Larsson.", "http://larslarsson.com", "127.0.0.1", currentDate)

      assert(profile.key != null)
      assert(profile.firstname == "Lars")
      assert(profile.lastname == "Larsson")
      assert(profile.phonenumber == "0603225533")
      assert(profile.email == "larslarsson@gmail.com")
      assert(profile.username == "larstheboss")
      assert(profile.password == "LampaGolvMatta45")
      assert(profile.description == "I'm Lars Larsson.")
      assert(profile.website == "http://larslarsson.com")
      assert(profile.lastip == "127.0.0.1")
      assert(profile.lastlogin == currentDate)
    }
  }

  /*
   * GET
   */
  describe("getProfile()") {
    it("should return the specified profile with a key") {
      val dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'")
      val currentDate = dateFormat.format(Calendar.getInstance().getTime)

      val createdProfile = profileHandler.createProfile("Lars", "Larsson", "0603225533", "larslarsson@gmail.com", "larstheboss", "LampaGolvMatta45", "I'm Lars Larsson.", "http://larslarsson.com", "127.0.0.1", currentDate)
      val fetchedProfile = profileHandler.getProfile(createdProfile.key)

      assert(fetchedProfile == createdProfile)
    }
  }

  describe("getProfiles()") {
    it("should return an array with profiles with ProfileDB format") {
      val dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'")
      val currentDate = dateFormat.format(Calendar.getInstance().getTime)

      val profle = profileHandler.createProfile("Lars", "Larsson", "0603225533", "larslarsson@gmail.com", "larstheboss", "LampaGolvMatta45", "I'm Lars Larsson.", "http://larslarsson.com", "127.0.0.1", currentDate)
      val profiles = profileHandler.getProfiles

      assert(profiles.getClass == new Array[ProfileDB](0).getClass)
    }
  }
}