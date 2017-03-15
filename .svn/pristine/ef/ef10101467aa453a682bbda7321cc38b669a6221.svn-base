package com.dinfo.basetool.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dinfo.plugtool.dao.HBaseDao;
import com.dinfo.plugtool.dao.impl.HBaseDaoImpl;

public class HBaseDaoTest {
	private static final String TABLE_NAME = "CODEX_INFO_T";
	private static final String FAMILY_NAME = "judgementInfo";
	static{
	    org.apache.log4j.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(org.apache.log4j.Level.FATAL);
	    java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.SEVERE);
	}
	static HBaseDao hBaseDao=new HBaseDaoImpl();
	
	public static void main(String[] args) {
//		String table_name="xll_test";
		try {
//			hBaseDao.createTable(table_name,"test");
//			hBaseDao.registerCoprocessor(table_name);
			String rowkey="77965d37dacefb61ddf1a99c13b5e3a5";
			List<String> columnList=new ArrayList<String>();
			
			Map<String, String> map=hBaseDao.getDateByRowKey(TABLE_NAME, FAMILY_NAME,rowkey, columnList);
			System.out.println(map.toString());
//			Scan scan = new Scan();
//	        scan.addColumn(Bytes.toBytes("cf1"), Bytes.toBytes("c1"));
//
//			int count=hBaseDao.getDataCount(table_name,scan);
//			System.out.println(count);
//			Table table=hBaseDao.getTable(table_name);
//			System.out.println(table);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
