package com.zh.device.numberOne;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Test8 {
    public static void main(String[] args) {
        Path path = Paths.get("D:\\ccc.txt");
        System.out.println("文件名:"+path.getFileName());
        System.out.println("名称元素数量:"+path.getNameCount());
        System.out.println("父路径:"+path.getParent());
        System.out.println("根路径:"+path.getRoot());
        System.out.println("是否绝对路径:"+path.isAbsolute());
        //startWith()方法的参数即是字符串也可是path对象
        System.out.println("是否以给定路径D：开始"+path.startsWith("D:\\"));
        System.out.println("该路径的字符串形式:"+path.toString());
    }
}
