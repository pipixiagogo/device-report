package com.zh.device.prepareFuture.jieYasuoUtils;

public class TestZip {
    public static void main(String[] args){
        ZipCompress zipCom = new ZipCompress("D:\\电影.zip","D:\\电影");
        try{
            zipCom.zip();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
