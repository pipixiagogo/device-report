package com.zh.device.numberOne;

import java.util.concurrent.atomic.AtomicInteger;

public class Test5 {
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger();
        int andSet = integer.getAndSet(3);
        //  atomic value:0，integer3
        System.out.println("atomic value:"+andSet+"，integer"+integer);
        andSet= integer.getAndIncrement();
        //andIncrement:3，integer4
        System.out.println("andIncrement:"+andSet+"，integer"+integer);
        andSet=integer.getAndAdd(8);
        //andAdd:4，integer12
        System.out.println("andAdd:"+andSet+"，integer"+integer);
        andSet=integer.incrementAndGet();//
        //incrementAndGet:13，integer13
        System.out.println("incrementAndGet:"+andSet+"，integer"+integer);
        andSet=integer.decrementAndGet();
        //decrementAndGet:12integer12
        System.out.println("decrementAndGet:"+andSet+"，integer"+integer);
    }
}
