package com.dinfo.spiderplug.main;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.dinfo.spiderplug.entry.annotation.Start;

public class StartTest {
    public static void main(String[] args) {
        
        try {
            List<Class> list = getAllClassByInterface("com.dinfo.spiderplug.entry");
            startClassList(list);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }

    private static void startClassList(List<Class> list) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException {
        // TODO Auto-generated method stub
        if(list != null && !list.isEmpty()){
            for(Class<?> c:list){
               Annotation start = c.getAnnotation(Start.class);
               if(start != null){
                   Method mainMethod  = c.getMethod("main", String[].class);
                   mainMethod.invoke(null,(Object)new String[]{});
               }
            }
        }
    }

    private static List<Class> getAllClassByInterface(String packageName) throws ClassNotFoundException {
        // TODO Auto-generated method stub
         List<Class>  list = new ArrayList<Class>();
         String path = packageName.replace(".","/");
         URL resource = Thread.currentThread().getContextClassLoader().getResource(path);
         File dir = new File(resource.getFile());
         
         if(dir.exists()){
             File[] files = dir.listFiles();
             for(int i=0;i<files.length;i++){
                 File c_file = files[i];
                 if(c_file.isFile() && c_file.getName().endsWith(".class")){
                     String name = c_file.getName();
                     name = name.substring(0,name.lastIndexOf("."));
                     String class_name = packageName+"."+name;
                     list.add(Class.forName(class_name));
                 }
             }
         }
         return list;
    }
}
