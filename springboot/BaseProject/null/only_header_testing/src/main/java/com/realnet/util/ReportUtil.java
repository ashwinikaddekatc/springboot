package com.realnet.util;

import java.io.File;
import java.sql.DriverManager;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.query.JRQueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRProperties;

public class ReportUtil {

	public static void runReport(ServletContext servletcontext, HttpServletRequest request,
			HttpServletResponse httpservletresponse, String s, Map map) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		java.sql.Connection connection = null;
//		connection = DriverManager.getConnection("jdbc:oracle:thin:@10.1.2.90:1521:DEVINST", "KOEL_EXPWMS", "KOEL_EXPWMS");
		connection = DriverManager.getConnection("jdbc:oracle:thin:@10.1.1.20:1521:KIRAN", "KOEL_EXPWMS", "KOEL_EXPWMS");
//		connection = DriverManager.getConnection("jdbc:oracle:thin:@10.0.0.12:1522:VIS", "wasib", "wasib");
//		connection = DriverManager.getConnection("jdbc:oracle:thin:@122.170.116.113:1522:VIS", "wasib", "wasib");
		
		System.out
				.println("report file path=" + request.getServletContext().getRealPath("/WEB-INF/classes/com/springmvc/util"));
		String serverpathtoreport = request.getServletContext().getRealPath("/WEB-INF/classes/com/springmvc/util");
		// String localpathtoreport =
		// "/home/Navin/Desktop/mywork/SpringHibernateExample/src/main/java/com/report";
		File file = new File(serverpathtoreport + "/" + s);
		// System.out.println(servletcontext.getServerInfo());
		if (!file.exists())
			throw new JRRuntimeException("File " + s + " not found.");
		JasperReport jasperreport = (JasperReport) JRLoader.loadObjectFromFile(file.getPath());
		jasperreport.setProperty( "net.sf.jasperreports.query.executer.factory.plsql"
                ,"com.jaspersoft.jrx.query.PlSqlQueryExecuterFactory");
		
		jasperreport.setProperty(JRQueryExecuterFactory.QUERY_EXECUTER_FACTORY_PREFIX + "plsql", "com.jaspersoft.jrx.query.PlSqlQueryExecuterFactory");

//Maybe this too, but not positive
JRProperties.setProperty( JRQueryExecuterFactory.QUERY_EXECUTER_FACTORY_PREFIX+"plsql"
               ,"com.jaspersoft.jrx.query.PlSqlQueryExecuterFactory");
		System.out.println(file.getPath());
		JasperPrint jasperprint = JasperFillManager.fillReport(jasperreport, map, connection);
		
		httpservletresponse.setContentType("application/pdf");
		// httpservletresponse.setHeader("Content-Disposition","attachment;
		// filename=\"Offer_letter\"");
		javax.servlet.ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperprint, servletoutputstream);
		
//		JRExporter exporter = new JRPdfExporter();
//		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletoutputstream);
//		   exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperprint);
//		   exporter.exportReport();
		
		servletoutputstream.flush();
		servletoutputstream.close();
		connection.close();
	}

	/*
	 * public byte[] getPdfStream(ServletContext servletcontext, String s, Map
	 * map) throws Exception { Class.forName("oracle.jdbc.driver.OracleDriver");
	 * java.sql.Connection connection = null; connection =
	 * DriverManager.getConnection("jdbc:oracle:thin:@10.0.0.5:1521:xe","omhrms"
	 * ,"omhrms"); File file = new
	 * File(servletcontext.getRealPath("/WEB-INF/classes/reports/" + s)); //
	 * System.out.println(servletcontext.getServerInfo()); if(!file.exists())
	 * throw new JRRuntimeException("File " + s + " not found."); JasperReport
	 * jasperreport = (JasperReport)JRLoader.loadObject(file.getPath());
	 * net.sf.jasperreports.engine.JasperPrint jasperprint =
	 * JasperFillManager.fillReport(jasperreport, map, connection);
	 * 
	 * return JasperExportManager.exportReportToPdf(jasperprint); }
	 * 
	 */

}
