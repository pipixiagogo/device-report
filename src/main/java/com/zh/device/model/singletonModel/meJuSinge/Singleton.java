package com.zh.device.model.singletonModel.meJuSinge;

public enum Singleton {
    //定义一个枚举的元素，它就是 Singleton 的一个实例
    //实例调用方法
    INSTANCE;

    public void doSomeThing() {
        System.out.println("枚举方法实现单例");
    }
}
