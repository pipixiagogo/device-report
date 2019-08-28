package com.zh.device.prepareFuture.myrunner.vola;

public class TestMain {
    public static volatile boolean flag=true;
    public static void main(String[] args) {
        new Thread(()->{
            while (flag){}
            System.out.println("线程停止,死循环被打开"+Thread.currentThread().getName());
        }).start();

        new Thread(()->{
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            flag=false;
            System.out.println(Thread.currentThread().getName() + "修改 flag 为" + flag);
        }).start();
    }
}
