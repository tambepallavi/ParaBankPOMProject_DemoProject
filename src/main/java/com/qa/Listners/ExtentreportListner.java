package com.qa.Listners;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.openxml4j.samples.GetThumbnails;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.parabank.Base.BasePage;

import bsh.classpath.BshClassPath.GeneratedClassSource;

public class ExtentreportListner extends BasePage implements ITestListener
{
   private static final String OUTPUT_FOLDER="./build/";
   private static final String FILE_NAME="TestExecutionReport.html";
   private static ExtentReports extent =init();
   
   public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
   
   
   private static ExtentReports init() 
   {
		Path path = Paths.get(OUTPUT_FOLDER);
		// if directory exist?
		if(!Files.exists(path))
		{
			try
			{
				Files.createDirectories(path);
			}
			catch(IOException e)
			{
				//fall to create dictionary
				e.printStackTrace();
			}
		}
			
			ExtentHtmlReporter htmlRepoter =new ExtentHtmlReporter(OUTPUT_FOLDER + FILE_NAME);
			htmlRepoter.config().setDocumentTitle("Automation Test Result");
			htmlRepoter.config().setReportName("Automation Test Result");
			htmlRepoter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlRepoter.config().setTheme(Theme.STANDARD);
			
			extent =new ExtentReports();
			extent.attachReporter(htmlRepoter);
			extent.setReportUsesManualConfiguration(true);
			return extent;
		
	}
   
   @Override
	public synchronized void onStart(ITestContext context) 
   {
		System.out.println("Test Suite Started");
		
	}
   
   @Override
	public synchronized void onFinish(ITestContext context)
   {
		System.out.println("Test Suite Ending");
		extent.flush();
		test.remove();
	}

	@Override
	public synchronized void onTestStart(ITestResult result) 
	{
		String methodName =result.getMethod().getMethodName();
		String qualifiedName =result.getMethod().getQualifiedName();
		int last =qualifiedName.lastIndexOf(".");
		int mid=qualifiedName.substring(0, last).lastIndexOf(".");
		String className =qualifiedName.substring(mid +1, last);
		
		System.out.println(methodName+" Started");
		ExtentTest extentTest =extent.createTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
		extentTest.assignCategory(result.getTestContext().getSuite().getName());
		extentTest.assignCategory(className);
		test.set(extentTest);
		test.get().getModel().setStartTime(getTime(result.getStartMillis()));	
	}
	
	@Override
	public synchronized void onTestSuccess(ITestResult result) 
	{
		System.out.println(result.getMethod().getMethodName()+ " Passed!");
		test.get().pass("Test Passed");
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}
	
	@Override
	public synchronized void onTestFailure(ITestResult result)
	{
		System.err.println(result.getMethod().getMethodName()+ " Failed!");
		try
		{
			test.get().fail(result.getThrowable(),MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
			
		}
		catch(IOException e)
		{
			System.out.println("Exceptional thrown while updating test fail status "+ Arrays.toString(e.getStackTrace()));
		}
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
		
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result)
	{
		System.err.println(result.getMethod().getMethodName() + " Skipped!");
		try 
		{
			test.get().skip(result.getThrowable(),MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		}
		catch(IOException e)
		{
			System.out.println("Exceptional thrown while updating test skip status "+Arrays.toString(e.getStackTrace()));
		}
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	@Override
	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result)
	{
		System.out.println("OnTestFailedButWithinSuccessPercentage for "+ result.getMethod().getMethodName());
	}

	private Date getTime(long millis) 
	{
		Calendar calendar =Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
}
