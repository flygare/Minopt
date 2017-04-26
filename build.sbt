lazy val commonSettings = Seq(
  organization := "me.flygare",
  version := "1.0",
  scalaVersion := "2.11.8",
  test in assembly := {}
)

lazy val monolithic = (project in file("Monolithic"))
  .settings(
    commonSettings,
    name := "Monolithic"
  )

lazy val restService = (project in file("RestService"))
  .settings(
    commonSettings,
    name := "RestService",
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
    assemblyJarName in assembly := "restService.jar"
  )

lazy val addressService = (project in file("AddressService"))
  .settings(
    commonSettings,
    name := "AddressService",
    libraryDependencies ++= Seq(
      "com.datastax.spark" %% "spark-cassandra-connector" % "2.0.1",
      "org.apache.spark" %% "spark-core" % "2.1.0",
      "org.apache.spark" %% "spark-sql" % "2.1.0",
      "org.scalatest" %% "scalatest" % "3.0.1" % "test",
      "com.typesafe.akka" %% "akka-http-core" % "10.0.5",
      "com.typesafe.akka" %% "akka-http" % "10.0.5",
      "com.typesafe.akka" %% "akka-http-testkit" % "10.0.5",
      "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.5",
      "com.typesafe.akka" %% "akka-http-jackson" % "10.0.5",

      // To use jackson 2.8.7 over 2.6.5, todo fix the duplicate in dependencies
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.8.7",
      "com.fasterxml.jackson.core" % "jackson-databind" % "2.8.7"
    ),
    resolvers ++= Seq(
      "Spark Packages Repo" at "https://dl.bintray.com/spark-packages/maven"
    ),
    mainClass in(Compile, run) := Some(s"$organization.$name"),
    mainClass in assembly := Some(s"$organization.$name"),
    assemblyJarName in assembly := "addressService.jar",
    assemblyMergeStrategy in assembly := {
      case PathList("META-INF", "services", "org.apache.hadoop.fs.FileSystem") => MergeStrategy.filterDistinctLines
      case PathList("META-INF", xs @ _*) => MergeStrategy.discard
      case x => MergeStrategy.first
    }
  )

lazy val profileService = (project in file("ProfileService"))
  .settings(
    commonSettings,
    name := "ProfileService",
    libraryDependencies ++= Seq(
      "com.datastax.spark" %% "spark-cassandra-connector" % "2.0.1",
      "org.apache.spark" %% "spark-core" % "2.1.0",
      "org.apache.spark" %% "spark-sql" % "2.1.0",
      "org.scalatest" %% "scalatest" % "3.0.1" % "test",
      "com.typesafe.akka" %% "akka-http-core" % "10.0.5",
      "com.typesafe.akka" %% "akka-http" % "10.0.5",
      "com.typesafe.akka" %% "akka-http-testkit" % "10.0.5",
      "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.5",
      "com.typesafe.akka" %% "akka-http-jackson" % "10.0.5",

      // To use jackson 2.8.7 over 2.6.5, todo fix the duplicate in dependencies
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.8.7",
      "com.fasterxml.jackson.core" % "jackson-databind" % "2.8.7"
    ),
    resolvers ++= Seq(
      "Spark Packages Repo" at "https://dl.bintray.com/spark-packages/maven"

    ),
    mainClass in(Compile, run) := Some(s"$organization.$name"),
    mainClass in assembly := Some(s"$organization.$name"),
    assemblyJarName in assembly := "profileService.jar",
    assemblyMergeStrategy in assembly := {
      case PathList("META-INF", "services", "org.apache.hadoop.fs.FileSystem") => MergeStrategy.filterDistinctLines
      case PathList("META-INF", xs @ _*) => MergeStrategy.discard
      case x => MergeStrategy.first
    }
  )

lazy val personService = (project in file("PersonService"))
  .settings(
    commonSettings,
    name := "PersonService",
    libraryDependencies ++= Seq(
      "com.datastax.spark" %% "spark-cassandra-connector" % "2.0.1",
      "org.apache.spark" %% "spark-core" % "2.1.0",
      "org.apache.spark" %% "spark-sql" % "2.1.0",
      "org.scalatest" %% "scalatest" % "3.0.1" % "test",
      "com.typesafe.akka" %% "akka-http-core" % "10.0.5",
      "com.typesafe.akka" %% "akka-http" % "10.0.5",
      "com.typesafe.akka" %% "akka-http-testkit" % "10.0.5",
      "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.5",
      "com.typesafe.akka" %% "akka-http-jackson" % "10.0.5",

      // To use jackson 2.8.7 over 2.6.5, todo fix the duplicate in dependencies
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.8.7",
      "com.fasterxml.jackson.core" % "jackson-databind" % "2.8.7"
    ),
    resolvers ++= Seq(
      "Spark Packages Repo" at "https://dl.bintray.com/spark-packages/maven"

    ),
    mainClass in(Compile, run) := Some(s"$organization.$name"),
    mainClass in assembly := Some(s"$organization.$name"),
    assemblyJarName in assembly := "personService.jar",
    assemblyMergeStrategy in assembly := {
      case PathList("META-INF", "services", "org.apache.hadoop.fs.FileSystem") => MergeStrategy.filterDistinctLines
      case PathList("META-INF", xs @ _*) => MergeStrategy.discard
      case x => MergeStrategy.first
    }
  )

enablePlugins(AssemblyPlugin)

