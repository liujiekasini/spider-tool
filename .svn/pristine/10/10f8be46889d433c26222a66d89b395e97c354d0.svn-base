package com.dinfo.plugtool.dao.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public class SolrDaoCommon {
	/**
	 * @Description: 转化成实体类
	 * @param @param record
	 * @param @param clazz
	 * @param @return   
	 * @return T  
	 * @throws
	 * @author xulonglong
	 * @date 2016-4-9 下午5:03:13
	 */
	public static <T> T toBean( SolrDocument record , Class<T> clazz){
	    ConvertUtilsBean convertUtils = new ConvertUtilsBean();
	    DateConverter dateConverter = new DateConverter();
	    convertUtils.register(dateConverter,Date.class);
	    BeanUtilsBean beanUtils = new BeanUtilsBean(convertUtils,new PropertyUtilsBean());
		
		 T t = null;
		try {
			t = clazz.newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		 Field[] fields =   clazz.getDeclaredFields();
		 for(Field field:fields){
			 Object value = record.get(field.getName());
			if(null!=value){
				try {
					beanUtils.setProperty(t, field.getName(), value);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		 }
		return t;
	}
	/**
	 * @Description: 转化成实体类列表
	 * @param @param records
	 * @param @param clazz
	 * @param @return   
	 * @return List<T>  
	 * @throws
	 * @author xulonglong
	 * @date 2016-4-9 下午5:03:19
	 */
	public static <T> List<T> toBeanList(SolrDocumentList records, Class<T>  clazz){
		List<T>  list = new ArrayList<T>();
		for(SolrDocument record : records){
			list.add(toBean(record,clazz));
		}
		return list;
	}
}
