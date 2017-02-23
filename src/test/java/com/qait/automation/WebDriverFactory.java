/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qait.automation;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

@SuppressWarnings("rawtypes")
public class WebDriverFactory {

	private static String browser;
	private static final DesiredCapabilities capabilities = new DesiredCapabilities();

	public WebDriver getDriver(Map<String, String> seleniumconfig) {
		browser = seleniumconfig.get("browser");

		if (seleniumconfig.get("seleniumserver").equalsIgnoreCase("local")) {
			if (browser.equalsIgnoreCase("android-chrome")) {
				return getChromeDriver(seleniumconfig);
			} else if (browser.equalsIgnoreCase("android-firefox")) {
				return getFirefoxDriver(seleniumconfig);
			}
		}
		return getChromeDriver(seleniumconfig);
	}

	
	private static AndroidDriver getChromeDriver(Map<String, String> seleniumconfig) {
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,BrowserType.CHROME);
		capabilities.setCapability(MobileCapabilityType.PLATFORM,Platform.ANDROID);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"my phone");
		capabilities.setCapability(MobileCapabilityType.VERSION,"4.4.2");
		/*capabilities.setCapability(MobileCapabilityType.APP, new File(seleniumconfig.get("app_file_path")).getAbsolutePath());*/
		capabilities.setJavascriptEnabled(true);
		URL url = null;
		try {
			url = new URL(seleniumconfig.get("appiumServer"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return new AndroidDriver(url, capabilities);
	}
	
	private static AndroidDriver getFirefoxDriver(Map<String, String> seleniumconfig) {
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,BrowserType.CHROME);
		capabilities.setCapability(MobileCapabilityType.PLATFORM,Platform.ANDROID);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"my phone");
		capabilities.setCapability(MobileCapabilityType.VERSION,"5.0.1");
		//capabilities.setCapability(MobileCapabilityType.APP, new File(seleniumconfig.get("app_file_path")).getAbsolutePath());
		capabilities.setJavascriptEnabled(true);
		URL url = null;
		try {
			url = new URL(seleniumconfig.get("appiumServer"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return new AndroidDriver(url, capabilities);
	}

}
