package com.zh.device.prepareFuture.myrunner;

public class Test {

    private static final Object LOCAK=new Object();
    public static void main(String[] args)throws Exception {
        Thread thread1=new Thread(new MyRunner1(),"1号");
        Thread thread2=new Thread(new MyRunner2(),"2号");
        thread1.start();
        Thread.sleep(100);
        thread2.start();
    }

    public void doit(){
        synchronized (LOCAK){
            try {
                System.out.println("小老弟来了"+Thread.currentThread().getName());
                Thread.sleep(5000);

            }catch (InterruptedException e){

            }

        }
    }
}
