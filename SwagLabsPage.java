package com.qa.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.*;

import com.qa.base.TestBase;

import com.qa.util.TestUtil;

public class SwagLabsPage extends TestBase {
	private static final Logger logger = LogManager.getLogger(SwagLabsPage.class);
	


	@FindBy(xpath = "//*[@id='user-name']")
	WebElement userName;

	@FindBy(xpath = "//*[@id='password']")
	WebElement password;

	@FindBy(xpath = "//*[@value='LOGIN']")
	WebElement loginBtn;

	@FindBy(xpath = "//*[text()='Products']")
	WebElement productLabel;

	@FindBy(xpath = "//button[text()='ADD TO CART']")
	List<WebElement> addToCartBtn;

	@FindBy(xpath = "//*[@class='inventory_container']//div[@class='inventory_item_name']")
	List<WebElement> itemNameList;

	@FindBy(xpath = "//*[@class='inventory_container']//div[@class='inventory_item_price']")
	List<WebElement> itemPriceList;

	@FindBy(xpath = "//*[@id='shopping_cart_container']//span")
	WebElement cartItemCount;

	@FindBy(xpath = "//*[@class='inventory_container']//button[text()='REMOVE']")
	List<WebElement> addedItemsInCart;

	@FindBy(xpath = "//*[@id='shopping_cart_container']/a")
	WebElement moveToCartBtn;

	@FindBy(xpath = "//*[@id='contents_wrapper']//div[text()='Your Cart']")
	WebElement yourCartLabel;

	@FindBy(xpath = "//*[@class='cart_list']//div[@class='cart_item']//button[text()='REMOVE']")
	WebElement removeItem;

	@FindBy(xpath = "//*[@class='cart_list']//div[@class='cart_item']")
	List<WebElement> totalItemsInCart;

	@FindBy(xpath = "//*[@class='cart_list']//div[@class='cart_item']//div/a/div")
	List<WebElement> finalItemNameList;

	@FindBy(xpath = "//*[@class='cart_list']//div[@class='cart_item']//div[@class='item_pricebar']/div")
	List<WebElement> finalItemPriceList;

	@FindBy(xpath = "//*[text()='CHECKOUT']")
	WebElement checkoutBtn;

	@FindBy(xpath = "//div[text()='Checkout: Your Information']")
	WebElement checkoutInfoPage;

	@FindBy(xpath = "//input[@id='first-name']")
	WebElement enterFirstName;

	@FindBy(xpath = "//input[@id='last-name']")
	WebElement enterLastName;

	@FindBy(xpath = "//input[@id='postal-code']")
	WebElement enterPostalAddress;

	@FindBy(xpath = "//*[@class='checkout_buttons']/input")
	WebElement continueBtn;

	@FindBy(xpath = "//div[text()='Checkout: Overview']")
	WebElement checkoutOverviewPage;

	@FindBy(xpath = "//*[@class='summary_subtotal_label']")
	WebElement subtotalShown;

	@FindBy(xpath = "//*[text()='FINISH']")
	WebElement finishBtn;

	@FindBy(xpath = "//*[text()='THANK YOU FOR YOUR ORDER']")
	WebElement thanksMsg;

	HashMap<String, String> ItemAndPrice = new HashMap<>();

	// Initializing the Page Objects:
	public SwagLabsPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean loginToApplication(String username, String pasword) {
		
		try {
			logger.info("*********Entering username*********");
			TestUtil.clickOnElement(driver, userName, 10);
			userName.sendKeys(username);
			logger.info("*********Entering password*********");
			TestUtil.clickOnElement(driver, password, 10);
			password.sendKeys(pasword);

			logger.info("*********Click on Login Button*********");
			TestUtil.clickOnElement(driver, loginBtn, 20);

			logger.info("*********Verify if login is successful*********");
			TestUtil.waitForWebElement(driver, productLabel, 10);
			boolean isLoginSuccessful = productLabel.isDisplayed();

			return isLoginSuccessful;

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception occured while executing the testcases");
			return false;
		}
	}

