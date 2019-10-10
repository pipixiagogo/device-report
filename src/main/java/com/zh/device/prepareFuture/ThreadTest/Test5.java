package com.zh.device.prepareFuture.ThreadTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test5 {
    private ReentrantLock lock = new ReentrantLock();
    private  Condition condition = lock.newCondition();
    public static void main(String[] args) {
        Test5 test5 = new Test5();
        new Thread() {
            @Override
            public void run() {
                test5.get(this);
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                test5.put(this);
            }
        }.start();
    }

    public void get(Thread thread) {
        lock.lock();
        try {
            condition.await();
            System.out.println(thread.getName() + "获取锁了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void put(Thread thread) {
        lock.lock();
        try {
            condition.signal();
            System.out.println(thread.getName() + "调用signal()");

        } finally {
            System.out.println(thread.getName()+"释放锁了");
            lock.unlock();
        }
    }
}
