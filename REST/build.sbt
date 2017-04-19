lazy val commonSettings = Seq(
  organization := "me.flygare",
  version := "1.0",
  scalaVersion := "2.11.8",
  test in assembly := {}
)

lazy val root = (project in file("."))
  .settings(
    commonSettings,
    name := "REST",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http-core" % "10.0.5",
      "com.typesafe.akka" %% "akka-http" % "10.0.5",
      "com.typesafe.akka" %% "akka-http-testkit" % "10.0.5",
      "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.5",
      "com.typesafe.akka" %% "akka-http-jackson" % "10.0.5",
      "org.scalatest" %% "scalatest" % "3.0.1" % "test"
    ),
    mainClass in (Compile,run) := Some(s"$organization.$name"),
    mainClass in assembly := Some(s"$organization.$name"),
    assemblyJarName in assembly := "rest.jar"
  )

enablePlugins(AssemblyPlugin)

