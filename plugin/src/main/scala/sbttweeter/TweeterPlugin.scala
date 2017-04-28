package sbttweeter

import sbt._
import sbt.Keys._

object TweeterKeys {
  val tweeterConsumerKey        = settingKey[String]("The Twitter application consumer key")
  val tweeterConsumerSecret     = settingKey[String]("The Twitter application consumer secret")
  val tweeterAccessToken        = settingKey[String]("The Twitter user access token")
  val tweeterAccessTokenSecret  = settingKey[String]("The Twitter user access token secret")
  val tweeterTweet              = inputKey[Long]("Post a tweet to the configured Twitter account")
}

object TweeterPlugin extends AutoPlugin {

  object autoImport {
    /* Whatever you want brought into scope automatically for users of your plugin */
    val TweeterKeys = sbttweeter.TweeterKeys
  }

  override def projectSettings = Seq(
    /* Whatever settings/behavior you want to make available to the project that includes your plugin */
  )
}