	public boolean SortByFilter(String filterName) throws InterruptedException {
		try {

			Select select = new Select(driver.findElement(By.xpath("//*[@id='inventory_filter_container']/select")));
			select.selectByVisibleText(filterName);
			logger.info("*********select*********"+filterName);
			TestUtil.sleep(5000l);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean addProductsPage() {

		try {
			logger.info("*********Add Item to Cart*********");
			logger.info("Total Item available to add in cart  :::  " + addToCartBtn.size());
			for (int i = 0; i < itemNameList.size(); i++) {
				logger.info("Item added to cart  ::  " + itemNameList.get(i).getText());
				logger.info("Item price  ::  " + itemPriceList.get(i).getText());
				TestUtil.clickOnElement(driver, addToCartBtn.get(0), 10);
				logger.info("Cart Items Count  ::  " + cartItemCount.getText());
				if (cartItemCount.getText().equals("4")) {
					TestUtil.clickOnElement(driver, moveToCartBtn, 10);
				}
			}

			logger.info("*********Verify user navigate to Cart*********");
			boolean isItemAddSuccessful = yourCartLabel.isDisplayed();
			return isItemAddSuccessful;

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception occured while executing the testcases");
			return false;
		}
	}

	public boolean deleteProductFromCartPage() {

		try {
			logger.info("*********Remove one item from the cart*********");
			TestUtil.clickOnElement(driver, removeItem, 10);
			Thread.sleep(3000l);
			if (!(totalItemsInCart.size() == 3)) {
				logger.info("ERROR  ::  Item is not removed");
				return false;
			}
			logger.info("Item has been removed successfully");

			for (int i = 0; i < finalItemNameList.size(); i++) {
				logger.info("******Final Item present in Cart*********");
				ItemAndPrice.put(finalItemNameList.get(i).getText(), finalItemPriceList.get(i).getText());
				logger.info("Final Item added to cart  ::  " + finalItemNameList.get(i).getText()
						+ "  with price  ::  " + finalItemPriceList.get(i).getText());

			}

			logger.info("******Click on Checkout Button**********");
			TestUtil.clickOnElement(driver, checkoutBtn, 10);

			return checkoutInfoPage.isDisplayed();

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception occured while executing the testcases");
			return false;
		}
	}

	public boolean checkoutInfoPage() {
		
		try {
			logger.info("******Enter First Name**********");
			TestUtil.clickOnElement(driver, enterFirstName, 10);
			enterFirstName.sendKeys(prop.getProperty("enterFirstName"));
			logger.info("******Enter Last Name**********");
			TestUtil.clickOnElement(driver, enterLastName, 10);
			enterLastName.sendKeys(prop.getProperty("enterLastName"));
			logger.info("******Enter postal Address**********");
			TestUtil.clickOnElement(driver, enterPostalAddress, 10);
			enterPostalAddress.sendKeys(prop.getProperty("enterPostalAddress"));
			logger.info("******Click on Continue Button**********");
			TestUtil.clickOnElement(driver, continueBtn, 10);

			return checkoutOverviewPage.isDisplayed();

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception occured while executing the testcases");
			return false;
		}
	}

	public boolean checkoutOverviewPage() {

		try {
			float expectedTotalAmount = 0.0f;
			for (Entry<String, String> e : ItemAndPrice.entrySet()) {
				expectedTotalAmount = expectedTotalAmount + Float.parseFloat(e.getValue());

			}
			logger.info("Expected Total amount ::  " + expectedTotalAmount);
			logger.info("Actual subtotal amount ::  " + subtotalShown.getText());
			if (subtotalShown.getText().endsWith(String.valueOf(expectedTotalAmount))) {
				logger.info("Amount of expected and actual are equal");
				TestUtil.clickOnElement(driver, finishBtn, 10);
			} else {
				logger.info("Amount of expected and actail are not equalequal");
				return false;
			}

			return thanksMsg.isDisplayed();

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception occured while executing the testcases");
			return false;
		}
	}

}
