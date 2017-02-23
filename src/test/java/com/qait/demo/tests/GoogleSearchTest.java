package com.qait.demo.tests;

import static com.qait.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qait.automation.TestSessionInitiator;
import com.qait.automation.utils.DataBaseConnecter;

public class GoogleSearchTest {

	TestSessionInitiator test;
	String launchUrl ;
	DataBaseConnecter connector = new DataBaseConnecter();

	@BeforeClass
	public void Start_Test_Session() {
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		initVars();
		test.launchApplication(launchUrl);
	}

	@BeforeMethod
	public void handleTestMethodName(Method method) {
		test.stepStartMessage(method.getName());
	}
   
	private void initVars() {
		launchUrl = getYamlValue("baseUrl");

	}
      
	@Test
	public void inputSearchTest(){
		test.googlePage.enterKeywordToSearch(getYamlValue("keyword"));
	}
	
	@AfterMethod
	public void take_screenshot_on_failure(ITestResult result) {
		test.takescreenshot.takeScreenShotOnException(result);
	}

	@AfterClass
	public void close_Test_Session() {
		// test.closeBrowserSession();
	}

}
