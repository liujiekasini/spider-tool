package com.dinfo.basetool.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dinfo.plugtool.dao.MySqlDaoBean;
import com.dinfo.plugtool.dao.MySqlDaoMap;
import com.dinfo.plugtool.dao.impl.MySqlDaoBeanImpl;
import com.dinfo.plugtool.dao.impl.MySqlDaoMapImpl;

public class MySqlDaoBeanTest {
	public static void main(String[] args) {
		String sql="";
		try {
			
			//MySqlDaoBean测试
			MySqlDaoBean mySqlDaoBean=new MySqlDaoBeanImpl();
			sql="select count(*) from case_area";
			int count=mySqlDaoBean.getCount(sql);
			System.out.println("getCount方法数量测试："+count);
			
			//MySqlDaoMap测试
			sql="select * from case_area where level=? and parent_code=?";
			MySqlDaoMap mySqlDaoMap=new MySqlDaoMapImpl();
			List<Object> paramlist=new ArrayList<Object>();
			paramlist.add("1");
			paramlist.add("0");
			List<Map<String,Object>> mapList=mySqlDaoMap.getDataMapList(sql, paramlist);
			System.out.println("统计数量："+mapList.size());
			for(Map<String,Object> map:mapList){
				Set<String> strSet=map.keySet();
				System.out.println("key:"+strSet.toString());
				System.out.println("value:"+map.values());
//				for(String str:strSet){
//					System.out.println("key:"+str+" , value:"+map.get(str));
//				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
