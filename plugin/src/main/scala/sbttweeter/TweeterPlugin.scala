package sbttweeter

import scala.util.{Success, Failure}
import sbt._
import sbt.Keys._
import sbt.complete.DefaultParsers.spaceDelimited
import com.example.sbt.TweeterService

object TweeterKeys {
  val tweeterConsumerKey        = settingKey[String]("The Twitter application consumer key")
  val tweeterConsumerSecret     = settingKey[String]("The Twitter application consumer secret")
  val tweeterAccessToken        = settingKey[String]("The Twitter user access token")
  val tweeterAccessTokenSecret  = settingKey[String]("The Twitter user access token secret")
  val tweeterTweet              = inputKey[Long]("Post a tweet to the configured Twitter account")
}

object TweeterPlugin extends AutoPlugin {

  override def trigger = allRequirements

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
      val log = streams.value.log
      val tweet = spaceDelimited("<text of tweet>").parsed.mkString(" ")
      val consumerKey = tweeterConsumerKey.value
      val consumerSecret = tweeterConsumerSecret.value
      val accessToken = tweeterAccessToken.value
      val accessTokenSecret = tweeterAccessTokenSecret.value
      val client = TweeterService(consumerKey, consumerSecret, accessToken, accessTokenSecret)
      client.post(tweet) match {
        case Success(tweetId) => log.info(s"""Successfully tweeted: "$tweet" ($tweetId)"""); tweetId
        case Failure(e) => sys.error("An error was encountered when trying to tweet: " + ErrorHandling.reducedToString(e))
      }
    }
  )

  def undefinedKeyError[A](key: AttributeKey[A]): A = {
    sys.error(
      s"${key.description.getOrElse("A required key")} is not defined. " +
      s"Please declare a value for the `${key.label}` key."
    )
  }
}
