package com.dinfo.spiderplug.entry;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import com.dinfo.plugtool.dao.impl.MySqlDaoMapImpl;
import com.dinfo.spiderplug.bean.Qichacha_UserBean;
import com.dinfo.spiderplug.common.QCC_common;

public class QCC_SearchTread implements Runnable{
    private String name;
    public QCC_SearchTread(String name) {
        // TODO Auto-generated constructor stub
        this.name = name;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
        try {
            searchCompany();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void searchCompany() throws UnsupportedEncodingException {
        // TODO Auto-generated method stub
         //搜索
         Map<String, Object> map = QCC_common.download(name);
         if(map == null){
             updateCompanyState(name,0);
             return;
         }
         Document dom = (Document) map.get("dom");
         Qichacha_UserBean  bean = (Qichacha_UserBean) map.get("bean");
         
         String text = dom.text();
         if(text.contains("输入验证码") || text.contains("亲，小查告诉你个秘密， 点击登录 后可以查看更多数据哦 ")){
             System.out.println("cookie ==="+bean.getId()+"---失效");
             QCC_common.u_queue.remove(bean);
             updateCompanyState(name, 0);
             updateCookieType(bean.getId(),1);
             System.out.println(dom);
             return;
         }
         
         if(text.contains("小查还没找到数据")){
             System.out.println(name+"-----没有搜索结果");
             updateCompanyState(name,2);
             return;
         }
         try {
             Elements as = dom.select("section#searchlist table tbody tr td a.ma_h1");
             for(int i=0;i<as.size();i++){
                 Element a = as.get(i);
                 String href = a.attr("href");
                 if(href.lastIndexOf("_") != -1){
                     href = href.substring(href.lastIndexOf("_")+1);
                 }
                 if(href.lastIndexOf(".")!= -1){
                     href = href.substring(0,href.lastIndexOf("."));
                 }
                 String c_name = a.text().trim();
                 addNewCompany(href,c_name);
             }
             updateCompanyState(name,2);
         } catch (Exception e) {
            // TODO: handle exception
             e.printStackTrace();
             System.out.println(dom);
         }
         
         
    }

    private void addNewCompany(String unique, String cmp_name) {
        // TODO Auto-generated method stub
        String sql = "insert ignore into cmp_base_info(cmp_name,unique_id) values(?,?)";
        try {
            MySqlDaoMapImpl.runner.update(sql,cmp_name,unique);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void updateCookieType(int id, int type) {
        // TODO Auto-generated method stub
        String sql = "update user set type=? where id=?";
        try {
            MySqlDaoMapImpl.runner.update(sql,type,id);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void updateCompanyState(String c_name, int type) {
        // TODO Auto-generated method stub
        String sql = "update company set rel_ok=? where name=? ";
        try {
            MySqlDaoMapImpl.runner.update(sql,type,c_name);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
