val colossus = "com.tumblr" %% "colossus" % "0.8.3"

lazy val commonSettings = Seq(
  organization := "me.flygare",
  version := "1.0",
  scalaVersion := "2.11.8"
)

lazy val root = (project in file("."))
  .settings(
    commonSettings,
    name := "REST",
    libraryDependencies += colossus,
    mainClass in (Compile,run) := Some(s"$organization.$name")
  )
