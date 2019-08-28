package com.zh.device.prepareFuture.myrunner.synchron;

public class Thread1 extends Thread {

    private HasSelfPrivateNum num;
    public Thread1(HasSelfPrivateNum num){
        this.num=num;
    }
    @Override
    public void run() {
        num.addl("a");

    }
}
