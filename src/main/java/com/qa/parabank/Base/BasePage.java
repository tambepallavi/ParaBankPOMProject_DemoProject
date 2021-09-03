package com.qa.parabank.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.parabank.Util.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage 
{
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionmanager;
	public static String highlight;
	
	public static ThreadLocal<WebDriver> tlDriver= new ThreadLocal<WebDriver>();
	/**
	 * This methos is used to launch the browser and initialize the webdriver
	 * @param browserName
	 * @return this method will return webdriver
	 */
	public WebDriver init_browser(Properties prop)
	{
		String BrowserName=prop.getProperty("browser");
		System.out.println("Browser name is: - "+BrowserName);
		highlight=prop.getProperty("highlight");
		optionmanager =new OptionsManager(prop);

		if(BrowserName.equals(prop.getProperty("browser")))
		{
			WebDriverManager.chromedriver().setup();
			//driver =new ChromeDriver(optionmanager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionmanager.getChromeOptions()));
		}
		else if(BrowserName.equals(prop.getProperty("browser")))
		{
			WebDriverManager.firefoxdriver().setup();
			//driver =new FirefoxDriver(optionmanager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionmanager.getFirefoxOptions()));
		}
		else
		{
			System.out.println("Please provide correct browser:- "+ BrowserName);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	/**
	 * getDriver using thread local
	 * @return
	 */
	public static synchronized WebDriver getDriver()
	{
		return tlDriver.get();
	}

	/**
	 * This method is used to initialized the properties from config file
	 * @return properties prop object
	 */
	public Properties init_prop()
	{
		prop =new Properties();
		try 
		{
			FileInputStream ip=new FileInputStream("./src/main/java/com/qa/parabank/Config/Config.properties");
			try 
			{
				prop.load(ip);
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
	}
	
	/**
	 * To take a screenshot
	 * @return
	 */
	public String getScreenshot() 
	{
		File src =((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path =System.getProperty("user.dir") + "/screenshots/"+ System.currentTimeMillis() + " .png";
		File destination = new File(path);
		try
		{
			FileUtils.copyFile(src, destination);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return path;
	}
}
