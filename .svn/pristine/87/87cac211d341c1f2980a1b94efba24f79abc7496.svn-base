package com.dinfo.plugtool.util;

import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class URLUtil {
    private final static Logger log = LoggerFactory.getLogger(URLUtil.class);
    /**
     * html中，把转义字符转换成普通字符
     * @param html
     * @return
     */
    public static String CodeToTen(String html){
        if(html!=null){
            html = html.replace("&quot;", "\"");
            html = html.replace("&amp;", "&");
            html = html.replace("&lt;", "<");
            html = html.replace("&gt;", ">");
        }
        return html;
    }
    
    /**
     * 获取url参数的值，只匹配第一个。
     * 如:getAttrFromURL("http://126.com/index.htm?a=12&b=45&c=67&b=66", "b" ) == 45
     * @param   url
     * @param   attr 
     */
    public static String getAttrFromURL(String url, String attr ){
        Pattern p = Pattern.compile( "[&|?]"+attr+"=([^&]*)" );
        Matcher m = p.matcher( url );
        while( m.find() ){
            return m.group(1);
        }
        return "";
    }
    
    /**
     * 返回URL中不带参数的部分,即'?'以前的部分。
     * 
     */
    public static String getIndexFromURL(String url){
        Pattern p = Pattern.compile("(.*?)\\?");
        Matcher m = p.matcher( url );
        while( m.find() ){
            return m.group(1);
        }
        return "";
    }
    
    /**
     * 返回URL的域名部分.
     * 
     */
    public static String getDomainFromURL(String url){
//        String group = "" ;
//        Pattern p = Pattern.compile("(?<=http\\://)(?:[^.\\s]*?\\.)+(com|cn|net|org|biz|info|cc|tv)",Pattern.CASE_INSENSITIVE);
//        Matcher matcher = p.matcher(url);
//        if( matcher.find() ){
//            group = matcher.group();
//        }
        try {
            URI uri = new URI(url);
             return uri.getHost();
        } catch (URISyntaxException ex) {
            log.warn("获取域名失败："+url, ex.getMessage());
            return null ;
        }
    }
    
    public static String getDomainFromURLWithoutWWW(String url){
        try {
            URI uri = new URI(url);
            String host =  uri.getHost();
            if( host.startsWith("www.") ){
                host = host.replaceFirst("www.", "");
            }
            return host ;
        } catch (URISyntaxException ex) {
            log.warn("获取域名失败："+url, ex.getMessage());
            return null ;
        }
    }
   
    /**
     * 获取顶级域名
     * @param url
     * @return 
     */
    public static String getToplevelDomainFromURLWithoutWWW(String url){
        Pattern p = Pattern.compile("(?<=(?:://\\w{1,10}\\.)?)(?:\\w+\\.)(?:com\\.cn|net\\.cn|org\\.cn|gov\\.cn|com|net|org|cn|biz|info|cc|tv)",Pattern.CASE_INSENSITIVE);//
        Matcher matcher = p.matcher(url);
        while ( matcher.find() ){
            return matcher.group();
        }
        return "";
    }
    
    /**
     * 是否为一个URL
     * @param url
     * @return 
     */
    private static final String regEx = "^(http|https|ftp)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-"
        + "Z0-9\\.&%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"
        + "2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}"
        + "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|"
        + "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-"
        + "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"
        + "-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/"
        + "[a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@$#\\*]*)*$";
    public static boolean isLegalURL(String url) {
        if(url == null || url.length() == 0) {
        return false;
        }
        Pattern isLegalURL_p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = isLegalURL_p.matcher(url);
        return matcher.matches();
    }
    
    /**
     * 根据URL和相对URL，组合URL
     * @param baseurl
     * @param relativeUrl
     * @return 
     */
//    private static Pattern convertURL_p = Pattern.compile("((\\.|\\.\\.)/|/)+");
    public static String convertURL(  String baseurl,  String relativeUrl ){
        relativeUrl = relativeUrl.trim();
        try {
            if( URLUtil.isLegalURL(relativeUrl) ){
                return relativeUrl ;
            }else{
                URL url_base = new URL(baseurl);
                URI uri_base = new URI(url_base.getProtocol(), url_base.getHost(), url_base.getPath(), url_base.getQuery(), null);
                String xiangdui = "";
                Pattern convertURL_p = Pattern.compile("((\\.|\\.\\.)/|/)+");
                Matcher m = convertURL_p.matcher( relativeUrl );
                while( m.find() ){
                    xiangdui = m.group() ;
                    break;
                }
                if( xiangdui.equals("") ){
                    relativeUrl =  URLEncoder.encode(relativeUrl,"utf-8");
                }else{
                    int idx = xiangdui.length();
                    relativeUrl =  relativeUrl.substring(0, idx+1) + URLEncoder.encode( relativeUrl.substring(idx+1),"utf-8");
                }
                URI abs=uri_base.resolve(relativeUrl);  
                return  URLDecoder.decode( abs.toURL().toString() , "utf-8") ;
            }
        } catch ( Exception ex) {
            log.warn( relativeUrl+" -- "+baseurl, ex.getMessage());
        }
        return null ;
    }
    
    /**
     * 排除# 、javascrip 、 js链接、 css链接
     * @param url
     * @return 
     */
    public static boolean isUsefulURL(  String url ){
        if( StringUtils.isBlank( url ) ){
            return false;
        }
        url = url.trim();
        Pattern isUsefulURL_p1 = Pattern.compile("(?is)^(javascript:)");
        Pattern isUsefulURL_p2 = Pattern.compile("(?is)(\\.js|\\.css)$");
        if( isUsefulURL_p1.matcher(url).find()
                || isUsefulURL_p2.matcher(url).find()
                || url.equals("#") ){
            return false;
        }
        return true;
    }
    /**
     * Unicode码转汉字
     * 
     *
     */
    public static String convert(String utfString) {
        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;
        while ((i = utfString.indexOf("\\u", pos)) != -1) {
            sb.append(utfString.substring(pos, i));
            if (i + 5 < utfString.length()) {
                pos = i + 6;
                sb.append((char) Integer.parseInt(utfString.substring(i + 2,
                        i + 6), 16));
            }else{
                break;
            }
        }

        return sb.toString();
    }
    
    
    public static void main(String[] args) throws Exception {
    	String s = convertURL("http://bbs.panzhihua.fang.com/board/3213168096/","http://bbs.fang.com/1010671189~-1~10904/152899308_152899308.htm");
    	System.out.println(s);
    }
        
        
}

