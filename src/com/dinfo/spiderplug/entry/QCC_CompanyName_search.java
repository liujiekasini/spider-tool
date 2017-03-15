package com.dinfo.spiderplug.entry;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import com.dinfo.plugtool.common.ClassCommon;
import com.dinfo.plugtool.dao.impl.MySqlDaoBeanImpl;
import com.dinfo.plugtool.dao.impl.MySqlDaoMapImpl;
import com.dinfo.plugtool.enumerate.BrowserType;
import com.dinfo.plugtool.spider.dao.impl.WebDriverDaoImpl;
import com.dinfo.spiderplug.bean.Qichacha_UserBean;
import com.dinfo.spiderplug.common.QCC_common;
import com.dinfo.spiderplug.entry.annotation.Start;

@Start
public class QCC_CompanyName_search {
    
    public static void main(String[] args) {
        
        QCC_common.init();
        Timer time = new Timer();
        //通过qq号   模拟登陆  企查查，间隔 5分钟
        time.schedule(new TimerTask() {
            
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    List<Qichacha_UserBean> userList = getAllEnableUser();
                    if(userList != null && !userList.isEmpty()){
                        for(Qichacha_UserBean user:userList){
                            loginQichacha(user);
                        }
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }, 5000,5*60*1000);
        
        //搜索 公司名称  获取 unique。  间隔  2分钟。  每次访问 10秒。
        time.schedule(new TimerTask() {
            
            @Override
            public void run() {
                // TODO Auto-generated method stub
              try {
                  int num =QCC_common.ThreadNUM-
                          ( QCC_common.search_pool.getActiveCount()+
                                  QCC_common.search_pool.getQueue().size());
                  if(num >0){
                      List<String> cList = getCompanyNameList(num);
                      if(cList != null && !cList.isEmpty()){
                          for(String name:cList){
                              QCC_SearchTread t = new QCC_SearchTread(name);
                              QCC_common.search_pool.execute(t);
                          }
                      }
                  }
                
              } catch (Exception e) {
                // TODO: handle exception
              }
            }
        },1000,2*60*1000);
    }
    /**
     * 获取要  搜索的公司名。
     * @param num
     * @return
     * @throws SQLException 
     */
    public static List<String> getCompanyNameList(int num){
        // TODO Auto-generated method stub
        String sql = "select name from company where rel_ok=0 limit "+num;
        
        try {
            List<String> list = MySqlDaoMapImpl.runner.query(sql,new ColumnListHandler<String>());
            updateStateNameList(list,1);
            return list;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 更新状态。
     * @param list
     * @param type
     */
    private static void updateStateNameList(List<String> list,int type) {
        // TODO Auto-generated method stub
        if(list == null || list.isEmpty()){
            return;
        }
        String sql = "update company set rel_ok=? where name=?";
        Object[][] params = new Object[list.size()][2];
        for(int i=0;i<list.size();i++){
            params[i][1] = list.get(i);
            params[i][0] = type;
        }
        try {
            MySqlDaoMapImpl.runner.batch(sql, params);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * 使用 PHANTOMJS，qq账号登陆 企查查。
     * @param user
     */
    public static void loginQichacha(Qichacha_UserBean user) {
        // TODO Auto-generated method stub
        WebDriver  driver = new WebDriverDaoImpl().getWebDriver(BrowserType.PHANTOMJS);
        try {
            login(user,driver);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            driver.close();
            driver.quit();
        }
    }
    private static void login(Qichacha_UserBean user, WebDriver driver) throws InterruptedException{
        // TODO Auto-generated method stub
        driver.manage().deleteAllCookies();
        driver.get("http://www.qichacha.com/user_qqlogin");
        Thread.sleep(5000L);
        driver.switchTo().frame("ptlogin_iframe");
        
        driver.findElement(By.id("switcher_plogin")).click();
        Thread.sleep(2000);
        
        driver.findElement(By.id("u")).clear();
        driver.findElement(By.id("u")).sendKeys(user.getUser_name());
        driver.findElement(By.id("p")).clear();
        driver.findElement(By.id("p")).sendKeys(user.getPasswd());
        
        driver.findElement(By.id("login_button")).click();
        
        Thread.sleep(45000);
        
        String context = driver.getPageSource();
        System.out.println(context);
        System.out.println("-----------------------------------------");
        if(context.contains("/user_logout")){
            Set<Cookie> cookies = driver.manage().getCookies();
            StringBuffer sb = new StringBuffer();
            for(Cookie c:cookies){
                sb.append(c.getName()+"="+c.getValue()+"; ");
            }
            System.out.println("更新成功 ----"+user.getId()+"----"+user.getUser_name());
            updateCookie(user,sb.toString());
        }else{
            System.out.println("更新失败-----"+user.getUser_name());
        }
    }
    /**
     * 登陆cookie保存数据库。
     * @param user
     * @param cookie
     * @throws SQLException
     */
    private static void updateCookie(Qichacha_UserBean user, String cookie){
        // TODO Auto-generated method stub
        String sql = "update user set cookie=?,type=0 where id=?";
        try {
            new MySqlDaoMapImpl().runner.update(sql,cookie,user.getId());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * 获取失效的  用户列表。
     * @return
     */
    public static List<Qichacha_UserBean> getAllEnableUser() {
        // TODO Auto-generated method stub
        String sql = "select * from user where type=1";
        List<Qichacha_UserBean> list = ClassCommon.mySqlDaoBean.getDataBeanList(sql, Qichacha_UserBean.class);
        return list;
    }
}
