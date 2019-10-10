package com.zh.device.prepareFuture.runner.myrunner;

public class InterruptThread extends Thread {
    
    @Override
    public void run() {
        super.run();
        for(int i=0;i<50000;i++){
            if(this.isInterrupted()){
                System.out.println("线程停止 退出");
                return;
            }
            System.out.println("i="+i);
        }
        System.out.println("看到这句话说明线程并未终止------");
    }
}
class TestInterruptThread{
    public static void main(String[] args) {
        try {
            InterruptThread thread = new InterruptThread();
            thread.start();
            Thread.sleep(100);
            thread.interrupt();
        }catch (InterruptedException e){
            System.out.println("main catch");
            e.printStackTrace();
        }

    }
}
