val SparkPackageRepo = "Spark Packages Repo" at "https://dl.bintray.com/spark-packages/maven"
val SparkCore = "org.apache.spark" % "spark-core_2.11" % "2.1.0"
val SparkSql = "org.apache.spark" % "spark-sql_2.11" % "2.1.0"
val SparkCassandraConnector = "com.datastax.spark" % "spark-cassandra-connector_2.11" % "2.0.1"

lazy val commonSettings = Seq(
  organization := "me.flygare",
  version := "1.0",
  scalaVersion := "2.11.8"
)

lazy val root = (project in file("."))
  .settings(
    commonSettings,
    name := "StressTester",
    libraryDependencies ++= Seq(
      SparkCassandraConnector,
      SparkCore,
      SparkSql
    ),
    resolvers += SparkPackageRepo,
    mainClass in (Compile,run) := Some(s"$organization.$name")
  )
