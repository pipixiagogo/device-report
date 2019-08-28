package com.zh.device.prepareFuture.myrunner.vola;

public class MyThread implements Runnable {
    private  boolean isRun=true;
    int m;


    public boolean isRun() {
        return isRun;
    }

    public void setRun(boolean run) {
        isRun = run;
    }

    @Override
    public void run() {
        while (isRun){
            int a=2;
            int b=3;
            int c=a+b;
            m=c;
            System.out.println("一直跑"+m+Thread.currentThread().getName());
        }
        System.out.println("线程停止了"+m+Thread.currentThread().getName());
    }
}
