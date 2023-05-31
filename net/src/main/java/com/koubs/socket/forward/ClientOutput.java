package com.koubs.socket.forward;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 给服务端发送消息
 * @author KouBeisi
 * @since 2021/8/29
 */
public class ClientOutput {

    public static void main(String[] args) throws IOException {


        try(final Socket socket = new Socket("127.0.0.1", 8888);
            final OutputStream outputStream = socket.getOutputStream();
            final PrintStream printStream = new PrintStream(outputStream)
        ){

            final Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("请输入：");
                final String msg = scanner.nextLine();
                if ("exit".equals(msg)) {
                    break;
                }
                printStream.println(msg);
                printStream.flush();
            }
        }
    }
}
