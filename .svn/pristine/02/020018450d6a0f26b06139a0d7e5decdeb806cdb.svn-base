package com.dinfo.plugtool.dao;

import java.util.List;
import java.util.Map;

public interface SolrDao {
	
	/**
	 * @Description: 添加字段
	 * @param @param clazz
	 * @param @param bean   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2016-3-30 下午5:03:14
	 */
	public <T> void insertBean(Class<T> clazz,T bean);
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
	public <T> void insertBeanList(Class<T> clazz,List<T> list);
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
	public <T> List<T> getDataBeanList(Map<String,String> map,Class<T> clazz);
	
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
	public void deleteDate(String param);
}
