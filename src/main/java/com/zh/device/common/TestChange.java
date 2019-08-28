package com.zh.device.common;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestChange {

    public static void main(String[] args) {
//        byte[] bytes = new byte[]{(byte)0x20,(byte)0x20};
//        System.out.println(new String(bytes));
//
//        int i = Short.MAX_VALUE - Short.MIN_VALUE;
//        System.out.println(i);
        ApplicationContext ac=new ClassPathXmlApplicationContext("");
        Object bean = ac.getBean("");
    }
}
