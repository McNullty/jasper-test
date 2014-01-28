package com.analemma.jasper_test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;


public class JasperTest {
	public static void main(String[] args) {
		String sourceFileName = "/home/mladenc/git/jasper-test/jasper-test/src/main/resources/Test_A4.jrxml";

		System.out.println("Compiling Report Design ...");
		try {
			String compileeFileName = JasperCompileManager
					.compileReportToFile(sourceFileName);

			DataBeanList DataBeanList = new DataBeanList();
			ArrayList<DataBean> dataList = DataBeanList.getDataBeanList();

			Map<String, Object> parameters = new HashMap<String, Object>();
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					dataList);

			JFrame frame = new JFrame("Report");
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					compileeFileName, parameters, beanColDataSource);
			frame.getContentPane().add(new JRViewer(jasperPrint));
			frame.pack();
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		} catch (JRException e) {
			e.printStackTrace();
		}
		System.out.println("Done compiling!!! ...");
	}
}
