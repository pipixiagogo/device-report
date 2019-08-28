package com.zh.device.prepareFuture.classLoad;


public class Son extends Father {

    static {
        System.out.println("son");
    }
    public Son(){
        System.out.println("ssssson");
    }
    public static String SONNAME="SON";
}
