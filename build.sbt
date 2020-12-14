import Dependencies._

ThisBuild / scalaVersion         := "2.13.0"
ThisBuild / libraryDependencies  += scalaTest % Test
ThisBuild / credentials          += Credentials(Path.userHome / ".sbt" / ".credentials")

lazy val release = (project in file(".")).settings(	  

	name             := (buildEnv.value match {
							case BuildEnv.Snapshot => 	"scalautils"
							case _ => 					"scalautils"
						}),
						
	version          := (buildEnv.value match {
							case BuildEnv.Snapshot => 	"0.1.0-SNAPSHOT"
							case _ => 					"1.0.0"
						}),
						
	organization     := (buildEnv.value match {
							case BuildEnv.Snapshot => 	"com.example"
							case _ => 					"io.github.hyperchessbot"
						}),
						
	organizationName := (buildEnv.value match {
							case BuildEnv.Snapshot => 	"example"
							case _ => 					"hyperchessbot"
						}),
						
	publishTo        := (buildEnv.value match {
							case BuildEnv.Snapshot =>	Some("Artifactory Realm" at "https://hyperbot.jfrog.io/artifactory/hyperbot;build.timestamp=" + new java.util.Date().getTime)
							case _ =>					Some("Artifactory Realm" at "https://hyperbot.jfrog.io/artifactory/hyperbot")
						})
						
)
		