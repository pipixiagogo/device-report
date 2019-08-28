package com.zh.device.prepareFuture.myrunner;

public class InterruptThread2 extends Thread {


    @Override
    public void run() {
        for (int i = 0; i < 2000; i++) {
            if (this.interrupted()) {
                System.out.println("线程没停止" + i);
                return;
            }
        }
        System.out.println("线程没停止 看到这句");
    }

    public static void main(String[] args) throws InterruptedException{
        InterruptThread2 thread2 = new InterruptThread2();
        Thread thread = new Thread(thread2);
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }
}
