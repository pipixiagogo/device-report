package com.zh.device.prepareFuture.algorithm;

import java.util.Objects;

public class ValueSend {
    public static void main(String[] args) {
//        int [] arr = new int[]{1};
//        fn1(arr);
//        System.out.println("main:"+arr[0]);
//        System.out.println(arr);
        //-------------------------------------
//        int i=0;
//        fn4(i);
//        System.out.println(i);
        //-----------------------------------
//        String str="zhangsan";
//        fn3(str);
//        System.out.println(str);

        //--------------------------------------

        User user  = new User("皮皮虾",16);
        System.out.println(Objects.hashCode(user));
        fn5(user);
        System.out.println(user);
    }

    private static void  fn5(User user){
        user.setName("张三");
        System.out.println(user);
        System.out.println(Objects.hashCode(user));

        user= new User("李四",99);
        System.out.println(user);
        System.out.println(Objects.hashCode(user));
    }

    private static void fn4(int i){
        System.out.println(Objects.hashCode(i));
        i=10;
        System.out.println(Objects.hashCode(i));
        System.out.println(i);
    }
    private static void fn3(String string){
        String s = string.getClass().getName() + "@" + Objects.hashCode(string);
        System.out.println(s);
        string="pipixia";
        s = string.getClass().getName() + "@" + Objects.hashCode(string);
        System.out.println(s);
        System.out.println(string);
    }

    private static void fn1(int [] arr){
        System.out.println(arr);
        arr[0]=10;
        System.out.println(arr);
        System.out.println("fn1:"+arr[0]);
    }

    private static void fn2(int [] arr){
        System.out.println(arr);
        arr = new int[]{1};
        arr[0]=20;
        System.out.println("fn2:"+arr);
    }

}
