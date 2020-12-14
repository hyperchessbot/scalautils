// Based on https://github.com/ryanberckmans/sbt-optimize-prod-with-build-env-plugin-example/blob/master/project/BuildEnvPlugin.scala
import sbt._
import sbt.Keys._
import sbt.plugins.JvmPlugin

object BuildEnvPlugin extends AutoPlugin {
  override def trigger = AllRequirements
  override def requires = JvmPlugin
  object autoImport {
    object BuildEnv extends Enumeration {
      val Snapshot, Release = Value
    }
    val buildEnv = settingKey[BuildEnv.Value]("the current build environment")
  }
  import autoImport._
  override def projectSettings: Seq[Setting[_]] = Seq(
    buildEnv := {
      sys.props.get("env")
         .orElse(sys.env.get("ENV"))
         .flatMap {
           case "snapshot" => Some(BuildEnv.Snapshot)
           case _ => Some(BuildEnv.Release)           
         }
         .getOrElse(BuildEnv.Release)
    },
    onLoadMessage := {
      val defaultMessage = onLoadMessage.value // depend on the old message as well
      val env = buildEnv.value
      s"""|$defaultMessage
          |Running in build environment: $env""".stripMargin
    }
  )
}