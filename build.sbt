lazy val root = (project in file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "scalajs-react-renderBackend-debugging",
    scalaVersion := "2.13.8",
    crossScalaVersions ++= List("2.13.8", "3.0.0"),
    libraryDependencies ++= List(
      "org.scala-js" %%% "scalajs-dom" % "2.1.0",
      "com.github.japgolly.scalajs-react" %%% "core" % "2.0.1"
    )
  )
