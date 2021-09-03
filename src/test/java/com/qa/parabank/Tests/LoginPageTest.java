package com.qa.parabank.Tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.parabank.Base.BaseTest;
import com.qa.parabank.Pages.HomePage;
import com.qa.parabank.Util.Constants;

public class LoginPageTest extends BaseTest
{
   
   @Test(priority =1)
   public void verifyPageTitle()
   {
	  String pageTitle= loginpage.getPageTitle();
	  System.out.println("The page title is:- "+pageTitle);
	  Assert.assertEquals(pageTitle, Constants.LOGIN_PAGE_TITLE, Constants.LOGIN_PAGE_TITLE_ERROR_MSG);
   }
   
   @Test(priority =2)
   public void verifyParaBankLogo()
   {
	   boolean paraBankLogo=loginpage.checkParaLogo();
	   Assert.assertTrue(paraBankLogo);
   }
   
   @Test(priority =3)
   public void verifyMenuList()
   {
	  List<String> menuList= loginpage.getMenuList();
	  System.out.println("Mene list: -" +menuList);
	  List<String>expectedList =new ArrayList<String>(Arrays.asList(Constants.MENU_LIST));
	  Assert.assertEquals(menuList, expectedList);
   }
   
   @Test(priority =4)
   public void verifyHomeIcon()
   {
	   Assert.assertTrue(loginpage.checkHomeIcon());
   }
   
   @Test(priority =5)
   public void verifyAboutIcon()
   {
	   Assert.assertTrue(loginpage.checkAboutIcon());
   }
   
   @Test(priority =6)
   public void verifyContactIcon()
   {
	   Assert.assertTrue(loginpage.checkContactIcon());
   }
   
   @Test(priority =7)
   public void verifyLoginText()
   {
	   String text =loginpage.checkloginText();
	   System.out.println("Login text is:- "+text);
	   Assert.assertEquals(text, Constants.LOGIN_TEXT);
   }
   
   @Test(priority =8)
   public void verifyAtmServicesLinkTest()
   {
	   List<String> list =loginpage.getAtmServicesLink();
	   System.out.println("ATM services link lis:- "+list);
	    List<String> expectedResult=new ArrayList<String>(Arrays.asList(Constants.ATM_SERVICES_LIST));
	    Assert.assertEquals(list, expectedResult);
   }
   
   @Test(priority =9)
   public void verifyOnlineServicesLinkTest()
   {
	   List<String> list =loginpage.getOnlineLink();
	   System.out.println("ATM services link lis:- "+list);
	    List<String> expectedResult=new ArrayList<String>(Arrays.asList(Constants.ONLINE_SERVICES_LIST));
	    Assert.assertEquals(list, expectedResult);
   }
   
  /* @Test
   public void verifyTodaysDatePresent()
   {
	   String todaysDate =loginpage.getTodaysDate();
	   
	   Date date =new Date();
	   SimpleDateFormat formatter =new SimpleDateFormat("MM/dd/yyyy");
       String toDate=formatter.format(date);
       System.out.println(toDate);
       
	   Assert.assertEquals(todaysDate, toDate);
   }*/
   
   @Test(priority =10)
   public void verifyLatestNewsText()
   {
	   String text =loginpage.getLatestNewsTest();
	   System.out.println("news text is:- "+text);
	   Assert.assertEquals(text, Constants.LATEST_NEWS);
   }
   
   @Test(priority =11)
   public void verifyNewsLink()
   {
	  List<String>list= loginpage.getNewsLink();
	  
	  List<String>expected=new ArrayList<String>(Arrays.asList(Constants.NEWS_LINK));
	  Assert.assertEquals(list, expected);
   }
   
   @Test(priority =12)
   public void verifyForgotLoginLink()
   {
	   String text =loginpage.getForgotLink();
	   System.out.println(text);
	   Assert.assertEquals(text, Constants.FORGOT_PASSWORD_TEXT);
   }
   
   @Test(priority =13)
   public void verifyRegisterLink()
   {
	   String text =loginpage.getRegisterLink();
	   System.out.println(text);
	   Assert.assertEquals(text, Constants.REGISTER);
   }
   
   @Test(priority =14)
   public void verifyLoginTest()
   {
	   HomePage homepage =loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	   String title=homepage.getPageTitle();
	   System.out.println("Homepage title is :- "+title);
	   Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
   }
 
   
   
}
