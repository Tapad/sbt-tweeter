import sbt._
import sbt.Keys._
import sbtrelease.ReleasePlugin.autoImport._
import sbtrelease.ReleaseStateTransformations._

object Publishing {

  /* `publish` performs a no-op */
  val NoopPublishSettings = Seq(
    packagedArtifacts in RootProject(file(".")) := Map.empty,
    publish := (),
    publishLocal := (),
    publishArtifact := false,
    publishTo := None
  )

  val PublishSettings = Seq(
    autoAPIMappings := true,
    pomIncludeRepository := { _ => false },
    publishArtifact in Test := false,
    publishArtifact in (Compile, packageDoc) := true,
    publishArtifact in (Compile, packageSrc) := true
  )

  val ReleaseSettings = NoopPublishSettings ++ Seq(
    releaseProcess := Seq[ReleaseStep](
      releaseStepCommandAndRemaining("+test"),
      releaseStepCommandAndRemaining("+publishLocal")
    )
  )
}
