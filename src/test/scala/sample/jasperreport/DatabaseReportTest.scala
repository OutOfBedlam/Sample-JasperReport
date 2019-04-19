package sample.jasperreport

import java.util.Collections

import net.sf.jasperreports.engine.{JasperCompileManager, JasperExportManager, JasperFillManager, SimpleJasperReportsContext}
import net.sf.jasperreports.repo.{FileRepositoryPersistenceServiceFactory, FileRepositoryService, PersistenceServiceFactory, RepositoryService}
import org.scalatest.{BeforeAndAfterAll, FlatSpec}
import sample.db.Database

class DatabaseReportTest extends FlatSpec with BeforeAndAfterAll {

  private var db: sample.db.Database = _

  override def beforeAll(): Unit = {
    db = new Database
  }

  "jasper report" should "print with data" in {

    // this repository service extension allows using relative path of resource in jrxml file
    //
    // https://community.jaspersoft.com/jasperreports-library/issues/10941
    val ctx = new SimpleJasperReportsContext

    val fileRepository = new FileRepositoryService(ctx, "./src/test/reports", false)
    ctx.setExtensions(classOf[RepositoryService], Collections.singletonList(fileRepository))
    ctx.setExtensions(classOf[PersistenceServiceFactory], Collections.singletonList(FileRepositoryPersistenceServiceFactory.getInstance))

    // 1. compile report from xml file

    val jasperReport = JasperCompileManager.getInstance(ctx).compile("./src/test/reports/demo_data.jrxml")

    // 2. set parameters
    val params = new java.util.HashMap[String, AnyRef]()
    params.put("TITLE", "Database Example")

    // 3. get database connection
    val connection = db.ds.getConnection

    // 4. create JasperPrint
    val jasperPrint = JasperFillManager.getInstance(ctx).fill(jasperReport, params, connection)

    // 5. create pdf
    JasperExportManager.getInstance(ctx).exportToPdfFile(jasperPrint, "./src/test/reports/demo_data.pdf")

    // 6. create html
    //JasperExportManager.getInstance(ctx).exportToHtmlFile(jasperPrint, "./src/test/reports/demo_data.pdf")

    // 7. view
    //net.sf.jasperreports.view.JasperViewer.viewReport(jasperPrint)
  }

}
