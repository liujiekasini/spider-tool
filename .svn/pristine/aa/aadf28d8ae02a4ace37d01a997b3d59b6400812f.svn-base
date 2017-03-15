package com.dinfo.plugtool.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ClassName: PropertyUtil
 * @Description: 读取配置文件工具类
 * @author xulonglong
 * @date 2016-1-13 上午11:39:20
 */
public class PropertyUtil {
	
	private static Properties p = new Properties();
	static{
		try {
		    File file = new File(System.getProperty("user.dir")+File.separator+"db.properties");
	        InputStream in = new FileInputStream(file);
			p.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description: 得到配置文件参数
	 * @param @param key
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 下午2:10:45
	 */
	public static String getValue(String key){
		String value = p.getProperty(key);
		return value;
	}
	/**
	 * @Description: 设置配置文件参数
	 * @param @param key
	 * @param @param value   
	 * @return void  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 下午2:10:26
	 */
    public static void setValue(String key, String value) {
        try {
            File file = new File(System.getProperty("user.dir")+File.separator+"db.properties");
            FileOutputStream out = new FileOutputStream(file);
            p.setProperty(key, value);
            p.store(out,"save");
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
