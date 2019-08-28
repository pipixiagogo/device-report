package com.zh.device.prepareFuture.myrunner.waitAndInterruped;

public class Test {
    public static void main(String[] args) {
        Object lock= new Object();
        ThreadA threadA = new ThreadA(lock);

        threadA.start();
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        threadA.interrupt();

    }
}
