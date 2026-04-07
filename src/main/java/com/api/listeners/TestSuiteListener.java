package com.api.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;

import com.api.utils.ExtentManager;
import com.aventstack.extentreports.ExtentReports;

public class TestSuiteListener implements ITestListener {
	
	@Override
	public void onFinish(ITestContext context) {
		
		ExtentReports extent = ExtentManager.getExtent();
		extent.flush();
		
		
	}
	

}
