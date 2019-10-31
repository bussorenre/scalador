
name := "scalador"

// common Settings
lazy val commonSettings = Seq(
  organization := "com.bussorenre",
  version := "0.1",
  scalaVersion := "2.12.7",
  libraryDependencies ++= Seq(
    "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.0",
    "org.scalatest" %% "scalatest" % "3.0.5",
    "com.google.inject" % "guice" % "4.2.2",

  ),
  scalafmtOnCompile := true
)

lazy val root = (project in file("."))
  .settings(commonSettings)
