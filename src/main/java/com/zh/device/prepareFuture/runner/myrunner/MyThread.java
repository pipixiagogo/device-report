package com.zh.device.prepareFuture.runner.myrunner;

public class MyThread extends Thread{
    private int count =5 ;

    public MyThread(String name){
        this.setName(name);
    }
    @Override
    public void run() {
            count--;
            System.out.println("线程名称:"+Thread.currentThread().getName()+""+count);
    }
}

class MyThreadTest{
    public static void main(String[] args) {
        MyThread thread1 = new MyThread("A");
        MyThread thread2 = new MyThread("B");
        MyThread thread3 = new MyThread("C");
        MyThread thread4 = new MyThread("D");
        MyThread thread5 = new MyThread("E");
        thread1.start();
        thread2.start();
        thread3.start();
        thread5.start();
        thread4.start();
    }
}
