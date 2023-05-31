package com.koubs.socket.file;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

/**
 * 目标：可以实现接收客户端的任意类型文件，并保存到服务端磁盘
 * @author KouBeisi
 * @since 2021/8/23
 */
public class Server {
    public static void main(String[] args) {
        try (
                final ServerSocket serverSocket = new ServerSocket(8888);
        ) {
            while(true){
                final Socket socket = serverSocket.accept();

                new Thread(() -> {
                    try (final DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());){
                        final String suffix = dataInputStream.readUTF();
                        // 3.定义一个字节输出管道，把接收的到文件保存到磁盘
                        final FileOutputStream fileOutputStream = new FileOutputStream("E:\\Temp" + UUID.randomUUID() + suffix);
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = dataInputStream.read(buf)) > 0){
                            fileOutputStream.write(buf,0,len);
                        }
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        System.out.println("文件保存成功");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
