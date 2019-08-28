package com.zh.device.prepareFuture.SimpleIOC;

public class SimpleIOCTest {
    public static void main(String[] args) throws Exception{
        String location = SimpleIOC.class.getClassLoader().getResource("ioc.xml").getFile();
        SimpleIOC bf = new SimpleIOC(location);
        Wheel wheel = (Wheel) bf.getBean("wheel");
        System.out.println(wheel);
        Car car = (Car) bf.getBean("car");
        System.out.println(car);
    }

}
