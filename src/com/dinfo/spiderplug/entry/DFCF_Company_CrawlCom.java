package com.dinfo.spiderplug.entry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dinfo.plugtool.common.ClassCommon;
import com.dinfo.plugtool.util.DateUtil;
import com.dinfo.spiderplug.bean.Company_BaseInfo_Bean;

public class DFCF_Company_CrawlCom {
//	private static String companybase_infotable=AttributeCommon.companybase_infotable_dfcf;
	private static String companybase_infotable="company_baseinfo";
	
	public static void main(String[] args) {
		Map<String,String> map=new HashMap<String, String>();
		map.put("url","http://quote.eastmoney.com/stocklist.html");
		crawlCommon(map);
	}
	public static void crawlCommon(Map<String,String> map){
		Document doc=ClassCommon.jsoupSpiderDao.getDocument(map.get("url"));
		Elements elements=doc.select("div[id=quotesearch] ul");//.parents().select("li a");
		if(elements!=null && elements.size()>0){
			List<Company_BaseInfo_Bean> companyInfoList=new ArrayList<Company_BaseInfo_Bean>();
			String companyCode="";
			String sql="";
			if(elements!=null && elements.size()>0){
				int mark=0;
				int elements_mark=0;
				for(int i=0;i<elements.size();i++){
					Element element=elements.get(i);
					Elements twoElements=element.select("li");
					elements_mark++;
					for(Element shElement:twoElements){//
						Company_BaseInfo_Bean companyInfo_bean=new Company_BaseInfo_Bean();
						companyCode=shElement.text();
						if(companyCode.contains("(")){
							if(i==0){
								companyInfo_bean.setStock_prefix("sh");
							}else if(i==1){
								companyInfo_bean.setStock_prefix("sz");
							}
							companyInfo_bean.setCompany_name(companyCode.substring(0,companyCode.indexOf("(")));
							companyInfo_bean.setStock_code(companyCode.substring(companyCode.indexOf("(")+1,companyCode.indexOf(")")));
							companyInfo_bean.setCreat_time(DateUtil.getTheCurrentTime());
							if(elements_mark==2 || companyInfo_bean.getStock_code().startsWith("60")){
								sql="select count(*) count from "+companybase_infotable+" where stock_code='"+companyInfo_bean.getStock_code()+"'";
								int num=ClassCommon.mySqlDaoBean.getCount(sql);
								if(num==0){
									companyInfoList.add(companyInfo_bean);
									System.out.println(mark++);
								}
							}
						}
					}
				}
				ClassCommon.mySqlDaoBean.insertBeanList(companybase_infotable,Company_BaseInfo_Bean.class,companyInfoList);
				System.out.println("采集公司名称成功！");
			}
		}
	}
	
}
