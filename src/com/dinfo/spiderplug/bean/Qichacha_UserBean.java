package com.dinfo.spiderplug.bean;

public class Qichacha_UserBean {
    private int id;
    private String user_name;
    private String passwd;
    private String cookie;
    private int type;
    private int rel_ok;
    private String cookiem;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public String getPasswd() {
        return passwd;
    }
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    public String getCookie() {
        return cookie;
    }
    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public int getRel_ok() {
        return rel_ok;
    }
    public void setRel_ok(int rel_ok) {
        this.rel_ok = rel_ok;
    }
    public String getCookiem() {
        return cookiem;
    }
    public void setCookiem(String cookiem) {
        this.cookiem = cookiem;
    }
    
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if(obj == null){
            return false;
        }
        if(obj instanceof Qichacha_UserBean){
            Qichacha_UserBean bean = (Qichacha_UserBean)obj;
            if(bean.getId() == this.getId()){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        int result = 17; 
        result = result*31+id;
        return result;
    }
}
