package com.api.base;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.api.utils.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseTest {

	protected ExtentTest test;

	@BeforeMethod
	public void beforeMethod(java.lang.reflect.Method method) {
		test = ExtentManager.createTest(method.getName());
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			test.fail(result.getThrowable());

		} else if (result.getStatus() == ITestResult.SKIP) {
			test.skip("test skipped" + result.getThrowable());

		} else {
			test.pass("Test case Passed");
		}
	}

}
