package sample.jasperreport

import net.sf.jasperreports.engine.{JREmptyDataSource, JasperCompileManager, JasperExportManager, JasperFillManager}
import org.scalatest.{BeforeAndAfterAll, FlatSpec}

class ReportTest extends FlatSpec with BeforeAndAfterAll {

  override def beforeAll(): Unit = {

  }

  "jasper report" should "print hello world" in {
    val jasperReport = JasperCompileManager.compileReport("./src/test/reports/demo_helloworld.jrxml")
    val jasperPrint = JasperFillManager.fillReport(jasperReport, new java.util.HashMap[String, AnyRef](), new JREmptyDataSource())
    JasperExportManager.exportReportToPdfFile(jasperPrint, "./src/test/reports/demo_helloworld.pdf")
  }

  it should "print with text param" in {
    val jasperReport = JasperCompileManager.compileReport("./src/test/reports/demo_param.jrxml")
    val params = new java.util.HashMap[String, AnyRef]()
    params.put("GREETING", "Hello World?")
    val jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource())
    JasperExportManager.exportReportToPdfFile(jasperPrint, "./src/test/reports/demo_param.pdf")
  }

  it should "print with data" in {
    val jasperReport = JasperCompileManager.compileReport("./src/test/reports/demo_data.jrxml")
    val params = new java.util.HashMap[String, AnyRef]()
    params.put("GREETING", "Hello World?")
    val jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource())
    JasperExportManager.exportReportToPdfFile(jasperPrint, "./src/test/reports/demo_data.pdf")
  }

}
