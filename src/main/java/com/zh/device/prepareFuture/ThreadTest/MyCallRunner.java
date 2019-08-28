package com.zh.device.prepareFuture.ThreadTest;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MyCallRunner implements Callable<Integer> {

    public static void main(String[] args) throws Exception{
        MyCallRunner runner= new MyCallRunner();
        FutureTask futureTask=new FutureTask(runner);
        new Thread(futureTask).start();
        Object o = futureTask.get();
        System.out.println(o);
    }

    @Override
    public Integer call() throws Exception {
        return 1;
    }
}
