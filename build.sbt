name := "stock-research"

scalaVersion := "2.11.2"

resolvers += "spray" at "http://repo.spray.io/"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

unmanagedBase := baseDirectory.value / "local-lib"

 libraryDependencies += "org.apache.lucene" % "lucene-core" % "4.9.0"
 
 libraryDependencies += "org.apache.lucene" % "lucene-queryparser" % "4.9.0"
 
 libraryDependencies += "org.apache.lucene" % "lucene-analyzers-common" % "4.9.0"
 
 libraryDependencies += "org.apache.lucene" % "lucene-analyzers" % "3.6.2"
 
 libraryDependencies += "log4j" % "log4j" % "1.2.9"
 
libraryDependencies += "org.scalaj" %% "scalaj-http" % "0.3.16"
 
libraryDependencies += "org.jsoup" % "jsoup" % "1.8.1"

 