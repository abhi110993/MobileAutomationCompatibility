package com.qait.demo.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;

public class GooglePageActions extends GetPage {

	WebDriver driver;

	public GooglePageActions(WebDriver driver) {
		super(driver, "GooglePage");
		this.driver = driver;

	}

	public void enterKeywordToSearch(String keyword){
		element("inp_searchTextBox").click();
		element("inp_searchTextBox").clear();
		element("inp_searchTextBox").sendKeys(keyword);
		logMessage("User enters '"+keyword+"' as a search term");
	}
}
