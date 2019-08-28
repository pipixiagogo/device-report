package com.zh.device.SSLClient;

public class TestT<T> {

   private T num;

    public T getNum() {
        return num;
    }

    public void setNum(T num) {
        this.num = num;
    }

    public static TestT success(){
       TestT t = new TestT();
       t.setNum(123);
       return t;
   }
}

class Test2{

    public <T>TestT<T> getTest(){
        return TestT.success();
    }

    public static void main(String[] args) {
        Test2 test2 =new Test2();
        TestT<Object> test = test2.getTest();
        System.out.println(test.getNum());
    }
}
