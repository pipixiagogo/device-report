package com.zh.device.prepareFuture.ThreadTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test1 {
    private Lock lock = new ReentrantLock();
    private List<String> list = new ArrayList<>();


    public static void main(String[] args) {
        Test1 test = new Test1();
        ThreadD thread1 = new ThreadD(test);
        ThreadD thread2 = new ThreadD(test);
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();
    }

    public void addNum(Thread thread) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            System.out.println(thread.getName()+"得到了锁");
            long startTime = System.currentTimeMillis();
            for(    ;     ;) {
                if(System.currentTimeMillis() - startTime >= Integer.MAX_VALUE)
                    break;
                //插入数据
            }
        } finally {
            System.out.println(thread.getName()+"释放了锁");
            lock.unlock();
        }
    }
}


class ThreadD extends Thread {
    private Test1 test1;

    public ThreadD(Test1 test1) {
        this.test1 = test1;
    }

    @Override
    public void run() {
        try {
            test1.addNum(this);
        }catch (InterruptedException e){
            System.out.println(this.getName()+"被中断");
        }

    }
}




