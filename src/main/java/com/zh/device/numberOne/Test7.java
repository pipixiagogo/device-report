package com.zh.device.numberOne;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test7 {
    public static void main(String[] args) throws IOException {
        //创建一个RandomAccessFile(随机访问文件对象)
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\test.txt","rw");
        //通过RandomAccessFile对象的getChannel()方法。
        FileChannel channel = randomAccessFile.getChannel();
        //创建一个读数据缓存区对象
        ByteBuffer buffer1 = ByteBuffer.allocate(48);
        //从通道中读取数据
        int read = channel.read(buffer1);
        //创建一个写数据缓冲区对象
        ByteBuffer buffer2 = ByteBuffer.allocate(48);
        //写入数据
        buffer2.put(" nio channel".getBytes());
        buffer2.flip();
        channel.write(buffer1);
        while (read!=-1){
            System.out.println("Read"+read);
            //在写模式下调用flip()，Buffer从写模式转变为读模式
            buffer1.flip();
            //如果还有未读内容
            while (buffer1.hasRemaining()){
                System.out.println((char)buffer1.get());
            }
            //清空缓存区
            buffer1.clear();
            read =channel.read(buffer1);
        }
        //关闭RandomAccessFile(随机访问文件)对象
        randomAccessFile.close();
    }

}
