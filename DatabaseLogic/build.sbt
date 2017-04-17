val SparkPackageRepo = "Spark Packages Repo" at "https://dl.bintray.com/spark-packages/maven"

val SparkCore = "org.apache.spark" %% "spark-core" % "2.1.0"
val SparkSql = "org.apache.spark" %% "spark-sql" % "2.1.0"
val SparkCassandraConnector = "com.datastax.spark" %% "spark-cassandra-connector" % "2.0.1"
val ScalaTest = "org.scalatest" %% "scalatest" % "3.0.1" % "test"

lazy val commonSettings = Seq(
  organization := "me.flygare",
  version := "1.0",
  scalaVersion := "2.11.8",
  test in assembly := {}
)

lazy val root = (project in file("."))
  .settings(
    commonSettings,
    name := "StressTester",
    libraryDependencies ++= Seq(
      SparkCassandraConnector,
      SparkCore,
      SparkSql,
      ScalaTest
    ),
    resolvers ++= Seq(
      SparkPackageRepo
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