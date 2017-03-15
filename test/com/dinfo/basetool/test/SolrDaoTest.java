package com.dinfo.basetool.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dinfo.basetool.test.bean.SolrBean;
import com.dinfo.plugtool.common.ClassCommon;
import com.dinfo.plugtool.dao.SolrDao;

public class SolrDaoTest {
	private SolrDao solrDao=ClassCommon.solrDao;
	
	/**
	 * @Description: 得到类列表
	 * @param @return   
	 * @return List<SolrBean>  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-6 下午2:37:17
	 */
	public List<SolrBean> getBeanList(){
		Map<String,String> map=new HashMap<String,String>();
		map.put("q","*:*");
		map.put("fl", "*");
		map.put("start","0");
		map.put("rows","99999");
		//map.put("sort","cluster_id asc");
		return solrDao.getDataBeanList(map,SolrBean.class);
	}
	/**
	 * @Description: 根据类一个参数查询类列表
	 * @param @param id
	 * @param @return   
	 * @return SolrBean  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-6 下午2:37:05
	 */
	public SolrBean getBeanByOneParam(String id){
		SolrBean solrBean=null;
		Map<String,String> map=new HashMap<String,String>();
		map.put("q","judgementId:"+id);
		map.put("fl", "*");
		map.put("start","0");
		map.put("sort","createDate desc");
		List<SolrBean> solrs=solrDao.getDataBeanList(map,SolrBean.class);
		if(solrs!=null && solrs.size()>0){
			solrBean=solrs.get(0);
		}
		return solrBean;
	}
	
	//根据类id查询sim_wbid的列表sim_mark=1
	public List<String> getSim_wbidDataId(String cluster_id){
		List<String> solrsList=new ArrayList<String>();
		Map<String,String> map=new HashMap<String,String>();
		map.put("q","sim_mark:1 AND cluster_id:"+cluster_id);
		map.put("fl", "sim_wbid");
		map.put("start","0");
		map.put("rows","999999");
		map.put("sort","post_time asc");
		List<SolrBean> solrs=solrDao.getDataBeanList(map,SolrBean.class);
		if(solrs!=null && solrs.size()>0){
			for(SolrBean sb:solrs){
				solrsList.add(sb.getCaseFacts());
			}
		}
		return solrsList;
	}
	/**
	 * @Description: 删除
	 * @param    
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-6 下午2:37:29
	 */
	public void deleteDate(){
		String param="infoType:判决";
//		String param="*:*";
		solrDao.deleteDate(param);
	}
	public static void main(String[] args) {
		new SolrDaoTest().deleteDate();
	}
}
