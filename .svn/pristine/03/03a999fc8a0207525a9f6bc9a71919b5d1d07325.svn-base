package com.dinfo.plugtool.dao.impl;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.coprocessor.AggregationClient;
import org.apache.hadoop.hbase.client.coprocessor.LongColumnInterpreter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;

import com.dinfo.plugtool.common.DBCommon;
import com.dinfo.plugtool.dao.HBaseDao;
import com.dinfo.plugtool.dao.common.BaseDataCommon;

/**
 * @ClassName: HbaseDao
 * @Description: 操作Hbse工具类
 * @author xulonglong
 * @date 2016-7-4 下午2:21:35
 */
public class HBaseDaoImpl implements HBaseDao{
	private static Connection connection =DBCommon.getInstance().getConnectionHbase();
	private static Configuration config=DBCommon.getInstance().getConfig();
//	private static final Log logger = LogFactory.getLog(HbaseDao.class);
	
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
	@SuppressWarnings({ "deprecation"})
	public void createTable(String table_name,String family_name)
			throws IOException {
		// HBaseAdmin用来创建表 删除表，使用 alt+shift+l 抽取变量
		HBaseAdmin hBaseAdmin = new HBaseAdmin(connection);
		if (!(hBaseAdmin.tableExists(table_name))) {
			HTableDescriptor hTableDescriptor = new HTableDescriptor(table_name);
			HColumnDescriptor family = new HColumnDescriptor(family_name);
			hTableDescriptor.addFamily(family);
			hBaseAdmin.createTable(hTableDescriptor);
			System.out.println("创建表"+table_name+"成功！");
		}
	}
	/**
	 * @Description: 对于某一个Table表注册Coprocessor
	 * @param @param table_name
	 * @param @throws IOException   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-8 下午3:22:57
	 */
	@SuppressWarnings("deprecation")
	public void registerCoprocessor(String table_name) throws IOException{
		String coprocessClassName = "org.apache.hadoop.hbase.coprocessor.AggregateImplementation";
        HBaseAdmin admin = new HBaseAdmin(HBaseConfiguration.create());
        admin.disableTable(table_name);
        HTableDescriptor htd = admin.getTableDescriptor(TableName.valueOf(table_name));
        htd.addCoprocessor(coprocessClassName);
        admin.modifyTable(table_name, htd);
        admin.enableTable(table_name);
	}
	/**
	 * @Description: 得到table对象
	 * @param @param tableName
	 * @param @return   
	 * @return Table  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-8 下午1:41:25
	 */
	public Table getTable(String tableName){
    	try {
			return connection.getTable(TableName.valueOf(tableName));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return null;
	}
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
	public void getLock(String tableName,String family,String rowkey,String col){
		Map<String,String> map = new HashMap<String,String>();
		map.put(col, "1");
		insertMap(tableName,family,rowkey,map);
	}
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
	public void releaseLock(String tableName,String family,String rowkey,String col){
		Map<String,String> map = new HashMap<String,String>();
		map.put(col, "0");
		insertMap(tableName,family,rowkey,map);
	}
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
    public int getDataCount(String tableName,Scan scan){
        AggregationClient aggregationClient = new AggregationClient(config);
		try {
			Long rowCount = aggregationClient.rowCount(TableName.valueOf(tableName), new LongColumnInterpreter(), scan);
			aggregationClient.close();
			return rowCount.intValue();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return 0;
	}
    /**
	 * @Description: 删除表
	 * @param @param tableName
	 * @param @throws IOException   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-8 下午1:41:47
	 */
	@SuppressWarnings({ "deprecation"})
	public void deleteTable(String tableName) throws IOException {
		// HBaseAdmin用来创建表 删除表使用 alt+shift+l 抽取变量
		HBaseAdmin hBaseAdmin = new HBaseAdmin(connection);
		hBaseAdmin.disableTable(tableName);
		hBaseAdmin.deleteTable(tableName);
	}
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
	public void deleteByWhere(String table_name,String family_name,Map<String,String> filterMap){
		Table hTable = null;
        try { 
        	hTable = (HTable) connection.getTable(TableName.valueOf(table_name));
        	
        	//配置过滤条件
        	FilterList filelist = new FilterList();
        	if(filterMap != null){
        		Set<String> set = filterMap.keySet();
        		Iterator<String> it = set.iterator();
        		while(it.hasNext()){
        			String key = it.next().toString();
        			String value = filterMap.get(key);
        			SingleColumnValueFilter filter = new SingleColumnValueFilter(Bytes.toBytes(family_name),Bytes.toBytes(key),CompareOp.EQUAL,Bytes.toBytes(value));
        			filter.setFilterIfMissing(true);
        			filelist.addFilter(filter);
        		}
        	}
        	//添加过滤条件
        	Scan scan = new Scan(); 
        	scan.setFilter(filelist); 
        	//查询
            ResultScanner rs = hTable.getScanner(scan);
            for (Result r : rs) {  
            	//删除
                Delete deleteAll = new Delete(Bytes.toBytes(new String(r.getRow(),"utf-8")));
                hTable.delete(deleteAll);
            }
            System.out.println("删除成功！");
        } catch (Exception e) {  
            e.printStackTrace();
        }finally{
        	try {
				hTable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
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
	@SuppressWarnings("deprecation")
	public Map<String, String> getDateByRowKey(String table_name,
			String family_name, String rowKey, List<String> columnList) {
		Table hTable = null;
		try {
			hTable = connection.getTable(TableName.valueOf(table_name));
			Get get = new Get(rowKey.getBytes("utf-8"));// 根据rowkey查询
			if (columnList != null && columnList.size() > 0) {
				for (String col : columnList) {
					get.addColumn(family_name.getBytes(), col.getBytes());
				}
			}
			Map<String, String> map = new HashMap<String, String>();
			Result r = hTable.get(get);
			map.put("rowkey", new String(r.getRow(), "utf-8"));
			for (KeyValue keyValue : r.raw()) {
				map.put(new String(keyValue.getQualifier(), "utf-8"),
						new String(keyValue.getValue(), "utf-8"));
			}
			return map;
		} catch (Exception e) {
			return null;
		} finally {
			try {
				hTable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
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
	@SuppressWarnings("deprecation")
	public List<Map<String,String>> getDataByWhere(String table_name,String family_name,
			Map<String,String> filterMap,List<String> columnList,String startRow,
			String endRow,boolean isAsc,int pagNum) {
		Table hTable = null;
        try { 
        	hTable = connection.getTable(TableName.valueOf(table_name));
        	
        	//配置过滤条件
        	FilterList filterlist = new FilterList();
        	if(filterMap != null){
        		Set<String> set = filterMap.keySet();
        		Iterator<String> it = set.iterator();
        		while(it.hasNext()){
        			String key = it.next().toString();
        			String value = filterMap.get(key);
        			SingleColumnValueFilter filter = new SingleColumnValueFilter(Bytes.toBytes(family_name),Bytes.toBytes(key),CompareOp.EQUAL,Bytes.toBytes(value));
        			filter.setFilterIfMissing(true);
        			filterlist.addFilter(filter);
        		}
        	}
        	//配置过滤页数
        	if(0 != pagNum){
        		filterlist.addFilter(new PageFilter(pagNum));
        	}
        	//添加查询条件
        	Scan scan = new Scan(); 
        	if(null != columnList && columnList.size() > 0){
        		for (String string : columnList) {
        			scan.addColumn(family_name.getBytes(), string.getBytes());
				}
        	}
        	//添加 false 正向扫描
        	scan.setReversed(isAsc);
        	if(null!=startRow&&!"".equals(startRow)){
        		scan.setStartRow(Bytes.toBytes(startRow));
        	}
        	//添加过滤条件
        	if(filterlist.getFilters().size() > 0){
        		scan.setFilter(filterlist); 
        	}
            //查询读取转化成map
            ResultScanner rs = hTable.getScanner(scan);
            List<Map<String,String>> listmap = new ArrayList<Map<String,String>>();
            for (Result result : rs) {  
            	Map<String,String> valueMap = new HashMap<String,String>();
                System.out.println("获得到rowkey:" + new String(result.getRow(),"utf-8"));
            	valueMap.put("rowkey", new String(result.getRow(),"utf-8"));
                for (KeyValue keyValue : result.raw()) {  
                	valueMap.put(new String(keyValue.getQualifier(),"utf-8"), new String(keyValue.getValue(),"utf-8"));
                    System.out.println("列：" + new String(keyValue.getQualifier(),"UTF-8")+ "值:" + new String(keyValue.getValue(),"utf-8"));
                }
                //两种方式
              /*  System.out.println("rowKey:"+new String(result.getRow()));
    			for (Cell cell : result.rawCells()) {
    				System.out.println("列族:"+new String(CellUtil.cloneFamily(cell),"utf-8")
    					+" 列:"+new String(CellUtil.cloneQualifier(cell),"utf-8")
    					+" 值:"+new String(CellUtil.cloneValue(cell),"utf-8"));
    			}*/
                listmap.add(valueMap);
            }
            return listmap;
        } catch (Exception e) {  
            e.printStackTrace();
            return null;
        }finally{
        	try {
				hTable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }  
    }  
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
	public void insertMap(String table_name,String family_name,String rowKey,Map<String,String> columnMap) {
		Table hTable = null;
		try {
			hTable = connection.getTable(TableName.valueOf(table_name));
			Put put = new Put(rowKey.getBytes("utf-8"));
			Set<String> set = columnMap.keySet();
			Iterator<String> it = set.iterator();
			while(it.hasNext()){
				String key = it.next().toString();
				put.addColumn(family_name.getBytes("utf-8"), key.getBytes("utf-8"),columnMap.get(key).getBytes("utf-8"));
			}
			hTable.put(put);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
        	try {
				hTable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
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
	public void insertMapList(String table_name,String family_name,List<Map<String,Map<String,String>>> dataMapList){
		System.out.println("传过来 "+dataMapList.size()+" 条数据！！！！！！！");
		Table hTable = null;
		try {
			hTable = connection.getTable(TableName.valueOf(table_name));
			List<Put> putList = new ArrayList<Put>();
			for (Map<String, Map<String,String>> map : dataMapList) {
				Set<String> set = map.keySet();
				Iterator<String> it = set.iterator();
				while(it.hasNext()){
					String rowKey = it.next().toString();
					Map<String,String> columnMap=map.get(rowKey);
//					insertMap(table_name,family_name,rowKey,map.get(rowKey));
					Put put = new Put(rowKey.getBytes("utf-8"));
					Set<String> set2 = columnMap.keySet();
					Iterator<String> it2 = set2.iterator();
					while(it2.hasNext()){
						String key = it2.next().toString();
						put.addColumn(family_name.getBytes("utf-8"), key.getBytes("utf-8"),columnMap.get(key).getBytes("utf-8"));
					}
					putList.add(put);
				}
			}
			hTable.put(putList);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
        	try {
				hTable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
	/**
	 * @Description: Hbase插入单条数据通用
	 * @param @param bean
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 上午11:19:07
	 */
	public <T> boolean insertBean(Class<T> clazz,T t,String tableName,String columnFamily,String rowkeyName){
		Table htable=null;
		try {
			htable=connection.getTable(TableName.valueOf(tableName));
			List<Put> listPut=new ArrayList<Put>();
			Method rowKeyMethod = BaseDataCommon.getGetMehtodByField(clazz,rowkeyName);
//			String rowKeyType =rowKeyMethod.getReturnType().getSimpleName();
			String rowKey=rowKeyMethod.invoke(t, new Object[] {})+"";
			Put put=new Put(rowKey.getBytes());
			
			Field[] s = t.getClass().getDeclaredFields();
			for (Field f : s) {
				String fieldName = f.getName();
				if(!"rowkey".equals(fieldName)){
					Method fieldMethod = BaseDataCommon.getGetMehtodByField(clazz, fieldName);
//					String fieldType =fieldMethod.getReturnType().getSimpleName();
					Object fieldValue;
					fieldValue = fieldMethod.invoke(t, new Object[] {});
					if (fieldValue != null && !"".equals(fieldValue)) {
						put.addColumn(columnFamily.getBytes("utf-8"), fieldName.getBytes("utf-8"),fieldValue!=null?(fieldValue+"").getBytes("utf-8"):"".getBytes("utf-8"));
					}
				}
			}
			listPut.add(put);
			htable.put(listPut);
			System.out.println(new Date()+"save data to table="+tableName+",columnFamily="+columnFamily+" success!!"+listPut.size());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
            if (htable != null){
                try {
                    htable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return true;
	}
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
			String index_tableName,String index_columnFamily,String index_rowkeyName,String index_fieldName){
		Table htable=null;
		try {
			htable=connection.getTable(TableName.valueOf(info_tableName));
			Table indexhtable=connection.getTable(TableName.valueOf(index_tableName));
			List<Put> indexListPut=new ArrayList<Put>();
			List<Put> infoListPut=new ArrayList<Put>();
			for (T info_tT : info_tList) {
				//保存数据表rowkey
				Method info_rowKeyMethod = BaseDataCommon.getGetMehtodByField(info_clazz,info_rowkeyName);
				String info_rowKey=info_rowKeyMethod.invoke(info_tT, new Object[] {})+"";
				Put infoPut=new Put(info_rowKey.getBytes());
				//保存索引表rowkey
				Method index_rowKeyMethod = BaseDataCommon.getGetMehtodByField(info_clazz,index_rowkeyName);
				String index_rowKey=index_rowKeyMethod.invoke(info_tT, new Object[] {})+"";
				Put indexPut=new Put(index_rowKey.getBytes());
				indexPut.addColumn(index_columnFamily.getBytes(), index_fieldName.getBytes(),info_rowKey.getBytes());
				//保存列
				Field[] s = info_tT.getClass().getDeclaredFields();
				for (Field f : s) {
					String fieldName = f.getName();
					if(!"rowkey".equals(fieldName)){
						Method fieldMethod = BaseDataCommon.getGetMehtodByField(info_clazz, fieldName);
//						String fieldType =fieldMethod.getReturnType().getSimpleName();
						Object fieldValue;
						fieldValue = fieldMethod.invoke(info_tT, new Object[] {});
						if (fieldValue != null && !"".equals(fieldValue)) {
							infoPut.addColumn(info_columnFamily.getBytes("utf-8"), fieldName.getBytes("utf-8"),fieldValue!=null?(fieldValue+"").getBytes("utf-8"):"".getBytes("utf-8"));
						}
					}
				}
				infoListPut.add(infoPut);
				indexListPut.add(indexPut);
			}
			//保存入库
			indexhtable.put(indexListPut);
			System.out.println(new Date()+"save data to table="+index_tableName+",columnFamily="+index_columnFamily+" success!!"+indexListPut.size());
			htable.put(infoListPut);
			System.out.println(new Date()+"save data to table="+info_tableName+",columnFamily="+info_columnFamily+" success!!"+infoListPut.size());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
            if (htable != null){
                try {
                    htable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return true;
	}
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
//		getLock("LOCK", "BAIDU_AK", "baidu");
//		releaseLock("LOCK","lock", "SHOP_INFO_T", "shop");
//		releaseLock("LOCK","lock", "BAIDU_AK", "baidu");
//		releaseLock("LOCK","lock", "COMMUNITY_INFO_T", "community");
//		releaseLock("LOCK","lock", "MERCHANT_INFO_T", "merchant");
		
		// 插入记录
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("shop", "0");
//		putRecord("LOCK","lock","SHOP_INFO_T",map);
//		releaseLock("LOCK","lock", "COMMUNITY_INFO_T", "community");
//		releaseLock("LOCK","lock", "MERCHANT_INFO_T", "merchant");
//		scanRecord("PARAM_INFO_T", "splitLatLng", null);
		
		// 查询全部
//		List<String> list1 = new ArrayList<String>();
//		list1.add("province");
//		list1.add("city");
//		scanRecord("PARAM_INFO_T","city",list1);
//		scanRecord("LOCK", "lock", null);
		
		//测试数量
//		int sum = 0;
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("splitLatLng", "d33d5ec8-f955-45de-a7a7-b89212658602");
//		List<Map<String,String>> list = queryByWhere("RESOURCE_MERCHANT_INFO_T", "merchantInfo", map, null, 0);
//		System.out.println(list.size());
//		for (Map<String, String> map2 : list) {
//			String rowkey = map2.get("rowkey");
//			Map<String,String> map3 = new HashMap<String,String>();
//			map3.put("merchantkeys", rowkey);
//			List<Map<String,String>> list2 = queryByWhere("RESOURCE_MERCHANT_KEYS_INDEX", "merchantIndex", map3, null, 0);
//			System.out.println(list2.size());
//			sum += list2.size();
//		}
//		System.out.println("总数："+sum);
		
		//监控状态
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("bank_name", "中国银行");
//		deleteByWhere("RESOURCE_MERCHANT_INFO_T_BOC", "preferentialMerchantInfo", map);
//		map.put("provinceId", "28"); 
//		map.put("lowerLeftLat", "23.268849");
//		map.put("lowerLeftLng", "113.682949");
//		map.put("status", "1");
//		map.put("province", "四川");
//		queryByWhere("PARAM_INFO_T", "splitLatLng", map);
//		List<Map<String,String>> list = queryByWhere("RESOURCE_COMMUNITY_INFO_T_PZH", "community", map, null, 0);
//		System.out.println(list.size());
//		map.put("status", "0");
//		for (Map<String, String> map2 : list) {
//			String rowkey = map2.get("rowkey");
//			putRecord("PARAM_INFO_T", "splitLatLng", rowkey, map);
//		}
		
		Map<String,String> map = new HashMap<String,String>();
//		map.put("status", "1");
		map.put("city", "上海");
//		List<Map<String,String>> list = queryByWhere("RESOURCE_COMMUNITY_INFO_T_BOC", "community", map, null, 0);
//		System.out.println(list.size());
//		map.put("status", "0");
//		for (Map<String, String> map2 : list) {
//			String rowkey = map2.get("rowkey");
//			putRecord("PARAM_INFO_T", "splitLatLng", rowkey, map);
//		}
		
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("dispost_status", "0");
//		List<Map<String,String>> list = queryByWhere("RESOURCE_MERCHANT_KEYS_INDEX", "merchantIndex", map, null, 0);
//		System.out.println(list.size());
		
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("city", "深圳市");
//		List<Map<String,String>> list = queryByWhere("PARAM_INFO_T", "splitLatLng", map, null, 0);
//		List<String> list2 = new ArrayList<String>();
//		for (Map<String, String> map2 : list) {
//			String merchant_rowkey = map2.get("merchantkeys");
//			list2.add(merchant_rowkey);
//		}
//		Set set = new HashSet(list2);
//		System.out.println(list2.size());
//		System.out.println(set.size());
		
//		Map<String,String> map = queryByRowKey("PARAM_INFO_T", "splitLatLng", "7c842635-624d-49fc-b4d8-4665f65b37ee", null);
//		System.out.println(map);
		
		//删除数据
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("city", "天津市");
//		deleteByWhere("PARAM_INFO_T", "splitLatLng", map);
		
	}
}
