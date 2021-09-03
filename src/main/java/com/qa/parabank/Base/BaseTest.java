package com.qa.parabank.Base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.parabank.Pages.AccountPage;
import com.qa.parabank.Pages.HomePage;
import com.qa.parabank.Pages.LoginPage;
import com.qa.parabank.Pages.RegisterPage;

public class BaseTest
{
	  protected WebDriver driver;
	  protected Properties prop;
	  protected  BasePage basepage;
	  protected LoginPage loginpage;
	  protected  HomePage homepage;
	  protected AccountPage accountPage;
	  protected RegisterPage registrationpage;
	   
	   @Parameters("browser")
	   @BeforeTest
	   public void setup(String browser)
	   {
		    basepage =new BasePage();
		    prop=basepage.init_prop();
		    prop.setProperty("browser", browser);
		    driver =basepage.init_browser(prop);
		    
		    loginpage=new LoginPage(driver);
		    //driver.get("https://parabank.parasoft.com/");
	   }
	   
	   @AfterTest
	   public void tearDown()
	   {
		   driver.quit();
	   }
}
