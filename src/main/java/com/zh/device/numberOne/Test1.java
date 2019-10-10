package com.zh.device.numberOne;

import java.util.Date;

public class Test1 {
    public static void main(String[] args) {

        TestThread1 testThread1 = new TestThread1();
        testThread1.start();
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testThread1.setStop(true);
    }
}

class TestThread1 extends Thread {
    private volatile boolean isStop = false;

    public void setStop(boolean stop) {
        isStop = stop;
    }

    @Override
    public void run() {
        System.out.println("我执行了" + new Date());
        int i=0;
        while (!isStop && i<Integer.MAX_VALUE ){
            System.out.println(i+"一直加");
            i++;
        }
    }
}
