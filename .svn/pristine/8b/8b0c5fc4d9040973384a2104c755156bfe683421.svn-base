package com.dinfo.spiderplug.entry;

import com.dinfo.spiderplug.entry.common.QCC_Crawl_Common;

public class QCC_Company_CrawlCom {

	private static String tablename_1 = "cmp_base_info";
	private static String tablename_2 = "external_invest";
	private static int counts = 1;
	
	public static void main(String[] args) {

		QCC_Company_CrawlCom main = new QCC_Company_CrawlCom();
		main.run();
	}
	
	private void run(){
		
		for (int i = 0; i < counts; i++) {
			QCC_Crawl_Common qcc = new QCC_Crawl_Common((i+1),tablename_1,tablename_2);
			Thread thread = new Thread(qcc);
			thread.start();
		}
	}
}