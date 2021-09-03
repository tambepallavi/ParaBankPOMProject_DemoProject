package com.qa.parabank.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.parabank.Base.BasePage;
import com.qa.parabank.Util.Constants;
import com.qa.parabank.Util.ElementUtil;

public class LoginPage extends BasePage
{
	ElementUtil elementUtil;
    WebDriver driver;
    
    public LoginPage(WebDriver driver) 
    {
		this.driver=driver;
		elementUtil=new ElementUtil(driver);
	}
    
    //By locators
    By paraLogo =By.xpath("//img[@class='logo']");
    By menuList=By.xpath("//ul[@class='leftmenu']//li");
    By homeIcon = By.xpath("//li[@class='home']//a[text()='home']");
    By aboutusIcon =By.xpath("//li[@class='aboutus']//a[text()='about']");
    By contactIcon=By.xpath("//li[@class='contact']//a[text()='contact']");
    By customerLoginText=By.xpath("//h2[text()='Customer Login']");
    By atmServicesList =By.xpath("//ul[@class='services']/li[@class='captionone']//following-sibling::li");
    By onlineServices=By.xpath("//ul[@class='servicestwo']/li[@class='captiontwo']//following-sibling::li");
    By todaysDate= By.xpath("//li[@class='captionthree']");
    By latestNews=By.xpath("//h4[text()='Latest News']");
    By newsLink=By.xpath("//ul[@class='events']//a");
    By forgotLogin=By.xpath("//a[text()='Forgot login info?']");
    By registerLink=By.xpath("//a[text()='Register']");
    By username=By.xpath("//div[@class='login']//input[@name='username']");
    By password=By.xpath("//div[@class='login']//input[@name='password']");
    By loginBtn=By.xpath("//div[@class='login']//input[@value='Log In']");
    /*
     * to get page title
     */
    public String getPageTitle()
    {
    	return elementUtil.waitForPageTitleContentPresent(Constants.LOGIN_PAGE_TITLE, 10);
    	//return driver.getTitle();
    }
    
    //Check the site logo 
    public boolean checkParaLogo()
    {
    	return elementUtil.doIsDisplayed(paraLogo);
    }
    
    //get menu list
    public List<String> getMenuList()
    {
    	return elementUtil.getDropdownValueWithoutSelectClass(menuList);
    }
    
    //check home icon
    public boolean checkHomeIcon()
    {
    	 //return driver.findElement(homeIcon).isDisplayed();
    	return elementUtil.doIsDisplayed(homeIcon);
    }
    
    //check about icon
    public boolean checkAboutIcon()
    {
    	//return driver.findElement(aboutusIcon).isDisplayed();
    	return elementUtil.doIsDisplayed(aboutusIcon);
    }
    
    //check contact icon
    public boolean checkContactIcon()
    {
    	//return driver.findElement(contactIcon).isDisplayed();
    	return elementUtil.doIsDisplayed(aboutusIcon);
    }
    
    //check text
    public String checkloginText()
    {
    	//return driver.findElement(customerLoginText).getText();
    	return elementUtil.doGetText(customerLoginText);
    }
    
    //get Atm services link
    public List<String> getAtmServicesLink()
    {
    	return elementUtil.getDropdownValueWithoutSelectClass(atmServicesList);
    	/*List<String>AtmServicesLink=new ArrayList<String>();
    	List<WebElement> servicesList=driver.findElements(atmServicesList);
    	
    	for(WebElement ele:servicesList)
    	{
    		String text=ele.getText();
    		AtmServicesLink.add(text);
    	}
    	return AtmServicesLink;*/
    }
    
    //get online services links
    public List<String> getOnlineLink()
    {
    	return elementUtil.getDropdownValueWithoutSelectClass(onlineServices);
    	/*List<String> optionList=new ArrayList<String>();
    	List<WebElement> onlinelist =driver.findElements(onlineServices);
    	
    	for(WebElement ele:onlinelist)
    	{
    		String text =ele.getText();
    		optionList.add(text);
    	}
    	
    	return optionList;*/
    }
    
    //To get todays date
    public String getTodaysDate()
    {
    	//return driver.findElement(todaysDate).getText();
    	return elementUtil.doGetText(todaysDate);
    }
    
    //to verify latest news text
    public String getLatestNewsTest()
    {
    	//return driver.findElement(latestNews).getText();
    	return elementUtil.doGetText(latestNews);
    }
    
    //to get news links
    public List<String> getNewsLink()
    {
    	return elementUtil.getDropdownValueWithoutSelectClass(newsLink);
    	/*List<String> optionList=new ArrayList<String>();
    	List<WebElement>links =driver.findElements(newsLink);
    	
    	for(WebElement ele:links)
    	{
    		String text =ele.getText();
    		optionList.add(text);
    	}
    	return optionList;*/
    }
    
    //verify forgot link
    public String getForgotLink()
    {
    	//return driver.findElement(forgotLogin).getText();
    	return elementUtil.doGetText(forgotLogin);
    }
    
    //verify register link
    public String getRegisterLink()
    {
    	//return driver.findElement(registerLink).getText();
    	return elementUtil.doGetText(registerLink);
    }
    
    public HomePage doLogin(String uName,String pwd)
    {
    	//driver.findElement(username).sendKeys(uName);
    	//driver.findElement(password).sendKeys(pwd);
    	//driver.findElement(loginBtn).click();
    	elementUtil.doSendKeys(username, uName);
    	elementUtil.doSendKeys(password, pwd);
    	elementUtil.doClick(loginBtn);
    	return new HomePage(driver);
    }
    
}
