package com.example.sbt

import scala.util.Try
import twitter4j.{Twitter, TwitterFactory}
import twitter4j.conf.ConfigurationBuilder

case class TweeterService(client: Twitter) {

  def post(tweet: String): Try[Long] = {
    Try(client.updateStatus(tweet)).map(_.getId)
  }
}

object TweeterService {

  def apply(consumerKey: String, consumerSecret: String, accessToken: String, accessTokenSecret: String): TweeterService = {
    val config = (new ConfigurationBuilder)
      .setOAuthConsumerKey(consumerKey)
      .setOAuthConsumerSecret(consumerSecret)
      .setOAuthAccessToken(accessToken)
      .setOAuthAccessTokenSecret(accessTokenSecret)
      .build()
    val client = new TwitterFactory(config).getInstance()
    TweeterService(client)
  }
}
