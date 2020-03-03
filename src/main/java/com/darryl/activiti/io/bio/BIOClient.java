package com.darryl.activiti.io.bio;

import java.io.IOException;
import java.net.Socket;

/**
 * @Auther: Darryl
 * @Description: 简单的BIO客户端
 * @Date: created in 2020/3/2 19:11
 */

public class BIOClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8080);
            socket.getOutputStream().write("客户端发来的消息TTT".getBytes());
            System.out.println("客户端发送完成");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
