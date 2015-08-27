name := "graphcreator"

version := "1.0"

lazy val `graphcreator` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(jdbc, anorm, cache, ws)

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")

//Postgres 9.4
libraryDependencies += "postgresql" % "postgresql" % "9.1-901-1.jdbc4"

//Slick 2 - ORM
//libraryDependencies += "com.typesafe.slick" % "slick_2.10" % "3.0.2"

libraryDependencies += "com.typesafe.play" %% "play-slick" % "1.0.1"

libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "1.0.1"

