package com.dinfo.spiderplug.entry.common;

import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.dinfo.plugtool.dao.MySqlDaoMap;
import com.dinfo.plugtool.dao.impl.MySqlDaoMapImpl;

public class QCC_Datas {

	public synchronized static Map<String,Object> getData(String tablename){
		
		String sql1 = "select unique_id from " + tablename
					+" where status=0 limit 0,1";
		Map<String, Object> map = null;
		MySqlDaoMap dao = new MySqlDaoMapImpl();
		try {
			map = dao.query(sql1).get(0);
			String unique = map.get("unique_id").toString().trim();
			if(StringUtils.isNotBlank(unique)){
				map.put("status", 1);
				try {
					dao.insertMapReplace(map, tablename);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return map;
	}
}