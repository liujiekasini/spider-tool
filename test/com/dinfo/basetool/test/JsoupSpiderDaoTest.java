package com.dinfo.basetool.test;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;

import com.dinfo.plugtool.spider.dao.JsoupSpiderDao;
import com.dinfo.plugtool.spider.dao.impl.JsoupSpiderDaoImpl;

public class JsoupSpiderDaoTest {
	public static void main(String[] args) {
		String url="http://www.jsbchina.cn/CN/wzdt/index.html";
		JsoupSpiderDao jsoupSpiderDao=new JsoupSpiderDaoImpl();
		Document doc=jsoupSpiderDao.getDocument(url);
		System.out.println(doc.html());
		
		//
		Map<String,String> map=new HashMap<String,String>();
		map.put("startIndex","0");
		map.put("endIndex","10");
		map.put("direction","x");
		map.put("paixu","r");
		map.put("prodtype","2");
		map.put("prodstatus","0");
		map.put("deadline","0");
		Document doc2=jsoupSpiderDao.postDocument(url,map);
		System.out.println(doc2.html());
	}
}
