package com.zh.device.Thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test6 {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,10,200, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(5));
        for(int i=0;i<15;i++){
            Task task = new Task(i);
            executor.execute(task);
            System.out.println("线程池中数目"+executor.getPoolSize()+"队列中等待执行数目"+executor.getQueue().size());
        }
    }
}
class Task extends Thread{
    private int num;
    public Task(int num){
        this.num=num;
    }
    @Override
    public void run() {
        System.out.println("正在执行task"+num);
        try {
            this.sleep(4000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(num+"执行完毕");
    }
}
