package sbttweeter

import sbt._
import sbt.Keys._

object TweeterKeys {
  /* Instantiate your setting, task, and input task keys here */
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
