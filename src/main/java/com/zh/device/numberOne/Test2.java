package com.zh.device.numberOne;

public class Test2 extends Thread{
    volatile public static int count;

    private static void addCount() {
        for (int i = 0; i < 100; i++) {
            count=i;
        }
        System.out.println("count=" + count);

    }
    @Override
    public void run() {
        addCount();
    }

    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            new Test2().start();
        }

    }
}
