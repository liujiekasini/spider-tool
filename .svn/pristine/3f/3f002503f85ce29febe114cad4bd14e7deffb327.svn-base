package com.dinfo.spiderplug.main;

import java.util.HashMap;
import java.util.Map;

import com.dinfo.plugtool.common.ClassCommon;
import com.dinfo.spiderplug.entry.DFCF_CompanyBaseInfo_CrawlCom;
import com.dinfo.spiderplug.entry.DFCF_Company_CrawlCom;
/**
 * 匹配键值对，并分别与DFCF_CompanyBaseInfo_CrawlCom和DFCF_Company_CrawlCom中的crawlCommon方法进行map关联
 * @ClassName: SpiderPlugMain
 * @Description: TODO
 * @author xulonglong
 * @date 2017-2-13 上午11:28:02
 */
public class SpiderPlugMain {

	public static void main(String[] args) {
		//初始化类文件
		ClassCommon.getInstance().getClassCommon();
		Map<String,String> map=new HashMap<String, String>();
		//得到公司名称和编号
		map.put("url","http://quote.eastmoney.com/stocklist.html");
		DFCF_Company_CrawlCom.crawlCommon(map);
		//采集公司基本信息
//		map.put("url","http://f10.eastmoney.com/f10_v2/CompanySurvey.aspx?code=sz000919");
		map.put("url","http://f10.eastmoney.com/f10_v2/CompanySurvey.aspx?code=");
		DFCF_CompanyBaseInfo_CrawlCom.crawlCommon(map);
		
	}
}