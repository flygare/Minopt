val spark = "Spark Packages Repo" at "https://dl.bintray.com/spark-packages/maven"
val SparkCassandraConnector = "datastax" % "spark-cassandra-connector" % "2.0.1-s_2.11"

lazy val commonSettings = Seq(
  organization := "me.flygare",
  version := "1",
  scalaVersion := "2.11.8"
)

lazy val root = (project in file("."))
  .settings(
    commonSettings,
    name := "StressTester",
    libraryDependencies += SparkCassandraConnector,
    resolvers += spark,
    mainClass in (Compile,run) := Some(s"$organization.$name")
  )
