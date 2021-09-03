package com.qa.parabank.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.parabank.Util.Constants;
import com.qa.parabank.Util.ElementUtil;

public class RegisterPage 
{
	ElementUtil elementUtil;
    WebDriver driver;
    
    public RegisterPage(WebDriver driver) 
    {
		this.driver=driver;
		elementUtil=new ElementUtil(driver);
	}
    
    By registrationLink=By.xpath("//a[text()='Register']");
    By firstName=By.xpath("//input[@id='customer.firstName']");
    By lastName=By.xpath("//input[@id='customer.lastName']");
    By address= By.xpath("//input[@id='customer.address.street']");
    By city= By.xpath("//input[@id='customer.address.city']");
    By state=By.xpath("//input[@id='customer.address.state']");
    By zipCode=By.xpath("//input[@id='customer.address.zipCode']");
    By ssn=By.xpath("//input[@id='customer.ssn']");
    By username=By.xpath("//input[@id='customer.username']");
    By password=By.xpath("//input[@id='customer.password']");
    By confirmPassword=By.xpath("//input[@id='repeatedPassword']");
    By registerutton=By.xpath("//input[@value='Register']");
    By UserRegisterSuccessMsg=By.xpath("//p[text()='Your account was created successfully. You are now logged in.']");
    By logOut=By.xpath("//a[text()='Log Out']");
    
    public String getPageTitle()
    {
    	//elementUtil.doClick(registrationLink);
    	return elementUtil.waitForPageTitleContentPresent(Constants.REGISTRATION_PAGE_TITLE, 10);
    }
    
    
    public void getRegistrationFormField(String fName,String lName,String addr,String city,String state,String Zipcode,String SSN,String uName,String pwd,String confirmPwd)
    {
    	elementUtil.doClick(registrationLink);
    	elementUtil.doSendKeys(firstName, fName);
    	elementUtil.doSendKeys(lastName, lName);
    	elementUtil.doSendKeys(address, addr);
    	elementUtil.doSendKeys(this.city, city);
    	elementUtil.doSendKeys(this.state, state);
    	elementUtil.doSendKeys(zipCode, Zipcode);
    	elementUtil.doSendKeys(ssn, SSN);
    	elementUtil.doSendKeys(username, uName);
    	elementUtil.doSendKeys(password, pwd);
    	elementUtil.doSendKeys(confirmPassword, confirmPwd);
    	elementUtil.doClick(registerutton);
    	elementUtil.doClick(logOut);
    }
    
    public String getRegistrationSuccessMessage()
    {
    	return elementUtil.doGetText(UserRegisterSuccessMsg);
    }
}
