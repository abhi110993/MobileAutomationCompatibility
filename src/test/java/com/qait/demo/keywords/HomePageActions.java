package com.qait.demo.keywords;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.YamlReader;
import com.thoughtworks.selenium.Wait;
import com.thoughtworks.selenium.Wait.WaitTimedOutException;

public class HomePageActions extends GetPage {

	WebDriver driver;
	private EventFiringWebDriver e_driver;

	public HomePageActions(WebDriver driver) {
		super(driver, "HomePage");
		this.driver = driver;

	}

	public void composeMail(String receiverEmail){
		element("compose_mail").click();
		System.out.println(receiverEmail);
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		element("receiver_email").sendKeys(receiverEmail);
		String timestamp = ts.toGMTString();
		element("subject").sendKeys(timestamp);
//		sendKeys(ts.toGMTString());
		element("send_btn").click();
		element("sent_mails").click();
//		StringBuffer sb = new StringBuffer();
		String str= "//span[normalize-space(text())='" +timestamp +"']";
		WebElement span_text = driver.findElement(By.xpath(str));
		String timeMail = span_text.getText();
		Assert.assertEquals(timestamp, timeMail);
		logMessage("mail with subject" +timestamp +"successfully sent" );
		
	}
	public void verifyUserIsOnHomePage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("txt_lastLogin");
		isElementDisplayed("tab_active", "HOME");
		logMessage("Assertion Passed :: User has successfully logged in");
	}

	public void clickMainTab(String tabName) {
		element("lnk_mainTab", tabName).click();
		logMessage("User clicked on '" + tabName + "' tab");
	}

	public void clickAdvanceCourse() {

		element("advance_course").click();
		// wait.hardWait(5);
		element("menu2").click();
		// wait.hardWait(5);
		element("go_next").click();
		wait.hardWait(5);

	}

	public void clickGame() {

		Map<String, WebElement> tatocElements = new LinkedHashMap<>();
		Map<String, WebElement> humanMap = new LinkedHashMap<>();
		Map<String, WebElement> tapMap = new LinkedHashMap<>();

		for (int i = 1; i < 4; i++) {
			for (int j = 1; j < 4; j++) {
				WebElement element1 = driver.findElement(By.id("" + i + j));
				tatocElements.put("" + i + j, element1);
			}
		}

		int counter = 9;
		while (counter-- >= 0) {

			checkHumanClickedBox(humanMap, tapMap);
			wait.hardWait(1);
			if (element("player_turn").getText().equals("TAP (Player 2)")) {
				clickTap(tatocElements, humanMap, tapMap);
				// tatocElements.get(0).click();
			}
		}

	}

	public String checkHumanClickedBox(Map<String, WebElement> humanMap,
			Map<String, WebElement> tapMap) {

		String id = null;
		loop1: for (int i = 1; i > 0; i++) {

			if (i > 3) {
				i = 1;
			}
			for (int j = 1; j < 4; j++) {
				try {
					if (humanMap.containsKey("" + i + j)
							|| tapMap.containsKey("" + i + j)) {
						continue;
					}
					WebElement element = driver.findElement(By
							.xpath("//td[@id=" + "'" + i + j + "'" + "]/img"));// td[@id='11']/img

					if (element.isDisplayed()) {

						if (element.getAttribute("src").contains("x.png")) {
							humanMap.put("" + i + j, element);
							id = "" + i + j;
							break loop1;
						}

					}
				} catch (Exception ex) {
					System.out.println("=================");
					ex.getMessage();
				}
			}
		}
		return id;
	}

	public void winConditions() {
		// need to implement this logic
	}

	public void clickTap(Map<String, WebElement> tatocElements,
			Map<String, WebElement> humanMap, Map<String, WebElement> tapMap) {

		for (Map.Entry<String, WebElement> entry : tatocElements.entrySet()) {
			if (humanMap.containsKey(entry.getKey())
					|| tapMap.containsKey(entry.getKey())) {
				continue;
			}
			entry.getValue().click();
			tapMap.put(entry.getKey(), entry.getValue());
			return;
		}

	}
}
