//package com.zh.device.prepareFuture.myrunner.Executorss;
//
//import java.util.Date;
//
//public class WorkerThread implements Runnable {
//    private String message;
//
//    public WorkerThread(String message) {
//        this.message = message;
//    }
//
//    @Override
//    public void run() {
//        System.out.println(Thread.currentThread().getName()+"start time :"+new Date());
//        processCommand();
//        System.out.println(Thread.currentThread().getName()+"end time :"+new Date());
//    }
//    private void processCommand(){
//        try {
//            Thread.sleep(5000);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    public String toString() {
//        return this.message;
//    }
//}
