package com.zh.device.prepareFuture.myrunner;

public class MyRunner1 implements Runnable {
    @Override
    public void run() {
            Test test = new Test();
            test.doit();

    }
}
