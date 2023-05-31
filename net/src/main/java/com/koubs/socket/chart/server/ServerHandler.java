package com.koubs.socket.chart.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author KouBeisi
 * @since 2021/8/29
 */
public class ServerHandler implements Runnable{

    private Socket socket;

    public ServerHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            final DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            // 1. 一直等待客户端的消息
            while (true){
                // 2.读取当前消息类型：登录，群发，私聊，@消息
                final int flag = dataInputStream.readInt();
                if (flag == 1){
                    final String name = dataInputStream.readUTF();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
