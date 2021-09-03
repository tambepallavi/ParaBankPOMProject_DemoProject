package com.qa.parabank.Util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil
{
	WebDriver driver;
	public JavaScriptUtil(WebDriver driver)
    {
		this.driver =driver;
	}
    
    /**
     * To get the title of the page
     * @return String
     */
    public String getTitleByJS()
    {
    	JavascriptExecutor js =(JavascriptExecutor)driver;
    	return js.executeScript("return document.title;").toString();
    }
    
    /**
     * To refresh the page
     */
    public void refreshBrowserByJS()
    {
    	JavascriptExecutor js=(JavascriptExecutor)driver;
    	js.executeScript("history.go(0)");
    }
    
    /**
     * To create the alert
     * @param message
     */
    public void generateAlert(String message)
    {
    	JavascriptExecutor js=(JavascriptExecutor)driver;
    	js.executeScript("alert('"+message+"')");
    }
    
    /**
     * To get the title of the page
     * @return String
     */
    public String getPageInnerText()
    {
    	JavascriptExecutor js =(JavascriptExecutor)driver;
    	return js.executeScript("return document.documentElement.innerText;").toString();
    }
    
    /**
     * to do click
     * @param element
     */
    public void clickElementByJs(WebElement element)
    {
    	JavascriptExecutor js =(JavascriptExecutor)driver;
    	js.executeScript("arguments[].click();",element);
    }
    
    /**
     * using js Sendkeys
     * @param id
     * @param value
     */
    public void sendkeysUsingWithId(String id,String value)
    {
    	JavascriptExecutor js =(JavascriptExecutor)driver;
    	js.executeScript("document.getElementById('"+id+"').value='"+value+"'");
    }
    
    /**
     * Method for to scroll down page
     */
    public void scrollPageDown()
    {
    	JavascriptExecutor js =(JavascriptExecutor)driver;
    	js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }
    
    /**
     * Method for to scroll down page
     */
    public void scrollPageDown(int height)
    {
    	JavascriptExecutor js =(JavascriptExecutor)driver;
    	js.executeScript("window.scrollTo(0, '"+height+"')");
    }
    
    /**
     * Method for to scroll to the element present
     */
    public void scrollIntoView(WebElement element)
    {
    	JavascriptExecutor js =(JavascriptExecutor)driver;
    	js.executeScript("arguments[0].scrollIntoView(true);,",element);
    }
    
    /**
     * Method for to scroll up page
     */
    public void scrollPageUp()
    {
    	JavascriptExecutor js =(JavascriptExecutor)driver;
    	js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
    }
    
    /**
     * Method for draw border
     */
    public void drowBorder(WebElement element)
    {
    	JavascriptExecutor js =(JavascriptExecutor)driver;
    	js.executeScript("arguments[0].style.border='3px solid red'",element);
    }

    /**
     * draw border
     * @return
     */
    public void drawBorder(WebElement element)
    {
 	   JavascriptExecutor js = (JavascriptExecutor)driver;
 	   js.executeScript("arguments[0].style.border='3px solid red'", element);
    }
    
    /**
     * flash 
     * @param driver
     */
    public void flash(WebElement element)
    {
 	   JavascriptExecutor js = (JavascriptExecutor)driver;
 	   String bgColor = element.getCssValue("backgroundColor");
 	   for(int i=0;i<1;i++)
 	   {
 		  changeColor("rgb(0,200,0)", element);
 		  changeColor(bgColor, element);
 	   }
    }
    
    /**
     * change color
     * @param color
     * @param element
     */
    public void changeColor(String color,WebElement element)
    {
 	   JavascriptExecutor js = (JavascriptExecutor)driver;
 	   js.executeScript("arguments[0].style.backgroundColor = '"+color+"'", element);
 	   try {
 		Thread.sleep(2000);
 	} catch (InterruptedException e) {
 		// TODO Auto-generated catch block
 		e.printStackTrace();
 	}
    }
  
}
