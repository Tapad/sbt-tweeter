import Publishing._

lazy val root = (project in file("."))
  .settings(ReleaseSettings: _*)
  .aggregate(plugin, library)
  .enablePlugins(CrossPerProjectPlugin)

lazy val plugin = (project in file("plugin"))
  .settings(PluginPublishSettings: _*)
  .settings(scriptedSettings: _*)
  .settings(
    name := "sbt-tweeter",
    sbtPlugin := true,
    scalaVersion := "2.10.6",
    scriptedLaunchOpts ++= Seq("-Dplugin.version=" + version.value),
    scriptedBufferLog := false,
    publishLocal := publishLocal.dependsOn(publishLocal in library).value
  )
  .dependsOn(library)

lazy val library = (project in file("library"))
  .settings(LibraryPublishSettings: _*)
  .settings(
    name := "tweeter-core",
    scalaVersion := "2.10.6",
    crossScalaVersions ++= Seq("2.11.11", "2.12.2"),
    libraryDependencies ++= Seq(
      "org.twitter4j" % "twitter4j-core" % "4.0.0",
      "org.scalatest" %% "scalatest" % "3.0.0" % Test
    ),
    publishMavenStyle := true
  )
