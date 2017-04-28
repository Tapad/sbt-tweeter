import sbtrelease.ReleaseStateTransformations._

name := "tweeter-positive"

organization := "com.example"

// current timestamp has been added as version metadata to prevent the suppression of duplicate tweets
version := s"1.2.3+${System.currentTimeMillis}"

// for 'real' projects, we'll want to use most of the default `releaseProcess`
// and insert our task after the default `publishArtifacts` or some custom `publish` step
releaseProcess := Seq[ReleaseStep](
  releaseStepInputTask(tweeterTweet, s" v${version.value} of ${organization.value}/${name.value} is available!")
)
