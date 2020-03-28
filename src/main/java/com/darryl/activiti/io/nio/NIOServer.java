package com.darryl.activiti.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @Auther: Darryl
 * @Description: 简单的NIO server端
 * @Date: created in 2020/3/2 19:57
 */

public class NIOServer {

    public static void main(String[] args) {
        // 接收数据用byteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8080));
            // 服务端接收客户端连接时，设置为非阻塞
            serverSocketChannel.configureBlocking(false);
            System.out.println("服务器端已经监听8080端口");
            while (true) {
                SocketChannel accept = serverSocketChannel.accept();
                if (accept == null) {
                    System.out.println("服务器端等待连接中。。。");
                    Thread.sleep(5000);
                } else {
                    System.out.println("服务器端等待数据中。。。");
                    //  设置接收客户端数据为非阻塞
                    accept.configureBlocking(false);
                    int readFlag = accept.read(byteBuffer);
                    if (readFlag != 0) {
                        byteBuffer.flip();
                        String msg = Charset.forName("UTF-8").decode(byteBuffer).toString();
                        System.out.println("服务器端接收到的数据：" + msg);
                    } else {
                        System.out.println("服务器端未接收到数据。");
                    }
                }

            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
