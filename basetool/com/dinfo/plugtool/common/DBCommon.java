package com.dinfo.plugtool.common;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.solr.client.solrj.impl.LBHttpSolrServer;

/**
 * @ClassName: DBCommon
 * @Description: 数据源配置
 * @author xulonglong
 * @date 2016-1-13 上午11:36:37
 */
public class DBCommon {
	private static DBCommon instance = null;
	private static DataSource ds_mysql;
	private static QueryRunner mysql_runner;
	private static LBHttpSolrServer solrServer;
	private static Configuration config = null;
	private static org.apache.hadoop.hbase.client.Connection hbaseConnection;
	
	private DBCommon() {

	}
	public static DBCommon getInstance() {
        if (instance == null) { 
            // 给类加锁 防止线程并发
            synchronized (DBCommon.class) {
                if (instance == null) {
                	instance = new DBCommon();
                }
            }
        }
        return instance;
    }
	
	/**
	 * @Description: 创建Mysql数据源
	 * @param @return   
	 * @return QueryRunner  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 上午11:12:59
	 */
	public synchronized QueryRunner getQueryRunnerMysql() {
		if(mysql_runner==null){
			if (ds_mysql == null) {
				BasicDataSource dbcpDataSource = new BasicDataSource();
				try{
					dbcpDataSource.setDriverClassName(AttributeCommon.driver_mysql);
					dbcpDataSource.setUrl(AttributeCommon.url_mysql);
					dbcpDataSource.setUsername(AttributeCommon.username_mysql);
					dbcpDataSource.setPassword(AttributeCommon.password_mysql);
					dbcpDataSource.setValidationQuery("select 1");
					dbcpDataSource.setValidationQueryTimeout(1);
					dbcpDataSource.setDefaultAutoCommit(true);
					dbcpDataSource.setMaxActive(100);
					dbcpDataSource.setMaxIdle(30);
					dbcpDataSource.setMaxWait(500);
				}catch (Exception e) {
					e.printStackTrace();
				}
				DBCommon.ds_mysql = (DataSource) dbcpDataSource;
				System.out.println("Init dbcp...");
			}
			return new QueryRunner(DBCommon.ds_mysql);
		}
		return mysql_runner;
	}
	/**
	 * @Description: 创建Hbase连接数据源
	 * @param @return   
	 * @return org.apache.hadoop.hbase.client.Connection  
	 * @throws
	 * @author xulonglong
	 * @date 2016-3-29 下午8:53:30
	 */
	public synchronized org.apache.hadoop.hbase.client.Connection getConnectionHbase() {
		 
//		if (hbaseConnection == null) {
//			Configuration config = null;
			// 加载集群配置
			config = HBaseConfiguration.create();
		//	config.setLong(HConstants.HBASE_REGIONSERVER_LEASE_PERIOD_KEY, 120000); 
			config.set("hadoop.home.dir", "F:\\hadoop2.6.X64");
			config.set("hbase.zookeeper.quorum", AttributeCommon.hbase_ip);
			config.set("hbase.zookeeper.property.clientPort", AttributeCommon.hbase_port);
			
			config.set("hbase.rpc.timeout", "2000"); 
			config.set("hbase.client.operation.timeout", "3000"); 
			config.set("hbase.client.scanner.timeout.period", "10000");
			
			// 创建表池(可伟略提高查询性能，具体说明请百度或官方API)
			try {
//				if(hbaseConnection!=null){
//					hbaseConnection.close();
//				}
				hbaseConnection = ConnectionFactory.createConnection(config);	
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Init hbase...");
//		}
		return hbaseConnection;
	}
	/**
	 * @Description: 创建Solr数据源
	 * @param @return   
	 * @return LBHttpSolrServer  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-7 下午1:49:21
	 */
	public synchronized LBHttpSolrServer getSolrServer() {
		if (solrServer == null) {
			try {
				DBCommon.solrServer = new LBHttpSolrServer(AttributeCommon.url_solr.split(";"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return solrServer;
	}
	
	public Configuration getConfig() {
		return config;
	}
	public static void main(String[] args) throws SQLException {

	}

}
