name := "reactor"
 
version := "1.0"
  
scalaVersion := "2.10.0"
   
resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies +=
      "com.typesafe.akka" %% "akka-actor" % "2.1.1"

libraryDependencies +=
      "org.specs2" %% "specs2" % "1.14" % "test"


libraryDependencies +=
      "org.scalacheck" %% "scalacheck" % "1.10.0" % "test"


libraryDependencies +=
      "junit" % "junit" % "4.11" % "test"
