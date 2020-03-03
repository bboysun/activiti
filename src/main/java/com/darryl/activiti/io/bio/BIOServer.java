package com.darryl.activiti.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Auther: Darryl
 * @Description: 简单的BIO server端
 * @Date: created in 2020/3/2 19:00
 */

public class BIOServer {


    public static void main(String[] args) {
        byte[] buffer = new byte[1024];
        try {
            // 服务端需要监听一个端口
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("服务器启动，并监听8080端口。。。");

            while (true) {
                System.out.println();
                System.out.println("服务器等待客户端连接。。。");
                // 服务端在accept之前都在阻塞状态中
                Socket accept = serverSocket.accept();
                System.out.println("服务器已连接成功");
                System.out.println();
                System.out.println("服务器等待数据接收。。。");
                // 服务端在接收数据之前也都在阻塞状态中
                accept.getInputStream().read(buffer);
                System.out.println("服务器已经接收数据，数据为：" + new String(buffer));
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
