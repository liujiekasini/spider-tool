package com.dinfo.plugtool.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;

/**
 * @ClassName: TxtUtil
 * @Description: 操作txt文本工具类
 * @author xulonglong
 * @date 2016-1-13 上午11:39:55
 */
public class TxtUtil {
	/**
	 * @Description: 读取文本内容
	 * @param @param filePath
	 * @param @param encoding
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author xulonglong
	 * @date 2016-1-13 上午11:40:02
	 */
	public static String readTxtFile(String filePath,String encoding){
		String content="";
        try {
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                	content=content+lineTxt;
                }
                read.close();
	        }else{
	            System.out.println("找不到指定的文件");
	        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return content;
    }
	/**
	 * @Description: 写入文本
	 * @param @param path
	 * @param @param content
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author xulonglong
	 * @date 2016-1-13 上午11:40:37
	 */
	public static String writeTxtFile(String path,String content) {
        File file = new File(path); // 找到File类的实例
        try {
        // 创建文件
            file.createNewFile(); 
        // 声明字符输出流
            Writer out = null; 
        // 通过子类实例化，表示可以追加
            out = new FileWriter(file,true); 
        // 写入数据
            out.write(content); 
            out.close();
            return "0";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "1";
    }
	//测试
	public static void main(String[] args) {
//		String filePath = "D:\\2.txt";
//	     String encoding="GBK";
//		String content= readTxtFile(filePath,encoding);
//		System.out.println(content);
//		new TxtUtil().writeTxtFile("D:\\22.txt", "222");
	}
}
