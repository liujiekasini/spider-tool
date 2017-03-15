package com.dinfo.plugtool.spider.dao.impl;

import org.openqa.selenium.WebDriver;

import com.dinfo.plugtool.dao.common.WebDriverCommon;
import com.dinfo.plugtool.enumerate.BrowserType;
import com.dinfo.plugtool.spider.dao.WebDriverDao;

public class WebDriverDaoImpl implements WebDriverDao{
	private static WebDriverCommon wDriverCommon=new WebDriverCommon();
	
	/**
	 * @Description: Get WebDriver
	 * @param @param type Type.IE32,Type.IE64,Type.FIREFOX,Type.CHROME,Type.HTMLUNIT,Type.PHANTOMJS
	 * @param @return   
	 * @return WebDriver  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-9 上午9:50:54
	 */
	public WebDriver getWebDriver(BrowserType type) {
		switch (type) {
			case FIREFOX: {
				return wDriverCommon.initFireFox();
			}
			case CHROME: {
				return wDriverCommon.initChrome();
			}
			case IE32: {
				return wDriverCommon.initIE32();
			}
			case IE64: {
				return wDriverCommon.initIE64();
			}
			case HTMLUNIT: {
				return wDriverCommon.initHtmlunit();
			}
			case PHANTOMJS: {
				return wDriverCommon.initPhantomJS();
			}
			default: {
				return null;
			}
		}
	}
	/**
	 * @Description: lose browser and corresponding process
	 * @param @param driver   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-9 上午9:50:26
	 */
	public void close(WebDriver driver) {
		String name = driver.getClass().getSimpleName();
		switch (name) {
			case "InternetExplorerDriver": {
				wDriverCommon.closeIE(driver);
				break;
			}
			case "FirefoxDriver": {
				wDriverCommon.closeFireFox(driver);
				break;
			}
			case "ChromeDriver": {
				wDriverCommon.closeChrome(driver);
				break;
			}
			case "PhantomJSDriver": {
				wDriverCommon.closePhantomJS(driver);
				break;
			}
			default: {
				break;
			}
		}
	}

}
