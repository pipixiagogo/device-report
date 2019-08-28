package com.zh.device.prepareFuture.myrunner.vola;

public class MainTest {
    public static void main(String[] args)throws InterruptedException{
        MyThread thread1 = new MyThread();
       Thread thread= new Thread(thread1,"Thread1");
       thread.start();
       Thread.sleep(200);
       thread1.setRun(false);

    }
}
