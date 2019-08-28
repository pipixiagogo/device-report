package com.zh.device.prepareFuture.myrunner.ThreadLockTest;

import java.util.Date;

public class Test4 {
    public static void main(String[] args) {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("从main方法中取值" + Tools.ext.get());
                Thread.sleep(1000);
            }
            Tools.ext.set("123");
            ThreadA threadA = new ThreadA();
            threadA.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static public class Tools {
        public static InheritableThreadLocalExt  ext = new InheritableThreadLocalExt ();
    }

    static public class InheritableThreadLocalExt  extends InheritableThreadLocal  {
        @Override
        protected Object initialValue() {
            return new Date().getTime();
        }
        @Override
        protected Object childValue(Object parentValue) {
            return parentValue + " 我在子线程加的~!";
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
