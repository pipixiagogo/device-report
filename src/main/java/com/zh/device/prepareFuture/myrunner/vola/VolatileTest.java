package com.zh.device.prepareFuture.myrunner.vola;

public class VolatileTest extends Thread{
    private static volatile boolean flag = false;

    public void run() {
        while (!flag) ;
    }

    public static void main(String[] args) throws Exception {
        new VolatileTest().start();
        Thread.sleep(2000);
        flag = true;
    }
}
