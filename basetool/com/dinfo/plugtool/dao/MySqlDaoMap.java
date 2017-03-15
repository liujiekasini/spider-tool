package com.dinfo.plugtool.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: MySqlDao
 * @Description: mysql操作dao层
 * @author xulonglong
 * @date 2016-1-13 上午11:33:10
 */
public interface MySqlDaoMap {
	
	/**
	 * @Description: 通用插入map类型数据 (拼接普通版)
	 * @param @param resultMap
	 * @param @param dataTableName   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-7 上午11:01:05
	 */
	public void insertMap(Map<String,Object> resultMap,String dataTableName);
	/**
	 * @Description: 通用插入Map类型数据 (拼接Ignore版)
	 * @param @param resultMap
	 * @param @param dataTableName   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-7 上午11:10:18
	 */
	public void insertMapIgnore(Map<String,Object> resultMap,String dataTableName);
	/**
	 * @Description: 通用插入Map类型数据 (拼接Replace数据覆盖版)
	 * @param @param resultMap
	 * @param @param dataTableName   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-7 上午11:13:13
	 */
	public void insertMapReplace(Map<String,Object> resultMap,String dataTableName);
	/**
	 * @Description: 通用插入Map类型数据 (传参ignore版)
	 * @param @param resultMap
	 * @param @param dataTableName   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-7 上午11:30:49
	 */
	public void saveMap(Map<String,Object> resultMap,String dataTableName);
	/**
	 * @Description: 通用插入MapList
	 * @param @param resultList
	 * @param @param dataTableName   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-7 上午11:14:18
	 */
	public void insertMapList(List<Map<String, Object>> resultList,String dataTableName);
	/**
	 * @Description: 根据条件查询数据信息
	 * @param @param tablename
	 * @param @param paramMap
	 * @param @param paramList
	 * @param @param startNum
	 * @param @param endNum
	 * @param @return   
	 * @return List<Map<String,Object>>  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-7 下午2:09:36
	 */
	public List<Map<String, Object>> getDataMapList(String tablename,
		 Map<String, Object> paramMap,List<String>paramList,Integer startNum,Integer endNum);
	/**
	 * @Description: 通用简单读取数据MapList (不带参数)
	 * @param @param sql
	 * @param @return
	 * @param @throws SQLException   
	 * @return List<Map<String,Object>>  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-7 上午11:24:34
	 */
	public List<Map<String,Object>> query(String sql) throws SQLException;
	/**
	 * @Description: 通用简单读取数据MapList (带参数)
	 * @param @param sql
	 * @param @param list
	 * @param @return
	 * @param @throws SQLException   
	 * @return List<Map<String,Object>>  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-7 上午11:21:10
	 */
	public List<Map<String,Object>> getDataMapList(String sql,List<Object> paramlist) throws SQLException;
	
}
