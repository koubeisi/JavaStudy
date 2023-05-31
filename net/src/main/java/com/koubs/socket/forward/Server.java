package com.koubs.socket.forward;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 目标：BIO模式下端口转发思想（群聊的实现基础）
 * <p>
 * 1.注册端口
 * 2.接收客户端的 Socket 连接，交给一个独立的线程来实现
 * 3.把当前连接的客户端 Socket 存入到一个所谓的在线 Socket 集合中保存
 * 4.接收客户端的消息，然后推送给当前所有在线的 Socket 接收
 * </p>
 * @author KouBeisi
 * @since 2021/8/24
 */
public class Server {

    protected static final List<Socket> ONLINE_SOCKET_LIST = new ArrayList<>(12);

    public static void main(String[] args) {
        try (final ServerSocket serverSocket = new ServerSocket(8888)) {

            while (true) {
                Socket socket = serverSocket.accept();
                ONLINE_SOCKET_LIST.add(socket);
                System.out.println("有人上线，当前在线人数：" + ONLINE_SOCKET_LIST.size());
                new Thread(new SocketHandler(socket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
