package com.darryl.activiti.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Auther: Darryl
 * @Description: bio 高级的服务端
 * @Date: created in 2020/3/2 19:33
 */

public class BIOAdvancedServer {

    public static void main(String[] args) {
        byte[] buffer = new byte[1024];
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("服务器端监听8080端口");
            while (true) {
                System.out.println("服务器端等待客户端的连接。。。");
                Socket accept = serverSocket.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName()+" 服务器端已连接上客户端");
                        System.out.println("服务器端等待客户端的数据。。。");

                        try {
                            accept.getInputStream().read(buffer);
                            System.out.println(Thread.currentThread().getName() +
                                    " 服务器端接收数据： " + new String(buffer));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
