package com.zh.device.prepareFuture.synchronization;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountdownTest {
    private static int frequency = 550;
    public static void main(String[] args){
        //计数器功能 到计数到0时候 阻塞线程继续执行 倒计时

        ExecutorService executorService = Executors.newFixedThreadPool(330);
        CountDownLatch countDownLatch = new CountDownLatch(550);

        for(int i=0;i<frequency;i++){
            final int index=i;
            executorService.execute(()->{
                try {
                    test(index);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
            executorService.shutdown();
            System.out.println("finish");
        }catch (InterruptedException e){
            e.printStackTrace();
        }


    }

    private static void test(int frequency) throws InterruptedException{
        Thread.sleep(1000);// 模拟请求的耗时操作
        System.out.println("threadnum:" + frequency);
        Thread.sleep(1000);// 模拟请求的耗时操作
    }
}
