package com.zh.device.prepareFuture.myrunner;

public class SharedVariableThread extends Thread {

    private int count = 5;
    Object object = new Object();

    @Override
    public void run() {
        synchronized (object) {
            while (count > 0) {

                count--;
                System.out.println("由" + Thread.currentThread().getName() + "计算得到的count的值" + count);
            }

        }
    }

    public static void main(String[] args) {
        SharedVariableThread thread = new SharedVariableThread();
        Thread a = new Thread(thread, "a");
        Thread b = new Thread(thread, "b");
        Thread c = new Thread(thread, "c");
        Thread d = new Thread(thread, "d");
        a.start();
        b.start();
        c.start();
        d.start();
    }
}
