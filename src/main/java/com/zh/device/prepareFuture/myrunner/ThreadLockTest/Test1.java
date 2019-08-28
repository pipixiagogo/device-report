package com.zh.device.prepareFuture.myrunner.ThreadLockTest;

public class Test1 {
    private static ThreadLocalExt local = new ThreadLocalExt();
    public static void main(String[] args) {
        if(local.get()==null){
            System.out.println("从未放过值");
            local.set("我的值");
        }
        System.out.println(local.get());
        System.out.println(local.get());
    }
    static public class ThreadLocalExt extends ThreadLocal {
        @Override
        protected Object initialValue() {
            return "我是默认值 第一次get不再为null";
        }
    }
}
