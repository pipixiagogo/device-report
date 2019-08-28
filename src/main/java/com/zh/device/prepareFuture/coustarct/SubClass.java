package com.zh.device.prepareFuture.coustarct;

public class SubClass extends SupClass {
    static {
        System.out.println("Sub is running ...");
    }

    {
        System.out.println("sub source block is running ...");
    }

    public SubClass() {
        System.out.println("sub constructor is running...");
    }
}
