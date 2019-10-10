package com.zh.device.prepareFuture.ThreadTest;

import java.util.concurrent.*;

public class Test8 {
    public static void main(String[] args) {
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<>(task);
//        ExecutorService   executorService = Executors.newSingleThreadExecutor();
//        executorService.submit(futureTask);
//        executorService.shutdown();
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            Integer integer = futureTask.get();
            System.out.println("获取线程运行结果"+integer);
        } catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }

    }
}
class Task implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        Thread.sleep(3000);
        int sum=0;
        for(int i=0;i<3000;i++){
           sum += i;
        }
        return sum;
    }
}
