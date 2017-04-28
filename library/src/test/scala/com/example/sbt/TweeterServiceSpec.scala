package com.example.sbt

import scala.util.Failure
import org.scalatest.{FlatSpec, Matchers}

class TweeterServiceSpec extends FlatSpec with Matchers {

  behavior of "TweeterService"

  it should "return a failure when ..." in {
    pending
    val service: TweeterService = ???
    val result = service.post("This tweet should never make it out!")
    result shouldBe a[Failure[_]]
  }
}
