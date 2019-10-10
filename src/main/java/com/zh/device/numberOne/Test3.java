package com.zh.device.numberOne;

public class Test3 {
    public static void main(String[] args) {
        ThreadLocal<Integer> local = new ThreadLocal<>();
        ThreadLocal<Integer> local2 = new ThreadLocal<>();
        local.set(1);
        local2.set(99);
        local.set(10);
        new Thread(){
            @Override
            public void run() {
                local.set(2);
                System.out.println(this.getName()+"线程"+local.get());
                System.out.println(this.getName()+"线程"+local2.get());
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                System.out.println(this.getName()+"线程"+local.get());
            }
        }.start();
        try {
            Thread.currentThread().sleep(2000);//等待两秒 让其他线程跑完
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("main线程"+local.get());
        System.out.println("main线程"+local2.get());
    }
}
