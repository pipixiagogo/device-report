package com.zh.device.prepareFuture.collectionTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class RemoveList {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        list.add("张三");
//        list.add("李四");
//        list.add("王五");
//        list.add("赵六");
//        int i=0;
//        String needRemoveName="赵六";
//        for(String name:list){
//            if(needRemoveName.equals(list.get(i))){
//                break;
//            }
//            i++;
//        }
//        list.remove(i);
//        for(String name:list){
//            System.out.println(name);
//        }
        testAsList2();
    }

    private static void testAsList(){
        int [] array={1,2,3};
        //底层还是数组
        List<int[]> ints = Arrays.asList(array);
//        System.out.println(list.get(0));
//        System.out.println(list.size());
//        System.out.println(list.get(1));
        ints.forEach((x)->{
            System.out.println(x[0]+","+x[1]+","+x[2]);
        });
    }

    //将数组转为list集合  也不算是转换 应用场景较少
    private static void testAsList1(){
        List list = new ArrayList(Arrays.asList(1,2,3));
        System.out.println(list.size());
        System.out.println(list.get(1));
        System.out.println(list.get(2));
    }

    private static void testAsList2(){
        Integer[] array={1,2,3,4};
        List<Integer> collect = Arrays.stream(array).collect(Collectors.toList());
        System.out.println(collect.size());
        System.out.println(collect.get(2));
        System.out.println(collect.get(3));
    }

    


}
