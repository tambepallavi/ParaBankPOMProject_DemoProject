package com.qa.parabank.Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil
{
    private static String TEST_DATA_SHEET_PATH="./src/main/java/com/qa/parabank/Testdata/Registrationdetails.xlsx";
    private static Workbook book;
    private static Sheet sheet;
    
    public static Object[][] getTestData(String SheetName)
    {
    	Object data[][]=null;
    	
    	try 
    	{
			FileInputStream ip=new FileInputStream(TEST_DATA_SHEET_PATH);
			try {
				book=WorkbookFactory.create(ip);
				sheet=book.getSheet(SheetName);
				
				data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
				
				for(int i=0;i<sheet.getLastRowNum();i++)
				{
					for(int j=0;j<sheet.getRow(0).getLastCellNum();j++)
					{
						data[i][j]=sheet.getRow(i+1).getCell(j).toString();
					}
				}
				
			} catch (InvalidFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} 
    	catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return data;
    	
    }
}
