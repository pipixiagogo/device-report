package com.zh.device.prepareFuture.freeMarkerTest;
import java.io.*;


import freemarker.template.Configuration;
import freemarker.template.Template;

import java.util.HashMap;
import java.util.Map;

public class FreeMarkerMain {
    public static final String path="src/main/java/com/zh/device/prepareFuture/freeMarkerTest/templates";
    public static final String FILE_PTAH="src/main/java/com/zh/device/prepareFuture/freeMarkerTest";
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        Writer writer=null;
        try {
            configuration.setDirectoryForTemplateLoading(new File(path));
            Map<String,Object> parms= new HashMap<>();
            parms.put("classpath","com.zh.device.prepareFuture.freeMarkerTest;");
            parms.put("className","Hello");
            parms.put("helloworld","\"简单代码演示\"");
            Template template = configuration.getTemplate("hello.ftl");
            File file = new File(FILE_PTAH+"\\"+"Hello.java");
            writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            template.process(parms,writer);
            System.out.println("运行完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(writer != null){
                    writer.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
