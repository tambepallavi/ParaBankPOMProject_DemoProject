package com.qa.parabank.Util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.parabank.Base.BasePage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ElementUtil extends BasePage
{
 WebDriver driver;
 JavaScriptUtil jsUtil;

public ElementUtil(WebDriver driver) 
{
	this.driver=driver;
	jsUtil=new JavaScriptUtil(driver);
}
    
    //################################# Browser lunnch #########################################
   /**
    * This method use to launce the browser
    * @param browser
    * @this return browser
    */
	public WebDriver init_driver(String browser)
	{
       if(browser.equals("chrome"))
       {
    	   WebDriverManager.chromedriver().setup();
    	   driver =new ChromeDriver();
       }
       else if(browser.equals("firefox"))
       {
    	   WebDriverManager.firefoxdriver().setup();
    	   driver =new FirefoxDriver();
       }
       else if(browser.equals("safari"))
       {
    	   driver =new SafariDriver();
    			   
       }
       else
       {
    	   System.out.println("Please pass the correct browser: "+browser);
       }
       return driver;
	}
	//###############################  Url ##############################
	/**
	 * This method for lunch the url
	 * @param url
	 */
	public void launchUrl(String url)
	{
		driver.get(url);
	}
	
	/**
	 * this method is use to get the title
	 */
	public String getPageTitle()
	{
		return driver.getTitle();
	}
	
	/**
	 * This method get the current page url
	 * @return String
	 */
	public String getCurrentPageUrl()
	{
		return driver.getCurrentUrl();
	}
	
	/**
	 * This method is use for close the current browser
	 */
	public void closeBrowser()
	{
		driver.close();
	
	}
	/**
	 * This method is use for close the all opened browser
	 */
	public void quitBrowser()
	{
		driver.quit();
	}
	
	public boolean doIsDisplayed(By locator)
	{
		return getElement(locator).isDisplayed();
	}
	
	public String doGetText(By locator)
	{
		return getElement(locator).getText();
	}
	/**
	 * this method for get locator
	 * @param locator
	 * @return
	 */
	public WebElement getElement(By locator)
	{
		WebElement element=driver.findElement(locator);
		if(highlight.equals("true"))
		{
			jsUtil.flash(element);
		}
		return element;
	}
	
	/**
	 * this method for sendkeys
	 * @param locator
	 * @param value
	 */
	public void doSendKeys(By locator, String value)
	{
		getElement(locator).sendKeys(value);
	}
	
	/**
	 * this method is for click on element
	 */
	public void doClick(By locator)
	{
		getElement(locator).click();
	}
	
	//###################################### Drop Down methods #################################
	/**
	 * This method is use to select value from dropdown by selecting visible text
	 * @param locator
	 * @param value
	 */
	public  void getDropDownListByVisibleText(By locator, String value)
	{
		Select select =new Select(getElement(locator));
		select.selectByVisibleText(value);
	}
	
	/**
	 * This method is use to select value from dropdown by selecting index
	 * @param locator
	 * @param value
	 */
	public  void getDropDownListByIndex(By locator, int value)
	{
		Select select =new Select(getElement(locator));
		select.selectByIndex(value);
	}
	
	/**
	 * This method is use to select value from dropdown by selecting value text
	 * @param locator
	 * @param value
	 */
	public  void getDropDownListByVValue(By locator, String value)
	{
		Select select =new Select(getElement(locator));
		select.selectByValue(value);
	}
	
	/**
	 * this method use for the get the dropdown list 
	 * @param locator
	 * @return
	 */
	public List<String> DropDownGetOption(By locator)
	{
		WebElement element = getElement(locator);
		List<String> optionsList = new ArrayList<String>();
		Select select = new Select(element);
		List<WebElement>List = select.getOptions();
		
		for(WebElement ele:List)
		{
			String text=ele.getText();
			optionsList.add(text);
		}
		return optionsList;
	}
	
	/**
	   this method is use for select the value from dropdown list
	 */
	public void selectDropdownValue(By locator, String value)
	{
		WebElement element = getElement(locator);
		
		Select select = new Select(element);
		List<WebElement>List = select.getOptions();
		
		for(WebElement ele:List)
		{
			String text=ele.getText();
			if(text.equals(value))
			{
				ele.click();
				break;
			}
		}
		
	}
	
	/**
	 * This method for select dropdown value without using select class
	 * @param locator
	 * @param value
	 */
	public  void selectDropdownValueWithoutSelectClass(By locator,String value)
	{
		List<WebElement>list = driver.findElements(locator);
       
		for(WebElement ele:list)
	       {
	    	   String text =ele.getText();
	    	   if(text.equals(value))
	    	   {
	    		    ele.click();  
	    		    break;
	    	   }
	       }
	}
	
	/**
	 * This method for select dropdown value without using select class
	 * @param locator
	 * @param value
	 */
	public  List<String> getDropdownValueWithoutSelectClass(By locator)
	{
		List<String> optionValues= new ArrayList<>();
		List<WebElement>list = driver.findElements(locator);
       
		for(WebElement ele:list)
	       {
	    	   String text =ele.getText();
	    	   optionValues.add(text);
	       }
		return optionValues;
	}
	
	/**
	 * This method is use to select single,multiple and all values from dropdown
	 * Please pass the unique values
	 * @param locator
	 * @param value...
	 */
	public  void getJsDropdownList(By locator, String... value)
	{
		List<WebElement>list =driver.findElements(locator);

		if(!value[0].equalsIgnoreCase("all"))
		{
			for(WebElement ele:list)
			{
				String text= ele.getText();

				for(int j=0;j<value.length;j++)
				{
					if(text.equals(value[j]))
					{
						ele.click();
						break;
					}
				}

			} 

		}

		else
		{
			try
			{
				//select all values
				for(int i=0;i<list.size();i++)
				{
					list.get(i).click();
				}
			}
			catch(Exception e)
			{

			}
		}


	}
	
	//############################################# Actions methods #####################
	
	/**
	 * this action method id use for move one element to other
	 * Move to Element
	 * @param locator
	 */
	public void getActionsPerformed(By locator)
	{
		Actions act =new Actions(driver);
	    act.moveToElement(getElement(locator)).build().perform();
	}
	
	/**
	 * This methos is for drag and drop using clickAndHold and moveToElement method
	 * @param dradggble
	 * @param droppble
	 */
	public void dragAndDropElement(By draggble,By droppble)
	{
		Actions act =new Actions(driver);
		act.clickAndHold(getElement(draggble)).moveToElement(getElement(droppble)).release().build().perform();
	}
	
	/**
	 * This methos is for drag and drop using dragAndDrop method
	 * @param draggble
	 * @param droppble
	 */
	public void dragAndDrap(By draggble,By droppble)
	{
		Actions act =new Actions(driver);
		act.dragAndDrop(getElement(draggble), getElement(droppble)).perform();
	}

	/**
	 * This method is for rightclick and select value from dropdown
	 * @param locator - webelement 
	 * @param locator1 -list of webelement
	 * @param value 
	 */
	public  void rightClickDropdown(By locator,By locator1 ,String value)
	{
		Actions act =new Actions(driver);
	    act.contextClick(getElement(locator)).perform();
	       
	       List<WebElement> list =driver.findElements(locator1);
	       
	       for(WebElement ele:list)
	       {
	    	  String text = ele.getText();
	    	  //System.out.println(text);
	    	  
	    	  if(text.equals(value))
	    	  {
	    		  ele.click();
	    		  break;
	    	  }
	       }
	}
	
	/**
	 * This method is for using sendkey with action class
	 * @param locator
	 * @param value
	 */
	public  void sendkeysUsingActionClass(By locator,String value)
	{
		Actions act = new Actions(driver);
	    act.sendKeys(getElement(locator), value).perform();
	}
	
	/**
	 * This method is for using click with action class
	 * @param locator
	 * @param value
	 */
	public  void doClickUsingActionClass(By locator)
	{
		Actions act = new Actions(driver);
	    act.click(getElement(locator)).perform();
	}
	
	/**
	 * to check element os present on the page or not
	 * @param locator
	 * @return boolean
	 */
	public boolean checkWebElementPresent(By locator)
	{
		int elementCount =driver.findElements(locator).size();
		
		if(elementCount>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//############################## Wait Utils #########################################################
	
	/**
	 * This method is for wait for element tobe located
	 * @param locator
	 * @param timeout
	 * @return WebElement
	 */
	public  WebElement waitForElementTobeLocated(By locator, int timeout)
	{
		WebDriverWait wait =new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	/**
	 * This is use for wait page title present
	 * @param title
	 * @param timeout
	 * @return String
	 */
	public  String waitForPageTitlePresent(String title,int timeout)
	{
		WebDriverWait wait1 = new WebDriverWait(driver, timeout);
		wait1.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}
	
	/**
	 * This is use for wait for page content title
	 * @param title
	 * @param timeout
	 * @return
	 */
	public  String waitForPageTitleContentPresent(String title,int timeout)
	{
		WebDriverWait wait1 = new WebDriverWait(driver, timeout);
		wait1.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}
	
	/**
	 * This is method for wait for alert to be present
	 * @param timeOut
	 * @return Alert
	 */
	public  Alert waitForAlertToBePresent(int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.alertIsPresent());
	} 
	
	/**
	 * This is for wait for the url
	 * @param url
	 * @param timeOut
	 * @return boolean
	 */
	public boolean waitForUrl(String url,int timeOut)
	{
		WebDriverWait wait =new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.urlContains(url));
	}
	
	/**
	 * this method for when element is ready then click
	 * @param locator
	 * @param timeOut
	 */
	public void clickWhenReady(By locator, int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}

	/**
	 * This is method for wait for element to be visible
	 * @param locator
	 * @param timeOut
	 * @return WebElement
	 */
   public WebElement waitForElementToBeVisible(By locator, int timeOut)
   {
	   WebElement element = getElement(locator);
	   WebDriverWait wait = new WebDriverWait(driver, timeOut);
	   return wait.until(ExpectedConditions.visibilityOf(element));
   }
   
   /**
    * This method for wait for all element visile
    * @param locator
    * @param timeOut
    * @return List<WebElement>
    */
   public List<WebElement> visiblityOfAllElements(By locator,int timeOut)
	{
		WebDriverWait wait =new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public void getPageLinkText(By locator, int timeOut)
	{
		visiblityOfAllElements(locator, timeOut).stream().forEach(ele -> System.out.println(ele.getText()));
	}
	
	/**
	 * wait of fluent wait 
	 * @param locator
	 * @param timeOut
	 * @param pullingTime
	 * @return WebElement
	 */
	public WebElement waitForElementWithFluentWait(By locator, int timeOut ,int pullingTime)
	{
		Wait<WebDriver> wait= new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pullingTime))
				.ignoring(NoSuchElementException.class);

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

}
