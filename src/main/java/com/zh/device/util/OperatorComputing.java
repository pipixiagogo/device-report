package com.zh.device.util;

public class OperatorComputing {

    public static void main(String[] args) {
        testRight();
    }

    public static void testRight(){
        int a=10;
        String aBinaryString = ByteUtil.toBinaryString(ByteUtil.fromInt(a));
        System.out.println(aBinaryString);
         /*
            10:  00000000000000000000000000001010
               右移两位: 2: 010
              2*1=2
               就是除以2的移动的位数次幂。
         */
        int i = a >>2;
        System.out.println(i);


//        int b = -2;
//        String bBinaryString = ByteUtil.toBinaryString(ByteUtil.fromInt(b));
//        System.out.println(bBinaryString);
//         /*
//            -10:  11111111111111111111111111110110
//        右移两位: 11111111111111111111111111111101
//            2*1=2
//            就是除以2的移动的位数次幂。
//         */
////        BigInteger bigInteger = new BigInteger("11111111111111111111111111111101",2);
////        System.out.println(bigInteger);
//        int i2 = b>>2;
//        String i2BinaryString = ByteUtil.toBinaryString(ByteUtil.fromInt(i2));
//        System.out.println(i2BinaryString);
//        System.out.println(i2);
    }

    public static void testLeft(){
        int a=10;
        String aBinaryString = ByteUtil.toBinaryString(ByteUtil.fromInt(a));
        System.out.println(aBinaryString);
        /*
            10:  00000000000000000000000000001010
               左移两位: 101000
               1*2*5+2*3=8+32 =40
               是乘以2的移动的位数次幂
         */
        int i = a << 2;
        System.out.println(i);
    }

    public static void testAnd(){
        int a=3;
        int b=2;
        String aBinaryString = ByteUtil.toBinaryString(ByteUtil.fromInt(a));
        String bBinaryString = ByteUtil.toBinaryString(ByteUtil.fromInt(b));
        System.out.println(aBinaryString);
        System.out.println(bBinaryString);
        /*
            3:00000000000000000000000000000011 1代表真 0代表假
            2:00000000000000000000000000000010
                011
                010
                ____________ 2个为真才为真
             2: 010
         */
        int i = a & b;
        System.out.println(i);
    }

    public static void testOr(){
        int a = 6;
        int b = 2;
        String aBinaryString = ByteUtil.toBinaryString(ByteUtil.fromInt(a));
        String bBinaryString = ByteUtil.toBinaryString(ByteUtil.fromInt(b));
        System.out.println(aBinaryString);
        System.out.println(bBinaryString);
        /*  a:00000000000000000000000000000110
            b:00000000000000000000000000000010
            110
            010
            ___________ 1个为真就为真
            6:110
         */
        int i = a | b;
        System.out.println(i);
    }

    public static void testAndOr(){
        int a = 8;
        int b = 4;
        String aBinaryString = ByteUtil.toBinaryString(ByteUtil.fromInt(a));
        String bBinaryString = ByteUtil.toBinaryString(ByteUtil.fromInt(b));
        System.out.println(aBinaryString);
        System.out.println(bBinaryString);
        /* 8: 00000000000000000000000000001000
           4: 00000000000000000000000000000100
            1000
            0100
            _____ 相同为0
        12:  1100
         */
        int i = a ^ b;
        System.out.println(i);

    }
}
