scalaVersion := "2.13.8"

name := "functional-pancakes"
organization := "ch.epfl.scala"
version := "1.0"

val circeVersion = "0.14.3"
val Fs2Version = "3.3.0"
val Http4sVersion = "0.23.12"
val TapirVersion = "1.2.1"

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.1",
  "co.fs2" %% "fs2-core" % Fs2Version,
  "co.fs2" %% "fs2-io" % Fs2Version,
  "co.fs2" %% "fs2-reactive-streams" % Fs2Version,
  "co.fs2" %% "fs2-scodec" % Fs2Version,
  "org.http4s" %% "http4s-blaze-server" % Http4sVersion,
  "com.softwaremill.sttp.tapir" %% "tapir-core" % TapirVersion,
  "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % TapirVersion,
  "com.softwaremill.sttp.tapir" %% "tapir-http4s-server" % TapirVersion,
  "com.softwaremill.sttp.tapir" %% "tapir-asyncapi-docs" % TapirVersion,
  "com.softwaremill.sttp.apispec" %% "asyncapi-circe-yaml" % "0.3.1",
  // "com.softwaremill.sttp.tapir" %% "tapir-asyncapi-circe-yaml" % "0.3.1",
  "com.softwaremill.sttp.shared" %% "core" % "1.3.11",
  "com.softwaremill.sttp.model" %% "core" % "1.5.2",
  "com.softwaremill.sttp.client3" %% "core" % "3.8.3",
  "com.softwaremill.sttp.shared" %% "fs2" % "1.3.11",
  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-generic-extras" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion
)

// Here's a quick glimpse of what a multi-project build looks like for this
// build, with only one "subproject" defined, called `root`:

// lazy val root = (project in file(".")).
//   settings(
//     inThisBuild(List(
//       organization := "ch.epfl.scala",
//       scalaVersion := "2.13.8"
//     )),
//     name := "hello-world"
//   )

// To learn more about multi-project builds, head over to the official sbt
// documentation at http://www.scala-sbt.org/documentation.html

