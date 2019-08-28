package com.zh.device.prepareFuture.myrunner.misReader;


public class PublicVar {
    private String name="a";
    private String password="aa";

    synchronized public void setValue(String name,String password){
        try {
            this.name=name;
            Thread.sleep(1000);
            this.password=password;
            System.out.println(Thread.currentThread().getName()+"name:"+name+"password:"+password);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    synchronized public void getValue() {
        System.out.println(Thread.currentThread().getName() + "name=" + name
                + " password=" + password);
    }
}
