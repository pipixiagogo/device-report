/*
 * Copyright (c) www.bugull.com
 */
package com.zh.device.util;

import java.util.Arrays;

/**
 * @author Frank Wen(xbwen@hotmail.com)
 */
public final class ByteUtil {
    public static byte[] getBytes(float data) {
        int intBits = Float.floatToIntBits(data);
        return getBytes(intBits);
    }
    public static byte[] getByteArray(byte value) {
        byte[] byteArr = new byte[8]; //一个字节八位
        for (int i = 7; i > 0; i--) {
            byteArr[i] = (byte) (value & 1); //获取最低位
            value = (byte) (value >> 1); //每次右移一位
        }
        return byteArr;
    }

    public static void main(String[] args) {
        byte[] byteArray = getByteArray((byte) 3);
        for(byte b:byteArray){
            System.out.print(b);
        }
    }

    public static byte[] getBytes(int data) {
        byte[] bytes = new byte[4];
        bytes[3] = (byte) (data & 0xff);
        bytes[2] = (byte) ((data & 0xff00) >> 8);
        bytes[1] = (byte) ((data & 0xff0000) >> 16);
        bytes[0] = (byte) ((data & 0xff000000) >> 24);
        return bytes;
    }

    public static byte[] float2byte(float f) {

        // 把float转换为byte[]
        int fbit = Float.floatToIntBits(f);

        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (fbit >> (24 - i * 8));
        }

        // 翻转数组
        int len = b.length;
        // 建立一个与源数组元素类型相同的数组
        byte[] dest = new byte[len];
        // 为了防止修改源数组，将源数组拷贝一份副本
        System.arraycopy(b, 0, dest, 0, len);
//        byte temp;
//        // 将顺位第i个与倒数第i个交换
//        for (int i = 0; i < len / 2; ++i) {
//            temp = dest[i];
//            dest[i] = dest[len - i - 1];
//            dest[len - i - 1] = temp;
//        }

