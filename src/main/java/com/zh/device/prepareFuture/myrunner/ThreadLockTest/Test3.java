package com.zh.device.prepareFuture.myrunner.ThreadLockTest;

import java.util.Date;

public class Test3 {
    public static void main(String[] args) {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("从main方法中取值" + Tools.ext.get());
                Thread.sleep(1000);
            }
            ThreadA threadA = new ThreadA();
            threadA.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static public class Tools {
        public static ThreadLoTest ext = new ThreadLoTest();
    }

    static public class ThreadLoTest extends ThreadLocal {
        @Override
        protected Object initialValue() {
            return new Date().getTime();
        }
    }

    static public class ThreadA extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("从ThreadA中取值" + Tools.ext.get());
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
