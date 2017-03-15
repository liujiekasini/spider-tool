package com.dinfo.plugtool.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import com.dinfo.plugtool.common.DBCommon;
import com.dinfo.plugtool.dao.MySqlDaoBean;
import com.dinfo.plugtool.dao.common.BaseDataCommon;

/**
 * @ClassName: MySqlDao
 * @Description: mysql操作dao层
 * @author xulonglong
 * @date 2016-1-13 上午11:33:10
 */
public class MySqlDaoBeanImpl implements MySqlDaoBean{
	private static QueryRunner runner =DBCommon.getInstance().getQueryRunnerMysql();
	
	/**
	 * @Description: 通用简单读取数据id
	 * @param @param sql
	 * @param @return   
	 * @return List<Integer>  
	 * @throws
	 * @author xulonglong
	 * @date 2016-1-13 上午11:33:49
	 */
	public List<Integer> getIdList(String sql) {
		List<Integer> idList = new ArrayList<Integer>();
		try {
			idList = runner.query(sql, new ColumnListHandler<Integer>());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idList;
	}
	
	/**
	 * @Description: 通用简单读取数据某个字段
	 * @param @param sql
	 * @param @return   
	 * @return List<String>  
	 * @throws
	 * @author xulonglong
	 * @date 2016-3-1 下午1:00:30
	 */
	public List<String> getFieldList(String sql) {
		List<String> fieldList = new ArrayList<String>();
		try {
			fieldList = runner.query(sql, new ColumnListHandler<String>());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fieldList;
	}
	/**
	 * @Description: 通用获取数量
	 * @param @param sql
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-7 上午10:31:22
	 */
	public int getCount(String sql){
		List<Long> fieldList = new ArrayList<Long>();
		try {
			fieldList = runner.query(sql, new ColumnListHandler<Long>());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fieldList.get(0).intValue();
	}
	/**
	 * @Description: 通用简单读取数据Bean
	 * @param @param sql
	 * @param @return   
	 * @return ReportBean  
	 * @throws
	 * @author xulonglong
	 * @date 2016-1-13 上午11:34:34
	 */
	public <T> T getDataBean(String sql,Class<T> clazz) {
		List<T> resultlist= new ArrayList<T>();
		T bean = null;
		try {
			bean = clazz.newInstance();
			resultlist = runner.query(sql, new BeanListHandler<T>(clazz));
			if(resultlist!=null && resultlist.size()>0){
				bean=resultlist.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	/**
	 * @Description: 通用简单读取数据BeanList
	 * @param @param sql
	 * @param @return   
	 * @return List<ReportBean>  
	 * @throws
	 * @author xulonglong
	 * @date 2016-1-13 上午11:34:47
	 */
	public <T> List<T> getDataBeanList(String sql,Class<T> clazz) {
		List<T> resultlist= new ArrayList<T>();
		try {
			resultlist = runner.query(sql, new BeanListHandler<T>(clazz));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultlist;
	}
	/**
	 * @Description: 通用修改
	 * @param @param sql
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author xulonglong
	 * @date 2016-1-13 上午11:34:57
	 */
	public int updateCommon(String sql){
		int num=0;
		try {
    		num=runner.update(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
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
	public <T> void insertBeanOutID(String tableName,Class<T> clazz,T t) {
		String fieldNameStr="";
		String fieldValueStr="";
		try {
			Field[] s = t.getClass().getDeclaredFields();
			for (Field f : s) {
				String fieldName = f.getName();
				if(!"id".equals(fieldName)){
					Method fieldMethod = BaseDataCommon.getGetMehtodByField(clazz, fieldName);
					String fieldType =fieldMethod.getReturnType().getSimpleName();
					Object fieldValue;
					fieldValue = fieldMethod.invoke(t, new Object[] {});
					if (fieldValue != null && !"".equals(fieldValue)) {
						if("String".equals(fieldType)){
							fieldNameStr=fieldNameStr+","+fieldName;
							fieldValueStr=fieldValueStr+",'"+fieldValue+"'";
						}else if("Int".equals(fieldType) || "int".equals(fieldType)){
							fieldNameStr=fieldNameStr+","+fieldName;
							fieldValueStr=fieldValueStr+","+fieldValue+"";
						}else{
							
						}
					}
				}else{
					continue;
				}
			}
			fieldNameStr=fieldNameStr.substring(1);
			fieldValueStr=fieldValueStr.substring(1);
			String sql="insert into "+tableName+"("+fieldNameStr+") values ("+fieldValueStr.replace("？", "?").replace("?", "")+")";
			try {
				runner.update(sql);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
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
	public <T> void insertBeanInID(String tableName,Class<T> clazz,T t) {
		String fieldNameStr="";
		String fieldValueStr="";
		try {
			Field[] s = t.getClass().getDeclaredFields();
			for (Field f : s) {
				String fieldName = f.getName();
				Method fieldMethod = BaseDataCommon.getGetMehtodByField(clazz, fieldName);
				String fieldType =fieldMethod.getReturnType().getSimpleName();
				Object fieldValue;
				fieldValue = fieldMethod.invoke(t, new Object[] {});
				if (fieldValue != null && !"".equals(fieldValue)) {
					if("String".equals(fieldType)){
						fieldNameStr=fieldNameStr+","+fieldName;
						fieldValueStr=fieldValueStr+",'"+fieldValue+"'";
					}else if("Int".equals(fieldType) || "int".equals(fieldType)){
						fieldNameStr=fieldNameStr+","+fieldName;
						fieldValueStr=fieldValueStr+","+fieldValue+"";
					}else{
						
					}
				}
			}
			fieldNameStr=fieldNameStr.substring(1);
			fieldValueStr=fieldValueStr.substring(1);
			String sql="insert into "+tableName+"("+fieldNameStr+") values ("+fieldValueStr+")";
			try {
				runner.update(sql);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
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
	public <T> void insertBeanInReID(String tableName,Class<T> clazz,T t) {
		String fieldNameStr="";
		String fieldValueStr="";
		try {
			Field[] s = t.getClass().getDeclaredFields();
			for (Field f : s) {
				String fieldName = f.getName();
				Method fieldMethod = BaseDataCommon.getGetMehtodByField(clazz, fieldName);
				String fieldType =fieldMethod.getReturnType().getSimpleName();
				Object fieldValue;
				fieldValue = fieldMethod.invoke(t, new Object[] {});
				if (fieldValue != null && !"".equals(fieldValue)) {
					if("String".equals(fieldType)){
						fieldNameStr=fieldNameStr+","+fieldName;
						fieldValueStr=fieldValueStr+",'"+fieldValue+"'";
					}else if("Int".equals(fieldType) || "int".equals(fieldType)){
						fieldNameStr=fieldNameStr+","+fieldName;
						fieldValueStr=fieldValueStr+","+fieldValue+"";
					}else{
						
					}
				}
			}
			fieldNameStr=fieldNameStr.substring(1);
			fieldValueStr=fieldValueStr.substring(1);
			String sql="replace into "+tableName+"("+fieldNameStr+") values ("+fieldValueStr+")";
			try {
				runner.update(sql);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
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
	public <T> void insertBeanList(String tableName,Class<T> clazz,List<T> tList) {
		if(tList!=null && tList.size()>0){
			for(T t:tList){
				insertBeanOutID(tableName,clazz,t);
			}
		}
	}
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
	public <T> void insertBeanInidList(String tableName,Class<T> clazz,List<T> tList) {
		if(tList!=null && tList.size()>0){
			for(T t:tList){
				insertBeanInID(tableName,clazz,t);
			}
		}
	}
	
	
	public static void main(String[] args) {
		int mark=0;
		for(int i=0;i<200;i++){
			MySqlDaoBeanImpl mySqlDao=new MySqlDaoBeanImpl();
			String sql="select id from case_rowkey_log";
			mySqlDao.getIdList(sql);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(mark++);
		}
//		MySqlDao mySqlDao=new MySqlDao();
//		String sql="CREATE TABLE casebean_01 (`id` int(25) NOT NULL AUTO_INCREMENT," +
//				"`title` varchar(255) DEFAULT NULL COMMENT '标题'," +
//				"`content` text COMMENT '内容'," +
//				"`judgmentType` varchar(255) DEFAULT NULL COMMENT '案件类型'," +
//				"`judgmentUnit` varchar(255) DEFAULT NULL COMMENT '法院名称', " +
//				"`ReferenceNumber` varchar(255) DEFAULT NULL COMMENT '案号'," +
//				"`judgmentDate` varchar(255) DEFAULT NULL COMMENT '裁判日期'," +
//				"`judgmentProcedure` varchar(255) DEFAULT NULL COMMENT '审判程序'," +
//				"`url` varchar(255) DEFAULT NULL," +
//				"`get_time` varchar(255) DEFAULT NULL COMMENT '采集时间'," +
//				" PRIMARY KEY (`id`)," +
//				"UNIQUE KEY `CaseBean_ReferenceNumber-01` (`ReferenceNumber`) " +
//				"USING BTREE) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8";
//		mySqlDao.updateCommon(sql);
	}
}
