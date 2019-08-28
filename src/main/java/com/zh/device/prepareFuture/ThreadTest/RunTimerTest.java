package com.zh.device.prepareFuture.ThreadTest;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class RunTimerTest {
    private static final ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2 + 1);

    public static void main(String[] args) {
//        long l = System.currentTimeMillis();
//        executorService.schedule(new RunTimer(l), 2, TimeUnit.SECONDS);
        while (true){
            System.out.println(321);
            return;
        }
//      RunTimer runTimer = new RunTimer();
//      runTimer.start(2000,true);
    }
}
