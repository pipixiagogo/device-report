package com.zh.device.numberOne;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class WebClient {
    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1",9000));

            ByteBuffer write = ByteBuffer.allocate(32);
            ByteBuffer read = ByteBuffer.allocate(32);

            write.put("hello".getBytes());
            write.flip();

            while (true){
                write.rewind();
                socketChannel.write(write);
                read.flip();
                socketChannel.read(read);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
