package com.zh.device.numberOne;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class WebServer {
    public static void main(String[] args) {
        try {
            ServerSocketChannel socketChannel = ServerSocketChannel.open();
            socketChannel.bind(new InetSocketAddress("127.0.0.1",9000));
            socketChannel.configureBlocking(false);

            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);

            ByteBuffer read = ByteBuffer.allocate(1024);
            ByteBuffer write = ByteBuffer.allocate(128);
            write.put("收到".getBytes());
            write.flip();
            while (true){
                int select = selector.select();
                if(select == 0){
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey next = iterator.next();
                    iterator.remove();
                    if(next.isAcceptable()){
                        // 创建新的连接，并且把连接注册到selector上，而且，
                        // 声明这个channel只对读操作感兴趣。
                        SocketChannel accept = socketChannel.accept();
                        accept.configureBlocking(false);
                        accept.register(selector,SelectionKey.OP_READ);
                    }else if(next.isReadable()){
                        SocketChannel channel =(SocketChannel) next.channel();
                        read.clear();
                        channel.read(read);
                        read.flip();
                        System.out.println("received"+new String(read.array()));
                        next.interestOps(SelectionKey.OP_WRITE);
                    }else if(next.isWritable()){
                        write.rewind();
                        SocketChannel channel =(SocketChannel) next.channel();
                        channel.write(write);
                        next.interestOps(SelectionKey.OP_READ);
                    }
                }
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
