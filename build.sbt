name := "Sample-JasperReport"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  // Test
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  // Logging
  "ch.qos.logback" % "logback-classic" % "1.2.3", // 01-Apr-2017 updated
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0",
)

//
// H2 Database
//
libraryDependencies += "com.h2database" % "h2" % "1.4.198"

//
// Jasper Report
//
val jasperReportVersion = "6.8.0"

libraryDependencies ++= Seq(
  // "net.sf.jasperreports" % "jasperreports-chart-themes", // this extension requires spring framework
  "net.sf.jasperreports" % "jasperreports-chart-customizers",
  "net.sf.jasperreports" % "jasperreports-functions",
  "net.sf.jasperreports" % "jasperreports-annotation-processors",
  "net.sf.jasperreports" % "jasperreports-custom-visualization",
  "net.sf.jasperreports" % "jasperreports-metadata",
  "net.sf.jasperreports" % "jasperreports-fonts",
  "net.sf.jasperreports" % "jasperreports",
).map( _ % jasperReportVersion )

//
// !!! add resolver to solve the issue related "com.lowagie" % "itext" % "2.1.7.js6"
// https://community.jaspersoft.com/questions/1071031/itext-js6-dependency-issue
//
resolvers += "jaspersoft-third-party" at "http://jaspersoft.jfrog.io/jaspersoft/third-party-ce-artifacts/"