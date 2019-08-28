package com.zh.device.prepareFuture.myrunner;

import java.util.Arrays;
import java.util.LinkedList;

public class CopyMain {
    public static void main(String[] args) {
        //arraycopy
//        int [] num=new int[10];
//        num[0]=1;
//        num[1]=11;
//        num[2]=8;
//        num[3]=6;
//        num[4]=9;
//        System.arraycopy(num,3,num,7,3);
//        for(int i:num){
//            System.out.print(i+",");
//        }
        //array.copyof
        int [] members=new int[4];
        members[0]=1;
        members[1]=10;
        members[2]=100;
        members[3]=1000;
        int[] copyOf = Arrays.copyOf(members, 10);
        System.out.println(copyOf.length);
        for(int copy:copyOf){
            System.out.print(copy+"\t");
        }
        System.out.println("---------------------------------");
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(2);
        Integer integer = linkedList.get(2);
        System.out.println(integer);
    }
}
