package com.dinfo.plugtool.spider.dao.impl;

import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.dinfo.plugtool.spider.dao.JsoupSpiderDao;

public class JsoupSpiderDaoImpl implements JsoupSpiderDao{
	/**
	 * @Description: get方法获得Document对象
	 * @param @param url
	 * @param @return   
	 * @return Document  
	 * @throws
	 * @author xulonglong
	 * @date 2017-1-20 上午9:16:19
	 */
	public Document getDocument(String url){
		Document doc=null;
		for(int i=0;i<3;i++){
			try {
				doc=Jsoup.connect(url).userAgent("Mozilla").timeout(30*1000).get();
				break;
			} catch (Exception e) {
				continue;
			}
		}
		return doc;
	}
	/**
	 * @Description: get方法获得Document对象
	 * @param @param url
	 * @param @return   
	 * @return Document  
	 * @throws
	 * @author xulonglong
	 * @date 2017-1-20 上午9:16:19
	 */
	public Document getJsonDocument(String url){
		Document doc=null;
		for(int i=0;i<3;i++){
			try {
				doc=Jsoup.connect(url).userAgent("Mozilla").ignoreContentType(true).timeout(30*1000).get();
				break;
			} catch (Exception e) {
				continue;
			}
		}
		return doc;
	}
	/**
	 * @Description: post方法获得Document对象
	 * @param @param url
	 * @param @return   
	 * @return Document  
	 * @throws
	 * @author xulonglong
	 * @date 2017-1-20 上午9:16:29
	 */
	public Document postDocument(String url,Map<String,String> map){
		Document doc=null;
		for(int i=0;i<3;i++){
			try {
				Connection conn = Jsoup.connect(url).userAgent("Mozilla").ignoreContentType(true).timeout(30*1000);
				doc=conn.data(map).post();
				break;
			} catch (Exception e) {
				continue;
			}
		}
		return doc;
	}
}
