package com.zh.device.prepareFuture.ThreadTest;

public class Test4 {
    public static Object lock = new Object();

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        thread1.start();
        Thread2 thread2 = new Thread2();
        try {
            Thread.sleep(200);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        thread2.start();
    }

   static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.getName() + "获取了锁");
            }
        }
    }

   static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                lock.notify();
                System.out.println("线程" + this.getName() + "调用了notify()");
            }
            System.out.println("线程" + this.getName() + "释放了锁");
        }
    }
}




