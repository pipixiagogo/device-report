package com.zh.device.prepareFuture.myrunner.Executorss;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException{
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
        for(int i=0;i<3;i++){
//            Thread.sleep(1000);
            WorkerThread thread = new WorkerThread("do heavy processing");
            //创建并执行在给定延迟后启用的单次操作。 
            service.scheduleAtFixedRate(thread,0,10, TimeUnit.SECONDS);
        }

        //添加一些延迟让调度程序产生一些线程
//        Thread.sleep(30000);
        System.out.println("Current Time = "+new Date());
        //关闭线程池
        service.shutdown();
        while(!service.isTerminated()){
            //等待所有任务完成
        }
        System.out.println("Finished all threads");
    }
}
