package com.zh.device.numberOne;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test12 {

    private static int len;
    
    public static void main(String[] args) {
        try {
            //使用内存映射的方式读取文件
            RandomAccessFile file = new RandomAccessFile("D://11.pdf","rw");
            FileChannel channel = file.getChannel();
            channel.map(FileChannel.MapMode.READ_ONLY,0,len);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            long startTime = System.currentTimeMillis();
            buffer.clear();
            // 读出所有数据
            while (channel.read(buffer) != -1) {
                buffer.flip();
                buffer.clear();
            }
            file.close();
            long endTime = System.currentTimeMillis();
            System.out.println("使用内存映射方式读取文件总耗时： "+(endTime - startTime));

            //普通IO流方式
            long startTime1 = System.currentTimeMillis();
            File file1 = new File("D://11.pdf");
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(file1));
                while ((reader.readLine()) != null) {
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                    }
                }
            }
            long endTime1 = System.currentTimeMillis();
            System.out.println("使用普通IO流方式读取文件总耗时： "+(endTime1 - startTime1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
