package com.zh.device;

public class ConcurrentTest {
    private static final int MAXIMUM_CAPACITY = 1 << 30;
    public static void main(String[] args) {
        int i = 10 + (10 >>> 1) + 1;
        System.out.println(i);
        int n = i - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        System.out.println((n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1);
    }
}
