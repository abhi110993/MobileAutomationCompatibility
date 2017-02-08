/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qait.automation.getpageobjects;

import static com.qait.automation.getpageobjects.ObjectFileReader.getPageTitleFromFile;
import static com.qait.automation.utils.ConfigPropertyReader.getProperty;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.qait.automation.utils.DataBaseConnecter;
import com.qait.automation.utils.RestAPITester;
import com.qait.automation.utils.SeleniumWait;


/**
 *
 * @author QAIT
 */
public class BaseUi {

	WebDriver driver;
	protected SeleniumWait wait;
	protected DataBaseConnecter dbConnector;
	protected RestAPITester apiTester;
	private String pageName;


	protected BaseUi(WebDriver driver, String pageName) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.pageName = pageName;
		this.wait = new SeleniumWait(driver, Integer.parseInt(getProperty(
				"Config.properties", "timeout")));
		this.apiTester = new RestAPITester();
		this.dbConnector = new DataBaseConnecter();
	}

	protected String getPageTitle() {
		return driver.getTitle();
	}

	protected void logMessage(String message) {
		Reporter.log(message, true);
	}

	protected String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	protected void verifyPageTitleExact() {
		String pageTitle = getPageTitleFromFile(pageName);
		verifyPageTitleExact(pageTitle.trim());
	}

	protected void verifyPageTitleExact(String expectedPagetitle) {
		if ((
			(expectedPagetitle=="") || (expectedPagetitle == null) || (expectedPagetitle.isEmpty()))  && (getProperty("browser").equalsIgnoreCase("chrome"))){
			expectedPagetitle = getCurrentURL();
		}
		try {
			wait.waitForPageTitleToBeExact(expectedPagetitle);
			logMessage("TEST PASSED: PageTitle for " + pageName + " is exactly: '"
					+ expectedPagetitle + "'");
		} catch (TimeoutException ex) {
			Assert.fail("TEST FAILED: PageTitle for " + pageName + " is not exactly: '"
					+ expectedPagetitle + "'!!!\n instead it is :- " + driver.getTitle());
		}
	}

	/**
	 * Verification of the page title with the title text provided in the page
	 * object repository
	 */
	protected void verifyPageTitleContains() {
		String expectedPagetitle = getPageTitleFromFile(pageName).trim();
		verifyPageTitleContains(expectedPagetitle);
	}

	/**
	 * this method will get page title of current window and match it partially
	 * with the param provided
	 *
	 * @param expectedPagetitle
	 *            partial page title text
	 */
	protected void verifyPageTitleContains(String expectedPagetitle) {

		if ((
					(expectedPagetitle=="") || (expectedPagetitle == null) || (expectedPagetitle.isEmpty()))
					&& (getProperty("browser").equalsIgnoreCase("chrome"))){
			expectedPagetitle = getCurrentURL();
		}
		try {
			wait.waitForPageTitleToContain(expectedPagetitle);
		} catch (TimeoutException exp) {
			String actualPageTitle = driver.getTitle().trim();
			logMessage("TEST FAILED: As actual Page Title: '" + actualPageTitle
					+ "' does not contain expected Page Title : '"
					+ expectedPagetitle + "'.");
		}
		String actualPageTitle = getPageTitle().trim();
		logMessage("TEST PASSED: PageTitle for " + actualPageTitle + " contains: '"
				+ expectedPagetitle + "'.");
	}

	protected WebElement getElementByIndex(List<WebElement> elementlist,
			int index) {
		return elementlist.get(index);
	}

	protected WebElement getElementByExactText(List<WebElement> elementlist,
			String elementtext) {
		WebElement element = null;
		for (WebElement elem : elementlist) {
			if (elem.getText().equalsIgnoreCase(elementtext.trim())) {
				element = elem;
			}
		}
		// FIXME: handle if no element with the text is found in list No element
		// exception
		if (element == null) {
		}
		return element;
	}

	protected WebElement getElementByContainsText(List<WebElement> elementlist,
			String elementtext) {
		WebElement element = null;
		for (WebElement elem : elementlist) {
			if (elem.getText().contains(elementtext.trim())) {
				element = elem;
			}
		}
		// FIXME: handle if no element with the text is found in list
		if (element == null) {
		}
		return element;
	}

	protected void switchToFrame(WebElement element) {
		// switchToDefaultContent();
		wait.waitForElementToBeVisible(element);
		driver.switchTo().frame(element);
	}

	public void switchToFrame(int i) {
		driver.switchTo().frame(i);
	}

	public void switchToFrame(String id) {
		driver.switchTo().frame(id);
	}

	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	protected void executeJavascript(String script) {
		((JavascriptExecutor) driver).executeScript(script);
	}

	protected void hover(WebElement element) {
		Actions hoverOver = new Actions(driver);
		hoverOver.moveToElement(element).build().perform();
	}

	protected void handleAlert() {
		try {
			switchToAlert().accept();
			logMessage("Alert handled..");
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			System.out.println("No Alert window appeared...");
		}
	}

	private Alert switchToAlert() {
		WebDriverWait wait = new WebDriverWait(driver, 1);
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	protected void selectProvidedTextFromDropDown(WebElement el, String text) {
		wait.waitForElementToBeVisible(el);
		scrollDown(el);
		Select sel = new Select(el);
		sel.selectByVisibleText(text);
	}

	protected void scrollDown(WebElement element) {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView(true);", element);
	}

	protected void hoverClick(WebElement element) {
		Actions hoverClick = new Actions(driver);
		hoverClick.moveToElement(element).click().build().perform();
	}

	protected void click(WebElement element) {
		try {
			wait.waitForElementToBeVisible(element);
			scrollDown(element);
			element.click();
		} catch (StaleElementReferenceException ex1) {
			wait.waitForElementToBeVisible(element);
			scrollDown(element);
			element.click();
			logMessage("Clicked Element " + element
					+ " after catching Stale Element Exception");
		} catch (Exception ex2) {
			logMessage("Element " + element + " could not be clicked! "
					+ ex2.getMessage());
		}
	}

}
