package com.zh.device.numberOne;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test9 {
    public static void main(String[] args) throws IOException {
        Path currentPath = Paths.get(".");
        System.out.println(currentPath.toAbsolutePath());
        Path path = Paths.get(".\\device-report.iml");
        System.out.println("原始路径:"+path.toAbsolutePath());
        System.out.println("执行normalize()方法后:"+path.toAbsolutePath().normalize());
        System.out.println("执行toRealPath()方法后:"+path.toRealPath());

        Path path2 = Paths.get("..");
        System.out.println("原始路径:"+path2.toAbsolutePath());
        System.out.println("执行normalize()方法后:"+path2.toAbsolutePath().normalize());
        System.out.println("执行toRealPath()方法后:"+path2.toRealPath());
    }
}
