package com.dinfo.plugtool.spider.dao;

import org.openqa.selenium.WebDriver;

import com.dinfo.plugtool.enumerate.BrowserType;

public interface WebDriverDao {
	/**
	 * @Description: Get WebDriver
	 * @param @param type Type.IE32,Type.IE64,Type.FIREFOX,Type.CHROME,Type.HTMLUNIT,Type.PHANTOMJS
	 * @param @return   
	 * @return WebDriver  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-9 上午9:50:54
	 */
	public WebDriver getWebDriver(BrowserType type);
	/**
	 * @Description: lose browser and corresponding process
	 * @param @param driver   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-9 上午9:50:26
	 */
	public void close(WebDriver driver);

}
