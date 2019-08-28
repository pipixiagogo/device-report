package com.zh.device.prepareFuture.myrunner.synchron;

public class Thread2 extends Thread{
    private HasSelfPrivateNum num;

    public Thread2(HasSelfPrivateNum num) {
        this.num = num;
    }

    @Override
    public void run() {
        super.run();
        num.addl("b");
    }
}
