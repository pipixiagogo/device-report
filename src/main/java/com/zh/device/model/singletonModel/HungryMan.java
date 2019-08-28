package com.zh.device.model.singletonModel;


public class HungryMan {
    //在静态初始化器中创建单例实例，这段代码保证了线程安全
    private static HungryMan hungryMan = new HungryMan();

    //HungryMan类只有一个构造方法并且是被private修饰的，所以用户无法通过new方法创建该对象实例
    private HungryMan(){}

    public static HungryMan getInstance(){
        return hungryMan;
    }
}
