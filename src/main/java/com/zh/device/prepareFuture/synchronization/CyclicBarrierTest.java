package com.zh.device.prepareFuture.synchronization;

import java.util.concurrent.*;

public class CyclicBarrierTest {
    // 请求的数量
    private static final int threadCount = 10;
    // 需要同步的线程数量
    private static  CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()->{
        System.out.println("线程到达5个后，我先跑");
    });
    public static void main(String[] args) throws InterruptedException{
        CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0;i<2;i++){
            final int index=i;
            Thread.sleep(1000);
            executorService.execute(()->{
                test(index,cyclicBarrier1);
            });
        }
        Thread.sleep(100);
        cyclicBarrier1.reset();
//        for(int i=0;i<2;i++){ //人满发车 不满的话清空发生异常
            executorService.execute(()->{
                try {
                    cyclicBarrier1.await();
                    System.out.println("线程执行");
                }catch (InterruptedException e){
                    System.out.println("被reset了 抓住interruptedException");
                }catch (BrokenBarrierException e){
                    System.out.println("被reset了 抓住InterruptedException");
                }
            });
//        }
        Thread.sleep(100);
        cyclicBarrier1.reset();
        for(int i=0;i<2;i++){ //人满发车 不满的话清空发生异常
            executorService.execute(()->{
                try {
                    cyclicBarrier1.await();
                    System.out.println("线程执行");
                }catch (InterruptedException e){
                    System.out.println("被reset了 抓住interruptedException");
                }catch (BrokenBarrierException e){
                    System.out.println("被reset了 抓住InterruptedException");
                }
            });
        }
        executorService.shutdown();
    }

    private static void test(int index,CyclicBarrier cyclicBarrier1) {
        System.out.println("ThreadNum:"+index+"is ready");
        try{

            //等待60S保证子线程执行完成  当线程到达要求的数量时候 才会执行await后面的方法
            cyclicBarrier1.await(60, TimeUnit.SECONDS);
        }catch (Exception e){
            System.out.println("等待过程中reset抛出异常");
        }
        System.out.println("ThreadNum:"+index+"is finish");


    }
}
