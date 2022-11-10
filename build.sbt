scalaVersion := "2.13.8"

name := "functional-pancakes"
organization := "ch.epfl.scala"
version := "1.0"

val tapirVersion = "1.2.0"
val circeVersion = "0.14.3"
val fs2Version = "3.3.0"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-effect" % "3.3.14",
  "co.fs2" %% "fs2-core" % fs2Version,
  "co.fs2" %% "fs2-io" % fs2Version,
  "co.fs2" %% "fs2-reactive-streams" % fs2Version,
  "co.fs2" %% "fs2-scodec" % fs2Version,
  "io.monix" %% "monix" % "3.4.1",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.1",
  "com.softwaremill.sttp.tapir" %% "tapir-core" % tapirVersion,
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
