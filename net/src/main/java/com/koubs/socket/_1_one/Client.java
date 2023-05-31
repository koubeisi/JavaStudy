package com.koubs.socket._1_one;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @author KouBeisi
 * @since 2021/8/21
 */
public class Client {

    public static void main(String[] args) {

        System.out.println("===========客户端启动===========");

        try (// 1.创建 Socket 对象请求服务端链接
             final Socket socket = new Socket("127.0.0.1", 6666)
        ) {
            // 2.从 Socket 对象中获取一个字节输出流
            final OutputStream outputStream = socket.getOutputStream();
            // 3.把字节输出流包装成一个打印流
            final PrintStream printStream = new PrintStream(outputStream);
            printStream.println("Hello World!");
            printStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("===========客户端结束===========");
        }

    }
}
