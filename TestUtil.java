package com.qa.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;

public class TestUtil extends TestBase{
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}
	
	

	public static void sleep(long number) throws InterruptedException {
		Thread.sleep(number);
	}
	
	
	public static WebElement waitForWebElement(WebDriver driver,WebElement element, int num) 
	{
		try {
		
		
		WebDriverWait wait=new WebDriverWait(driver, num);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		//return element;
		}
		catch(Exception e){
			System.out.println("unable to click the element");
			e.printStackTrace();
			
		}
		return element;
	}
	
	public static void clickOnElement(WebDriver driver, WebElement element, int timeout){
		
		try{
			new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
			Thread.sleep(2000l);
			System.out.println("Clicking on element  ::  "+element);
			element.click();
			
			
		}catch(Exception e){
			System.out.println("unable to click the element");
			e.printStackTrace();
		}
	}
}
