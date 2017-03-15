package com.dinfo.basetool.test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogTest {
	public static void main(String[] args) {
		Logger log = Logger.getLogger(LogTest.class.getName());
//		PropertyConfigurator.configure("src/log4j.properties");
		PropertyConfigurator.configure("log4j.properties");
		log.debug("yes,debug");
		log.info("yes,info");
		log.error("yes,error");
		log.warn("yes,warn");
	}
}
