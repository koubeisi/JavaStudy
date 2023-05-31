package com.koubs.socket.forward;

import java.io.*;
import java.net.Socket;

/**
 * @author KouBeisi
 * @since 2021/8/24
 */
public class SocketHandler implements Runnable{

    private final Socket socket;

    public SocketHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try(final InputStream inputStream = socket.getInputStream();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
            char[] buf = new char[1024];
            int length;
            while((length = reader.read(buf)) > -1){
                System.out.print(new String(buf,0,length));
                sendMsgToAllClient(new String(buf,0,length));
            }
        } catch (IOException e) {
            Server.ONLINE_SOCKET_LIST.remove(socket);
            System.out.println("当前有人下线，当前在线人数：" + Server.ONLINE_SOCKET_LIST.size());
        }

    }

    private void sendMsgToAllClient(String msg) throws IOException {
        for (Socket sk : Server.ONLINE_SOCKET_LIST) {
            final PrintStream printStream = new PrintStream(sk.getOutputStream());
            printStream.print(msg);
            printStream.flush();
        }
    }
}
