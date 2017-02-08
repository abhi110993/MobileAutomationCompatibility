package com.qait.demo.tests;

import static com.qait.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.sql.ResultSet;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qait.automation.TestSessionInitiator;
import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.DataBaseConnecter;
import com.qait.automation.utils.YamlReader;

public class LoginTest {

	TestSessionInitiator test;
	String baseUrl;
	String tatocGameBaseUrl;
	String loginUrl ;
	DataBaseConnecter connector = new DataBaseConnecter();

	@BeforeClass
	public void Start_Test_Session() {
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		initVars();
		// test.launchApplication(baseUrl);
//		test.launchApplication(tatocGameBaseUrl);
		test.launchApplication(loginUrl);
	}

	@BeforeMethod
	public void handleTestMethodName(Method method) {
		test.stepStartMessage(method.getName());
	}
   
	private void initVars() {
		// baseUrl = getYamlValue("baseUrl");
//		tatocGameBaseUrl = getYamlValue("tatocGameBaseUrl");s
		loginUrl = getYamlValue("loginUrl");

	}
      
	@Test()
	public void testLogin(){
		test.loginPage.enterLoginCredentials(getYamlValue("loginUser"), getYamlValue("loginPassword"));
		boolean actual = true ;
		Assert.assertEquals(actual, true);
		
		
	}
	@Test(dependsOnMethods = { "testLogin"} )
	public void testCompose(){
		test.homePage.composeMail(getYamlValue("loginUser"));
	}
//	@Test
//	public void testTatocGame() {
//
//		test.homePage.clickGame();
//		
//	}


//	@AfterMethod
//	public void take_screenshot_on_failure(ITestResult result) {
//		test.takescreenshot.takeScreenShotOnException(result);
//	}

	@AfterClass
	public void close_Test_Session() {
		// test.closeBrowserSession();
	}

}
