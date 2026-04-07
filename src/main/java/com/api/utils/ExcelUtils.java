package com.api.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;


public class ExcelUtils {
	
	@DataProvider(name="userData")
	public static Object[][] readExcelData() throws IOException{
		
		String filePath = "src/test/resources/testdata/UserTestData.xlsx";
		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		Sheet sheet = workbook.getSheetAt(0);
		int rows = sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getLastCellNum();
        
		Object[][] data = new Object[rows - 1][cols];
		
		for (int i=1;i<rows;i++) {
			Row row = sheet.getRow(i);
			for (int j=0;j<cols;j++) {
				
				data[i-1][j]=row.getCell(j).toString();
			}
		}
	   
		workbook.close();
		fis.close();
		return data ;

		
	}

}
