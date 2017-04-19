lazy val commonSettings = Seq(
  organization := "me.flygare",
  version := "1.0",
  scalaVersion := "2.11.8",
  test in assembly := {}
)

lazy val root = (project in file("."))
  .settings(
    commonSettings,
    name := "DatabaseLogic",
    libraryDependencies ++= Seq(
      "com.datastax.spark" %% "spark-cassandra-connector" % "2.0.1",
      "org.apache.spark" %% "spark-core" % "2.1.0",
      "org.apache.spark" %% "spark-sql" % "2.1.0",
      "org.scalatest" %% "scalatest" % "3.0.1" % "test"
    ),
    resolvers ++= Seq(
        "Spark Packages Repo" at "https://dl.bintray.com/spark-packages/maven"
    ),
    mainClass in(Compile, run) := Some(s"$organization.$name"),
    assemblyMergeStrategy in assembly := {
      case PathList("META-INF", xs @ _*) => MergeStrategy.discard
      case x => MergeStrategy.first
    },
    mainClass in assembly := Some(s"$organization.$name"),
    assemblyJarName in assembly := "DatabaseLogic.jar"
  )

enablePlugins(AssemblyPlugin)
