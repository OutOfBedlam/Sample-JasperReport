package sample.jasperreport

import net.sf.jasperreports.engine._
import org.scalatest.FlatSpec

//
// Since jdt compiler get class path from system property 'java.class.path',
// 'sbt test' requires set 'fork in Test := true'.
// otherwise the compiler can not find system classes
//
class HelloWorldReportTest extends FlatSpec {

  "jasper report" should "print hello world" in {
//    val ctx: JasperReportsContext = DefaultJasperReportsContext.getInstance()
//    ctx.setProperty("net.sf.jasperreports.compiler.keep.java.file", "true")
//    ctx.setProperty("net.sf.jasperreports.compiler.temp.dir", "src/test/reports")
//    ctx.setProperty("net.sf.jasperreports.compiler.class", "net.sf.jasperreports.engine.design.JRJdtCompiler")
//    println(s"1===> ${System.getProperty("java.class.path")}")
//    println(s"2===> ${ctx.getProperty("net.sf.jasperreports.compiler.classpath")}")

    val jasperReport = JasperCompileManager.compileReport("./src/test/reports/demo_helloworld.jrxml")
    val jasperPrint = JasperFillManager.fillReport(jasperReport, new java.util.HashMap[String, AnyRef], new JREmptyDataSource)
    JasperExportManager.exportReportToPdfFile(jasperPrint, "./src/test/reports/demo_helloworld.pdf")
  }

  it should "print with text param" in {
    val jasperReport = JasperCompileManager.compileReport("./src/test/reports/demo_param.jrxml")
    val params = new java.util.HashMap[String, AnyRef]()
    params.put("GREETING", "Hello World?")
    val jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource)
    JasperExportManager.exportReportToPdfFile(jasperPrint, "./src/test/reports/demo_param.pdf")
  }

}
