package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.qa.base.TestBase;


import com.qa.pages.SwagLabsPage;

public class SwagLabsTest extends TestBase {

	SwagLabsPage SaucedemoPageObj;
	private static ExtentReports extent;
	ExtentHtmlReporter htmlReporter;

	public SwagLabsTest() {
		super();
	}

	@BeforeSuite
	public void beforeSuiteExtentReportSetup() {
		htmlReporter = new ExtentHtmlReporter("ExtentHtmlReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	@BeforeTest
	public void setUp() {
		initialization();
		SaucedemoPageObj = new SwagLabsPage();

	}

	@Test(priority = 1)
	public void loginTest() {
		ExtentTest test = extent.createTest("loginTest :TC-001",
				"Verify User login Functionality on SWAG LABS Application");
		boolean isLoginSuccessfully = SaucedemoPageObj.loginToApplication(prop.getProperty("username"),
				prop.getProperty("password"));

		if (isLoginSuccessfully) {
			Assert.assertEquals(isLoginSuccessfully, true);

			test.pass("Successfully Verify User login Functionality on SWAG LABS Application");
			test.info("User able login successfully  and redirect on product page");
		} else {
			test.fail("Fail to Verify User login Functionality on SWAG LABS Application");
		}
	}

	@Test(priority = 2)
	public void SortByFilter() throws InterruptedException {
		ExtentTest test = extent.createTest("SortByFilter :TC-002",
				"Verify sort the items with price from high to low.");
		String highToLowFilter="Price (high to low)";
		boolean highToLowSelected = SaucedemoPageObj.SortByFilter(highToLowFilter);

		if (highToLowSelected) {
			Assert.assertEquals(highToLowSelected, true);
			test.pass("Sucessfully Verify sort the items with price from high to low.");

		} else {
			test.fail("Fail to  Verify User login Functionality on SWAG LABS Application");
		}
	}

	@Test(priority = 3)
	public void addProductsTest() {
		ExtentTest test = extent.createTest("addProductsTest :TC-003",
				"Verify User able to add More than 3 items On Cart");
		boolean isItemAddSuccessfullyOnCart = SaucedemoPageObj.addProductsPage();

		if (isItemAddSuccessfullyOnCart) {
			Assert.assertEquals(isItemAddSuccessfullyOnCart, true);
			test.pass("Sucessfully Verify User able to add More than 3 items On Cart");
			test.info("Added More than 3 items  then it redirect to cart ");

		} else {
			test.fail("Fail to Verify User able to add More than 3 items On Cart");
		}

	}

	@Test(priority = 4)
	public void deleteProductFromCartTest() {
		ExtentTest test = extent.createTest("deleteProductFromCartTest :TC-004", "Verify Remove 1 item on cart ");

		boolean checkoutInfoPageDisplaying = SaucedemoPageObj.deleteProductFromCartPage();

		if (checkoutInfoPageDisplaying) {
			Assert.assertEquals(checkoutInfoPageDisplaying, true);
			test.pass("Sucessfully Verify Verify Remove 1 item on cart ");
			test.info("verify the more than 3 items then remove 1 item on cart ");
		} else {
			test.fail("Fail to Verify Remove 1 item on cart ");
		}
	}

	@Test(priority = 5)
	public void checkoutInfoTest() {
		ExtentTest test = extent.createTest("checkoutInfoTest :TC-005", "Verify check out information");
		boolean checkoutOverviewPageDisplaying = SaucedemoPageObj.checkoutInfoPage();

		if (checkoutOverviewPageDisplaying) {
			Assert.assertEquals(checkoutOverviewPageDisplaying, true);
			test.pass("Sucessfully Verify check out information ");
			test.info("Entered the user information and redirect to payment page");
		} else {
			test.fail("Fail to Verify check out information");

		}
	}

	@Test(priority = 6)
	public void checkoutOverviewTest() {
		ExtentTest test = extent.createTest("checkoutOverviewTest :TC-006", "Verify calculate total payment");
		boolean thanksMsgDisplaying = SaucedemoPageObj.checkoutOverviewPage();

		if (thanksMsgDisplaying) {
			Assert.assertEquals(thanksMsgDisplaying, true);
			test.pass("Sucessfully Verify calculate total payment");
			test.info("Calculate the total payment then redirect to thank you page");
		} else {
			test.fail("Fail to Verify calculate total payment");
		}
	}

	@AfterTest
	public void tearDown() {
		driver.quit();

	}

	@AfterSuite
	public void afterSuiteExtentReportClose() {
		extent.flush();
	}

}
