package com.qait.demo.keywords;

import java.io.IOException;
import java.sql.ResultSet;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.qait.automation.domain.PostParameters;
import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.DataBaseConnecter;
import com.qait.automation.utils.RestAPITester;
import com.qait.automation.utils.YamlReader;

public class LoginPageActions extends GetPage {

	WebDriver driver;
	RestAPITester service = new RestAPITester();

	public LoginPageActions(WebDriver driver) {
		super(driver, "Loginpage");
		this.driver = driver;
	}

	public void enterLoginCredentials(String username, String password) {
		wait.waitForPageToLoadCompletely();
		// element("inp_username").sendKeys(username);
		// element("inp_password").sendKeys(password);
		// element("btn_login").click();
		// element(name).se
		/*
		 * host: 10.0.1.86 name: tatoc user: tatocuser password: tatoc01 query1:
		 * "select
		 */

		logMessage("User submitted login form with login credentials '"
				+ username + " and " + password + "'");
//		element("user_name").sendKeys(username);
		element("login_username").sendKeys(username);
//		element("pass_key").sendKeys(password);
		element("login_password").sendKeys(password);
		// login_button
		element("login_btn").click();
//		wait.hardWait(5);
//
//		executeJavascript("player.play()");
//		executeJavascript("player.seek(5)");
//		// executeJavascript("player.pause()");
//
//		driver.get(YamlReader.getYamlValue("database.url"));
//		String id = element("session_id").getText();
//		id = id.substring(id.indexOf(":") + 1);
//		System.out.println(YamlReader.getYamlValue("database.getRequestUrl")
//				+ id.trim());
//		// webservice hits
//		String getResponse = null;
//		String token;
//		int expiryTime;
//		try {
//			getResponse = RestAPITester.sendRequest(
//					YamlReader.getYamlValue("database.getRequestUrl")
//							+ id.trim(), "", "GET");
//			org.json.JSONObject jsonObj = new org.json.JSONObject(getResponse);
//			token = (String) jsonObj.get("token");
//			expiryTime = jsonObj.getInt("expires");
//
//			// post request
//			PostParameters para = new PostParameters();
//			para.setId(id.trim());
//			para.setAllow_access(1);
//			para.setSignature(token);
//			ObjectMapper mapper = new ObjectMapper();
//			String content = mapper.writeValueAsString(para);
//			System.out.println(YamlReader
//					.getYamlValue("database.postRequestUrl") + "?" + content);
//			RestAPITester.sendRequest(
//					YamlReader.getYamlValue("database.postRequestUrl") + "?"
//							+ "id=" + para.getId() + "&signature="
//							+ para.getSignature() + "&allow_access=1", "",
//					"POST");

		}
//	catch (IOException | JSONException ex) {
//
//			ex.printStackTrace();
//		}

		// download file and enter the signature
//		element("proceed_from_services").click();
//		wait.waitForPageToLoadCompletely();
//
//		FirefoxProfile firefoxProfile = new FirefoxProfile();
//
//		firefoxProfile.setPreference("browser.download.folderList", 2);
//		firefoxProfile.setPreference(
//				"browser.download.manager.showWhenStarting", false);
//		firefoxProfile.setPreference("browser.download.dir",
//				"/home/qainfotech/Downloads");
//		firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
//				"text/csv/dat");
//
//		driver = new FirefoxDriver(firefoxProfile);
//		// driver.navigate().to(element("download_file").toString());
//		// driver.findElement(By.xpath("//div[@class='page']/a")).click();
//
//		element("download_file").click();
//		element("signature").sendKeys("abcd");
//		// element("proceed_after_download").click();
//
//	}
}
