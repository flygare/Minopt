val colossus = "com.tumblr" %% "colossus" % "0.8.3"

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
    libraryDependencies += colossus,
    resolvers += Resolver.sonatypeRepo("public"),
    mainClass in (Compile,run) := Some(s"$organization.$name"),
    mainClass in assembly := Some(s"$organization.$name"),
    assemblyJarName in assembly := "rest.jar"
  )

enablePlugins(AssemblyPlugin)