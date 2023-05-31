package com.koubs.socket.chart.server;

import com.koubs.socket.forward.SocketHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author KouBeisi
 * @since 2021/8/29
 */
public class ServerChart {

    public static Map<String, Socket> onlineSocketS = new HashMap<>(8);

    public static void main(String[] args) {

        try(final ServerSocket serverSocket = new ServerSocket(8888)){

            while (true){
                final Socket socket = serverSocket.accept();

                new Thread(new SocketHandler(socket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
