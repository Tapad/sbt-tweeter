package com.example.sbt

import scala.util.Failure
import org.scalatest.{FlatSpec, Matchers}

class TweeterServiceSpec extends FlatSpec with Matchers {

  behavior of "TweeterService"

  it should "return a failure when acting on behalf of a client with bogus credentials" in {
    val service = TweeterService("foo", "bar", "baz", "qux")
    val result = service.post("This tweet should never make it out!")
    result shouldBe a[Failure[_]]
  }
}
