package com.api.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

	private static ExtentReports extent;

	public static ExtentReports getExtent() {

		if (extent == null) {

			ExtentSparkReporter spark = new ExtentSparkReporter("reports/usertestsreport.html");
			spark.config().setDocumentTitle("API test Report for user module");
			spark.config().setReportName("API Test report user module");
			extent = new ExtentReports();
			extent.attachReporter(spark);
			extent.setSystemInfo("Environment", "Demo");
			extent.setSystemInfo("Tester name", "jack");

		}

		return extent;

	}

	public static ExtentTest createTest(String testName) {
		return getExtent().createTest(testName);
	}

}
