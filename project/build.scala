import sbt._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import com.mojolly.scalate.ScalatePlugin._
import ScalateKeys._

object StockResearchBuild extends Build {
  val Organization = "org.scalatra"
  val Name = "stock-research"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.10.3"
  val ScalatraVersion = "2.3.0"

  lazy val project = Project(
    "stock-research",
    file("."),
    settings = Defaults.defaultSettings ++ ScalatraPlugin.scalatraWithJRebel ++ scalateSettings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      //scalaVersion := ScalaVersion,
      resolvers += Classpaths.typesafeReleases,
      libraryDependencies ++= Seq(
        "io.spray" %%  "spray-json" % "1.2.6",
        "org.scalatra" %% "scalatra" % ScalatraVersion,
        "org.scalatra" %% "scalatra-scalate" % ScalatraVersion,
        "org.scalatra" %% "scalatra-specs2" % ScalatraVersion % "test",
        "ch.qos.logback" % "logback-classic" % "1.0.6" % "runtime",
        "org.eclipse.jetty" % "jetty-webapp" % "8.1.8.v20121106" % "container",
        "commons-httpclient" % "commons-httpclient" % "3.1",
      //  "com.codahale" % "jerkson_2.9.1" % "0.5.0",
        "org.sorm-framework" % "sorm" % "0.3.15",
        "org.json4s" %% "json4s-native" % "3.2.10",
        "jaxen" % "jaxen" % "1.1.4",

        "dom4j" % "dom4j" % "1.6.1",
        "commons-dbutils" % "commons-dbutils" % "1.6",
        "c3p0" % "c3p0" % "0.9.1.2",
        "commons-io" % "commons-io" % "2.4",
        "com.lambdaworks" %% "jacks" % "2.3.3",
        "mysql" % "mysql-connector-java" % "5.1.30",
        "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar"))),
      scalateTemplateConfig in Compile <<= (sourceDirectory in Compile) { base =>
        Seq(
          TemplateConfig(
            base / "webapp" / "WEB-INF" / "templates",
            Seq.empty, /* default imports should be added here */
            Seq.empty, /* add extra bindings here */
            Some("templates")))
      }))
}
