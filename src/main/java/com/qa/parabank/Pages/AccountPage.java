package com.qa.parabank.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.parabank.Util.Constants;
import com.qa.parabank.Util.ElementUtil;

public class AccountPage
{
	WebDriver driver;
	ElementUtil elementUtil;
	
    public AccountPage(WebDriver driver)
    {
		this.driver=driver;
		elementUtil =new ElementUtil(driver);
	}
    
    //By locators
    By openAccountText=By.xpath("//h1[text()='Open New Account']");
    By accountOpenQuestion =By.xpath("//b[text()='What type of Account would you like to open?']");
    By accountTypeDropDown=By.xpath("//select[@id='type']");
    By accountFundTransfer=By.xpath("//select[@id='fromAccountId']");
    By openNewAccountBtn=By.xpath("//input[@type='submit']");
    By accountOpenCongradulationMessage=By.xpath("//p[text()='Congratulations, your account is now open.']");
    
    public String getAccountPageTitle()
    {
    	return elementUtil.waitForPageTitleContentPresent(Constants.ACCOUNT_PAGE_TITLE, 10);
    }
    public String getOpenAccountText()
    {
    	return elementUtil.doGetText(openAccountText);
    }
    
    public String getAccountOpenQuestion()
    {
    	return elementUtil.doGetText(accountOpenQuestion);
    }
    
    public void getOpenNewAccount(String value,String value2)
    {
    	 elementUtil.selectDropdownValue(accountTypeDropDown, value);
    	 elementUtil.selectDropdownValue(accountFundTransfer, value2);
    	 elementUtil.doClick(openNewAccountBtn);
    }
    
    public String getAccountOpenedCongrtMessage()
    {
    	return elementUtil.doGetText(accountOpenCongradulationMessage);
    }

}
