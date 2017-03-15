package com.dinfo.spiderplug.entry.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Document;

import com.dinfo.plugtool.spider.dao.JsoupSpiderDao;
import com.dinfo.plugtool.spider.dao.impl.JsoupSpiderDaoImpl;
import com.dinfo.plugtool.util.JsonUtils;

public class QCC_JsonHandle {

	private static JsoupSpiderDao jsoupDao = new JsoupSpiderDaoImpl();

	/**
	 * 对外投资每页显示条数
	 */
	private static int pageSize = 0;
	/**
	 * 对外投资总数
	 */
	private static int totalRecords = 0;
	/**
	 * 对外投资数据当前页码
	 */
	private static int pageNum = 0;
	/**
	 * 下一页标志，当且仅当为true时表示存在下一页
	 */
	private static boolean nextPageFlag = false;

	/**
	 * 根据unique_id获取shard_id
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static String getShardId(Map<String, Object> map) throws Exception {

		String unique_id = map.get("unique_id").toString().trim();
		if (StringUtils.isBlank(unique_id)) {
			throw new Exception("unique_id为空，无法采集");
		}
		String url = "http://apptoken.qichacha.net/" + "app/v1/html5/getShareidByUnique?" + "unique=" + unique_id;
		Document doc = jsoupDao.getJsonDocument(url);
		return getShardIdByJson(doc.text().trim());
	}

	/**
	 * 解析json -- 获取shard_id
	 * 
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static String getShardIdByJson(String json) {

		HashMap<String, Object> map = JsonUtils.toHashMap(json);

		if (map.get("status").toString().equals("200") && map.get("message").toString().equals("成功")) {

			return map.get("result").toString().trim();
		} else {
			return null;
		}
	}

	/**
	 * 解析json -- 获取企业基本信息
	 * 
	 * @param map
	 *            采集到的信息临时存放
	 * @param json
	 *            企业全部信息[json]
	 * @param k
	 *            递归标志[递归第几数]
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static void getEssentialInformation(Map<String, Object> map, String json, int k) throws Exception {

		// json基本处理
		if (json.startsWith("[")) {
			json = json.substring(1, json.length() - 1);
		}
		HashMap<String, Object> m_;
		try {
			m_ = JsonUtils.toHashMap(json);
		} catch (Exception e1) {
			try {
				json = json.replaceAll("\\\\\"", "\"");
				m_ = JsonUtils.toHashMap(json);
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception();
			}
		}
		if (k == 0) {
			try {
				String status = m_.get("status").toString().trim();
				String message = m_.get("message").toString().trim();
				if ("200".equals(status) && "成功".equals(message)) {
					String result = m_.get("result").toString().trim();
					getEssentialInformation(map, result, ++k);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (k == 1) {
			String result = m_.get("Company").toString().trim();

			try {
				String json_ = m_.get("CountInfo").toString().trim();
				if (json_.startsWith("[")) {
					json_ = json_.substring(1, json_.length() - 1);
				}
				HashMap<String, Object> m__ = JsonUtils.toHashMap(json_);
				// 浏览数
				try {
					String viewCount = m__.get("ViewCount").toString().trim();
					map.put("browse", viewCount);
				} catch (Exception e) {
				}
				// 关注
				try {
					String followCount = m__.get("FollowCount").toString().trim();
					map.put("attention", followCount);
				} catch (Exception e) {
				}
				// 评论
				try {
					String commentCount = m__.get("CommentCount").toString().trim();
					map.put("comment", commentCount);
				} catch (Exception e) {
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			getEssentialInformation(map, result, ++k);
		} else if (k == 2) {
			// 企业名称
			try {
				String name = m_.get("Name").toString().trim();
				map.put("cmp_name", name);
			} catch (Exception e) {
				return;
			}
			// 企业英文名称
			try {
				String englishName = m_.get("EnglishName").toString().trim();
				if(!"null".equals(englishName)){
					map.put("cmp_ename", englishName);
				}
			} catch (Exception e) {
			}
			// 企业曾用名
			try {
				String text = m_.get("OriginalName").toString().trim();
				if (text.startsWith("[")) {
					text = text.substring(1, text.length() - 1);
				}
				HashMap<String, Object> m__ = JsonUtils.toHashMap(text);
				String originalName = m__.get("Name").toString().trim();
				map.put("cmp_fname", originalName);
			} catch (Exception e) {
			}
			// 联系电话、邮箱、企业官网
			try {
				String text1 = m_.get("ContactInfo").toString().trim();
				if (text1.startsWith("[")) {
					text1 = text1.substring(1, text1.length() - 1);
				}
				HashMap<String, Object> m__ = JsonUtils.toHashMap(text1);
				// 联系电话
				try {
					String phoneNumber = m__.get("PhoneNumber").toString().trim();
					map.put("telephone", phoneNumber);
				} catch (Exception e) {
				}
				// 邮箱
				try {
					String email = m__.get("Email").toString().trim();
					map.put("email", email);
				} catch (Exception e) {
				}
				try {
					String text2 = m__.get("WebSite").toString().trim();
					if (text2.startsWith("[")) {
						text2 = text2.substring(1, text2.length() - 1);
					}
					HashMap<String, Object> m___ = JsonUtils.toHashMap(text2);
					// 企业官网
					try {
						String webSite = m___.get("Url").toString().trim();
						map.put("cmp_website", webSite);
					} catch (Exception e) {
					}
				} catch (Exception e) {
				}
			} catch (Exception e) {
			}
			// 企业地址
			try {
				String address = m_.get("Address").toString().trim();
				map.put("address", address);
			} catch (Exception e) {
			}
			// 注册号
			try {
				String register_num = m_.get("No").toString().trim();
				map.put("register_num", register_num);
			} catch (Exception e) {
			}
			// 组织机构代码
			try {
				String orgNo = m_.get("OrgNo").toString().trim();
				map.put("tissue_num", orgNo);
			} catch (Exception e) {
			}
			// 经营状态
			try {
				String status = m_.get("Status").toString().trim();
				map.put("statu", status);
			} catch (Exception e) {
			}
			// 公司类型
			try {
				String econKind = m_.get("EconKind").toString().trim();
				map.put("cmp_type", econKind);
			} catch (Exception e) {
			}
			// 成立日期
			try {
				String startDate = m_.get("StartDate").toString().trim();
				map.put("formed_time", startDate);
			} catch (Exception e) {
			}
			// 法定代表人
			try {
				String operName = m_.get("OperName").toString().trim();
				map.put("legal_person", operName);
			} catch (Exception e) {
			}
			// 注册资本
			try {
				String registCapi = m_.get("RegistCapi").toString().trim();
				map.put("register_money", registCapi);
			} catch (Exception e) {
			}
			// 营业期限开始时间
			try {
				String termStart = m_.get("TermStart").toString().trim();
				map.put("operation_term_start", termStart);
			} catch (Exception e) {
			}
			// 营业期限结束时间
			try {
				String termEnd = m_.get("TeamEnd").toString().trim();
				map.put("operation_term_end", termEnd);
			} catch (Exception e) {
			}
			// 登记机关
			try {
				String belongOrg = m_.get("BelongOrg").toString().trim();
				map.put("register_office", belongOrg);
			} catch (Exception e) {
			}
			// 发照日期
			try {
				String checkDate = m_.get("CheckDate").toString().trim();
				map.put("issue_date", checkDate);
			} catch (Exception e) {
			}
			// 公司规模、公司简介
			try {
				String text = m_.get("companyExtendInfo").toString().trim();
				if (text.startsWith("[")) {
					text = text.substring(1, text.length() - 1);
				}
				HashMap<String, Object> m__ = JsonUtils.toHashMap(text);
				// 公司规模
				try {
					String info = m__.get("Info").toString().trim();
					map.put("asset", info);
				} catch (Exception e) {
				}
				// 公司简介
				try {
					String desc = m__.get("Desc").toString().trim();
					map.put("company_profile", desc);
				} catch (Exception e) {
				}
			} catch (Exception e) {
			}
			// 所属行业
			try {
				String text = m_.get("Industry").toString().trim();
				if (text.startsWith("[")) {
					text = text.substring(1, text.length() - 1);
				}
				HashMap<String, Object> m__ = JsonUtils.toHashMap(text);
				String subIndustry = m__.get("SubIndustry").toString().trim();
				map.put("induetry", subIndustry);
			} catch (Exception e) {
			}
			// 经营范围
			try {
				String scope = m_.get("Scope").toString().trim();
				map.put("business_scope", scope);
			} catch (Exception e) {
			}
		}
	}
	
	/**
	 * 解析json -- 获取企业高管信息
	 * @param list	采集数据
	 * @param map	企业基本信息
	 * @param json
	 * @param k		递归调用第几次
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static void getKeyPersonnelInformation(List<Map<String, Object>> list,Map<String, Object> map, String json, int k) throws Exception {

		// json基本处理
		if (json.startsWith("[")) {
			json = json.substring(1, json.length() - 1);
		}
		HashMap<String, Object> m_;
		try {
			m_ = JsonUtils.toHashMap(json);
		} catch (Exception e1) {
			try {
				json = json.replaceAll("\\\\\"", "\"");
				m_ = JsonUtils.toHashMap(json);
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception();
			}
		}
		System.out.println(json);
		if(k == 0){
			try {
				String status = m_.get("status").toString().trim();
				String message = m_.get("message").toString().trim();
				if ("200".equals(status) && "成功".equals(message)) {
					String result = m_.get("result").toString().trim();
					getEssentialInformation(map, result, ++k);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 解析json -- 获取对外投资信息
	 * 
	 * @param list
	 *            临时存放采集数据
	 * @param json
	 * @param k
	 *            递归调用第几次
	 * @param unique_id
	 * @param cmp_name
	 *            公司名称
	 * @param gettime
	 *            获得json数据的时间
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void getInvestmentInformation(List<Map<String, Object>> list, String json, int k, String unique_id,
			String cmp_name, String gettime) throws Exception {

		HashMap<String, Object> m_ = new HashMap<String, Object>();
		if (k < 2) {
			// json基本处理
			try {
				if (json.startsWith("[")) {
					json = json.substring(1, json.length() - 1);
				}
				m_ = JsonUtils.toHashMap(json);
			} catch (Exception e) {
				try {
					json = json.replaceAll("\\\\\"", "\"");
					m_ = JsonUtils.toHashMap(json);
				} catch (Exception e1) {
					e1.printStackTrace();
					throw new Exception();
				}
			}
		} else {
			if (!json.startsWith("[")) {
				json = "[" + json + "]";
			}
		}
		if (k == 0) {
			// 初始化相关临时数据
			pageSize = 0;
			pageNum = 0;
			totalRecords = 0;

			String status = m_.get("status").toString().trim();
			String message = m_.get("message").toString().trim();
			if ("200".equals(status) && "成功".equals(message)) {
				String result = m_.get("result").toString().trim();
				getInvestmentInformation(list, result, ++k, unique_id, cmp_name, gettime);
				return;
			}
		} else if (k == 1) {
			try {
				String paging = m_.get("Paging").toString().trim();
				if (paging.startsWith("[")) {
					paging = paging.substring(1, paging.length() - 1);
				}
				HashMap<String, Object> m__ = JsonUtils.toHashMap(paging);
				pageSize = Integer.parseInt(m__.get("PageSize").toString().trim());
				pageNum = Integer.parseInt(m__.get("PageIndex").toString().trim());
				totalRecords = Integer.parseInt(m__.get("TotalRecords").toString().trim());

				// 更改下一页标志
				hasNextPage();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}

			
			String result = m_.get("Result").toString().trim();
			getInvestmentInformation(list, result, ++k, unique_id, cmp_name, gettime);
			return;
		} else if (k == 2) {
			List<Map<String, Object>> li;
			try {
				li = JsonUtils.toList(json);
			} catch (Exception e1) {
				try {
					json = json.replaceAll("\\\\\"", "\"");
					li = JsonUtils.toList(json);
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception();
				}
			}
			int size = li.size();
			for (int i = 0; i < size; i++) {
				Map<String, Object> map_json = li.get(i);
				
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("cmp_unique", unique_id);
				map.put("cmp_name", cmp_name);
				map.put("gettime", gettime);
				//对外投资企业unique_id
				try {
					String keyNo = map_json.get("KeyNo").toString().trim();
					map.put("investment_unique", keyNo);
					String url = "http://www.qichacha.com/firm_" + keyNo + ".shtml#touzi";
					map.put("url", url);
				} catch (Exception e) {
					continue;
				}
				//对外投资企业名称
				try {
					String name = map_json.get("Name").toString().trim();
					map.put("investment_name", name);
				} catch (Exception e) {}
				//法人
				try {
					String operName = map_json.get("OperName").toString().trim();
					map.put("legal_person", operName);
				} catch (Exception e) {}
				//成立日期
				try {
					String startDate = map_json.get("StartDate").toString().trim();
					map.put("formed_time", startDate);
				} catch (Exception e) {}
				//注册资本
				try {
					String registCapi = map_json.get("RegistCapi").toString().trim();
					map.put("register_money", registCapi);
				} catch (Exception e) {}
				//地址
				try {
					String address = map_json.get("Address").toString().trim();
					map.put("address", address);
				} catch (Exception e) {}
				list.add(map);
			}
		}
		if (!nextPageFlag) {
			throw new Exception("没有下一页了");
		}
	}
	
	/**
	 * 下一页标志
	 */
	private static void hasNextPage() {

		if (pageNum * pageSize < totalRecords) {
			nextPageFlag = true;
		} else {
			nextPageFlag = false;
		}
	}

}
