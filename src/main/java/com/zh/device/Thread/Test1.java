package com.zh.device.Thread;

import java.util.concurrent.CountDownLatch;

public class Test1 {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(2);
        new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println(this.getName()+"开始执行");
                    Thread.sleep(3000);
                    System.out.println(this.getName()+"执行完成");
                    latch.countDown();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println(this.getName()+"开始执行");
                    Thread.sleep(3000);
                    System.out.println(this.getName()+"执行完成");
                    latch.countDown();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }.start();
        try {
            System.out.println("等待两个线程执行完毕");
            latch.await();
            System.out.println("主线程继续执行");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
