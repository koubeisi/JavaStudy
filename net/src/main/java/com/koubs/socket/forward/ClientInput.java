package com.koubs.socket.forward;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 接收服务端发来的消息
 * @author KouBeisi
 * @since 2021/8/29
 */
public class ClientInput {

    public static void main(String[] args) {
        try(final Socket socket = new Socket("127.0.0.1",8888)){

            final InputStream inputStream = socket.getInputStream();

            byte[] buf = new byte[1024];
            int length;
            while(true){
                if ((length = inputStream.read(buf)) > -1) {
                    System.out.print(new String(buf,0,length));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
