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

val jasperReportVersion = "6.8.0"

libraryDependencies ++= Seq(
  //    "net.sf.jasperreports" % "jasperreports-functions" % jasperReportVersion,
  //    "net.sf.jasperreports" % "jasperreports-chart-themes" % jasperReportVersion,
  //    "net.sf.jasperreports" % "jasperreports-chart-customizers" % jasperReportVersion,
  //    "net.sf.jasperreports" % "jasperreports-custom-visualization" % jasperReportVersion,
  //    "net.sf.jasperreports" % "jasperreports-annotation-processors" % jasperReportVersion,
  //    "net.sf.jasperreports" % "jasperreports-metadata" % jasperReportVersion,
  "net.sf.jasperreports" % "jasperreports-fonts" % jasperReportVersion,
  "net.sf.jasperreports" % "jasperreports" % jasperReportVersion exclude("com.lowagie", "itext"),
  "com.lowagie" % "itext" % "2.1.7", // instead of glyph rendering patched version: "itext-2.1.7.js4.jar"
)