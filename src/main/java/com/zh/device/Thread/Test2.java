package com.zh.device.Thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Test2 {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, () -> {
            System.out.println(Thread.currentThread().getName() + "你们搞完了 我来搞");
        });
        for (int i = 0; i < 4; i++) {
            new Writer(cyclicBarrier).start();
        }
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("开始重用");
        for (int i = 0; i < 4; i++) {
            new Writer(cyclicBarrier).start();
        }
    }
}

class Writer extends Thread {
    private CyclicBarrier cyclicBarrier;

    public Writer(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println(this.getName() + "正在写入数据");
        try {
            Thread.sleep(2000);//模拟写入数据
            System.out.println(this.getName() + "写完了");
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(this.getName() + "继续后面的故事");
    }
}
