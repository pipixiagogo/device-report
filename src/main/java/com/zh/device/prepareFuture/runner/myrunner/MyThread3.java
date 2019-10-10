package com.zh.device.prepareFuture.runner.myrunner;

public class MyThread3 extends Thread {
    private int i = 0;
    @Override
    public void run() {
        try {
            while (true) {
                i++;
                System.out.println("i=" + (i)+this.isDaemon());

                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
class  TestThread3{
    public static void main(String[] args) {
        try {
            MyThread3 thread = new MyThread3();
            thread.setDaemon(true);
            thread.start();
            Thread.sleep(5000);
            System.out.println("我离开thread对象也不再打印了，也就是停止了！");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
