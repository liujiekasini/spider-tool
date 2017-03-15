package com.dinfo.spiderplug.entry.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Document;

import com.dinfo.plugtool.dao.MySqlDaoMap;
import com.dinfo.plugtool.dao.impl.MySqlDaoMapImpl;
import com.dinfo.plugtool.spider.dao.JsoupSpiderDao;
import com.dinfo.plugtool.spider.dao.impl.JsoupSpiderDaoImpl;

public class QCC_Crawl_Common implements Runnable {

	private static DateFormat df_1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private static MySqlDaoMap dao = new MySqlDaoMapImpl();

	/**
	 * 线程名称
	 */
	private String thread_name;
	/**
	 * 表1
	 */
	private String tablename_1;
	/**
	 * 表2
	 */
	private String tablename_2;

	public QCC_Crawl_Common() {
		super();
	}

	public QCC_Crawl_Common(int thread_name, String tablename_1, String tablename_2) {
		super();
		this.thread_name = "线程<"+thread_name+"> ";
		this.tablename_1 = tablename_1;
		this.tablename_2 = tablename_2;
	}

	public static void main(String[] args) {

		QCC_Crawl_Common qcc = new QCC_Crawl_Common(1, "cmp_base_info", "external_invest");
		Thread thread = new Thread(qcc);
		thread.start();
	}

	@Override
	public void run() {

		System.out.println(thread_name+">启动");
		//while (true) {
			try {
				enter();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println("=============================================================");
			}
		//}
	}

	/**
	 * 入口
	 */
	private void enter() {

		// 测试
		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("unique_id", "9cce0780ab7644008b73bc2120479d31");
		 map.put("cmp_name", "测试公司");
		 map.put("url", "http://www.qichacha.com/firm_" +
		 map.get("unique_id").toString().trim() + ".shtml");

		// 获取需采集公司的必要信息(公司名称、unique_id)
//		Map<String, Object> map = QCC_Datas.getData(tablename_1);
//		if (map == null) {
//			try {
//				Thread.sleep(1000 * 5);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			return;
//		}

		// 根据unique_id获取shard_id
		String shard_id;
		try {
			shard_id = QCC_JsonHandle.getShardId(map);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		if (StringUtils.isBlank(shard_id)) {
			System.out.println("未获取到shard_id");
			return;
		}
		map.put("cmp_shareId", shard_id);

		// 开始采集企业基本信息
		try {
			crawlEssentialInformation(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 开始采集企业对外投资
		List<Map<String, Object>> list;
		try {
			list = crawlInvestmentInformation(map);
		} catch (Exception e2) {
			e2.printStackTrace();
			return;
		}
		int size = list.size();
		for (int i = 0; i < size; i++) {
			Map<String, Object> m = null;
			boolean flag = true;
			for (int j = 0; j < 3; j++) {
				try {
					m = list.get(i);
					// 企业对外投资数据入库
					dao.insertMap(m, tablename_2);
					System.out.println("\t企业对外投资: " + m);
					flag = false;
					break;
				} catch (Exception e) {
					try {
						Thread.sleep(1000*1);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				} 
			}
			if(flag || m == null || m.keySet().size() == 0){
				continue;
			}
			// 企业信息入库基本信息表
			for (int j = 0; j < 3; j++) {
				try {
					Map<String, Object> m_in = new HashMap<String, Object>();
					m_in.put("unique_id", m.get("investment_unique").toString());
					m_in.put("cmp_name", m.get("investment_name").toString());
					m_in.put("status", 2);
					dao.insertMapIgnore(m_in, tablename_1);
					System.out.println("\t对外投资企业： " + m_in);
					break;
				} catch (Exception e) {
					try {
						Thread.sleep(1000 * 1);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				} 
			}
		}
	}

	/**
	 * 开始采集企业基本信息
	 * 
	 * @param map
	 *            企业基本信息
	 * @param shard_id
	 * @throws Exception 
	 */
	private void crawlEssentialInformation(Map<String, Object> map) throws Exception {

		String start_url = "http://wxapi.qichacha.com/wechat/v1/share/getEntDetail?shareId="
				+ map.get("cmp_shareId").toString();
		Document doc = null;
		try {
			JsoupSpiderDao dao = new JsoupSpiderDaoImpl();
			doc = dao.getJsonDocument(start_url);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		map.put("gettime", df_1.format(new Date()));

		String json = doc.text().trim();

		// 解析json获取企业基本信息
		QCC_JsonHandle.getEssentialInformation(map, json, 0);
		map.put("status", 1);
		// 企业基本信息入库
		dao.insertMapReplace(map, tablename_1);
		System.out.println("企业基本信息: "+map);
		
		// 解析json获取企业高管信息
		List<Map<String, Object>> list_kp = new ArrayList<Map<String, Object>>();
		QCC_JsonHandle.getKeyPersonnelInformation(list_kp, map, json, 0);
	}

	/**
	 * 开始采集企业对外投资
	 * 
	 * @param map
	 * @return
	 */
	private List<Map<String, Object>> crawlInvestmentInformation(Map<String, Object> map) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int pageNum = 1;
		String cmp_unique = map.get("unique_id").toString();
		String shareId = map.get("cmp_shareId").toString();
		String cmp_name = map.get("cmp_name").toString();

		while (true) {
			System.out.println(thread_name + "开始采集第<" + pageNum + ">页");
			String start_url = "http://wxapi.qichacha.com/wechat/v1/share/getInvestments?shareId=" + shareId
					+ "&province=&cityCode=&pageIndex=" + pageNum;
			Document doc = null;
			try {
				JsoupSpiderDao dao = new JsoupSpiderDaoImpl();
				doc = dao.getJsonDocument(start_url);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String gettime = df_1.format(new Date());
			try {
				QCC_JsonHandle.getInvestmentInformation(list, doc.text().trim(), 0, cmp_unique, cmp_name, gettime);
			} catch (Exception e) {
				break;
			}
			pageNum++;
		}
		return list;
	}
}