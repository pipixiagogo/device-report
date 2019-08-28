package com.zh.device.prepareFuture.myrunner.Executorss;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for(int i=0;i<10;i++){
            Runnable runnable = new WorkerThread(""+i);
            service.execute(runnable);
        }
        service.shutdown();
        while (!service.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }
}
