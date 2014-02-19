package com.analemma.jasper_test;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;


public class CustomDataSourceExample {

	public static void main(String[] args) {
		try {
			CustomDataSourceExample.pdf();
		}
		catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void pdf() throws JRException
	{
		long start = System.currentTimeMillis();
		
		String compileeFileName = JasperCompileManager
						.compileReportToFile("/home/mladenc/git/jasper-test/jasper-test/src/main/resources/DataSourceReport.jrxml");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("ReportTitle", "Address Report");
		parameters.put("DataFile", "CustomDataSource.java");
		
		JFrame frame = new JFrame("Report");
		JasperPrint jasperPrint = JasperFillManager.fillReport(
				compileeFileName, parameters, new CustomDataSource());
		frame.getContentPane().add(new JRViewer(jasperPrint));
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		System.err.println("PDF creation time : " + (System.currentTimeMillis() - start));
	}
}
