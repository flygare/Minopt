val SparkPackageRepo = "Spark Packages Repo" at "https://dl.bintray.com/spark-packages/maven"
val SparkCore = "org.apache.spark" % "spark-core_2.11" % "2.1.0"
val SparkSql = "org.apache.spark" % "spark-sql_2.11" % "2.1.0"
val SparkCassandraConnector = "datastax" % "spark-cassandra-connector" % "2.0.1-s_2.11"
val cassandraDriverCore = "com.datastax.cassandra" % "cassandra-driver-core" % "3.0.0"

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
      SparkCore,
      SparkSql,
      cassandraDriverCore
    ),
    resolvers += SparkPackageRepo,
    mainClass in (Compile,run) := Some(s"$organization.$name")
  )
