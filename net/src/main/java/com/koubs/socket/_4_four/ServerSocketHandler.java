package com.koubs.socket._4_four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author KouBeisi
 * @since 2021/8/22
 */
public class ServerSocketHandler implements Runnable{

    private final Socket socket;

    public ServerSocketHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try(final InputStream inStream = socket.getInputStream()) {

            // 4.把字节流包装成一个缓存字符输入流
            final BufferedReader bReader = new BufferedReader( new InputStreamReader(inStream));
            // 5.读取信息
            String msg;
            while ((msg = bReader.readLine()) != null) {
                System.out.println("服务端接收到：" + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
