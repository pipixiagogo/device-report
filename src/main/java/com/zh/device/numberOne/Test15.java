package com.zh.device.numberOne;

import com.zh.device.util.Md5;

import java.util.Base64;

public class Test15 {
    public static void main(String[] args) {
        Md5 md5 = Md5.getInstance();
        //32位MD5加密
//        String md5_32 = md5.md5_32("123456");
//        //16位MD5加密
//        String md5_16=md5_32.substring(8,24);
//        //8位MD5加密
//        String md5_8=md5_32.substring(0,8);
//        System.out.println(md5_32);
//        System.out.println(md5_16);
//        System.out.println(md5_8);
        byte[] bytes = md5.md5_16_byte("123456".getBytes());
        String s = Base64.getEncoder().encodeToString(bytes);
        System.out.println(s);
    }
}
