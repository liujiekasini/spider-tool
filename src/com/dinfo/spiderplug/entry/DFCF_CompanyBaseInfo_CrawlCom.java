package com.dinfo.spiderplug.entry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.dinfo.plugtool.common.ClassCommon;
import com.dinfo.plugtool.util.DateUtil;
import com.dinfo.spiderplug.bean.Company_BaseInfo_Bean;

public class DFCF_CompanyBaseInfo_CrawlCom {
//	private static String companybase_infotable=AttributeCommon.companybase_infotable_dfcf;
	private static String companybase_infotable="company_baseinfo";
	
	public static void main(String[] args) {
		//初始化类文件
		ClassCommon.getInstance().getClassCommon();
		Map<String,String> map=new HashMap<String, String>();
//		map.put("url","http://f10.eastmoney.com/f10_v2/CompanySurvey.aspx?code=sz000919");
		map.put("url","http://f10.eastmoney.com/f10_v2/CompanySurvey.aspx?code=");
		crawlCommon(map);
	}
	public static void crawlCommon(Map<String,String> map){
		List<Company_BaseInfo_Bean> companyInfoList=new ArrayList<Company_BaseInfo_Bean>();
		String sql="";
		sql="select * from "+companybase_infotable;
		companyInfoList=ClassCommon.mySqlDaoBean.getDataBeanList(sql, Company_BaseInfo_Bean.class);
		if(companyInfoList!=null && companyInfoList.size()>0){
			int mark=0;
			for(Company_BaseInfo_Bean companyInfo_bean:companyInfoList){
				System.out.println(mark++);
				Document doc=ClassCommon.jsoupSpiderDao.getDocument(map.get("url")+companyInfo_bean.getStock_prefix()+companyInfo_bean.getStock_code());
				Elements elements=doc.select("div[class=content] table tr");
				if(elements!=null && elements.size()>0){
//					Company_BaseInfo_Bean companyInfo_bean=new Company_BaseInfo_Bean();
					for(int i=0;i<elements.size();i++){
						Elements thElements=elements.select("th");
						Elements tdElements=elements.select("td");
						for(int j=0;j<thElements.size();j++){
							if("公司名称".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setGongsimingcheng(tdElements.get(j).text());
							}else if("英文名称".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setYingwenmingcheng(tdElements.get(j).text());
							}else if("曾用名".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setCengyongming(tdElements.get(j).text());
							}else if("A股代码".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setAgu_daima(tdElements.get(j).text());
//								if("--".equals(thElements.get(j).text().trim())){
//									companyInfo_bean.setStock_code("")
//								}
							}else if("A股简称".equals(thElements.get(j).text().trim())){	
								companyInfo_bean.setAgu_jiancheng(tdElements.get(j).text());
							}else if("B股代码".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setBgu_daima(tdElements.get(j).text());
							}else if("B股简称".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setBgu_jiancheng(tdElements.get(j).text());
							}else if("H股代码".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setHgu_daima(tdElements.get(j).text());
							}else if("H股简称".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setHgu_jiancheng(tdElements.get(j).text());
							}else if("证券类别".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setZhengquan_leibie(tdElements.get(j).text());
							}else if("所属行业".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setSuoshu_hangye(tdElements.get(j).text());
							}else if("总经理".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setZongjingli(tdElements.get(j).text());
							}else if("法人代表".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setFaren_daibiao(tdElements.get(j).text());
							}else if("董秘".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setDongmi(tdElements.get(j).text());
							}else if("董事长".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setDongshizhang(tdElements.get(j).text());
							}else if("证券事务代表".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setZhengquan_shiwu_daibiao(tdElements.get(j).text());
							}else if("独立董事".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setDuli_dongshi(tdElements.get(j).text());
							}else if("联系电话".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setLianxi_dianhua(tdElements.get(j).text());
							}else if("电子信箱".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setDianzi_xinxiang(tdElements.get(j).text());
							}else if("传真".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setChuanzhen(tdElements.get(j).text());
							}else if("公司网址".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setGongsi_wangzhi(tdElements.get(j).text());
							}else if("办公地址".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setBangong_dizhi(tdElements.get(j).text());
							}else if("注册地址".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setZhuce_dizhi(tdElements.get(j).text());
							}else if("区域".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setQuyu(tdElements.get(j).text());
							}else if("邮政编码".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setYouzheng_bianma(tdElements.get(j).text());
							}else if("注册资本(元)".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setZhuce_ziben(tdElements.get(j).text());
							}else if("工商登记".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setGongshang_dengji(tdElements.get(j).text());
							}else if("雇员人数".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setGuyuan_renshu(tdElements.get(j).text());
							}else if("管理人员人数".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setGuanli_renyuan_renshu(tdElements.get(j).text());
							}else if("律师事务所".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setLvshi_shiwusuo(tdElements.get(j).text());
							}else if("会计师事务所".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setKuaijishi_shiwusuo(tdElements.get(j).text());
							}else if("公司简介".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setGongsi_jianjie(tdElements.get(j).text());
							}else if("经营范围".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setJingying_fanwei(tdElements.get(j).text());
							}else if("成立日期".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setChengli_riqi(tdElements.get(j).text());
							}else if("上市日期".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setShangshi_riqi(tdElements.get(j).text());
							}else if("发行市盈率(倍)".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setFaxing_shiyinglv(tdElements.get(j).text());
							}else if("网上发行日期".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setWangshang_faxing_riqi(tdElements.get(j).text());
							}else if("发行方式".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setFaxing_fangshi(tdElements.get(j).text());
							}else if("每股面值(元)".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setMeigu_mianzhi(tdElements.get(j).text());
							}else if("发行量(股)".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setFaxingliang(tdElements.get(j).text());
							}else if("每股发行价(元)".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setMeigu_faxingjia(tdElements.get(j).text());
							}else if("发行费用(元)".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setFaxing_feiyong(tdElements.get(j).text());
							}else if("发行总市值(元)".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setFaxing_zongshizhi(tdElements.get(j).text());
							}else if("募集资金净额(元)".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setMuji_zijin_jinge(tdElements.get(j).text());
							}else if("首日开盘价(元)".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setShouri_kaipanjia(tdElements.get(j).text());
							}else if("首日收盘价(元)".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setShouri_shoupanjia(tdElements.get(j).text());
							}else if("首日换手率".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setShouri_huanshoulv(tdElements.get(j).text());
							}else if("首日最高价(元)".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setShouri_zuigaojia(tdElements.get(j).text());
							}else if("网下配售中签率".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setWangxia_peishou_zhongqianlv(tdElements.get(j).text());
							}else if("定价中签率".equals(thElements.get(j).text().trim())){
								companyInfo_bean.setDingjia_zhongqianlv(tdElements.get(j).text());
							}
						}
						
					}
					companyInfo_bean.setCreat_time(DateUtil.getTheCurrentTime());
					companyInfo_bean.setUrl(map.get("url")+companyInfo_bean.getStock_prefix()+companyInfo_bean.getStock_code());
					sql="select count(*) count from "+companybase_infotable+" where stock_code='"+companyInfo_bean.getAgu_daima()+"'";
					int num=ClassCommon.mySqlDaoBean.getCount(sql);
					if(num==0){
						ClassCommon.mySqlDaoBean.insertBeanOutID(companybase_infotable,Company_BaseInfo_Bean.class,companyInfo_bean);
//						companyInfoList.add(companyInfo_bean);
//						System.out.println();
					}else{
						sql="delete from "+companybase_infotable+" where stock_code='"+companyInfo_bean.getAgu_daima()+"'";
						ClassCommon.mySqlDaoBean.updateCommon(sql);
						ClassCommon.mySqlDaoBean.insertBeanOutID(companybase_infotable,Company_BaseInfo_Bean.class,companyInfo_bean);
						
					}
//			mySqlDao.insertBeanList(AttributeCommon.companybase_infotable,EnterpriseBaseInfo_bean.class,companyInfoList);
				}
			}
			System.out.println("采集公司基本信息成功！");
		}
	}

}
