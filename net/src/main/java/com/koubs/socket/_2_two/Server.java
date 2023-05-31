package com.koubs.socket._2_two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 目标：服务端可以反复接收消息，客户端可以反复发送消息
 * @author koubs
 * @date 2021/3/10
 */
public class Server {

    public static void main(String[] args) {

        System.out.println("===========服务端启动===========");

        try (// 1.定义一个 ServerSocket 对象进行服务端端口注册
             final ServerSocket serverSocket = new ServerSocket(6666);
             // 2.监听客户端的 Socket 链接请求
             final Socket socket = serverSocket.accept()
        ) {
            // 3.从 Socket 管道中得到一个字节流输入对象
            final InputStream inStream = socket.getInputStream();
            // 4.把字节流包装成一个缓存字符输入流
            final BufferedReader bReader = new BufferedReader(new InputStreamReader(inStream));
            // 5.读取信息
            String msg;
            while ((msg = bReader.readLine()) != null) {
                System.out.println("服务端接收到：" + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("===========服务端结束===========");
        }
    }
}
