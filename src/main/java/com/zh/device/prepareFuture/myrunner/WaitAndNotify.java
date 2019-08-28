package com.zh.device.prepareFuture.myrunner;

import java.util.Comparator;

public class WaitAndNotify implements Comparator{
    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }

    private final static Object OBJECT = new Object();

    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            synchronized (OBJECT) {
                try {
                    System.out.println(Thread.currentThread().getName() + "run");
                    OBJECT.wait();
                    System.out.println("321");
                } catch (InterruptedException e) {

                }
            }
        },"皮皮虾");
        t.start();
        try {
            //监视对象的notify 跟wait方法
            Thread.sleep(300);
            synchronized (OBJECT){
                System.out.println("唤醒"+Thread.currentThread().getName());
                OBJECT.notify();
               Thread.sleep(200);
            }
            System.out.println("这边不会执行");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
