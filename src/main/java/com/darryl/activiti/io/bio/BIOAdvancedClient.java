package com.darryl.activiti.io.bio;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Auther: Darryl
 * @Description: 高级的客户端
 * @Date: created in 2020/3/2 19:23
 */

public class BIOAdvancedClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",8080);
            System.out.println("客户端发起了连接，请输入需要发送的消息：");
            String msg = null;
            Scanner sc = new Scanner(System.in);
            msg = sc.next();
            socket.getOutputStream().write(msg.getBytes());
            System.out.println("客户端发送消息完成");
            socket.close();
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
