package com.dinfo.basetool.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.dinfo.plugtool.enumerate.BrowserType;
import com.dinfo.plugtool.spider.dao.WebDriverDao;
import com.dinfo.plugtool.spider.dao.impl.WebDriverDaoImpl;

public class DriverDaoTest {
	
	public static void main(String[] args) {
		String url="https://www.baidu.com/";
		WebDriverDao webDriverDao=new WebDriverDaoImpl();
		WebDriver webDriver=webDriverDao.getWebDriver(BrowserType.FIREFOX);
		webDriver.get(url);
		String ssString=webDriver.getTitle();
		System.out.println(ssString);
		List<WebElement> as = null;
		try {
			as = webDriver.findElements(By.className("left_bar_item"));
		} catch (Exception e1) {}
		System.out.println(as.toString());
	}
}
