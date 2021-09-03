package com.qa.parabank.Tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.parabank.Base.BaseTest;
import com.qa.parabank.Pages.RegisterPage;
import com.qa.parabank.Util.Constants;
import com.qa.parabank.Util.ExcelUtil;

public class RegisterPageTest extends BaseTest
{
	
	   @BeforeClass
	   public void homePageSetUp()
	   {
		   registrationpage=new RegisterPage(driver);
	   }
	  
	   @Test(priority=2)
	   public void verifyPageTitle()
	   {
		  String title= registrationpage.getPageTitle();
		  System.out.println("Page title is:- "+title);
		  Assert.assertEquals(title, Constants.REGISTRATION_PAGE_TITLE);
	   }
	   
	   @DataProvider
	   public Object[][] getRegistrationFormData()
	   {
		   Object data[][]=ExcelUtil.getTestData(Constants.REGISTRATION_SHEET_NAME);
		   return data;
	   }
	   @Test(priority=1, dataProvider="getRegistrationFormData")
	   public void verifyRegistrationForm(String fName,String lName,String addr,String city,String state,String Zipcode,String SSN,String uName,String pwd,String confirmPwd)
	   {
		   registrationpage.getRegistrationFormField(fName,lName,addr,city,state,Zipcode,SSN,uName,pwd,confirmPwd);
		  // String text=registrationpage.getRegistrationSuccessMessage();
		   String text=loginpage.getPageTitle();
		   Assert.assertEquals(text, Constants.LOGIN_PAGE_TITLE);
	   }
	   
	   
	   
	
}
