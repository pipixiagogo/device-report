package com.zh.device.prepareFuture.atom;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtom {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        int andIncrement = atomicInteger.incrementAndGet();//返回值为旧值
        System.out.println(andIncrement);
        int i = 16 << 1;
        System.out.println(i);
    }
}
