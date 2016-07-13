import sbt.Keys._

name := "uscala-cats"

lazy val uscalaVersion = "0.2.2"
lazy val specs2Version = "3.7.2"
lazy val catsVersion = "0.6.0"

val pomInfo = (
  <url>https://github.com/janstenpickle/uscala-cats</url>
  <licenses>
    <license>
      <name>The MIT License (MIT)</name>
      <url>https://github.com/janstenpickle/uscala-cats/blob/master/LICENSE</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:janstenpickle/uscala-cats.git</url>
    <connection>scm:git:git@github.com:janstenpickle/uscala-cats.git</connection>
  </scm>
  <developers>
    <developer>
      <id>janstepickle</id>
      <name>Chris Jansen</name>
    </developer>
  </developers>
)

lazy val commonSettings = Seq(
  version := "0.1.0",
  scalaVersion := "2.11.8",
  organization := "uscala-cats",
  pomExtra := pomInfo,
  autoAPIMappings := true,
  publishArtifact in Test := false,
  pomIncludeRepository := { _ => false },
  bintrayReleaseOnPublish := false,
  licenses += ("MIT", url("https://github.com/janstenpickle/uscala-cats/blob/master/LICENSE")),
  resolvers ++= Seq(Resolver.sonatypeRepo("releases"), "Bintray jcenter" at "https://jcenter.bintray.com/"),
  libraryDependencies ++= Seq(
    compilerPlugin("org.spire-math" %% "kind-projector" % "0.8.0"),
    "org.typelevel" %% "cats-core" % catsVersion,
    "org.specs2" %% "specs2-core" % specs2Version % "test",
    "org.specs2" %% "specs2-scalacheck" % specs2Version % "test",
    "org.specs2" %% "specs2-junit" % specs2Version % "test"
  ),
  autoCompilerPlugins := true,
  scalacOptions in Test ++= Seq(
    "-Yrangepos",
    "-Xlint",
    "-deprecation",
    "-Xfatal-warnings"
  ),
  scalacOptions ++= Seq(
    "-Xlint",
    "-Xcheckinit",
    "-Xfatal-warnings",
    "-unchecked",
    "-deprecation",
    "-feature",
    "-language:implicitConversions")
)

lazy val result = (project in file("result")).
  settings(name := "uscala-cats-result").
  settings(libraryDependencies ++= Seq(
    "org.uscala" %% "uscala-result" % uscalaVersion,
    "org.uscala" %% "uscala-result-specs2" % uscalaVersion % "test"
  )).
  settings(commonSettings: _*)
