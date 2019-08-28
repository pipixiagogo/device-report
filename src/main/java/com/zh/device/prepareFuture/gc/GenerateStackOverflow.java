package com.zh.device.prepareFuture.gc;

public class GenerateStackOverflow {
    private final String value;

    static final GenerateStackOverflow E1 = new GenerateStackOverflow("value1");
    //final GenerateStackOverflow E2 = new GenerateStackOverflow("value2");
    final Test2 test2=new Test2(); //final类执行比static修饰的类早 

    public GenerateStackOverflow(String value) {
        System.out.println("GenerateStackOverflow.GenerateStackOverflow()");
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static void main(String[] args) {
//        GenerateStackOverflow.class.getName();
//        GenerateStackOverflow e1 = GenerateStackOverflow.E1;
    }
}
