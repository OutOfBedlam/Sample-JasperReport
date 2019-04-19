package sample.jasperreport

import net.sf.jasperreports.engine.{JREmptyDataSource, JasperCompileManager, JasperExportManager, JasperFillManager}
import org.scalatest.{BeforeAndAfterAll, FlatSpec}
import sample.db.Database

class DatabaseReportTest extends FlatSpec with BeforeAndAfterAll {

  private var db: sample.db.Database = _

  override def beforeAll(): Unit = {
    db = new Database
  }

  "jasper report" should "print with data" in {
    // 1. compile report from xml file
    val jasperReport = JasperCompileManager.compileReport("./src/test/reports/demo_data.jrxml")
    // 2. set parameters
    val params = new java.util.HashMap[String, AnyRef]()
    params.put("TITLE", "Database Example")
    // 3. get database connection
    val connection = db.ds.getConnection
    // 4. create JasperPrint
    val jasperPrint = JasperFillManager.fillReport(jasperReport, params, connection)
    // 5. create pdf
    JasperExportManager.exportReportToPdfFile(jasperPrint, "./src/test/reports/demo_data.pdf")
    // 6. view
    //net.sf.jasperreports.view.JasperViewer.viewReport(jasperPrint)
  }

}
