package com.zh.device.prepareFuture.myrunner;

public class MyThread extends Thread {

    private int count=5;

    public MyThread(String name) {
        this.setName(name);
    }

    @Override
    public void run() {
        while (count>0){
            count--;
            System.out.println("由"+Thread.currentThread().getName()+"计算得到count的值为:"+count);
        }
    }

    public static void main(String[] args) {
        MyThread a=new MyThread("a");
        MyThread b=new MyThread("b");
        MyThread c=new MyThread("c");
        MyThread d=new MyThread("d");
        a.start();
        b.start();
        c.start();
        d.start();
    }
}
