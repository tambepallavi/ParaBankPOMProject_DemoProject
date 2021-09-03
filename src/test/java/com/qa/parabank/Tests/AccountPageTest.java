package com.qa.parabank.Tests;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.parabank.Base.BaseTest;
import com.qa.parabank.Util.Constants;

public class AccountPageTest extends BaseTest
{


	@BeforeClass
	public void homePageSetUp()
	{
		homepage= loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		accountPage =homepage.gotoAccountPage();
	}



	@Test(priority=1)
	public void verifyAccountPageTitle()
	{
		String title=accountPage.getAccountPageTitle();
		System.out.println("Account Page Title is : "+title);
		AssertJUnit.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE);
	}

	@Test(priority=2)
	public void verifyAccountText()
	{
		String text =accountPage.getOpenAccountText();
		System.out.println("Account page test is: -"+text);
		Assert.assertEquals(text, Constants.ACCOUNT_PAGE_TEXT);
	}
	@Test(priority=3)
	public void verifyAccountQuestion()
	{
		String text =accountPage.getAccountOpenQuestion();
		System.out.println("Account page test is: -"+text);
		Assert.assertEquals(text, Constants.ACCOUNT_PAGE_QUESTION);
	}

	@Test(priority=4)
	public void verifyAccountOpened()
	{
		accountPage.getOpenNewAccount(Constants.ACCOUNT_TYPE, Constants.ACCOUNT_NUMBER);
		String actualResult=accountPage.getAccountOpenedCongrtMessage();
		System.out.println("Account opened message: - "+actualResult);
		Assert.assertEquals(actualResult, Constants.ACCOUNT_OPEN_MESSAGE);
	}



}
