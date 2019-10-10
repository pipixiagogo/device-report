package com.zh.device.Thread;

import java.util.concurrent.Semaphore;

public class Test3 {
    public static void main(String[] args) {
        int num=8;
        Semaphore semaphore = new Semaphore(5);
        for(int i=1;i<=num;i++){
            GotoWC gotoWC = new GotoWC(semaphore);
            gotoWC.setName(i+"号种子");
            gotoWC.start();
            gotoWC.start();
        }
    }
}
class GotoWC extends Thread{
    private Semaphore semaphore;
    public GotoWC(Semaphore semaphore){
        this.semaphore=semaphore;
    }
    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println(this.getName()+"在用呢等下");
            Thread.sleep(3000);
            semaphore.release();
            System.out.println(this.getName()+"用完了，溜溜球");
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
