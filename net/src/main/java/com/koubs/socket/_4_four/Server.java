package com.koubs.socket._4_four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * 目标：实现服务端可以同时接收多个客户端的Socket通信需求
 * 思路：服务端每接收到一个客户端的Socket请求后都交给一个独立的线程来处理客户端的数据
 *
 * @author koubs
 * @date 2021/3/10
 */
public class Server {

    public static void main(String[] args) {

        System.out.println("===========服务端启动===========");

        try (// 1.定义一个 ServerSocket 对象进行服务端端口注册
             final ServerSocket serverSocket = new ServerSocket(6666);
        ) {
            final ServerSocketPool socketPool = new ServerSocketPool(6, 20, 120, TimeUnit.MILLISECONDS);
            while (true) {
                // 2.定义一个死循环，负责不断的客户端的 Socket 链接请求
                final Socket socket = serverSocket.accept();
                socketPool.execute(new ServerSocketHandler(socket));
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("===========服务端结束===========");
        }
    }
}
