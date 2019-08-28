package com.zh.device.prepareFuture.myrunner.misReader;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        PublicVar publicVar = new PublicVar();
        ThreadA threadA = new ThreadA(publicVar);
        threadA.start();
        Thread.sleep(200);
        publicVar.getValue();
    }
}
