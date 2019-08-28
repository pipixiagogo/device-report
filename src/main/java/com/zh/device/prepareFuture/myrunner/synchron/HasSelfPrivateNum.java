package com.zh.device.prepareFuture.myrunner.synchron;

public class HasSelfPrivateNum {
    private int num=0;

    synchronized public void addl(String username){
        try {
            if(username.equals("a")){
                num=100;
                System.out.println("a set over!");
                //如果去掉hread.sleep(2000)，那么运行结果就会显示为同步的效果
                Thread.sleep(2000);
            }else {
                num = 200;
                System.out.println("b set over!");
            }
            System.out.println(username + " num=" + num);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
