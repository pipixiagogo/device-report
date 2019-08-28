package com.zh.device.prepareFuture.myrunner.notifyAndWait;


public class Run {
    public static void main(String[] args) {
        Object lock = new Object();
        ThreadA threadA = new ThreadA(lock);
        ThreadB threadB = new ThreadB(lock);
        threadA.start();
        threadB.start();
    }
}
