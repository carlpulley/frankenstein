import sbt._

object Dependencies {

  val scala = "2.11.4"

  object spark {
    val version = "1.2.0"

    val core      = "org.apache.spark"  %% "spark-core"      % version
    val streaming = "org.apache.spark"  %% "spark-streaming" % version
    val sql       = "org.apache.spark"  %% "spark-sql"       % version
  }

  object spores {
    val core     = "org.scala-lang.modules" %% "spores-core"     % "0.1.0-SNAPSHOT"
    val pickling = "org.scala-lang.modules" %% "spores-pickling" % "0.1.0-SNAPSHOT"
  }

  val commons       = "org.apache.commons" %  "commons-lang3"   % "3.3.2"
  val logback       = "ch.qos.logback"     %  "logback-classic" % "1.1.2"
  val pickling      = "org.scala-lang"     %% "scala-pickling"  % "0.9.2-SNAPSHOT"
  val scalacheck    = "org.scalacheck"     %% "scalacheck"      % "1.11.6"
  val scalacompiler = "org.scala-lang"     %  "scala-compiler"  % scala
  val scalatest     = "org.scalatest"      %% "scalatest"       % "2.2.1"
  val specs2        = "org.specs2"         %% "specs2"          % "2.4.2-scalaz-7.0.6"
  val typesafe      = "com.typesafe"       %  "config"          % "1.2.1"

}
