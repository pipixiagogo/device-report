package com.zh.device.prepareFuture.myrunner;

import java.util.ArrayList;

public class ListTest {
    public static void main(String[] args) {
        ArrayList<Integer> list=new ArrayList<>();
        int i=10000000;
        long l = System.currentTimeMillis();
        for(int j=0;j<i;j++){
            list.add(j);
        }
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l);

        list=new ArrayList<>();
        long l2 = System.currentTimeMillis();
        //添加大量元素时候使用可以先指定ensureCapacity()提高性能
        list.ensureCapacity(i);
        for(int j=0;j<i;j++){
            list.add(j);
        }
        long l3 = System.currentTimeMillis();
        System.out.println(l3-l2);
    }
}
