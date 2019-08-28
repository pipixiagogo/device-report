package com.zh.device.prepareFuture.myrunner.vola;

public class RunThread extends Thread {
    /** volatile */
    private volatile boolean isRunning = true;

    private void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void run() {
        System.out.println("进入 run() 方法中...");
        while (isRunning == true) {
            // doSomething()
            //不能够写输出语句 写了输出语句退出循环
            //System.out.println("在循环中");
        }
        System.out.println("线程结束了...");
    }

    public static void main(String[] args) throws InterruptedException {
        RunThread myThread = new RunThread();
        myThread.start();
        Thread.sleep(3000);
        myThread.setRunning(false);
        System.out.println("isRunning 的值已经设置为了 false");
        Thread.sleep(1000);
        System.out.println(myThread.isRunning);
    }
}
