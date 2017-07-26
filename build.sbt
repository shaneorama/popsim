import org.scalajs.sbtplugin.ScalaJSPlugin.AutoImport.jsDependencies

resolvers in GlobalScope += "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

val scalaV = "2.12.2"
val springV = "1.5.6.RELEASE"
val scalaJsDomV = "0.9.1"

scalaJSUseMainModuleInitializer := true


def commonSettings = Seq(
  scalaVersion := scalaV
)

lazy val popsim = project.in(file("."))
  .aggregate(server, client, paper, simulator)

lazy val server = project
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "org.springframework.boot" % "spring-boot-starter-websocket" % springV,
      "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided"
    ),

    resourceGenerators in Compile += Def.task {
      val clientResources = (resourceDirectory in Compile in client).value
      val serverResources = (resourceManaged in Compile).value / "static"
      IO.copyDirectory(clientResources, serverResources)

      val clientFile = (fastOptJS in Compile in client).value.data
      val serverFile = (resourceManaged in Compile).value / "static" / clientFile.getName
      IO.copyFile(clientFile, serverFile)

      Seq() ++ serverResources.listFiles()
    }.taskValue,
    watchSources ++= (watchSources in client).value
  )
  .dependsOn(sharedJVM, client)

lazy val client = project
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % scalaJsDomV
    ),
    jsDependencies += "org.webjars" % "paperjs" % "0.9.22" / "paper-full.min.js" commonJSName "paper"
  )
  .dependsOn(paper,sharedJS)

lazy val simulator = project
  .settings(commonSettings: _*)

lazy val paper = project
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % scalaJsDomV
    )
  )

lazy val shared = crossProject.crossType(CrossType.Pure)
  .settings(commonSettings: _*)

lazy val sharedJS = shared.js
lazy val sharedJVM = shared.jvm

