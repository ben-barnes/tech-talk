lazy val root = (project in file (".")).
  settings(
    name := "tech-talk",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.11.8",
    libraryDependencies ++= Seq(
      "org.scalaz" %% "scalaz-core" % "7.2.2"
    ),
    scalacOptions ++= Seq(
      "-Xlint",  "-feature"
    )
  )
