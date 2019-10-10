package com.zh.device.Thread;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test7 {
    public static void main(String[] args) throws InterruptedException {

        //创建一个ScheduledThreadPoolExecutor对象
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        //计划在某段时间后运行
        System.out.println("Current Time = "+new Date());
        for(int i=0; i<3; i++){
            WorkerThread worker = new WorkerThread();
            //10S后执行线程操作
            scheduledThreadPool.schedule(worker, 10, TimeUnit.SECONDS);
        }

        //添加一些延迟让调度程序产生一些线程
        Thread.sleep(30000);
        System.out.println("Current Time = "+new Date());
        //关闭线程池
        scheduledThreadPool.shutdown();
        while(!scheduledThreadPool.isTerminated()){
            //等待所有任务完成
        }
        System.out.println("Finished all threads");
    }
}
class WorkerThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"start time :"+new Date());
        processCommand();
        System.out.println(Thread.currentThread().getName()+"end time :"+new Date());
    }
    private void processCommand(){
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

}
