package com.dinfo.plugtool.dao.common;

import java.lang.reflect.Method;

public class BaseDataCommon {
	/**
	 * @Description: 
	 * @param @param clazz
	 * @param @param field
	 * @param @return   
	 * @return Method  
	 * @throws
	 * @author xulonglong
	 * @date 2016-3-1 下午5:33:14
	 */
	public static <T> Method getGetMehtodByField(Class<T> clazz, String field) {
		Method method = null;
		String methodName = "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
		try {
			method = clazz.getMethod(methodName, new Class[] {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return method;
	}
	/**
	 * @Description: 
	 * @param @param clazz
	 * @param @param field
	 * @param @return   
	 * @return Method  
	 * @throws
	 * @author xulonglong
	 * @date 2016-3-1 下午5:33:14
	 */
	public <T> Method getSetMehtodByField(Class<T> clazz, String field) {
		Method method = null;
		String methodName = "set" + field.substring(0, 1).toUpperCase() + field.substring(1);
		try {
			method = clazz.getMethod(methodName,String.class);
			method.setAccessible(true);//因为写成private 所以这里必须设置
		} catch (Exception e) {
			e.printStackTrace();
		}
		return method;
	}
	
}
