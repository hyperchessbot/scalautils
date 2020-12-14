import Dependencies._

ThisBuild / scalaVersion         := "2.13.0"
ThisBuild / libraryDependencies  += scalaTest % Test
ThisBuild / credentials          += Credentials(Path.userHome / ".sbt" / ".credentials")

lazy val release = (project in file("."))
  .settings(
  	name             := "scalautils",
    version          := "1.0.0",
	organization     := "io.github.hyperchessbot",
	organizationName := "hyperchessbot",
	publishTo        := Some("Artifactory Realm" at "https://hyperbot.jfrog.io/artifactory/hyperbot")
  )

lazy val snapshot = (project in file("."))
  .settings(
    name             := "scalautils",
    version          := "0.1.0-SNAPSHOT",
	organization     := "com.example",
	organizationName := "example",
	publishTo        := Some("Artifactory Realm" at "https://hyperbot.jfrog.io/artifactory/hyperbot;build.timestamp=" + new java.util.Date().getTime)
  )
