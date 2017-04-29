import sbt._
import sbt.Keys._
import sbtrelease.ReleasePlugin.autoImport._
import sbtrelease.ReleaseStateTransformations._
import bintray.BintrayKeys._

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

  val PluginPublishSettings = PublishSettings ++ Seq(
    bintrayRepository := "sbt-plugins"
  )

  val LibraryPublishSettings = PublishSettings ++ Seq(
    bintrayRepository := "maven",
    bintrayPackage := "sbt-tweeter-libs",
    publishMavenStyle := true,
    homepage := Some(new URL("https://github.com/Tapad/sbt-tweeter")),
    pomExtra := {
      <developers>
        <developer>
          <id>jeffreyolchovy</id>
          <name>Jeffrey Olchovy</name>
          <email>jeffo@tapad.com</email>
          <url>https://github.com/jeffreyolchovy</url>
        </developer>
      </developers>
      <scm>
        <url>https://github.com/Tapad/sbt-tweeter</url>
        <connection>scm:git:git://github.com/Tapad/sbt-tweeter.git</connection>
      </scm>
    }
  )

  val ReleaseSettings = NoopPublishSettings ++ Seq(
    releaseProcess := Seq[ReleaseStep](
      checkSnapshotDependencies,
      inquireVersions,
      releaseStepCommandAndRemaining("+test"),
      setReleaseVersion,
      commitReleaseVersion,
      tagRelease,
      releaseStepCommandAndRemaining("+publish"),
      setNextVersion,
      commitNextVersion,
      pushChanges
    )
  )
}
