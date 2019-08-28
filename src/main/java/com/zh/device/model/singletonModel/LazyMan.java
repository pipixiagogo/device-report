package com.zh.device.model.singletonModel;


//双重检查加锁 创建单例
public class LazyMan {
    //volatile保证，当uniqueInstance变量被初始化成Singleton实例时，
    // 多个线程可以正确处理uniqueInstance变量
    private volatile static LazyMan lazyMan;

    private LazyMan() {}

    public static LazyMan getInstance() {
        //检查实例，如果不存在，就进入同步代码块
        if (lazyMan == null) {
            //只有第一次才彻底执行这里的代码
            synchronized (LazyMan.class) {
                //进入同步代码块后，再检查一次，如果仍是null，才创建实例
                if (lazyMan == null) {
                    lazyMan = new LazyMan();
                }
            }
        }
        return lazyMan;
    }
}
