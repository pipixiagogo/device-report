package com.zh.device.prepareFuture.collectionTest;

public class LinkedListTest {
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {
//        List<String> list = new LinkedList<>();
//
//        list.add("1");
//        list.add("皮皮虾");
//        list.add("2");
//        list.add("皮皮猪");
//        String remove = list.remove(3);
//        System.out.println(remove);
//        System.out.println(8>>1);

        int i = tableSizeFor(5);
        int i1 = tableSizeFor(6);
        int i2 = tableSizeFor(9);
        int i3 = tableSizeFor(10);
        System.out.println(i);
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i3);

    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