        return dest;

    }

    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static int toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }

    /**
     * The left is Bit7, the right is Bit0
     *
     * @param b
     * @param position 7-0 from left to right
     * @param value
     * @return
     */
    public static byte setBit(byte b, int position, boolean value) {
        int op = 1 << position;
        int temp = 0;
        if (value) {
            temp = b | op;
        } else {
            op = ~op;
            temp = b & op;
        }
        return (byte) temp;
    }

    /**
     * The left is Bit7, the right is Bit0
     *
     * @param b
     * @param position 7-0 from left to right
     * @return
     */

    public static boolean getBit(byte b, int position) {
        //将字节转换为二进制文件 然后取出比较
        String s = toBinaryString(b);
        char c = s.charAt(7 - position);
        return c == '1';
    }

    /**
     * the binary string use big-endian
     *
     * @param s
     * @param position index 0 this the left
     * @return
     */
    public static boolean getBitByBinaryStr(String s, int position) {
        try {
            char c = s.charAt(position);
            return c == '1';
        } catch (StringIndexOutOfBoundsException e) {
            return Boolean.FALSE;
        }
    }

    //单个字节 --> 二进制字符串
    public static String toBinaryString(byte b) {
        String s = Integer.toBinaryString(b & 0xFF);
        int len = s.length();
        if (len < 8) {
            int offset = 8 - len;
            for (int j = 0; j < offset; j++) {
                s = "0" + s;
            }
        }
        return s;
    }

    //单个字节转为16进制的字符串
    public static String toHexString(byte b) {
        String s = Integer.toHexString(b & 0xFF);
        int len = s.length();
        if (len < 2) {
            s = "0" + s;
        }
        return s.toUpperCase();
    }

    public static boolean auth(byte[] a, byte[] b) {
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    //字节数组转为二进制字符串
    public static String toBinaryString(byte[] bytes) {
        if (isEmpty(bytes)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(toBinaryString(b));
        }
        return sb.toString();
    }

    //字符串转为二进制的单个字节
    public static byte parseBinaryString(String s) {
        int i = Integer.parseInt(s, 2);
        return (byte) i;
    }

    //字符串转为16进制的单个字节
    public static byte parseHexString(String s) {
        int i = Integer.parseInt(s, 16);
        return (byte) i;
    }


    public static byte xor(byte... bytes) {
        byte result = 0x00;
        for (byte b : bytes) {
            result ^= b;
        }
        return result;
    }

    public static byte xor(byte[] bytes, int begin, int end) {
        byte result = 0x00;
        for (int i = begin; i < end; i++) {
            result ^= bytes[i];
        }
        return result;
    }

    public static byte sum(byte... bytes) {
        byte result = 0x00;
        for (byte b : bytes) {
            result += b;
        }
        return result;
    }

    public static byte sum(byte[] bytes, int begin, int end) {
        byte result = 0x00;
        for (int i = begin; i < end; i++) {
            result += bytes[i];
        }
        return result;
    }

    public static byte[] fromInt(int x) {
        byte[] result = new byte[4];
        result[0] = (byte) ((x >> 24) & 0xFF);
        result[1] = (byte) ((x >> 16) & 0xFF);
        result[2] = (byte) ((x >> 8) & 0xFF);
        result[3] = (byte) (x & 0xFF);
        return result;
    }

    public static byte[] fromIntLE(int x) {
        byte[] result = new byte[4];
        result[3] = (byte) ((x >> 24) & 0xFF);
        result[2] = (byte) ((x >> 16) & 0xFF);
        result[1] = (byte) ((x >> 8) & 0xFF);
        result[0] = (byte) (x & 0xFF);
        return result;
    }

    //字节数组转int
    public static int toInt(byte[] bytes) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (bytes[i] & 0xFF) << shift;
        }
        return value;
    }

    //字节数组转小端序
    public static int toIntLE(byte[] bytes) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = i * 8;
            value += (bytes[i] & 0xFF) << shift;
        }
        return value;
    }

    //字节数组转无符号long
    public static long toUnsignedInt(byte[] bytes) {
        long value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += ((long) (bytes[i] & 0xFF)) << shift;
        }
        return value;
    }

    //字节数组转无符号小端序int
    public static long toUnsignedIntLE(byte[] bytes) {
        long value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = i * 8;
            value += ((long) (bytes[i] & 0xFF)) << shift;
        }
        return value;
    }

    //short转字节数组
    public static byte[] fromShort(short x) {
        byte[] result = new byte[2];
        result[0] = (byte) ((x >> 8) & 0xFF);
        result[1] = (byte) (x & 0xFF);
        return result;
    }

    //short小端序转字节数组
    public static byte[] fromShortLE(short x) {
        byte[] result = new byte[2];
        result[1] = (byte) ((x >> 8) & 0xFF);
        result[0] = (byte) (x & 0xFF);
        return result;
    }

    //字节数组转short
    public static short toShort(byte[] bytes) {
        short value = 0;
        for (int i = 0; i < 2; i++) {
            int shift = (2 - 1 - i) * 8;
            value += (bytes[i] & 0xFF) << shift;
        }
        return value;
    }

    //字节数组转小端序short
    public static short toShortLE(byte[] bytes) {
        short value = 0;
        for (int i = 0; i < 2; i++) {
            int shift = i * 8;
            value += (bytes[i] & 0xFF) << shift;
        }
        return value;
    }

    //字节数组转无符号short
    public static int toUnsignedShort(byte[] bytes) {
        // byte[] unsignedBytes =
        // {FarmConstant.VAL_00,FarmConstant.VAL_00,bytes[0],bytes[1]};
        // return toInt(unsignedBytes);
        return toShort(bytes) & 0xFFFF;
    }

    //字节数组转无符号小端序short
    public static int toUnsignedShortLE(byte[] bytes) {
        // byte[] unsignedBytes =
        // {FarmConstant.VAL_00,FarmConstant.VAL_00,bytes[0],bytes[1]};
        // return toInt(unsignedBytes);
        return toShortLE(bytes) & 0xFFFF;
    }

    //long类型转字节数组
    public static byte[] fromLong(long x) {
        byte[] result = new byte[8];
        result[0] = (byte) ((x >> 56) & 0xFF);
        result[1] = (byte) ((x >> 48) & 0xFF);
        result[2] = (byte) ((x >> 40) & 0xFF);
        result[3] = (byte) ((x >> 32) & 0xFF);
        result[4] = (byte) ((x >> 24) & 0xFF);
        result[5] = (byte) ((x >> 16) & 0xFF);
        result[6] = (byte) ((x >> 8) & 0xFF);
        result[7] = (byte) (x & 0xFF);
        return result;
    }

    //long类型转小端序字节数组
    public static byte[] fromLongLE(long x) {
        byte[] result = new byte[8];
        result[7] = (byte) ((x >> 56) & 0xFF);
        result[6] = (byte) ((x >> 48) & 0xFF);
        result[5] = (byte) ((x >> 40) & 0xFF);
        result[4] = (byte) ((x >> 32) & 0xFF);
        result[3] = (byte) ((x >> 24) & 0xFF);
        result[2] = (byte) ((x >> 16) & 0xFF);
        result[1] = (byte) ((x >> 8) & 0xFF);
        result[0] = (byte) (x & 0xFF);
        return result;
    }

    //字节数组转为long类型
    public static long toLong(byte[] bytes) {
        long value = 0;
        for (int i = 0; i < 8; i++) {
            int shift = (8 - 1 - i) * 8;
            value += ((long) (bytes[i] & 0xFF)) << shift;
        }
        return value;
    }

    public static long toLongLE(byte[] bytes) {
        long value = 0;
        for (int i = 0; i < 8; i++) {
            int shift = i * 8;
            value += ((long) (bytes[i] & 0xFF)) << shift;
        }
        return value;
    }

    public static byte[] fromFloat(float x) {
        int i = Float.floatToIntBits(x);
        return fromInt(i);
    }

    public static float toFloat(byte[] bytes) {
        int i = toInt(bytes);
        return Float.intBitsToFloat(i);
    }

    public static byte[] fromDouble(double x) {
        long l = Double.doubleToRawLongBits(x);
        return fromLong(l);
    }

    public static double toDouble(byte[] bytes) {
        long l = toLong(bytes);
        return Double.longBitsToDouble(l);
    }

    public static String toHexString(byte[] bytes) {
        if (isEmpty(bytes)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(toHexString(b));
        }
        return sb.toString();
    }

    public static byte[] parseHexStringToArray(String s) {
        if (s == null || "".equals(s.trim())) {
            return null;
        }
        int len = s.length();
        if (len % 2 != 0) {
            return null;
        }
        int size = len / 2;
        byte[] data = new byte[size];
        for (int i = 0; i < size; i++) {
            String sub = s.substring(i * 2, i * 2 + 2);
            data[i] = parseHexString(sub);
        }
        return data;
    }

    public static boolean isEmpty(byte[] bytes) {
        return bytes == null || bytes.length == 0;
    }


    //填充字节数组 后面补0
    public static byte[] plusToFixLen(byte[] bytes, int i, byte b) {
        if (bytes == null)
            bytes = new byte[]{};
        if (bytes.length > i)
            return bytes;
        byte[] remain = new byte[i - bytes.length];
        Arrays.fill(remain, b);
        return PlusArrayUtils.combine(bytes, remain);
    }
}

