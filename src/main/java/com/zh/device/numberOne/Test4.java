package com.zh.device.numberOne;

import java.util.concurrent.atomic.AtomicInteger;

public class Test4 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1);

        new Thread(){
            @Override
            public void run() {
                int i = atomicInteger.get();
                System.out.println(this.getName()+"当前的值为:"+i);
                try {
                    this.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                boolean b = atomicInteger.compareAndSet(1, 2);
                System.out.println(this.getName()+"设置值"+atomicInteger+"设置是否成功"+b);
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                int i = atomicInteger.get();
                System.out.println(this.getName()+"当前的值为:"+i);
                boolean b = atomicInteger.compareAndSet(1, 2);
                System.out.println(this.getName()+"设置值"+atomicInteger+"设置是否成功"+b);
                try {
                    this.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                b = atomicInteger.compareAndSet(2, 1);
                System.out.println(this.getName()+"设置值"+atomicInteger+"设置是否成功"+b);
            }
        }.start();
    }
}
