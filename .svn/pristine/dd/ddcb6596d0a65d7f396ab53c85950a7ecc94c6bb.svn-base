package com.dinfo.plugtool.dao;

import java.util.List;

public interface MySqlDaoBean {
	
	/**
	 * @Description: 通用简单读取数据id
	 * @param @param sql
	 * @param @return   
	 * @return List<Integer>  
	 * @throws
	 * @author xulonglong
	 * @date 2016-1-13 上午11:33:49
	 */
	public List<Integer> getIdList(String sql);
	/**
	 * @Description: 通用简单读取数据某个字段
	 * @param @param sql
	 * @param @return   
	 * @return List<String>  
	 * @throws
	 * @author xulonglong
	 * @date 2016-3-1 下午1:00:30
	 */
	public List<String> getFieldList(String sql);
	/**
	 * @Description: 通用获取数量
	 * @param @param sql
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-7 上午10:31:22
	 */
	public int getCount(String sql);
	/**
	 * @Description: 通用简单读取数据Bean
	 * @param @param sql
	 * @param @return   
	 * @return ReportBean  
	 * @throws
	 * @author xulonglong
	 * @date 2016-1-13 上午11:34:34
	 */
	public <T> T getDataBean(String sql,Class<T> clazz);
	/**
	 * @Description: 通用简单读取数据BeanList
	 * @param @param sql
	 * @param @return   
	 * @return List<ReportBean>  
	 * @throws
	 * @author xulonglong
	 * @date 2016-1-13 上午11:34:47
	 */
	public <T> List<T> getDataBeanList(String sql,Class<T> clazz);
	/**
	 * @Description: 通用修改
	 * @param @param sql
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author xulonglong
	 * @date 2016-1-13 上午11:34:57
	 */
	public int updateCommon(String sql);
	/**
	 * @Description: 通用插入Bean(不插入id版)
	 * @param @param tableName
	 * @param @param clazz
	 * @param @param t   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2016-3-1 下午5:32:37
	 */
	public <T> void insertBeanOutID(String tableName,Class<T> clazz,T t);
	/**
	 * @Description: 通用插入Bean(插入id版)
	 * @param @param tableName
	 * @param @param clazz
	 * @param @param t   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2016-3-1 下午5:32:37
	 */
	public <T> void insertBeanInID(String tableName,Class<T> clazz,T t);
	/**
	 * @Description: 通用插入Bean(数据覆盖版)
	 * @param @param tableName
	 * @param @param clazz
	 * @param @param t   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2016-3-1 下午5:32:37
	 */
	public <T> void insertBeanInReID(String tableName,Class<T> clazz,T t);
	/**
	 * @Description: 通用插入beanList(不插入id版)
	 * @param @param tableName
	 * @param @param clazz
	 * @param @param tList   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2016-3-10 上午9:55:58
	 */
	public <T> void insertBeanList(String tableName,Class<T> clazz,List<T> tList);
	/**
	 * @Description: 通用插入beanList(数据覆盖版)
	 * @param @param tableName
	 * @param @param clazz
	 * @param @param tList   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2016-3-10 上午9:55:58
	 */
	public <T> void insertBeanInidList(String tableName,Class<T> clazz,List<T> tList);
	
}
