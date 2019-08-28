package com.zh.device.prepareFuture.myrunner.synchron;

public class Test {
    public static void main(String[] args) {
        HasSelfPrivateNum num1= new HasSelfPrivateNum();
        HasSelfPrivateNum num2= new HasSelfPrivateNum();
        Thread1 thread1 = new Thread1(num1);
        thread1.start();

        Thread2 thread2 = new Thread2(num2);
        thread2.start();
    }
}
