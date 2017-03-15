package com.dinfo.plugtool.dao;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;

/**
 * @ClassName: HbaseDao
 * @Description: 操作Hbse工具类
 * @author xulonglong
 * @date 2016-7-4 下午2:21:35
 */
public interface HBaseDao {
	
	/**
	 * @Description: 创建表
	 * @param @param hBaseAdmin
	 * @param @param table_name
	 * @param @param family_name
	 * @param @throws IOException   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-7 下午3:34:00
	 */
	public void createTable(String table_name,String family_name)
			throws IOException;
	/**
	 * @Description: 对于某一个Table表注册Coprocessor
	 * @param @param table_name
	 * @param @throws IOException   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-8 下午3:22:57
	 */
	public void registerCoprocessor(String table_name) throws IOException;
	/**
	 * @Description: 得到table对象
	 * @param @param tableName
	 * @param @return   
	 * @return Table  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-8 下午1:41:25
	 */
	public Table getTable(String tableName);
	/**
	 * @Description: 得到锁
	 * @param @param tableName
	 * @param @param family
	 * @param @param rowkey
	 * @param @param col   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-8 下午1:42:14
	 */
	public void getLock(String tableName,String family,String rowkey,String col);
	/**
	 * @Description: 释放锁
	 * @param @param tableName
	 * @param @param family
	 * @param @param rowkey
	 * @param @param col   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-8 下午1:42:26
	 */
	public void releaseLock(String tableName,String family,String rowkey,String col);
	/**
	 * @Description: 得到所要查询数据的总数
	 * @param @param tableName
	 * @param @param scan
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-7 下午2:47:55
	 */
    public int getDataCount(String tableName,Scan scan);
    /**
	 * @Description: 删除表
	 * @param @param tableName
	 * @param @throws IOException   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-8 下午1:41:47
	 */
	public void deleteTable(String tableName) throws IOException;
	/**
	 * @Description: 根据条件删除数据
	 * @param @param table_name
	 * @param @param family_name
	 * @param @param map   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-7 下午5:20:11
	 */
	public void deleteByWhere(String table_name,String family_name,Map<String,String> filterMap);
    /**
	 * @Description: 根据rowkey查询单条数据
	 * @param @param table_name
	 * @param @param family
	 * @param @param rowKey
	 * @param @param columnList 要获取的值列表
	 * @param @return   
	 * @return Map<String,String>  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-7 下午2:00:30
	 */
	public Map<String, String> getDateByRowKey(String table_name,
			String family_name, String rowKey, List<String> columnList);
	/**
	 * @Description: 条件查询:
	 * @param tableName：表名  || family_name：列镞
	 * @param filterMap 过滤条件->key为列,value为值   || columnList：查询条件
	 * @param pagNum
	 * @param @return   
	 * @return List<Map<String,String>>  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-7 下午5:05:11
	 */
	public List<Map<String,String>> getDataByWhere(String table_name,String family_name,
			Map<String,String> filterMap,List<String> columnList,String startRow,
			String endRow,boolean isAsc,int pagNum);  
	/**
	 * @Description: 插入数据
	 * @param @param table_name：table名称
	 * @param @param family_name
	 * @param @param rowKey：rowKey名称
	 * @param @param columnMap：key代表列，value代表值
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-7 下午5:46:19
	 */
	public void insertMap(String table_name,String family_name,String rowKey,Map<String,String> columnMap);
	/**
	 * @Description: 批量插入数据
	 * @param @param table_name   表名
	 * @param @param family_name  列镞
	 * @param @param dataMapList  List<rowkey,Map<字段名，字段值>>
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-7 下午5:50:31
	 */
	public void insertMapList(String table_name,String family_name,List<Map<String,Map<String,String>>> dataMapList);
	/**
	 * @Description: Hbase插入单条数据通用
	 * @param @param bean
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 上午11:19:07
	 */
	public <T> boolean insertBean(Class<T> clazz,T t,String tableName,String columnFamily,String rowkeyName);
	/**
	 * @Description: 批量插入数据列表通用
	 * @param @param list
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 上午11:18:13
	 */
	public <T> boolean insertBeanList(Class<T> info_clazz,List<T> info_tList,String info_tableName,String info_columnFamily,String info_rowkeyName,
			String index_tableName,String index_columnFamily,String index_rowkeyName,String index_fieldName);
	
}
