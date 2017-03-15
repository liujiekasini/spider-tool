package com.dinfo.spiderplug.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.dbutils.handlers.MapListHandler;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.dinfo.plugtool.dao.impl.MySqlDaoBeanImpl;
import com.dinfo.plugtool.dao.impl.MySqlDaoMapImpl;
import com.dinfo.spiderplug.bean.Qichacha_UserBean;

public class QCC_common {
    public static final int ThreadNUM = 20;
    public static int user_id=0;
    public static ThreadPoolExecutor search_pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(ThreadNUM);
    public static Random r = new Random(System.currentTimeMillis());
    public static BlockingQueue<Qichacha_UserBean> u_queue = new LinkedBlockingDeque<Qichacha_UserBean>();
    
    public synchronized static Map<String,Object> download(String name) throws UnsupportedEncodingException {
        // TODO Auto-generated method stub
        String keyword = URLEncoder.encode(name,"utf-8");
        String url = "http://www.qichacha.com/search?key="+keyword+"&index=0";
        System.out.println("搜索公司名称 -----"+name+"----"+url);
        //获取有用的  cookie
        Qichacha_UserBean  bean = selectCookieFromDB();
        user_id = bean.getId();
        
        Connection conn = Jsoup.connect(url).timeout(60000);
        conn.header("Cookie",bean.getCookie());
        conn.header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0");
        conn.header("Host", "www.qichacha.com");
        Map<String, Object> map = new HashMap<String, Object>();
        for(int i=0;i<3;i++){
            try {
                Document dom = conn.get();
                map.put("dom", dom);
                map.put("bean", bean);
                return map;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                continue;
            }finally{
                try {
                    int num = 30%u_queue.size()==0?30/u_queue.size():(30/u_queue.size()+1);
                    Thread.sleep(num*1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    private static Qichacha_UserBean selectCookieFromDB() {
        // TODO Auto-generated method stub
        long time = 0L;
        while(true){
            try {
                if(u_queue.size() == 1){
                    int i=1/0;
                }
                Qichacha_UserBean bean = u_queue.take();
                u_queue.offer(bean);
                return bean;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                System.out.println("没有  可用 的  cookie");
              try {
                  time = time+30*1000;
                  Thread.sleep(30*1000);
              } catch (InterruptedException e1) {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
              }
              continue;
            }
//            List<Qichacha_UserBean> cList = getAbleCookie();
//            if(cList != null && !cList.isEmpty()){
//                int num = r.nextInt(cList.size());
//                Qichacha_UserBean bean = cList.get(num);
//                return bean;
//            }else{
//                System.out.println("没有  可用 的  cookie");
//                try {
//                    time = time+30*1000;
//                    Thread.sleep(30*1000);
//                } catch (InterruptedException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                continue;
//            }
        }
    }
    
    private static BlockingQueue<Qichacha_UserBean> getAbleCookie() {
        // TODO Auto-generated method stub
        BlockingQueue<Qichacha_UserBean> list = new LinkedBlockingDeque<Qichacha_UserBean>();
        String sql = "select * from user where type = 0";
        
        try {
            List<Map<String, Object>> resMap = MySqlDaoMapImpl.runner.query(sql,new MapListHandler());
            if(resMap != null){
                for(Map<String, Object> map:resMap){
                    int id = Integer.parseInt(map.get("id").toString());
                    String cookie = map.get("cookie").toString();
                    
                    Qichacha_UserBean bean = new Qichacha_UserBean();
                    bean.setId(id);
                    bean.setCookie(cookie);
                    
                    list.add(bean);
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return list;
    }


    public static void init() {
        // TODO Auto-generated method stub
        updateAllCompanyState();
        u_queue = getAbleCookie();
    }


    private static void updateAllCompanyState() {
        // TODO Auto-generated method stub
        String sql = "update company set rel_ok=0 where rel_ok=1";
        try {
            MySqlDaoMapImpl.runner.update(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
