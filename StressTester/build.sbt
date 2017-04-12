val spark = "Spark Packages Repo" at "https://dl.bintray.com/spark-packages/maven"
val Spark = "org.apache.spark" % "spark-core_2.11" % "2.1.0"
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
    libraryDependencies ++= Seq(
      SparkCassandraConnector,
      Spark
    ),
    resolvers += spark,
    mainClass in (Compile,run) := Some(s"$organization.$name")
  )
