package com.zh.device.prepareFuture.myrunner.vola;

public class TestThread3 {
    public static void main(String[] args)throws InterruptedException {
        MyThread3 thread3 = new MyThread3();

        Thread thread = new Thread(thread3,"Thread3");
        thread.start();
        Thread.sleep(300);
        thread3.setFlag();

    }
}
