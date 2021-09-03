package com.qa.parabank.Pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.parabank.Base.BasePage;
import com.qa.parabank.Util.Constants;
import com.qa.parabank.Util.ElementUtil;

public class HomePage extends BasePage
{
	ElementUtil elementUtil;
    WebDriver driver;
	
    //xpaths
    By welcomeMsg=By.xpath("//p[@class='smallText']//b");
    By accountOverviewText=By.xpath("//h1[contains(text(),'Accounts Overview')]");
    By overivewText =By.xpath("//h2[contains(text(),'Account Services')]");
    By overviewServiceLis=By.xpath("//div[@id='leftPanel']//a");
    By overviewAccountDetail=By.xpath("//tr[@class='ng-scope']//td");
   // By overviewAccountDetail=By.xpath("//table[@id='accountTable']//tr//th");
    By openNewAccount=By.xpath("//a[text()='Open New Account']");
  
    
    public HomePage(WebDriver driver) 
    {
    	this.driver=driver;
    	elementUtil=new ElementUtil(driver);
    }
    
    public String getPageTitle()
    {
    	return elementUtil.waitForPageTitleContentPresent(Constants.HOME_PAGE_TITLE, 10);
    }
    
    public String getWelcomeMessageDisplay()
    {
    	if(elementUtil.doIsDisplayed(welcomeMsg))
    	{
    		return elementUtil.doGetText(welcomeMsg);
    	}
    	return null;
    }

    public String getOverviewMessage()
    {
    	if(elementUtil.doIsDisplayed(accountOverviewText))
    	{
    		return elementUtil.doGetText(accountOverviewText);
    	}
    	return null;
    }
    
    public String getOverviewServiceMessage()
    {
    	return elementUtil.doGetText(overivewText);
    }
    
    public List<String> getOverviewServicesList()
    {
    	return elementUtil.getDropdownValueWithoutSelectClass(overviewServiceLis);
    }
    
    public List<String> getOverviewAccountDetails()
    {
    	return elementUtil.getDropdownValueWithoutSelectClass(overviewAccountDetail);
    }
    
    public AccountPage gotoAccountPage()
    {
    	clickOnOpenNewAccount();
    	return new AccountPage(driver);
    }
    
    private void clickOnOpenNewAccount()
    {
    	elementUtil.waitForElementTobeLocated(openNewAccount, 10);
    	elementUtil.doClick(openNewAccount);
    }
}
