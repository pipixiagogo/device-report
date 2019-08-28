package com.zh.device.prepareFuture.myrunner.misReader;

public class ThreadB extends Thread {

    private PublicVar publicVar;

    public ThreadB(PublicVar publicVar){
        this.publicVar=publicVar;
    }
    @Override
    public void run() {
        super.run();
        publicVar.setValue("BB","BBBB");
    }
}
