package com.zh.device.prepareFuture.myrunner.waitAndInterruped;

public class ThreadA extends Thread{

    private Object lock;

    public ThreadA(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock){
                System.out.println("begin wait()");
                lock.wait();
                System.out.println("end wait");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
            System.out.println("出现异常了，因为呈wait状态的线程被interrupt了！");
        }

    }
}
