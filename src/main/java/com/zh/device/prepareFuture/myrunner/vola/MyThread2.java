package com.zh.device.prepareFuture.myrunner.vola;

public class MyThread2 extends Thread {

    public static int count;
    private Object object;

    public MyThread2(Object lock) {
        this.object=lock;
    }

     public static void addcount(){

    }
    @Override
    public void run() {
        synchronized (object){
            for (int i = 0; i < 100; i++) {
                count = i;
            }
            System.out.println(count);
        }

    }
}
