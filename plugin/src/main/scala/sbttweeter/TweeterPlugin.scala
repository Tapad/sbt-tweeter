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
    val TweeterKeys               = sbttweeter.TweeterKeys
    val tweeterConsumerKey        = TweeterKeys.tweeterConsumerKey
    val tweeterConsumerSecret     = TweeterKeys.tweeterConsumerSecret
    val tweeterAccessToken        = TweeterKeys.tweeterAccessToken
    val tweeterAccessTokenSecret  = TweeterKeys.tweeterAccessTokenSecret
    val tweeterTweet              = TweeterKeys.tweeterTweet
  }

  import autoImport._

  override def projectSettings = Seq(
    // Required settings that must be defined by the project utilizing the plugin
    tweeterConsumerKey        := undefinedKeyError(tweeterConsumerKey.key),
    tweeterConsumerSecret     := undefinedKeyError(tweeterConsumerSecret.key),
    tweeterAccessToken        := undefinedKeyError(tweeterAccessToken.key),
    tweeterAccessTokenSecret  := undefinedKeyError(tweeterAccessTokenSecret.key),

    // The one input task that will be available to our plugin users, by default
    tweeterTweet := {
      ???
    }
  )

  def undefinedKeyError[A](key: AttributeKey[A]): A = {
    sys.error(
      s"${key.description.getOrElse("A required key")} is not defined. " +
      s"Please declare a value for the `${key.label}` key."
    )
  }
}
