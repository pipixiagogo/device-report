package com.zh.device.prepareFuture.collectionTest;

import java.util.HashSet;
import java.util.Set;

public class SetMain {
    public static void main(String[] args) {
        //无须 set
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(3);
        set.add(10);
        set.add(20);
        set.add(6);
        set.add(8);
        set.add(23);
        set.add(40);
        set.forEach(x->
                System.out.println(x));
    }
}
