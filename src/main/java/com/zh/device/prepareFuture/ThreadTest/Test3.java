package com.zh.device.prepareFuture.ThreadTest;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test3 {
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    public static void main(String[] args) {
        Test3 test3 = new Test3();
        
        new Thread(){
            public void run() {
                test3.get(Thread.currentThread());
            }
        }.start();

        new Thread(){
            public void run() {
                test3.get(Thread.currentThread());
            }
        }.start();
    }

    public  void get(Thread thread) {
        lock.readLock().tryLock();
        try {
            long start = System.currentTimeMillis();
            while(System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName()+"正在进行读操作");
            }
            System.out.println(thread.getName()+"读操作完毕");
        }finally {
            lock.readLock().unlock();
        }

    }
}
