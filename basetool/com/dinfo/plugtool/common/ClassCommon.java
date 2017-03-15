package com.dinfo.plugtool.common;

import com.dinfo.plugtool.dao.HBaseDao;
import com.dinfo.plugtool.dao.MySqlDaoBean;
import com.dinfo.plugtool.dao.MySqlDaoMap;
import com.dinfo.plugtool.dao.SolrDao;
import com.dinfo.plugtool.dao.impl.HBaseDaoImpl;
import com.dinfo.plugtool.dao.impl.MySqlDaoBeanImpl;
import com.dinfo.plugtool.dao.impl.MySqlDaoMapImpl;
import com.dinfo.plugtool.dao.impl.SolrDaoImpl;
import com.dinfo.plugtool.spider.dao.JsoupSpiderDao;
import com.dinfo.plugtool.spider.dao.WebDriverDao;
import com.dinfo.plugtool.spider.dao.impl.JsoupSpiderDaoImpl;
import com.dinfo.plugtool.spider.dao.impl.WebDriverDaoImpl;

/**
 * @ClassName: ClassCommon
 * @Description: 初始化类通用
 * @author xulonglong
 * @date 2017-2-9 下午5:32:46
 */
public class ClassCommon {
	private static ClassCommon instance = null;
	public static JsoupSpiderDao jsoupSpiderDao=null;
	public static MySqlDaoBean mySqlDaoBean=null;
	public static MySqlDaoMap mySqlDaoMap=null;
	public static HBaseDao hBaseDao=null;
	public static WebDriverDao webDriverDao=null;
	public static SolrDao solrDao=null;
	
	/**
	 * @Description: 得到类对象
	 * @param    
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2017-2-13 上午10:11:27
	 */
	public synchronized void getClassCommon() {
		if(mySqlDaoBean==null){
			ClassCommon.mySqlDaoBean=new MySqlDaoBeanImpl();
		}
		if(jsoupSpiderDao==null){
			ClassCommon.jsoupSpiderDao=new JsoupSpiderDaoImpl();
		}
		if(mySqlDaoBean==null){
			ClassCommon.mySqlDaoBean=new MySqlDaoBeanImpl();
		}
		if(mySqlDaoMap==null){
			ClassCommon.mySqlDaoMap=new MySqlDaoMapImpl();
		}
		if(hBaseDao==null){
			ClassCommon.hBaseDao=new HBaseDaoImpl();
		}
		if(webDriverDao==null){
			ClassCommon.webDriverDao=new WebDriverDaoImpl();
		}
		if(solrDao==null){
			ClassCommon.solrDao=new SolrDaoImpl();
		}
	}
	
	private ClassCommon() {

	}
	public static ClassCommon getInstance() {
        if (instance == null) { 
            // 给类加锁 防止线程并发
            synchronized (ClassCommon.class) {
                if (instance == null) {
                	instance = new ClassCommon();
                }
            }
        }
        return instance;
    }
}
