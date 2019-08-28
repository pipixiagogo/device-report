package com.zh.device.prepareFuture.myrunner.join;

public class Test {
    public static void main(String[] args) throws InterruptedException{
        MyThread myThread = new MyThread();
        myThread.start();
        myThread.join(2000);//2秒后超时 会释放锁   sleep不会释放锁
        System.out.println("当myThread执行完后在执行 end Time"+System.currentTimeMillis());
    }

    static public class MyThread extends Thread {

        @Override
        public void run() {
            try {
                System.out.println("start time "+System.currentTimeMillis());
                Thread.sleep(10000);//睡10S
                System.out.println("我想先执行");
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }
}
