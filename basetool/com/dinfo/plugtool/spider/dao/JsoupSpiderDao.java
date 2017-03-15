package com.dinfo.plugtool.spider.dao;

import java.util.Map;

import org.jsoup.nodes.Document;

public interface JsoupSpiderDao {
	/**
	 * @Description: get方法获得Document对象
	 * @param @param url
	 * @param @return   
	 * @return Document  
	 * @throws
	 * @author xulonglong
	 * @date 2017-1-20 上午9:16:19
	 */
	public Document getDocument(String url);
	/**
	 * @Description: get方法获得Document对象
	 * @param @param url
	 * @param @return   
	 * @return Document  
	 * @throws
	 * @author xulonglong
	 * @date 2017-1-20 上午9:16:19
	 */
	public Document getJsonDocument(String url);
	/**
	 * @Description: post方法获得Document对象
	 * @param @param url
	 * @param @return   
	 * @return Document  
	 * @throws
	 * @author xulonglong
	 * @date 2017-1-20 上午9:16:29
	 */
	public Document postDocument(String url,Map<String,String> map);
}
