package com.dinfo.plugtool.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;

import com.dinfo.plugtool.common.DBCommon;
import com.dinfo.plugtool.dao.SolrDao;
import com.dinfo.plugtool.dao.common.BaseDataCommon;
import com.dinfo.plugtool.dao.common.SolrDaoCommon;

public class SolrDaoImpl implements SolrDao{
	private static SolrServer solrServer =DBCommon.getInstance().getSolrServer();
	
	/**
	 * @Description: 添加字段
	 * @param @param clazz
	 * @param @param bean   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2016-3-30 下午5:03:14
	 */
	public synchronized <T> void insertBean(Class<T> clazz,T bean){
		List<SolrInputDocument> records = new ArrayList<SolrInputDocument>();
		try {
//			bean= clazz.newInstance();
			if(bean!=null){
				SolrInputDocument sid = new SolrInputDocument();
				Field[] s=bean.getClass().getDeclaredFields();
				for(Field f:s){
					 String fieldName=f.getName();
					 Method getFieldMethod = BaseDataCommon.getGetMehtodByField(clazz,fieldName);  
					 Object fieldValue= getFieldMethod.invoke(bean, new Object[] {});
		             if (fieldValue != null) {  
		                 sid.addField(fieldName, fieldValue);
		             }  
				}
				records.add(sid);
				solrServer.add(records);
				solrServer.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description: 批量添加bean
	 * @param @param clazz
	 * @param @param list
	 * @param @return   
	 * @return List<SolrInputDocument>  
	 * @throws
	 * @author xulonglong
	 * @date 2016-3-30 下午5:03:02
	 */
	public synchronized <T> void insertBeanList(Class<T> clazz,List<T> list){
		List<SolrInputDocument> records = new ArrayList<SolrInputDocument>();
		try {
			if(list!=null&&list.size()>0){
				for (T t : list) {
					SolrInputDocument sid = new SolrInputDocument();
					Field[] s=t.getClass().getDeclaredFields();
					for(Field f:s){
						 String fieldName=f.getName();
						 Method getFieldMethod = BaseDataCommon.getGetMehtodByField(clazz,fieldName);  
						 Object fieldValue;
							fieldValue = getFieldMethod.invoke(t, new Object[] {});
			             if (fieldValue != null) {  
			                 sid.addField(fieldName, fieldValue);
			             }  
					}
					records.add(sid);
				}
				solrServer.add(records);
				solrServer.commit();
				System.out.println("批量插入成功！");
			}
		} catch (Exception e) {
			try {
				solrServer.rollback();
			} catch (Exception e1) {
				
			}
			for(SolrInputDocument sid:records){
				try {
					solrServer.add(sid);	
					solrServer.commit();
				} catch (Exception e2) {
					System.err.println("插入solr错误："+e2.getMessage());
				}
			}
		}
	}
	/**
	 * @Description: 得到类列表-通用方法
	 * @param @param map
	 * @param @param clazz
	 * @param @return   
	 * @return List<T>  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-13 上午9:34:21
	 */
	public <T> List<T> getDataBeanList(Map<String,String> map,Class<T> clazz){
		List<T> solrs=new ArrayList<T>();
		try {
			ModifiableSolrParams params = new ModifiableSolrParams();
			Set<String> keys=map.keySet();
			for(String key:keys){
				params.set(key,map.get(key));
			}
			QueryResponse response = solrServer.query(params);
			solrs=SolrDaoCommon.toBeanList(response.getResults(), clazz);
		}catch(Exception e){
			e.printStackTrace();
		}
		return solrs;
	}
//	public Map<String, Integer> queryByGroup(String qStr,String groupField,
//	 String sortField,boolean asc,Integer pageSize,Integer pageNum){
// Map<String, Integer> rmap = new LinkedHashMap<String, Integer>();
// try {
//	   SolrQuery query = new SolrQuery();
//	   if(qStr!=null&&qStr.length()>0){
//		   query.setQuery(qStr);
//	   }else{
//		   query.setQuery("*:*");//如果没有查询语句，必须这么写，否则会报异常
//	   }
//	   query.setIncludeScore(false);//是否按每组数量高低排序
//	   query.setFacet(true);//是否分组查询
//	   query.setRows(0);//设置返回结果条数，如果你是分组查询，你就设置为0
//	   query.addFacetField(groupField);//增加分组字段
//	   query.setFacetSort(true);//分组是否排序
//	   query.setFacetLimit(pageSize);//限制每次返回结果数
//	   query.setSortField(sortField,asc ? SolrQuery.ORDER.asc :SolrQuery.ORDER.desc );//分组排序字段
//	   query.set(FacetParams.FACET_OFFSET,(pageNum-1)*pageSize);//当前结果起始位置
//	   QueryResponse rsp = solrServer.query( query );   
//	   
//	   List<Count> countList = rsp.getFacetField(groupField).getValues();
//	   List<Count> returnList = new ArrayList<Count>();
//	   if(pageNum*pageSize<countList.size()){
//	       returnList = countList.subList((pageNum-1)*pageSize,pageNum*pageSize);
//	   }else{
//	       returnList = countList.subList((pageNum-1)*pageSize,countList.size()-1);
//	   }
//	   for (Count count : returnList) {
//		   if(count.getCount()>0){
//			   rmap.put(count.getName(), (int) count.getCount());
//		   }
//	   }
// } catch (Exception e) {
//  e.printStackTrace();
// }
// return rmap;
//}
	
	/**
	 * @Description: 删除数据
	 * @param @param param   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-13 上午9:41:18
	 */
	public void deleteDate(String param){
		try {
			 System.out.println("删除"+param);
             //删除所有的索引
			 solrServer.deleteByQuery(param);
			 solrServer.commit();
		} catch (Exception e) {
             e.printStackTrace();
		}
		
	}
}
