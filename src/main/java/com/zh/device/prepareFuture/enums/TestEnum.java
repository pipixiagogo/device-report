package com.zh.device.prepareFuture.enums;

import org.springframework.messaging.handler.annotation.SendTo;

import java.util.*;
import java.util.stream.Stream;

public class TestEnum {
    public static void main(String[] args) {
//        MyEnum val = MyEnum.getVal(6);
//        List<Integer> nums = val.getNums();
//        nums.stream().forEach((x) -> {
//            System.out.print(x);
//        });

//        String[] str={"1","2","3","4","5","6"};//将偶数移到另一个数组中
//        String[] array = Arrays.stream(str).filter(x ->
//                (Integer.valueOf(x)) % 2 == 0
//        ).toArray(String[]::new );
//        System.out.println(array.length);
//        Arrays.stream(array).forEach(x-> System.out.println(x));
        int i = 10 + (10 >> 1);
        System.out.println(i);
    }
}
