package com.zh.device.prepareFuture.collectionTest;

public class HashMapTest {
    public static void main(String[] args) {
//        int hash = (24 - 1) & hash("皮皮虾");
//        System.out.println(hash);
        int i = 1 ^ (1 >>> 16);
        System.out.println(i);
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
