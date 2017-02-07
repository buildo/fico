val typesafeConf = "com.typesafe" % "config" % "1.3.1"
val ficus = "com.iheart" %% "ficus" % "1.4.0"
val test = "org.scalatest" %% "scalatest" % "3.0.1"

val dependencies = Seq(
  ficus,
  typesafeConf,
  test
)

lazy val fico = (project in file(".")).
  settings(inThisBuild(Seq(
    name := "fico",
    //fork := true,
    //envVars := Map("FICO" -> "ciao"),
    resolvers += Resolver.bintrayRepo("kailuowang", "maven"),
    libraryDependencies ++= dependencies,
    addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
    parallelExecution in Test := false //so that data in db test don't get messed-up
  )))
