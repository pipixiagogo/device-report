package com.zh.device.prepareFuture.ThreadTest;

public class RunTimer implements Runnable {

    private Thread thread;
    private long time;
    private boolean isStart;

    public RunTimer() {
    }

    public RunTimer(long time) {
        this.time = time;
    }

    public void start(long time, boolean isStart){
        this.time=time;
        this.isStart=isStart;
        if(thread == null ){
            thread=new Thread(this);
        }
        thread.start();
    }
    @Override
    public void run() {
        //while (true){
            System.out.println("皮皮虾"+System.currentTimeMillis());
        long l2 = System.currentTimeMillis();
        System.out.println(l2-time);
        //isStart=false;
//            try {
//                Thread.sleep(time);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        //}

    }
}
