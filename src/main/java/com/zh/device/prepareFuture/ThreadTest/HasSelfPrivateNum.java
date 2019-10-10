package com.zh.device.prepareFuture.ThreadTest;

public class HasSelfPrivateNum {
    private int num = 0;

    synchronized public void addI(String username) {
        try {
            if (username.equals("a")) {
                num = 100;
                System.out.println("a set over!");
                //如果去掉hread.sleep(2000)，那么运行结果就会显示为同步的效果
                Thread.sleep(2000);
            } else {
                num = 200;
                System.out.println("b set over!");
            }
            System.out.println(username + " num=" + num);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
class ThreadA extends Thread{
    private HasSelfPrivateNum num;
    public ThreadA(HasSelfPrivateNum num){
        this.num=num;
    }
    @Override
    public void run() {
        super.run();
        num.addI("a");
    }
}

class ThreadB extends Thread{
    private HasSelfPrivateNum num;
    public ThreadB(HasSelfPrivateNum num){
        this.num=num;
    }

    @Override
    public void run() {
        super.run();
        num.addI("b");
    }
}

class Test{
    public static void main(String[] args) {
        HasSelfPrivateNum num = new HasSelfPrivateNum();
        HasSelfPrivateNum num1 = new HasSelfPrivateNum();
        ThreadA threadA = new ThreadA(num);
        threadA.start();
        ThreadB threadB = new ThreadB(num1);
        threadB.start();
    }
}
