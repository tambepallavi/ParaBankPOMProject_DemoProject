package com.qa.parabank.Tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.parabank.Base.BaseTest;
import com.qa.parabank.Util.Constants;

public class HomePageTest extends BaseTest
{
   @BeforeClass
   public void homePageSetUp()
   {
	   homepage= loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
   }
  
  @Test(priority=1)
   public void verifyPageTitle()
   {
	  String title= homepage.getPageTitle();
	  System.out.println("Home page title- "+title);
	  Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
   }
   
   @Test(priority=2)
   public void verifyWelcomeMessage()
   {
	   String msg=homepage.getWelcomeMessageDisplay();
	   System.out.println("Welcome message - "+msg);
	   Assert.assertEquals(msg, Constants.HOMEPAGE_WELCOME_MESSEGE);
   }
   
   @Test(priority=3)
   public void verifyOverViewMessage()
   {
	  String overviewMsg= homepage.getOverviewMessage();
	  System.out.println("overview message - "+overviewMsg);
	  Assert.assertEquals(overviewMsg, Constants.ACCOUNT_TEXT);
   }
   
   @Test(priority=4)
   public void verifyOverviewServiceMessage()
   {
	  String overviewMessage= homepage.getOverviewServiceMessage();
	  System.out.println("overview service message - "+overviewMessage);
	  Assert.assertEquals(overviewMessage, Constants.ACCOUNT_SERVICES_TEXT);
   }
   
  @Test(priority=5) 
  public void verifyOverViewServicesList()
  {
	  List<String>list =homepage.getOverviewServicesList();
	  System.out.println("Overview list: - "+list);
	  List<String>expectedList=new ArrayList<String>(Arrays.asList(Constants.OVERVIEW_SERVICES_LIST));
	  Assert.assertEquals(list, expectedList);
  }
   
  @Test(priority=6)
  public void verifyAccountDetail()
  {
	  List<String> list =homepage.getOverviewAccountDetails();
	  System.out.println("Account table: "+list);
	  
	  //List<String>expectedResult=new ArrayList<String>();
  }
   
  
}
