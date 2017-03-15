package com.dinfo.plugtool.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.dinfo.plugtool.common.DBCommon;
import com.dinfo.plugtool.dao.MySqlDaoMap;

/**
 * @ClassName: MySqlDao
 * @Description: mysql操作dao层
 * @author xulonglong
 * @date 2016-1-13 上午11:33:10
 */
public class MySqlDaoMapImpl implements MySqlDaoMap{
	public static QueryRunner runner =DBCommon.getInstance().getQueryRunnerMysql();
	
	/**
	 * @Description: 通用插入map类型数据 (拼接普通版)
	 * @param @param resultMap
	 * @param @param dataTableName   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-7 上午11:01:05
	 */
	public void insertMap(Map<String,Object> resultMap,String dataTableName){
		try {
			StringBuffer keyStr = new StringBuffer();
			StringBuffer valueStr = new StringBuffer();
			for(Entry<String,Object> entry : resultMap.entrySet()){
				keyStr.append(entry.getKey());
				keyStr.append(",");
				if(entry.getValue() != null){
					if(entry.getValue().toString().contains("'")){
						valueStr.append("\""+entry.getValue()+"\"");
					}else{
						valueStr.append("'"+entry.getValue()+"'");
					}
					valueStr.append(",");
				}else{
					valueStr.append("NULL");
					valueStr.append(",");
				}
				
			}
			
			if(keyStr.length()>0 || valueStr.length()>0){
				keyStr.deleteCharAt(keyStr.length()-1);
				valueStr.deleteCharAt(valueStr.length()-1);
			}
			
			String sql = "INSERT INTO "+dataTableName+"("+keyStr.toString()+") VALUES("+valueStr.toString()+")";
			runner.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description: 通用插入Map类型数据 (拼接Ignore版)
	 * @param @param resultMap
	 * @param @param dataTableName   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-7 上午11:10:18
	 */
	public void insertMapIgnore(Map<String,Object> resultMap,String dataTableName){
		
		StringBuffer sql = new StringBuffer();
		StringBuffer values = new StringBuffer();
		sql.append("INSERT IGNORE INTO ");
		sql.append(dataTableName);
		sql.append("(");
		Set<String> keySet = resultMap.keySet();
		Object[] params = new Object[keySet.size()];
		int i = 0;
		for (String key : keySet) {
			sql.append(key+",");
			values.append("?,");
			params[i] = resultMap.get(key).toString();
			i++;
		}
		
		StringBuffer sql2 = new StringBuffer();
		sql2.append(sql.substring(0, sql.length()-1));
		sql2.append(") VALUES("+values.substring(0, values.length()-1)+")");
		
		try {
			runner.update(sql2.toString(), params);
			System.out.println("数据存库成功："+sql2.toString());
		} catch (SQLException e) {
			System.out.println("数据存库失败："+sql2.toString());
			e.printStackTrace();
		}
	}
	/**
	 * @Description: 通用插入Map类型数据 (拼接Replace数据覆盖版)
	 * @param @param resultMap
	 * @param @param dataTableName   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-7 上午11:13:13
	 */
	public void insertMapReplace(Map<String,Object> resultMap,String dataTableName){
		
		StringBuffer sql = new StringBuffer();
		StringBuffer values = new StringBuffer();
		sql.append("REPLACE INTO ");
		sql.append(dataTableName);
		sql.append("(");
		Set<String> keySet = resultMap.keySet();
		Object[] params = new Object[keySet.size()];
		int i = 0;
		for (String key : keySet) {
			sql.append(key+",");
			values.append("?,");
			params[i] = resultMap.get(key);
			i++;
		}
		
		StringBuffer sql2 = new StringBuffer();
		sql2.append(sql.substring(0, sql.length()-1));
		sql2.append(") VALUES("+values.substring(0, values.length()-1)+")");
		
		try {
			runner.update(sql2.toString(), params);
			System.out.println("数据存库成功");
		} catch (SQLException e) {
			System.out.println("数据存库失败");
			e.printStackTrace();
		}
	}
	/**
	 * @Description: 通用插入Map类型数据 (传参ignore版)
	 * @param @param resultMap
	 * @param @param dataTableName   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-7 上午11:30:49
	 */
	public void saveMap(Map<String,Object> resultMap,String dataTableName){
		int num = resultMap.size();
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < num; i++){
			sb.append(",?");
		}
		
		String sql ="insert ignore into "+dataTableName+" values (null"+sb.toString()+")";
		
		Object[][] param = new Object[1][num];
		
		int i = 0;
		for(Entry<String, Object> entry : resultMap.entrySet()){
			param[0][i] = entry.getValue();
			i++;
		}
		try {
			runner.batch(sql, param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description: 通用插入MapList
	 * @param @param resultList
	 * @param @param dataTableName   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-7 上午11:14:18
	 */
	public void insertMapList(List<Map<String, Object>> resultList,String dataTableName){
		if(CollectionUtils.isNotEmpty(resultList)){
			Map<String, Object> map = resultList.get(0);
			StringBuffer keyStr = new StringBuffer();
			StringBuffer valueStr = new StringBuffer();
			for(Entry<String,Object> entry : map.entrySet()){
				keyStr.append(""+entry.getKey()+"");
				keyStr.append(",");
				valueStr.append("?,");
			}
			
			if(keyStr.length()>0 || valueStr.length()>0){
				keyStr.deleteCharAt(keyStr.length()-1);
				valueStr.deleteCharAt(valueStr.length()-1);
			}
			
			String sql = "INSERT IGNORE INTO "+dataTableName+"("+keyStr.toString()+") VALUES ("+valueStr.toString()+")";
			
			Object[][] param = new Object[resultList.size()][map.size()];
			System.out.println("个数："+resultList.size()+"  参数个数：："+map.size());
			for(int i=0;i<resultList.size();i++){
				Map<String,Object> bean =  resultList.get(i);
				
				int j = 0;
				for(Entry<String, Object> entry : bean.entrySet()){
					if(entry.getValue() == null){
						param[i][j] = "";
					}else{
						param[i][j] = entry.getValue();
					}
					j++;
				}
			}
			
			try {
				runner.batch(sql, param);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
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
		 Map<String, Object> paramMap,List<String>paramList,Integer startNum,Integer endNum) {

		List<Map<String, Object>> list = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select " );
			if(paramList != null && paramList.size() > 0 ){
				for(int i = 0;i<paramList.size();i++){
					if(i == paramList.size()-1){
						sql.append( paramList.get(i) );
					}else{
						sql.append( paramList.get(i) +",");
					}
				}
			}else{
				sql.append(" * ");
			}
			
			sql.append("  from "+tablename+" where 1=1");
			if(paramMap != null && paramMap.size() > 0 ){
				Set<Map.Entry<String, Object>> sets = paramMap.entrySet();
				for (Map.Entry<String, Object> entry : sets) {
					sql.append(" and " + entry.getKey() + "= '" + entry.getValue()+ "' ");
				}
			}
			if(startNum != null && endNum != null){
				sql.append(" LIMIT "+startNum+","+endNum);
			}
			list = runner.query(sql.toString(),new MapListHandler());
			for (Map<String, Object> map : list) {
				System.out.println("-----------------");
				for (Map.Entry<String, Object> me : map.entrySet()) {
					System.out.println(me.getKey() + "=" + me.getValue());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
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
	public List<Map<String,Object>> query(String sql) throws SQLException{
		List<Map<String,Object>> listmap = runner.query(sql, new MapListHandler());
		return listmap;
	}
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
	@SuppressWarnings("deprecation")
	public List<Map<String,Object>> getDataMapList(String sql,List<Object> paramlist) throws SQLException{
		int size = paramlist.size();
		Object[] param = new Object[size];
		for (int i = 0; i < size; i++) {
			param[i] = paramlist.get(i);
		}
		List<Map<String,Object>> listmap = runner.query(sql,param,new MapListHandler());
		return listmap;
	}
	
}
