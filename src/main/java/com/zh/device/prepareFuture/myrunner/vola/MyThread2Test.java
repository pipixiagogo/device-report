package com.zh.device.prepareFuture.myrunner.vola;

public class MyThread2Test {
    public static void main(String[] args) {
        MyThread2[] myThread2s = new MyThread2[100];
        Object object = new Object();
        for(int i=0;i<100;i++){
            myThread2s[i] = new MyThread2(object);
        }
        for(int i=0;i<100;i++){
            myThread2s[i].start();
        }
    }
}
