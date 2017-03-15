package com.dinfo.plugtool.dao.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;

import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverCommon {
	
	/**
	 * @Description: Init Firefox Browser
	 * @param @return   
	 * @return WebDriver  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-9 上午9:51:34
	 */
	public WebDriver initFireFox() {
		System.out.println("Init Firefox Browser...");
		ProfilesIni profiles = new ProfilesIni();
		System.setProperty("webdriver.firefox.bin", "browser/Mozilla Firefox/firefox.exe");
		FirefoxProfile profile = profiles.getProfile("default");
		FirefoxDriver driver = new FirefoxDriver(profile);
		return driver;
	}
	/**
	 * @Description: Init chrome Browser
	 * @param @return   
	 * @return WebDriver  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-9 上午9:51:45
	 */
	public WebDriver initChrome() {
		System.out.println("Init chrome Browser...");
		System.setProperty("webdriver.chrome.driver", "browser/chrome/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		return driver;
	}
	/**
	 * @Description: Init IE 32 bit Browser
	 * @param @return   
	 * @return WebDriver  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-9 上午9:52:12
	 */
	public WebDriver initIE32() {
		System.out.println("Init IE 32 bit Browser...");
		// IE's general settings that will be perform automated test
		System.setProperty("webdriver.ie.driver", "browser/IE32/IEDriverServer.exe");
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		InternetExplorerDriver driver = new InternetExplorerDriver(ieCapabilities);
		return driver;
	}
	/**
	 * @Description: Init IE 64 bit Browser
	 * @param @return   
	 * @return WebDriver  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-9 上午9:52:31
	 */
	public WebDriver initIE64() {
		System.out.println("Init IE 64 bit Browser...");
		// IE's general settings that will be perform automated test
		System.setProperty("webdriver.ie.driver", "browser/IE64/IEDriverServer.exe");
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		InternetExplorerDriver driver = new InternetExplorerDriver(ieCapabilities);
		return driver;
	}
	/**
	 * @Description: Init PhantomJS Browser
	 * @param @return   
	 * @return WebDriver  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-9 上午9:52:46
	 */
	public WebDriver initPhantomJS() {
		System.out.println("Init PhantomJS Browser...");
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log",
				"org.apache.commons.logging.impl.NoOpLog");
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setJavascriptEnabled(true);
		caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				"browser/phantomjs-2.0.0-windows/bin/phantomjs.exe");
		PhantomJSDriver driver = new PhantomJSDriver(caps);
		return driver;
	}
	/**
	 * @Description: Init HtmlUnit Browser
	 * @param @return   
	 * @return WebDriver  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-9 上午9:53:01
	 */
	public WebDriver initHtmlunit() {
		System.out.println("Init HtmlUnit Browser...");
		// Close HtmlUnit log
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log",
				"org.apache.commons.logging.impl.NoOpLog");
		// The function of a parameter is to open JS
		HtmlUnitDriver driver = new HtmlUnitDriver(true);
		return driver;
	}

	/**
	 * @Description: Universal closing method
	 * @param @param driver   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-9 上午9:53:21
	 */
	private void current(WebDriver driver) {
		Set<String> windowHandles = driver.getWindowHandles();
		for (String string : windowHandles) {
			try {
				driver.switchTo().window(string);
				driver.close();
			} catch (Exception e) {
			}
		}
		try { driver.quit(); } catch (Exception e) {}
	}

	/**
	 * @Description: Close Firefox Browser
	 * @param @param driver   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-9 上午9:53:39
	 */
	public void closeFireFox(WebDriver driver) {
		System.out.println("Close Firefox Browser...");
		current(driver);
	}
	/**
	 * @Description: Close Chrome Browser
	 * @param @param driver   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-9 上午9:53:53
	 */
	public void closeChrome(WebDriver driver) {
		System.out.println("Close Chrome Browser...");
		current(driver);
		closeProcess("chromedriver.exe");
	}
	/**
	 * @Description: Close IE Browser
	 * @param @param driver   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-9 上午9:54:04
	 */
	public void closeIE(WebDriver driver) {
		System.out.println("Close IE Browser...");
		current(driver);
		closeProcess("IEDriverServer.exe");
	}
	/**
	 * @Description: Close PhantomJS Browser
	 * @param @param driver   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-9 上午9:54:16
	 */
	public void closePhantomJS(WebDriver driver) {
		System.out.println("Close PhantomJS Browser...");
		current(driver);
	}
	
	/**
	 * @Description: The question is solved that process resident memory cannot be closed
	 * @param @param processName   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-9 上午9:54:33
	 */
	public void closeProcess(String processName){
		InputStream ins = null;
		String[] cmd = new String[] { "cmd.exe", "/C", "TASKKILL /F /IM "+processName }; //Command
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			ins = process.getInputStream(); // Get the information after executing the command
			BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line); // Output
			}
			int exitValue = process.waitFor();
			System.out.println("Return Value:\n" + exitValue);
			process.getOutputStream().close(); // Close stream
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
