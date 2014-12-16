import sbt._
import Keys._

name := "Frankenstein"

lazy val laboratory = project.in(file("laboratory"))

lazy val sparky = project.in(file("sparky"))

lazy val root = project.in(file(".")).aggregate(laboratory, sparky)
