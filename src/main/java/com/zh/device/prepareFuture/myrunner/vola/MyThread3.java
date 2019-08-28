package com.zh.device.prepareFuture.myrunner.vola;


public class MyThread3 extends Thread {
    private  boolean flag= false;

    @Override
    public void run() {
        int i=0;
        while (!flag){
//            System.out.println("执行了MyThread3");
            i++;
        }
        System.out.println("退出了循环"+i);
    }
    public void setFlag(){
        flag=true;
    }
}
