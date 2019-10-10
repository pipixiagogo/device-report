package com.zh.device.numberOne;

public class Test13 {
    public static void main(String[] args) {
        ClassLoader classLoader = Test13.class.getClassLoader();
        ClassLoader parent = Test13.class.getClassLoader().getParent();
        ClassLoader parent1 = Test13.class.getClassLoader().getParent().getParent();
        System.out.println("Test13 ClassLoader is:"+classLoader);
        System.out.println("Test13 ClassLoader parent is:"+parent);
        System.out.println("Test13 ClassLoader Grandparent is:"+parent1);
    }
}
