package com.zh.device.prepareFuture.myrunner.notifyAndWait;

public class ThreadB extends Thread {

    private Object lock;

    public ThreadB(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    MyList.add();
                    if (MyList.size() == 5) {
                        lock.notify();
                    }
                    System.out.println("已经添加了" + MyList.size() + "个元素");
                    Thread.sleep(1000);
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
