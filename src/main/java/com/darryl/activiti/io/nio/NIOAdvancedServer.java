package com.darryl.activiti.io.nio;

import com.google.common.collect.Lists;
import org.jboss.netty.channel.ServerChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @Auther: Darryl
 * @Description: NIO 高级server
 * @Date: created in 2020/3/2 20:27
 */

public class NIOAdvancedServer {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        List<SocketChannel> list = Lists.newArrayList();
        //Selector
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8080));
            System.out.println("服务器端已经监听8080端口");
            // 设置接收客户端请求为非阻塞
            serverSocketChannel.configureBlocking(false);
            while (true) {
                SocketChannel accept = serverSocketChannel.accept();
                if (accept == null) {
                    System.out.println("服务器端等待客户端连接。。。");
                    Thread.sleep(5000);
                } else {
                    System.out.println("服务器端等待数据中。。。");
                    // 已经连接的客户端添加到循环列表中
                    list.add(accept);
                }
                for (SocketChannel socketChannel : list) {

                    // 设置接收客户端数据为非阻塞
                    socketChannel.configureBlocking(false);
                    int readFlag = socketChannel.read(byteBuffer);
                    if (readFlag > 0) {
                        byteBuffer.flip();
                        String msg = Charset.forName("UTF-8").decode(byteBuffer).toString();
                        System.out.println(Thread.currentThread().getName() +
                                " 服务器端接收的数据为：" + msg);
                        byteBuffer.clear();
                    } else {
                        System.out.println(Thread.currentThread().getName() + " 服务器端未接收到数据");
                    }

                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
